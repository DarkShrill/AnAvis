package anAvis;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import interfaces.Account;
import interfaces.AccountType;
import interfaces.NetworkInterface;
import interfaces.ViewInterface;

/**
 * 
 */

/**
 * @author edoardo e marica
 *
 */
public class Donor implements Account {

	/**
	 * Tipo di account
	 */
	private AccountType accountType;

	/**
	 * Tipo di account sotto forma di stringa
	 */
	private String accountToString;
	/**
	 * Nome del donatore
	 */
	private String name;
	
	/**
	 * Cognome del donatore
	 */
	private String surname;

	/**
	 * Email
	 */
	private String email;

	/**
	 * Password
	 */
	private String password;

	/**
	 * Gruppo sanguigno
	 */
	private String bloodGroup;

	/**
	 * Abilitazione alle richieste di 'carenza sangue'
	 */
	private boolean enableToEmergencyRequest;
	
	/**
	 * Residenza del donatore
	 */
	private String residence;
	
	/**
	 * Sesso del donatore
	 */
	private char gender;
	
	/**
	 * Network per invio richiesta prenotazione attuale
	 */
	private NetworkInterface<?> network;
	
	/**
	 * Interfaccia Grafica
	 */
	private ViewInterface view;

	public Donor(String name, String surname, String email, String password, String bloodGroup, boolean enableToEmergencyRequest, 
			NetworkInterface<?> network, ViewInterface view, String residence, char gender) {
		this.accountType = AccountType.DONOR;
		this.accountToString = "DONOR";
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.bloodGroup = bloodGroup;
		this.enableToEmergencyRequest = enableToEmergencyRequest;
		this.network = network;
		this.view = view;
		this.residence = residence;
		this.gender = gender;
	}
	
	
	/**
	 * Metodo che consente di effettuare la prenotazione della donazione
	 * 
	 */
	public boolean reservationForBloodDonation(){
		
		boolean corretInput = false;
		int avisOffice = -1;
		
		if(!canDonate()) {
			return false;
		}
		
		Form f = new Form();
		if(f.compileForm() == null) {
			return false;
		}
		
		List<String> list = network.getAvisOffices();
		view.printList(list);
		
		do {
			avisOffice = view.getAvisOffice();
			
			if(avisOffice > list.size() - 1) {
				corretInput = false;
				view.showRepeatOperationMessage();
			}
			else {
				corretInput = true;
			}
				
		}while(!corretInput);
		
		HashMap<String, String> dates = network.getAvisOfficesAviableDates(list.get(avisOffice));
		String date = view.selectAvisOfficeDate((List<String>) dates.values());
		
		String id = "";
		for(Entry<String, String> val : dates.entrySet()) {
			if(val.getValue().equals(date)) {
				id = val.getKey();
				break;
			}
		}
		
		String hour = view.selectAvisOfficeHour(network.getAvisOfficesAviableHours(id));
		
		if (!this.view.getConfirmation()) {
			return false;
		}
		
		Reservation reservation = new Reservation(list.get(avisOffice), date, hour);
		reservation.setForm(f);
		return network.saveReservation(reservation, this);
	}
	
	
	/**
	 * Il metodo controlla se il donatore pu� effettuare una prenotazione per la donazione
	 * 
	 * @return true se il donatore pu� effettuare una donazione, false altrimenti
	 */
	@SuppressWarnings("deprecation")
	private boolean canDonate() {
		
		if(this.network.getReservation(this) != null) {
			
			return false;
		}
		
		String lastDonationDate = this.network.getLastDonationDate(this);
		
		if(lastDonationDate !=null) {
			
			if(gender == 'M' && differenceBetweenTwoDates(new Date(), new Date(lastDonationDate)) < 90) {
				return false;
			
			} else if(gender == 'F' && differenceBetweenTwoDates(new Date(), new Date(lastDonationDate)) < 180) {
				
				return false;
			}
			
		}
		
		return true;
		
	}
	
	
	/**
	 * Il metodo calcola la differenza in giorni tra la data corrente e la data dell'ultima donazione effettuata dall'utente
	 * 
	 * @param d1
	 * @param d2
	 * 
	 * @return la differenza in giorni tra la data corrente e quella dell'ultima donazione effettuata 
	 */
	private long differenceBetweenTwoDates(Date d1, Date d2) {
		
		return (d1.getTime() - d2.getTime()) / 86400000L;
		
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public boolean isEnableToEmergencyRequest() {
		return enableToEmergencyRequest;
	}

	public void setEnableToEmergencyRequest(boolean enableToEmergencyRequest) {
		this.enableToEmergencyRequest = enableToEmergencyRequest;
	}

	public void setAccountType(AccountType account) {
		this.accountType = account;
	}

	public void setAccountToString(String accountToString) {
		this.accountToString = accountToString;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setResidence(String residence) {
		this.residence = residence;
	}

	public AccountType getAccountType() {
		return this.accountType;
	}

	public String getAccountTypeToString() {
		return this.accountToString;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getResidence() {
		return this.residence;
	}
	
	public char getGender() {
		return this.gender;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	


}
