package interfaces;

import java.util.List;

import anAvis.AvaiableDateAndHours;
import anAvis.AvisOffice;
import anAvis.Donor;
import anAvis.Reservation;

/**
 * @author edoardo e marica 
 *
 */
public interface NetworkInterface <T extends Account> {

	/**
	 * Questo metodo permette di inviare la richiesta di creazione 
	 * 		dell'account all'amministratore di sistema
	 * @param account da creare
	 * 
	 * Questo metodo ritorna :
	 * 
	 * true -> se l'invio è andato a buon fine
	 * false -> se c'è stato qualche errore durante l'invio
	 * 
	 * @return esito
	 */
	public boolean sendRequest(T account);
	
	/**
	 * Questo metodo verifica la validit� delle credenziali inserite e autentica l'utente
	 * 
	 * @return true se l'utente viene autenticato, false altrimenti
	 * 
	 */
	public boolean login(AccountType accountType, String email, String password);
	
	/**
	 * Questo metodo restituisce la lista dei donatori che possono donare
	 * quando viene inviata una richiesta di emergenza sangue da parte del
	 * Pronto Soccorso
	 * 
	 * @return una lista di donatori
	 */
	public List<Donor> getEmergencyDonorList();
	
	/**
	 * Questo metodo consente di recuperare la prenotazione corrente
	 */
	public Reservation getReservation(Donor donor);
	
	/**
	 * Questo metodo consente di recuperare la data dell'ultima donazione effettuata dal donatore
	 */
	public String getLastDonationDate(Donor donor);
	 
	/**
	 * Questo metodo consente di recuperare l'elenco delle sedi AVIS
	 */
	public List<AvisOffice> getAvisOffices();
	
	/**
	 * Questo metodo consente di recuperare l'elenco delle dete disponibile per una determiata sede AVIS
	 */
	public List<String> getAvisOfficesAviableDates(AvisOffice avis);
	
	/**
	 * Questo metodo consente di salvare una prenotazione nella sede in una determinata sede AVIS
	 * @return <code>true</code> -> se tutto è andato a buon fine 
	 * <code>false</code> -> se qualcosa è andato storto
	 */
	public boolean saveReservation(Reservation res);

	/**
	 * Questo metodo consente di salvare le date ,con i relativi orari, in cui è possibile fare una prenotazione
	 * 		su una determinata sede avis
	 * @param site - sede avis
	 * @param listAvaiableDateAndHour - lista delle date/orari disponibili
	 * @return <code>true</code> -> se tutto è andato a buon fine 
	 * <code>false</code> -> se qualcosa è andato storto
	 */
	public boolean sendAvaiableDateAndHours(String site, List<AvaiableDateAndHours> listAvaiableDateAndHour);

	/**
	 * Questo metodo consente di ritornare la lista delle date, con i relativi orari, disponibili per 
	 * 	effettuare una prenotazione in una specifica sede
	 * @param site - sede avis 
	 * @return lista delle date-orari
	 */
	public List<AvaiableDateAndHours> getListAvaiableDateAndHours(String site);

	/**
	 * Questo metodo consente di salvare le modifiche fatte ad una sede avis:
	 * 		- una specifica data
 	 *      - orari di una speficia data
	 * @param site sedeAvis
	 * @param index è l'indice che rappresenta quale AvaiableDateAndHours si vuole modificare di una specifica sede
	 * @param date
	 * @param hours
	 * @return <code>true</code> -> se tutto è andato a buon fine 
	 * <code>false</code> -> se qualcosa è andato storto
	 */
	public boolean sendModifyAvaiableDateAndHours(String site, int index, String date, String hours);
	
}
