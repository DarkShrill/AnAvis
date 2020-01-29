package anAvis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import interfaces.Account;
import interfaces.AccountType;
import interfaces.NetworkInterface;
import interfaces.ViewInterface;

/**
 * 
 */

/**
 * @author edoardo
 *
 */
public class AvisOffice implements Account {

	/**
	 * Tipo di account
	 */
	private AccountType accountType;

	/**
	 * Tipo di account sotto forma di stringa
	 */
	private String accountToString;

	/**
	 * Email
	 */
	private String email;

	/**
	 * Password
	 */
	private String password;

	/**
	 * Sede
	 */
	private String site;

	NetworkInterface<?> network;

	ViewInterface view;

	public AvisOffice(String email, String password, String sede, NetworkInterface<?> network, ViewInterface view) {
		this.accountType = AccountType.AVIS_OFFICE;
		this.accountToString = "SEDE AVIS";
		this.email = email;
		this.password = password;
		this.site = sede;
		this.network = network;
		this.view = view;
	}

	/**
	 * Questo metodo permette di inserire delle date, con i relativi orari, in cui
	 * è possibile effettuare delle donazioni presso una specifica sede.
	 */
	public void insertAvaibleDatesAndHours() {

		boolean wantExit = false;
		String date;
		List<String> hours;
		boolean correctHours;
		List<AvaiableDateAndHours> listAvaiableDateAndHour = new ArrayList<>();

		this.view.showMessageForInsertAvaiableDatesAndHours();

		while (!wantExit) {

			wantExit = !this.view.doYouWantAddNewAvaiableDate();

			if (!wantExit) {

				date = this.view.getAvaiableDate();
				correctHours = false;

				do {
					hours = this.view.getAvaiableHours();

					correctHours = this.checkCorretHours(hours);

					if (!correctHours) {
						this.view.showMessageForInsertCorrectHour();
					}

				} while (!correctHours);

				AvaiableDateAndHours avaiableDateAndHours = new AvaiableDateAndHours(date, hours);
				listAvaiableDateAndHour.add(avaiableDateAndHours);

			}

		}

		if (!this.view.getConfirmation()) {
			// L'UTENTE HA ANNULLATO L'OPERAZIONE
			this.accountType = null;
			this.accountToString = null;
			this.email = null;
			this.password = null;
			this.site = null;
		}

		if (!this.network.sendAvaiableDateAndHours(this.site, listAvaiableDateAndHour)) {
			// SE QUALCOSA E' ANDATO STORTO
			this.view.showRepeatOperationMessage();
			this.view.goToMainView();
		}

	}

	public void modifyAvaibleDatesAndHours() {
		List<AvaiableDateAndHours> listAvaiableDateAndHour = new ArrayList<>();
		Pair<Integer, String> date = null;
		Pair<Integer, String> hour = null;
		boolean correctHours = false;

		listAvaiableDateAndHour = this.network.getListAvaiableDateAndHours(this.site);

		if (this.view.getDateOrHours().contains("DATE")) {
			// MODIFICA DELLA DATA
			date = this.view.getAvaiableDate(listAvaiableDateAndHour, true);
		} else {
			// MODIFICA DEGLI ORARI
			do {
				date = this.view.getAvaiableDate(listAvaiableDateAndHour, false);
				hour = this.view.getAvaiableHours(listAvaiableDateAndHour, date.getKey());

				List<String> tmpHourList = new LinkedList<String>();
				tmpHourList.add(hour.getValue());

				correctHours = this.checkCorretHours(tmpHourList);

				if (!correctHours) {
					this.view.showMessageForInsertCorrectHour();
				} else {
					tmpHourList.remove(0);
				}
			} while (!correctHours);
		}

		if (!this.view.getConfirmation()) {
			// L'UTENTE HA ANNULLATO L'OPERAZIONE
			this.accountType = null;
			this.accountToString = null;
			this.email = null;
			this.password = null;
			this.site = null;
		}

		if (!this.network.sendModifyAvaiableDateAndHours(this.site, listAvaiableDateAndHour, date.getKey(),
				hour == null ? -1 : hour.getKey(), date.getValue(), hour == null ? "" : hour.getValue())) {
			// SE QUALCOSA E' ANDATO STORTO
			this.view.showRepeatOperationMessage();
			this.view.goToMainView();
		}
	}

	/**
	 * Questo metodo permette di verificare se le date inserite sono corrette
	 * secondo il formato HH:MM
	 * 
	 * @param hours true -> se le date sono tutte corrette. false -> se è presente
	 *              almeno una date non corretta.
	 * 
	 * @return boolean
	 */
	private boolean checkCorretHours(List<String> hours) {

		for (String str : hours) {
			// DEVE ESSERE LUNGO 5 PERCHE COMPOSTO COSI : HH:MM
			if (str.length() != 5) {
				return false;
			}

			char hourL = str.charAt(0);
			char hourR = str.charAt(1);

			if (hourL > 2) {
				return false;
			}
			if ((hourL == 2) && (hourR > 4)) {
				return false;
			}

			char minuteL = str.charAt(3);
			char minuteR = str.charAt(4);

			if (minuteL > 6) {
				return false;
			}
			if ((minuteL == 6) && (minuteR >= 0)) {
				return false;
			}

		}

		return true;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String sede) {
		this.site = sede;
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

}
