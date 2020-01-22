/**
 * 
 */

/**
 * @author edoardo
 *
 */
public class CreateAccount{

	/**
	 * Tipo di account
	 */
	private AccountType 	accountType;
	
	/**
	 * Tipo di account sotto forma di stringa
	 */
	private String 			account_to_string;
	
	/**
	 * Email
	 */
	private String 			email;
	
	/**
	 * Password
	 */
	private String 			password;
	
	
	/**
	 * Se l'account è di tipo 'Medico', questo è il documento
	 * 		che attesta l'attendibilità di tale utente.
	 */
	private Object 			document;
	
	/**
	 * Gruppo sanguigno
	 */
	private String 			blood_group;
	
	/**
	 * Abilitazione alle richieste di 'carenza sangue'
	 */
	private boolean 		enable_to_emergency_request;
	
	/**
	 * Sede
	 */
	private String 			site;
	
	
	/**
	 * Account Medico
	 */
	private Medico 			doctor;
	
	/**
	 * Account Donatore
	 */
	private Donatore 		donor;
	
	/**
	 * Account Pronto Soccorso
	 */
	private ProntoSoccorso 	emergency;
	
	/**
	 * Account Sede AVIS
	 */
	private SedeAvis 		avisSite;
	
	/**
	 * Interfaccia Grafica
	 */
	private ViewInterface   view;
	
	/**
	 * Network per invio richiesta creazione account
	 */
	private NetworkInterface<?> network; 
	
	
	/**
	 * Questo metodo permette di ritornare la stringa che identifica il
	 * 		tipo di account
	 * @return typeAccountString
	 */
	private String accountToString() {
		if(this.accountType == accountType.DONATORE) {
			return "DONATORE";
		}
		if(this.accountType == accountType.MEDICO) {
			return "MEDICO";
		}
		if(this.accountType == accountType.PRONTO_SOCCORSO) {
			return "PRONTO SOCCORSO";
		}
		if(this.accountType == accountType.SEDE_AVIS) {
			return "SEDE AVIS";
		}
		return "ERROR";
	}


	/**
	 * Costruttore
	 * @param view -> interfaccia grafica
	 * @parm network -> Network per invio richiesta creazione account
	 */
	public CreateAccount(ViewInterface view,NetworkInterface<?> network) {
		
		this.network = network;
		this.view = view;
		
		this.accountType 		= this.view.getAccountType();
		this.account_to_string 	= accountToString();
		
		setCredentials();
		
		if(this.accountType == accountType.MEDICO) {
			doctor = new Medico(this.email,this.password,this.view.getDocument());
		}
		else if(this.accountType == accountType.DONATORE) {
			donor = new Donatore(this.email,this.password,this.view.getBloodGroup(),this.view.getEnableToEmergencyRequest());
		}
		else if(this.accountType == accountType.PRONTO_SOCCORSO) {
			emergency = new ProntoSoccorso(this.email,this.password,this.view.getSite());
		}
		else if(this.accountType == accountType.SEDE_AVIS) {
			avisSite = new SedeAvis(this.email,this.password,this.view.getSite());
		}
		
		
		
		
		if(!this.view.getConfirmation()) {
			//L'UTENTE HA ANNULLATO L'OPERAZIONE
			this.accountType = null;
			this.account_to_string = null;
			this.email = null;
			this.password = null;
			this.document = null;
			this.blood_group = null;
			this.site = null;
			this.doctor = null;
			this.donor = null;
			this.emergency = null;
			this.avisSite = null;
		}
		
		if(!this.network.sendRequest(this.getAccount())) {
			// SE QUALCOSA E' ANDATO STORTO
			this.view.showRepeteOperationMessage();
		}

	}
		
	/**
	 * Questo metodo permette di 'settare' le credenziali (Email e Password)
	 * 		inserite dall'utente. In caso non siano corrette/valide,
	 * 		viene richiesto all'utente di inserirle nuovamente
	 */
	private void setCredentials() {
		
		while(((this.email == "")||(this.email == null)) && ((this.password == "")||(this.password == null))) {
			this.email 		= this.view.getEmail();
			this.password 	= this.view.getPassword();
			
			if(((this.email == "")||(this.email == null)) || ((this.password == "")||(this.password == null))) {
				this.view.showCredentialsErrorMessage();
			}
		}
		
	}
	
	/**
	 * Questo metodo permette di ritornare il tipo di account che è stato creato
	 * @return account
	 */
	public <T> T getAccount() {
		if(this.accountType == accountType.DONATORE) {
			return (T) this.donor;
		}
		if(this.accountType == accountType.MEDICO) {
			return (T) this.doctor;
		}
		if(this.accountType == accountType.PRONTO_SOCCORSO) {
			return (T) this.emergency;
		}
		if(this.accountType == accountType.SEDE_AVIS) {
			return (T) this.avisSite;
		}
		return null;
	}
	
	/**
	 * Questo metodo permette di ritornare la stringa che identifica il tipo
	 * 		di account
	 * @return
	 */
	public String getAccountTypeToString() {
		return this.account_to_string;
	}
	
	
	
	
	//POSSO PURE LEVARLI

//	public void setAccountType(AccountType account) {
//		this.accountType = account;
//	}
//
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}	
//	
//	public AccountType getAccountType() {
//		return this.accountType;
//	}
//
//	
//
//	
//	public String getEmail() {
//		return this.email;
//	}
//
//	
//	public String getPassword() {
//		return this.password;
//	}

}
