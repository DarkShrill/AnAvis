package interfaces;
/**
 * 
 */

import java.util.List;

/**
 * @author edoardo
 *
 */
public interface ViewInterface {
	
	/**
	 * Questo metodo permette di ritornare il tipo di account : 
	 * 						- DONATORE
	 * 						- MEDICO
	 * 						- PRONTO SOCCORSO
	 * 						- SEDE AVIS
	 * @return tipoDiAccount
	 */
	public AccountType getAccountType();
	
	
	/**
	 * Nel caso in cui il tipo di account sia 'Medico', questo metodo permette
	 * 		di ritornare i documenti caricati dal medico per verificare la sua
	 * 		attendibilità
	 * @return documento
	 */
	public Object getDocument();
	
	
	/**
	 * Nel caso in cui il tipo di account sia 'Donatore', questo metodo permette
	 * 		di ritornare il gruppo sanguigno inserito dall'utente.
	 * @return gruppoSanguigno
	 */
	public String getBloodGroup();
	
	
	/**
	 * Nel caso in cui il tipo di account sia 'Donatore', questo metodo permette
	 * 		di ritornare se l'utente ha abilitato o meno la possibilità di essere
	 * 		chiamato in caso di 'emergenza carenza sangue'
	 * 
	 * true -> l'utente ha abilitato questa funzione.
	 * false -> l'utente non ha abilitato questa funzione.
	 * 
	 * @return enableEmergency
	 */
	public boolean getEnableToEmergencyRequest();
	
	
	/**
	 * Nel caso in cui il tipo di account sia 'SedeAvis' o 'ProntoSoccorso', questo
	 * 		metodo permette di ritornare la sede che l'utente ha stabilito.
	 * @return sedeAvis
	 */
	public String getSite();
	
	
	/**
	 * Questo metodo permette di mostrare un messaggio di richiesta di conferma da parte
	 * 		dell'utente
	 * 
	 * ritorna true -> se l'utente ha confermato.
	 * ritorna false -> se l'utente non ha confermato.
	 * 
	 * @return confirm
	 */
	public boolean getConfirmation();
	
	
	/**
	 * Questo metodo permette di ritornare l'email inserita dall'utente
	 * @return
	 */
	public String getEmail();
	
	
	/**
	 * Questo metodo permette di ritornare la password inserita dall'utente
	 * @return
	 */
	public String getPassword();
	
	/**
	 * Questo metodo permette di restituire la residenza del donatore
	 */
	public String getResidence();
	
	/**
	 * Questo metodo permette di restituire il sesso del donatore
	 */
	public char getGender();
	
	/**
	 * Questo metodo permette di mostrare una lista
	 */
	public void printList(List<?> list);
	
	/**
	 * Questo metodo permette di restituire una sede Avis selezionata
	 */
	public int getAvisOffice();
	
	/**
	 * Questo metodo permette di restituire una sede Avis selezionata
	 */
	public String selectAvisOfficeDates(List<String> list);
	
	//----------------------------------------------------------
	//------------------------- MESSAGE ------------------------
	//----------------------------------------------------------
	
	
	/**
	 * Questo metodo permette di mostrare all'utente che c'è stato un errore
	 *  	e deve ripetere la procedura da capo
	 */
	public void showRepeatOperationMessage();
	
	
	/**
	 * Questo metodo permette di mostrare all'utente che c'è stato un errore sulle
	 * 		credenziali inserite, quindi, deve ri-inserirle.
	 */
	public void showCredentialsErrorMessage();

}
