/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import AirCraft.AirCraft;
import com.mycompany.flights.Flight;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MARIEM
 */
public class FlightsCrewController implements Initializable {

   @FXML
    private TableView<Flight> flightTable;
    @FXML
    private TableColumn<Flight, Integer> numberColumn;
    @FXML
    private TableColumn<Flight, String> departureColumn;
    @FXML
    private TableColumn<Flight, String> arrivalColumn;
    @FXML
    private TableColumn<Flight, String> statusColumn;
     @FXML
    private TableColumn<Flight, String> timeColumn; 
    @FXML
    private TableColumn<Flight, String> durationColumn;
     @FXML
    private TableColumn<Flight, String> aircraftColumn;
    @FXML
    private TextField statusFilterField;
    private final ObservableList<Flight> flightList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane id16;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    numberColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getNumber()));
        departureColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDeparture_airport().name()));
        arrivalColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getArrival_airport().name()));
        statusColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getStatus().getDisplayName()));
        timeColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDeparture_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        durationColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(String.format("%.2f hours", data.getValue().getDuration())));
        aircraftColumn.setCellValueFactory(data -> {
            AirCraft aircraft = data.getValue().getAirCaft();
            return new SimpleObjectProperty<>(aircraft != null ? aircraft.getName() : "Not Assigned");
        });
    flightList.addAll(test.Flights);

    // Lier flightList au tableau
    flightTable.setItems(flightList);
        
    }    

    @FXML
    private void handleFilterByStatus(ActionEvent event) {
        String filterStatus = statusFilterField.getText().toUpperCase();
        ObservableList<Flight> filteredFlights = flightList.stream()
                .filter(flight -> flight.getStatus().toString().equalsIgnoreCase(filterStatus))
                .collect(FXCollections::observableArrayList, ObservableList::add, ObservableList::addAll);

        flightTable.setItems(filteredFlights);
    }

    @FXML
    private void handleResetTable(ActionEvent event) {
        flightTable.setItems(flightList);
        statusFilterField.clear();
    }
    
}
