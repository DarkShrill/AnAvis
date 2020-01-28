package interfaces;
/**
 * 
 */

import java.util.List;

import anAvis.AvaiableDateAndHours;

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
	 * Questo metodo permette di restituire una data selezionata per la sede avis
	 */
	public String selectAvisOfficeDate(List<String> list);
	
	/**
	 * Questo metodo permette di restituire un orario per la sede avis
	 */
	public String selectAvisOfficeHour(List<String> list);

	/**
	 * 	Questo metodo chiede all'utente se vuole inserire un nuovo orario/data.
	 * @return sceltaUtente
	 */
	public boolean doYouWantAddNewAvaiableDate();

	/**
	 * Questo metodo chiede all'utente di inserire una data valida.
	 * @return date
	 */
	public String getAvaiableDate();
	
	/**
	 * Questo metodo chiede all'utente di inserire degli orari validi per una 
	 * 	specifica data
	 * @return hours
	 */
	public String getAvaiableHours();
	
	/**
	 * Questo metodo permette di ritornare nella schermata principale
	 */
	public void goToMainView();
	
	/**
	 * Questo metodo permette di ritornare la scelta fatta dall'utente
	 * @return index - scelta fatta dall'utente
	 */
	public int getSelectedAvaiableDateAndHour();

	/**
	 * Questo metodo chiede all'utente se vuole modificare la data o gli orari
	 * @return choose 
	 */
	public String getDateOrHours();
	
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

	/**
	 * Questo metodo permette di mostrare all'utente che deve inserire delle date e
	 * 	degli orari
	 */
	public void showMessageForInsertAvaiableDatesAndHours();

	/**
	 * Questo messaggio notifica all'utente che sono state inserite delle date non
	 * 	valide, quindi l'utente è pregato di rinserirle
	 */
	public void showMessageForInsertCorrectHour();

	/**
	 * Questo metodo permette di mostrare all'utente tutte le date disponibili con i 
	 * 	relativi orari
	 * @param listAvaiableDateAndHour
	 */
	public void showListAvaiableDateAndHour(List<AvaiableDateAndHours> listAvaiableDateAndHour);







}
