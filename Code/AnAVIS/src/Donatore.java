/**
 * 
 */

/**
 * @author edoardo
 *
 */
public class Donatore implements Account {

	/**
	 * Tipo di account
	 */
	private AccountType accountType;

	/**
	 * Tipo di account sotto forma di stringa
	 */
	private String account_to_string;

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
	private String blood_group;

	/**
	 * Abilitazione alle richieste di 'carenza sangue'
	 */
	private boolean enable_to_emergency_request;

	/**
	 * Costruttore
	 */
	public Donatore() {
		this.accountType = AccountType.DONATORE;
		this.account_to_string = "DONATORE";
		this.email = null;
		this.password = null;
		this.blood_group = null;
		this.enable_to_emergency_request = false;
	}

	/**
	 * Costruttore
	 */
	public Donatore(String email, String password, String gruppo_sanguigno, boolean enable_to_emergency_request) {
		this.accountType = AccountType.DONATORE;
		this.account_to_string = "DONATORE";
		this.email = email;
		this.password = password;
		this.blood_group = gruppo_sanguigno;
		this.enable_to_emergency_request = enable_to_emergency_request;
	}

	public String getBloodGroup() {
		return blood_group;
	}

	public void setBloodGroup(String gruppo_sanguigno) {
		this.blood_group = gruppo_sanguigno;
	}

	public boolean isEnableToEmergencyRequest() {
		return enable_to_emergency_request;
	}

	public void setEnableToEmergencyRequest(boolean enable_to_emergency_request) {
		this.enable_to_emergency_request = enable_to_emergency_request;
	}

	public void setAccountType(AccountType account) {
		this.accountType = account;
	}

	public void setAccountToString(String account_to_string) {
		this.account_to_string = account_to_string;
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
		return this.account_to_string;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

}
