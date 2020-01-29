package anAvis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import interfaces.Account;
import interfaces.AccountType;
import interfaces.NetworkInterface;

/**
 * @author marica
 *
 */
public class Network<T extends Account> implements NetworkInterface<T> {

	private Connection connection;

	public Network() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/anavis?", "admin", "admin");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean sendRequest(T account) {
		return true;
	}

	@Override
	public boolean login(AccountType accountType, String email, String password) {
		String query = "SELECT * FROM ";

		switch (accountType) {
		case DOCTOR:
			break;
		case DONOR:
			query += "donatore ";
			break;
		case AVIS_OFFICE:
			query += "sede_avis ";
			break;
		case EMERGENCY_ROOM:
			query += "pronto_soccorso ";
			break;
		default:
			break;
		}

		query += "WHERE email = '" + email + "' AND password = '" + password + "'";

		try {

			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				return true;
			}

			return false;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}catch (NullPointerException e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public List<Donor> getEmergencyDonorList(EmergencyRoom emergencyRoom, String bloodGroup) {

		String emergencyRoomId = null;
		LinkedList<Donor> list = new LinkedList<Donor>();

		String query1 = "SELECT pronto_soccorso.ID FROM pronto_soccorso" + "WHERE pronto_soccorso.ID = '"
				+ emergencyRoom.getSite() + "'";

		try {

			PreparedStatement pstmt = connection.prepareStatement(query1);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				emergencyRoomId = result.getString("ID");
			}

			if (emergencyRoomId == null) {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

		String query2 = "SELECT nome, cognome, email, gruppo_sanguigno, disponibilita, citta, sesso FROM donatore"
				+ "JOIN distanza JOIN pronto_soccorso ON email = distanza.ID_donatore "
				+ "AND pronto_soccorso.ID = distanza.ID_pronto_soccorso WHERE donatore.disponibilita = 1"
				+ "AND donatore.gruppo_sanguigno = '" + bloodGroup + "' AND distanza.distanza <= 40";

		try {

			PreparedStatement pstmt = connection.prepareStatement(query2);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				list.add(new Donor(result.getString("nome"), result.getString("cognome"), result.getString("email"), "",
						result.getString("gruppo_sanguigno"), true, null, null, result.getString("citta"),
						result.getString("sesso").charAt(0)));
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

	}

	@Override
	public Reservation getReservation(Donor donor) {

		String query = "SELECT nome_sede, data, ora FROM sede_avis JOIN data JOIN ora JOIN donatore JOIN prenotazione "
				+ "ON prenotazione.ID_ora = ora.ID AND prenotazione.ID_utente = '" + donor.getEmail() + "' AND "
				+ "ora.ID_data = data.ID AND data.ID_sede_avis = sede_avis.ID ORDER BY prenotazione.ID DESC";

		try {

			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				if (result.getString("eseguita").equals("0")) {
					return new Reservation(result.getString("nome_sede"), result.getString("data"),
							result.getString("ora"));
				} else {
					return null;
				}
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public String getLastDonationDate(Donor donor) {

		String query = "SELECT ultima_prenotazione FROM donatore WHERE email = '" + donor.getEmail() + "'";

		try {

			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				return result.getString("ultima_prenotazione");
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public List<String> getAvisOffices() {

		LinkedList<String> list = new LinkedList<String>();
		String query = "SELECT * FROM sede_avis";

		try {

			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				list.add(result.getString("nome_sede"));
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public HashMap<String, String> getAvisOfficesAviableDates(String avis) {

		HashMap<String, String> list = new HashMap<String, String>();
		String query = "SELECT data FROM data JOIN sede_avis ON data.ID_sede_avis = sede_avis.ID "
				+ "WHERE sede_avis.nome_sede = '" + avis + "'";

		try {

			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				list.put(result.getString("ID"), result.getString("data"));
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public List<String> getAvisOfficesAviableHours(String date) {

		LinkedList<String> list = new LinkedList<String>();
		String query = "SELECT ora FROM ora JOIN data ON ora.ID_data = data.ID " + "WHERE data.ID = '" + date
				+ "' AND prenotata = 0";

		try {

			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				list.add(result.getString("data"));
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean saveReservation(Reservation res, Donor donor) {

		String hour = null;
		PreparedStatement pstmt;
		ResultSet result;

		String query1 = "SELECT ora.ID FROM sede_avis, data, ora  " + "WHERE sede_avis.nome_sede = '"
				+ res.getAvisOffice() + "' AND data.data = '" + res.getDate() + "' " + "AND ora.ora = '" + res.getHour()
				+ "'";

		try {

			pstmt = connection.prepareStatement(query1);
			result = pstmt.executeQuery();

			while (result.next()) {
				hour = result.getString("ora.ID");
			}

			if (hour == null) {

				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

		String query2 = "INSERT INTO prenotazione(ID_ora, ID_utente, eseguita) " + "VALUES( " + hour + ", '"
				+ donor.getEmail() + "', 0)";

		try {

			pstmt = connection.prepareStatement(query2);

			if (pstmt.executeUpdate() > 0) {

				return true;

			} else {

				return false;

			}

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

	}

	@Override
	public boolean sendAvaiableDateAndHours(String site, List<AvaiableDateAndHours> listAvaiableDateAndHour) {

		String avisOfficeId = null;
		String dataId = null;
		PreparedStatement pstmt;
		ResultSet result;

		String query1 = "SELECT ID FROM sede_avis WHERE nome_sede = '" + site + "'";

		try {

			pstmt = connection.prepareStatement(query1);
			result = pstmt.executeQuery();

			while (result.next()) {
				avisOfficeId = result.getString("ID");
			}

			if (avisOfficeId == null) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

		String query2 = "INSERT INTO data(data, ID_sede_avis) ";

		for (AvaiableDateAndHours item : listAvaiableDateAndHour) {

			try {
				pstmt = connection
						.prepareStatement(query2 + "VALUES( '" + item.getDate() + "'" + ", " + avisOfficeId + ")");

				if (pstmt.executeUpdate() == 0) {

					return false;
				}

				String query3 = "SELECT data.ID FROM data WHERE data = '" + item.getDate() + "'";
				pstmt = connection.prepareStatement(query3);
				result = pstmt.executeQuery();

				if (result.next()) {

					dataId = result.getString("ID");

				}

				for (String itm : item.getHours()) {

					String query4 = "INSERT INTO ora(ora, prenotata, ID_data) " + "VALUES( '" + itm + "' , 0, " + dataId
							+ ")";

					pstmt = connection.prepareStatement(query4);

					if (pstmt.executeUpdate() == 0) {

						return false;
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		}

		return true;
	}

	@Override
	public List<AvaiableDateAndHours> getListAvaiableDateAndHours(String site) {

		HashMap<String, String> date = getAvisOfficesAviableDates(site);
		LinkedList<AvaiableDateAndHours> list = new LinkedList<AvaiableDateAndHours>();

		for (Entry<String, String> val : date.entrySet()) {

			list.add(new AvaiableDateAndHours(val.getValue(), getAvisOfficesAviableHours(val.getKey())));

		}

		return list;
	}

	@Override
	public boolean sendModifyAvaiableDateAndHours(String site, List<AvaiableDateAndHours> list, int indexData,
			int indexHour, String date, String hours) {

		PreparedStatement pstmt;
		HashMap<String, String> dates = getAvisOfficesAviableDates(site);
		if (dates == null)
			return false;

		String dateId = "";

		if (date.equals("") == false) {

			for (Entry<String, String> val : dates.entrySet()) {

				if (list.get(indexData).getDate().equals(val.getValue())) {
					dateId = val.getKey();
					break;
				}

			}

			String query = "UPDATE data SET data.data = '" + date + "' WHERE ID = " + dateId;

			try {
				pstmt = connection.prepareStatement(query);
				if (pstmt.executeUpdate() == 0) {

					return false;
				}

				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			for (Entry<String, String> val : dates.entrySet()) {

				if (list.get(indexData).getDate().equals(val.getValue())) {
					dateId = val.getKey();
					break;
				}

			}

			String query = "UPDATE ora SET ora.ora = '" + hours + "' WHERE ID = " + dateId + " AND ora.ora = '"
					+ list.get(indexData).getHours().get(indexHour) + "'";

			try {
				pstmt = connection.prepareStatement(query);
				if (pstmt.executeUpdate() == 0) {

					return false;
				}

				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@SuppressWarnings({ "resource", "unchecked" })
	@Override
	public <T extends Account> T getAccountData(AccountType accountType, String email) {

		String query = null;
		PreparedStatement pstmt;
		ResultSet result;

		try {

			switch (accountType) {
			case DOCTOR:
				return null;
			case DONOR:
				query = "SELECT * FROM donatore WHERE email = '" + email + "'";

				pstmt = connection.prepareStatement(query);
				result = pstmt.executeQuery();

				while (result.next()) {
					return (T) new Donor(result.getString("nome"), result.getString("cognome"), result.getString("email"),
							"", result.getString("gruppo_sanguigno"),
							result.getString("disponibilita").equals("0") ? false : true, null, null,
							result.getString("citta"), result.getString("sesso").charAt(0));
				}

				pstmt.close();
				result.close();
				return null;
			case AVIS_OFFICE:
				query = "SELECT * FROM sede_avis WHERE email = '" + email + "'";

				pstmt = connection.prepareStatement(query);
				result = pstmt.executeQuery();

				while (result.next()) {
					return (T) new AvisOffice(result.getString("email"), "", result.getString("nome_sede"), null, null);
				}

				pstmt.close();
				result.close();
				return null;
			case EMERGENCY_ROOM:
				query = "SELECT * FROM pronto_soccorso WHERE email = '" + email + "'";

				pstmt = connection.prepareStatement(query);
				result = pstmt.executeQuery();

				while (result.next()) {
					return (T) new EmergencyRoom(result.getString("email"), "", result.getString("nome_sede"), null, null);
				}

				pstmt.close();
				result.close();
				return null;
			default:
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

		return null;
	}
}
