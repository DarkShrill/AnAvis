package anAvis;

 import java.util.List;

/**
 * @author edoardo
 *
 *	Questa classe permette di memorizzare una data disponibile per la prenotazione e memorizza gli orari disponibili per
 *		quella specifica data.
 */
public class AvaiableDateAndHours {

	/**
	 * Memorizza la data disponibile per la prenotazione
	 */
	private String date;
	
	/**
	 * Memorizza l'orario della prenotazione
	 */
	private List<String> hours;
	

	public AvaiableDateAndHours(String date, List<String> hours) {
		this.date = date;
		this.hours = hours;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getHours() {
		return hours;
	}

	public void setHours(List<String> hours) {
		this.hours = hours;
	}
	
}
