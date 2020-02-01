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
public class Doctor implements Account {

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
	 * Se l'account è di tipo 'DOCTOR', questo è il documento
	 * 		che attesta l'attendibilità di tale utente.
	 */
	private Object document;
	
	


	public Object getDocument() {
		return document;
	}

	public void setDocument(Object document) {
		this.document = document;
	}

	public Doctor() {
		this.accountType = AccountType.DOCTOR;
		this.accountToString = "DOCTOR";
		this.email = null;
		this.password = null;
		this.document = null;
	}

	public Doctor(String email, String password, Object document) {
		this.accountType = AccountType.DOCTOR;
		this.accountToString = "DOCTOR";
		this.email = email;
		this.password = password;
		this.document = document;
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
