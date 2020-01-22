/**
 * 
 */

/**
 * @author edoardo
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
	
}
