package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Flight {
	
private int time;

	private Flight next;
	
	private Flight before;
	
	private Calendar data;

	private String airline;
	
	private String code;
	
	private String destination;
	
	private String boardingGate;
	
	public Flight(int time, Calendar data, String airline, String code, String destination, String boardingGate) {
		this.time =time;
		this.data = data;
		this.airline = airline;
		this.code = code;
		this.destination = destination;
		this.boardingGate = boardingGate;
		this.next = null;
		this.before = null;
		
	}
	
	public String getTime() {
		String timeF ="";
		int hour = time/60;
		int min = (int)(((double)time/60)-hour)*60;
		String format = " AM";
		if((hour-12)>0) {
			hour = hour-12;
			format = " PM";
		}
		timeF = hour<10 ? "0"+hour : hour+"";
		timeF += ":";
		timeF += min<10 ? "0"+min : min+"";
		timeF += format;
		return timeF;
	}
	
	public int getTimeN(){
		return time;
	}
	public String getData() {
		String dataF = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yy");
        dataF = sdf.format(data.getTime());
		return dataF;
	}
	
	public Flight getNext() {
		return next;
	}
	
	public void setNext(Flight next) {
		this.next = next;
	}
	
	public Flight getBefore() {
		return before;
	}
	
	public void setBefore(Flight before) {
		this.before = before;
	}
	
	public Calendar getDataF() {
		return data;
	}
	
	public String getAirline() {
		return airline;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public String getBoardingGate() {
		return boardingGate;
	}
	
}
