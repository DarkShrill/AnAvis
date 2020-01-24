package anAvis;
import interfaces.Account;
import interfaces.AccountType;

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

	public AvisOffice() {
		this.accountType = AccountType.AVIS_OFFICE;
		this.accountToString = "SEDE AVIS";
		this.email = null;
		this.password = null;
		this.site = null;
	}

	public AvisOffice(String email, String password, String sede) {
		this.accountType = AccountType.AVIS_OFFICE;
		this.accountToString = "SEDE AVIS";
		this.email = email;
		this.password = password;
		this.site = sede;
	}

	public void setDateAndHourForBookingDay(int Year,int Month, int Day, int HourStart,int MinuteStart,int HourEnd,int MinuteEnd) {
		//RIVEDERE DOPO CHE MARICA CREA QUELLA CLASSE
		//FARE ANCHE IL GET
		//METTERE LA RICHIESTA ALL?INTERNO DI NETWORK INTERFACE?
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
