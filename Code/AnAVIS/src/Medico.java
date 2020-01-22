/**
 * 
 */

/**
 * @author edoardo
 *
 */
public class Medico implements Account {

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
	 * Se l'account è di tipo 'Medico', questo è il documento
	 * 		che attesta l'attendibilità di tale utente.
	 */
	private Object document;
	
	


	public Object getDocument() {
		return document;
	}

	public void setDocument(Object document) {
		this.document = document;
	}

	public Medico() {
		this.accountType = AccountType.MEDICO;
		this.account_to_string = "MEDICO";
		this.email = null;
		this.password = null;
		this.document = null;
	}

	public Medico(String email, String password, Object document) {
		this.accountType = AccountType.MEDICO;
		this.account_to_string = "MEDICO";
		this.email = email;
		this.password = password;
		this.document = document;
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
