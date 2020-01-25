package anAvis;

import interfaces.FormInterface;

/**
 * @author marica 
 *
 */

public class Reservation {
	
	/**
	 * Memorizza la sede AVIS in cui effettuare la prenotazione
	 */
	private String avisOffice;
	
	/**
	 * Memorizza la data della prenotazione
	 */
	private String date;
	
	/**
	 * Memorizza l'orario della prenotazione
	 */
	private String hour;
	
	
	private FormInterface form;

	public Reservation(String avisOffice, String date, String hour) {
		this.avisOffice = avisOffice;
		this.date = date;
		this.hour = hour;
	}

	public String getAvisOffice() {
		return avisOffice;
	}

	public void setAvisOffice(String avisOffice) {
		this.avisOffice = avisOffice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public void setForm(FormInterface form) {
		this.form = form;
	}
	
	public FormInterface getForm() {
		return form;
	}

}
