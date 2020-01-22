/**
 * 
 */

/**
 * @author edoardo
 *
 */
public class ProntoSoccorso implements Account {

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
	 * Password
	 */
	private String site;


	public ProntoSoccorso() {
		this.accountType = AccountType.PRONTO_SOCCORSO;
		this.account_to_string = "PRONTO SOCCORSO";
		this.email = null;
		this.password = null;
		this.site = null;
	}

	public ProntoSoccorso(String email, String password, String sede) {
		this.accountType = AccountType.PRONTO_SOCCORSO;
		this.account_to_string = "PRONTO SOCCORSO";
		this.email = email;
		this.password = password;
		this.site = sede;
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
