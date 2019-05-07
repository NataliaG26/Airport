package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Airport;
import model.Flight;

public class MainController {
	
	@FXML
    private Pagination pagination;
	
	private TableView<Flight> table;
	
	private Airport airport;
	
	@FXML
    private TextField list;
	
    @FXML
    private Label wait;
    
    @FXML
    private TextField search;

	    @FXML
	    void code(ActionEvent event) {
	    	long f = System.currentTimeMillis();
	    	airport.organizeForCode();
	    	actualizaTable();
	    	f = System.currentTimeMillis()-f;
	    	wait.setText(f+" mls");
	    }

	    @FXML
	    void dataAndTime(ActionEvent event) {
	    	long f = System.currentTimeMillis();
	    	airport.organizeDataAndTime();
	    	actualizaTable();
	    	f = System.currentTimeMillis()-f;
	    	wait.setText(f+" mls");
	    }

	    @FXML
	    void destinationAZ(ActionEvent event) {
	    	long f = System.currentTimeMillis();
	    	airport.organizeDestinationAZ();
	    	actualizaTable();
	    	f = System.currentTimeMillis()-f;
	    	wait.setText(f+" mls");
	    }

	    @FXML
	    void generateList(ActionEvent event) {
	    	airport.setNumFlights(list.getText());
	    	airport.generateList();
	    	airport.organizeDataAndTime();
	    	actualizaTable();
		
	    }

	    @FXML
	    void searchCode(ActionEvent event) {
	    	long f = System.currentTimeMillis();
	    	airport.searchForCode(search.getText());
	    	actualizaTable();
	    	f = System.currentTimeMillis()-f;
	    	wait.setText(f+" mls");
	    }

	    @FXML
	    void searchDestination(ActionEvent event) {
	    	long f = System.currentTimeMillis();
	    	airport.searchForDestination(search.getText());
	    	actualizaTable();
	    	f = System.currentTimeMillis()-f;
	    	wait.setText(f+" mls");
	    }
	    
	    @FXML
	    void searchForAirline(ActionEvent event) {
	    	long f = System.currentTimeMillis();
	    	airport.searchForAirline(search.getText());
	    	actualizaTable();
	    	f = System.currentTimeMillis()-f;
	    	wait.setText(f+" mls");
	    }
	    
	    private void actualizaTable() {
	    	createTable();
			pagination.setPageFactory(this::createPage);
	    }
	    
	    public void initialize() {
			airport = new Airport();
			createTable();
		}
		
		private Node createPage(int pageIndex) {
			int fromIndex = pageIndex*10;
			int size = airport.getFlights().size();
			int toIndex = Math.min(fromIndex+10, size);
			table.setItems(FXCollections.observableArrayList(airport.getFlights().subList(fromIndex, toIndex)));
			return table;
		}
		

		private void createTable() {
			table = new TableView<Flight>();
			table.setMinWidth(700);
			
			TableColumn<Flight, String> date = new TableColumn<>("Fecha");
			date.setCellValueFactory(new PropertyValueFactory<Flight, String>("data"));
			date.setMinWidth(70);
			
			TableColumn<Flight, String> time = new TableColumn<>("Hora");
			time.setCellValueFactory(new PropertyValueFactory<Flight, String>("time"));
			time.setMinWidth(70);
			
			TableColumn<Flight, String> airline = new TableColumn<>("Aerolinea");
			airline.setCellValueFactory(new PropertyValueFactory<Flight, String>("airline"));
			airline.setMinWidth(160);
			
			TableColumn<Flight, String> code = new TableColumn<>("Vuelo");
			code.setCellValueFactory(new PropertyValueFactory<Flight, String>("code"));
			code.setMinWidth(70);
			
			TableColumn<Flight, String> destination = new TableColumn<>("Destino");
			destination.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
			destination.setMinWidth(180);
			
			TableColumn<Flight, String> gate = new TableColumn<>("P. Embarque");
			gate.setCellValueFactory(new PropertyValueFactory<Flight, String>("boardingGate"));
			gate.setMinWidth(90);
			
			table.getColumns().setAll(date, time, airline, code, destination, gate);
			
		}
}
