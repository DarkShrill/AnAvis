package anAvis;
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
@SuppressWarnings("unused")
public class UnregisteredUser {

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
	 * Se l'account è di tipo 'DOCTOR', questo è il documento che attesta
	 * l'attendibilità di tale utente.
	 */
	
	private Object document;

	/**
	 * Gruppo sanguigno
	 */
	private String bloodGroup;

	/**
	 * Abilitazione alle richieste di 'carenza sangue'
	 */
	private boolean enableToEmergencyRequest;

	/**
	 * Sede
	 */
	private String site;

	/**
	 * Account DOCTOR
	 */
	private Doctor doctor;

	/**
	 * Account DONOR
	 */
	private Donor donor;

	/**
	 * Account Pronto Soccorso
	 */
	private EmergencyRoom emergency;

	/**
	 * Account Sede AVIS
	 */
	private AvisOffice avisSite;

	/**
	 * Interfaccia Grafica
	 */
	private ViewInterface view;

	/**
	 * Network per invio richiesta creazione account
	 */
	private NetworkInterface<?> network;

	/**
	 * Questo metodo permette di ritornare la stringa che identifica il tipo di
	 * account
	 * 
	 * @return typeAccountString
	 */
	private String accountToString() {
		if (this.accountType == AccountType.DONOR) {
			return "DONOR";
		}
		if (this.accountType == AccountType.DOCTOR) {
			return "DOCTOR";
		}
		if (this.accountType == AccountType.EMERGENCY_ROOM) {
			return "PRONTO SOCCORSO";
		}
		if (this.accountType == AccountType.AVIS_OFFICE) {
			return "SEDE AVIS";
		}
		return "ERROR";
	}


	/**
	 * Costruttore
	 * 
	 * @param view -> interfaccia grafica
	 * @parm network -> Network per invio richiesta creazione account
	 */
	public UnregisteredUser(ViewInterface view, NetworkInterface<?> network) {

		this.network = network;
		this.view = view;
	}
	
	public void createAccount() {
		
		this.accountType = this.view.getAccountType();
		this.accountToString = accountToString();

		setCredentials();

		if (this.accountType == AccountType.DOCTOR) {
			doctor = new Doctor(this.email, this.password, this.view.getDocument());
		} else if (this.accountType == AccountType.DONOR) {
			donor = new Donor(this.view.getName(),this.view.getSurname(),this.email, this.password, this.view.getBloodGroup(),
					this.view.getEnableToEmergencyRequest(), this.network, this.view, this.view.getResidence(), this.view.getGender());
		} else if (this.accountType == AccountType.EMERGENCY_ROOM) {
			emergency = new EmergencyRoom(this.email, this.password, this.view.getSite(), this.view, this.network);
		} else if (this.accountType == AccountType.AVIS_OFFICE) {
			avisSite = new AvisOffice(this.email, this.password, this.view.getSite(),this.network,this.view);
		}

		if (!this.view.getConfirmation()) {
			// L'UTENTE HA ANNULLATO L'OPERAZIONE
			this.accountType = null;
			this.accountToString = null;
			this.email = null;
			this.password = null;
			this.document = null;
			this.bloodGroup = null;
			this.site = null;
			this.doctor = null;
			this.donor = null;
			this.emergency = null;
			this.avisSite = null;
		}

		if (!this.network.sendRequest(this.getAccount())) {
			// SE QUALCOSA E' ANDATO STORTO
			this.view.showRepeatOperationMessage();
		}
	}
	
	/**
	 * Questo metodo permette di effettuare il login nella propria area riservata.
	 */
	public boolean loginToAccount(AccountType accountType, String email, String password) {
		
		if (!this.network.login(accountType, email, password)) {
			// SE QUALCOSA E' ANDATO STORTO
			this.view.showRepeatOperationMessage();
			return false;
		}

		return true;
		
	}

	/**
	 * Questo metodo permette di 'settare' le credenziali (Email e Password)
	 * inserite dall'utente. In caso non siano corrette/valide, viene richiesto
	 * all'utente di inserirle nuovamente
	 */
	private void setCredentials() {

		while (((this.email == "") || (this.email == null)) && ((this.password == "") || (this.password == null))) {
			this.email = this.view.getEmail();
			this.password = this.view.getPassword();

			if (((this.email == "") || (this.email == null)) || ((this.password == "") || (this.password == null))) {
				this.view.showCredentialsErrorMessage();
			}
		}

	}

	/**
	 * Questo metodo permette di ritornare il tipo di account che è stato creato
	 * 
	 * @return account
	 */
	@SuppressWarnings("unchecked")
	public <T> T getAccount() {
		if (this.accountType == AccountType.DONOR) {
			return (T) this.donor;
		}
		if (this.accountType == AccountType.DOCTOR) {
			return (T) this.doctor;
		}
		if (this.accountType == AccountType.EMERGENCY_ROOM) {
			return (T) this.emergency;
		}
		if (this.accountType == AccountType.AVIS_OFFICE) {
			return (T) this.avisSite;
		}
		return null;
	}

	/**
	 * Questo metodo permette di ritornare la stringa che identifica il tipo di
	 * account
	 * 
	 * @return
	 */
	public String getAccountTypeToString() {
		return this.accountToString;
	}


}
