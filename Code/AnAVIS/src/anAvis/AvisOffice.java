package anAvis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import interfaces.Account;
import interfaces.AccountType;
import view.Console;

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

	private Network<?> network;

	private Console view;

	public AvisOffice(String email, String password, String sede, Network<?> network, Console view) {
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
		boolean correctDate;
		List<AvaiableDateAndHours> listAvaiableDateAndHour = new ArrayList<>();

		this.view.showMessageForInsertAvaiableDatesAndHours();

		while (!wantExit) {

			wantExit = !this.view.doYouWantAddNewAvaiableDate();

			if (!wantExit) {

				do {
					date = this.view.getAvaiableDate();
					correctDate = checkCorrectDate(date);
					if(!correctDate) {
						this.view.showMessageForInsertCorrectDate();
					}
					if(!correctDate) {
						
					}
				}while(!correctDate);
				
				correctHours = false;

				do {
					hours = this.view.getAvaiableHours();

					correctHours = this.checkCorrectHours(hours);

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
		} else {
			this.view.success();
		}

	}

	public void modifyAvaibleDatesAndHours() {
		List<AvaiableDateAndHours> listAvaiableDateAndHour = new ArrayList<>();
		Pair<Integer, String> date = null;
		Pair<Integer, String> hour = null;
		boolean correctHours = false;
		boolean correctDate = false;

		listAvaiableDateAndHour = this.network.getListAvaiableDateAndHours(this.site);
		
		String choice = this.view.getDateOrHours();

		if (choice.contains("DATA")) {
			// MODIFICA DELLA DATA
			do {
				date = this.view.getAvaiableDate(listAvaiableDateAndHour, true);
				correctDate = checkCorrectDate(date.getValue());
				if(!correctDate) {
					this.view.showMessageForInsertCorrectDate();
				}
			}while(!correctDate);
			
		} else if (choice.contains("ORARIO")) {
			// MODIFICA DEGLI ORARI
			do {
				date = this.view.getAvaiableDate(listAvaiableDateAndHour, false);
				hour = this.view.getAvaiableHours(listAvaiableDateAndHour, date.getKey());

				List<String> tmpHourList = new LinkedList<String>();
				tmpHourList.add(hour.getValue());

				correctHours = this.checkCorrectHours(tmpHourList);

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
		} else {
			view.success();
		}
	}

	private boolean checkCorrectDate(String date) {
		return date.matches("^([0-2][0-9]|3[0-1])\\/(0[1-9]|1[1-2])\\/2[0-9]{3}$");
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
	private boolean checkCorrectHours(List<String> hours) {

		for (String str : hours) {
			if(str.matches("^([0|1][0-9]|2[0-3]):[0-5][0-9]$") == false) {
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
