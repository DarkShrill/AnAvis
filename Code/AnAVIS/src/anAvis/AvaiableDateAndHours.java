package anAvis;

 import java.util.ArrayList;
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
	
	/**
	 * Memorizza gli indici degli orari che sono stati modificati
	 */
	private List<Integer> indexOfModifyHour;
	
	public List<Integer> getIndexOfModifyHour(){
		return indexOfModifyHour;
	}
	
	public void addIndexOfModifyHour(int index){
		indexOfModifyHour.add(index);
	}
	
	public void clearIndexOfModifyHour(){
		indexOfModifyHour = null;
		indexOfModifyHour = new ArrayList<>();
	}

	public AvaiableDateAndHours(String date, List<String> hours) {
		this.date = date;
		this.hours = hours;
		indexOfModifyHour = new ArrayList<>();
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
