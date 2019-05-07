package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Airport {
	
	private Flight firstFlight;
	
	private int numFlights;
	
	public final static String PATH_AIRLINES = "./data/airlines.txt";
	public final static String PATH_CITIES = "./data/cities.txt";
	public final static String PATH_GATES =  "./data/boardingGates.txt";
	
	//Constructor
	public Airport() {
		
	}
	
	
	//retorna el vuelo que corresponde al codigo, si no lo encuentra retorna null
	public void searchForCode(String code) {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Flight actual = firstFlight;
		while(actual != null) {
			if(actual.getCode().equalsIgnoreCase(code)) {
				flights.add(actual);
			}
			actual = actual.getNext();
		}
		pastList(flights);
		//return flights;
	}
	
	public void searchForDestination(String dest) {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Flight actual = firstFlight;
		while(actual != null) {
			if(actual.getDestination().equals(dest)) {
				flights.add(actual);
			}
			actual = actual.getNext();
		}
		pastList(flights);
		//return flights;
	}
	
	public void searchForAirline(String airl) {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Flight actual = firstFlight;
		while(actual != null) {
			if(actual.getAirline().equals(airl)) {
				flights.add(actual);
			}
			actual = actual.getNext();
		}
		pastList(flights);
		//return flights;
	}
	
	
	
	//seleccion
	public void organizeForCode() {
		ArrayList<Flight> list = getFlights();
		for (int i = 0; i <list.size()-1; i++) {
			Flight menor = list.get(i);
			int c = i;
			for (int j = i+1; j < list.size(); j++) {
				if(list.get(j).getCode().compareTo(menor.getCode()) <= 0) {
					menor = list.get(j);
					c = j;
				}
			}
			Flight temp = list.get(i);
			list.set(i, menor);
			list.set(c, temp);
		}
		pastList(list);
		
	}
	
	//insercion
	public void organizeDataAndTime() {
		ArrayList<Flight> list = getFlights();
		for (int i = 0; i < list.size(); i++) {
			for (int j = i; j > 0 && (list.get(j-1).getDataF().after(list.get(j).getDataF()) || list.get(j-1).getDataF().equals(list.get(j).getDataF())) ; j--) {
				Flight temp = list.get(j);
				list.set(j, list.get(j-1));
				list.set(j-1, temp);
				}
		}
		pastList(list);
	}
	
	//burbuja
	public void organizeDestinationAZ() {
		ArrayList<Flight> list = getFlights();
		for (int i = list.size(); i > 0; i--) {

			for (int j = 0; j < i-1; j++) {
				if(list.get(j).getDestination().compareTo(list.get(j+1).getDestination()) >= 0) {
					
					Flight temp = list.get(j);
					list.set(j, list.get(j+1));
					list.set(j+1, temp);
				}
			}
		}
		pastList(list);
	}
	
	public void pastList(ArrayList<Flight> list) {
		firstFlight = null;
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setBefore(null);
			list.get(i).setNext(null);
			addFlight(list.get(i));
		}
	}
	
	public void setNumFlights(String n) {
		int num = 0;
		try {
			num = Integer.parseInt(n);
		}catch(Exception e) {
			e.getMessage();
		}
		numFlights = num;
	}
	
	//agrega vuelo al final de la lista
	public void addFlight(Flight flight) {
		
		if (firstFlight == null) {
			firstFlight = flight;
		} else {
			Flight f = firstFlight;
			Flight u = f;
			while (f != null ) {
				u = f;
				
				f = f.getNext();
			}
			u.setNext(flight);
			flight.setBefore(u);
		}
	}
	
	public void generateList() {
		firstFlight = null;
		ArrayList<String> cities = loadList(PATH_CITIES);
		ArrayList<String> airlines = loadList(PATH_AIRLINES);
		ArrayList<String> gates = loadList(PATH_GATES);
		Random rmd = new Random();
		String city = "";
		String airline = "";
		String gate = "";
		Calendar day;
		//Calendar hour;
		int time = 0;
		String code = "";
		for (int i = 0; i < numFlights; i++) {
			city = cities.get(rmd.nextInt(cities.size()));
			airline = airlines.get(rmd.nextInt(airlines.size()));
			gate = gates.get(rmd.nextInt(gates.size()));
			//hour = hour.getInstance();
			//hour.set
			day = Calendar.getInstance();
		    day.set (2019, rmd.nextInt(12)+1, rmd.nextInt(30)+1);
		    time =  rmd.nextInt(24*60)+1;
		    code = airline.charAt(0)+""+(i+1);    
			addFlight(new Flight(time, day, airline, code, city, gate));
		}
	}
	
	public ArrayList<String> loadList(String path) {
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(path));
			String line = bf.readLine();
			while(line != null) {
				list.add(line);
				line = bf.readLine();
				}
				line = bf.readLine();
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<Flight> getFlights() {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Flight f = firstFlight;
		while(f != null) {
			flights.add(f);
			f = f.getNext();
		}
		return flights;
	}

}
