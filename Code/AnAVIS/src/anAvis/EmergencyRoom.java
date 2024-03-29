package anAvis;
import java.util.List;

import interfaces.Account;
import interfaces.AccountType;
import view.Console;

/**
 * 
 */

/**
 * @author edoardo e marica 
 *
 */
public class EmergencyRoom implements Account {

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
	 * nome del pronto soccorso
	 */
	private String site;
	
	/**
	 * Network per invio richiesta lista donatori
	 */
	private Network<?> network;
	
	/**
	 * Interfaccia Grafica
	 */
	private Console view;


	public EmergencyRoom(String email, String password, String site, Console view, Network<?> network) {
		this.accountType = AccountType.EMERGENCY_ROOM;
		this.accountToString = "PRONTO SOCCORSO";
		this.email = email;
		this.password = password;
		this.site = site;
		this.network = network;
		this.view = view;
	}
	
	
	/**
	 * Questo metodo invia una richiesta di carenza sangue a tutti i donatori che rispondono ai criteri richiesti,
	 * ovvero:  disponibilità, prossimità geografica con il Pronto Soccorso (raggio di 40 km), 
	 * gruppo sanguigno e ultima donazione effettuata

	 * @return true se l'operazione è andata a buon fine, false altrimenti
	 */
	public boolean bloodEmergencyRequest() {
		
		String bloodGroup;
		
		bloodGroup = this.view.getBloodGroupForEmergencyRequest();
		
		if (!this.view.getConfirmation()) {
			return false;
		}
		
		List<Donor> emergencyDonorList = this.network.getEmergencyDonorList(this, bloodGroup);
		
		if (emergencyDonorList == null) {
			// SE QUALCOSA E' ANDATO STORTO
			this.view.showNoValideCandidateFound();
			return false;
		}
	
		return sendEmergencyRequest(emergencyDonorList);
	}
	
	
	private boolean sendEmergencyRequest(List<Donor> emergencyDonorList) {	
		Donor tmp;
		for(int i=0; i<emergencyDonorList.size(); i++) {
			tmp = emergencyDonorList.get(i);
			System.out.println(tmp.getName() + " " + tmp.getSurname() + " - " + tmp.getResidence());
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
