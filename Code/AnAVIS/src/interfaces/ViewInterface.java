package interfaces;
/**
 * 
 */

import java.util.List;

import anAvis.AvaiableDateAndHours;
import anAvis.Pair;

/**
 * @author edoardo
 *
 */
public interface ViewInterface {

	/**
	 * Questo metodo permette di ritornare il tipo di account : - DONATORE - MEDICO
	 * - PRONTO SOCCORSO - SEDE AVIS
	 * 
	 * @return tipoDiAccount
	 */
	public AccountType getAccountType();

	/**
	 * Nel caso in cui il tipo di account sia 'Medico', questo metodo permette di
	 * ritornare i documenti caricati dal medico per verificare la sua
	 * attendibilità
	 * 
	 * @return documento
	 */
	public Object getDocument();

	/**
	 * Nel caso in cui il tipo di account sia 'Donatore', questo metodo permette di
	 * ritornare il gruppo sanguigno inserito dall'utente.
	 * 
	 * @return gruppoSanguigno
	 */
	public String getBloodGroup();

	/**
	 * Nel caso in cui il tipo di account sia 'Donatore', questo metodo permette di
	 * ritornare se l'utente ha abilitato o meno la possibilità di essere chiamato
	 * in caso di 'emergenza carenza sangue'
	 * 
	 * true -> l'utente ha abilitato questa funzione. false -> l'utente non ha
	 * abilitato questa funzione.
	 * 
	 * @return enableEmergency
	 */
	public boolean getEnableToEmergencyRequest();

	/**
	 * Nel caso in cui il tipo di account sia 'SedeAvis' o 'ProntoSoccorso', questo
	 * metodo permette di ritornare la sede che l'utente ha stabilito.
	 * 
	 * @return sedeAvis
	 */
	public String getSite();

	/**
	 * Questo metodo permette di mostrare un messaggio di richiesta di conferma da
	 * parte dell'utente
	 * 
	 * ritorna true -> se l'utente ha confermato. ritorna false -> se l'utente non
	 * ha confermato.
	 * 
	 * @return confirm
	 */
	public boolean getConfirmation();

	/**
	 * Questo metodo permette di ritornare l'email inserita dall'utente
	 * 
	 * @return
	 */
	public String getEmail();

	/**
	 * Questo metodo permette di ritornare la password inserita dall'utente
	 * 
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
	 * Questo metodo chiede all'utente se vuole inserire un nuovo orario/data.
	 * 
	 * @return sceltaUtente
	 */
	public boolean doYouWantAddNewAvaiableDate();
	
	/**
	 * Questo metodo chiede all'utente di inserire una data valida.
	 * 
	 * @return date
	 */
	public String getAvaiableDate();
	
	/**
	 * Questo metodo chiede all'utente di inserire degli orari validi per una
	 * specifica data
	 * 
	 * @return hours
	 */
	public List<String> getAvaiableHours();

	/**
	 * Questo metodo chiede all'utente di inserire una data valida.
	 * 
	 * @return date
	 */
	public Pair<Integer, String> getAvaiableDate(List<AvaiableDateAndHours> list, boolean isDate);

	/**
	 * Questo metodo chiede all'utente di inserire degli orari validi per una
	 * specifica data
	 * 
	 * @return hours
	 */
	public Pair<Integer, String> getAvaiableHours(List<AvaiableDateAndHours> list, int dateIndex);

	/**
	 * Questo metodo permetti di modificare un orario a scelta dell'utente
	 * 
	 * @param avaiableDateAndHours
	 * @return
	 */
	public AvaiableDateAndHours getModifyHours(AvaiableDateAndHours avaiableDateAndHours);

	/**
	 * Questo metodo permette di ritornare nella schermata principale
	 */
	public void goToMainView();

	/**
	 * Questo metodo permette di ritornare la scelta fatta dall'utente
	 * 
	 * @return index - scelta fatta dall'utente
	 */
	public int getSelectedAvaiableDateAndHour();

	/**
	 * Questo metodo chiede all'utente se vuole modificare la data o gli orari
	 * 
	 * @return choose
	 */
	public String getDateOrHours();

	/**
	 * Questo metodo permette di mostrare a video un messaggio.
	 * 
	 * @param msg messaggio da mostrare a video
	 */
	public void printMessage(String msg);

	/**
	 * Questo metodo permette di mostrare il menu dell'applicaizone
	 */
	public void printMenu();

	/**
	 * Questo metodo permette di restituire la scelta fatta nel menu
	 * 
	 * @return
	 */
	public int getMenuChoice();

	/**
	 * Questo metodo mi permette di mostrare i vari sottomenu di ogni tipo di
	 * account
	 * 
	 * @param accountType
	 */
	public int showSubMenu(AccountType accountType);

	/**
	 * Sottomenu di donor con al suo interno le funzioni che puo effettuare
	 */
	public void donorMenu();

	/**
	 * Sottomenu di avisOffice con al suo interno le funzioni che puo effettuare
	 */
	public void avisOfficeMenu();

	/**
	 * Sottomenu di emergencyRoom con al suo interno le funzioni che puo effettuare
	 */
	public void emergencyRoomMenu();

	// ----------------------------------------------------------
	// ------------------------- MESSAGE ------------------------
	// ----------------------------------------------------------

	/**
	 * Questo metodo permette di mostrare all'utente che c'è stato un errore e deve
	 * ripetere la procedura da capo
	 */
	public void showRepeatOperationMessage();

	/**
	 * Questo metodo permette di mostrare all'utente che c'è stato un errore sulle
	 * credenziali inserite, quindi, deve ri-inserirle.
	 */
	public void showCredentialsErrorMessage();

	/**
	 * Questo metodo permette di mostrare all'utente che deve inserire delle date e
	 * degli orari
	 */
	public void showMessageForInsertAvaiableDatesAndHours();

	/**
	 * Questo messaggio notifica all'utente che sono state inserite delle date non
	 * valide, quindi l'utente è pregato di rinserirle
	 */
	public void showMessageForInsertCorrectDate();
	
	public void showNoValideCandidateFound();
	
	/**
	 * Questo messaggio notifica all'utente che sono stati inseriti degli orari non
	 * validi, quindi l'utente è pregato di rinserirle
	 */
	public void showMessageForInsertCorrectHour();

	/**
	 * Questo metodo permette di inserire il nome dell'utente
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Questo metodo permetti di inserire il cognome dell'utente
	 * 
	 * @return
	 */
	public String getSurname();

}
