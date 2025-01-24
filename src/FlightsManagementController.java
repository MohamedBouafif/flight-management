import AirCraft.AirCraft;
import AirCraft.Status;
import com.mycompany.flights.Flight;
import com.mycompany.flights.FlightStatus;
import com.mycompany.flights.Airport;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
public class FlightsManagementController {

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
    private TextField flightNumberField, departureField, arrivalField, statusField, statusFilterField,durationField,departureTimeField,flightSelectionField; 
    @FXML
    private ComboBox<String> aircraftComboBox;  
    private final ObservableList<Flight> flightList = FXCollections.observableArrayList();
    private final ObservableList<String> aircraftList = FXCollections.observableArrayList();
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/airport_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "nfcyy662";
    @FXML
    private VBox id11;
    public void initialize() {
        for (AirCraft aircraft : test.Aircraft) {
        if (aircraft.getStatus().equals(Status.WORKING)) {

            aircraftList.add(aircraft.getName());
        }
    }
        aircraftComboBox.setItems(aircraftList);
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
        flightTable.setItems(flightList);
    }
    @FXML
    public void handleAddFlight() {
        try {
            int number = Integer.parseInt(flightNumberField.getText());
            String departure = departureField.getText();
            String arrival = arrivalField.getText();
            String statusStr = statusField.getText();
            String departureTimeStr = departureTimeField.getText();
            float duration = Float.parseFloat(durationField.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime departureTime = LocalDateTime.parse(departureTimeStr, formatter);
            FlightStatus status = FlightStatus.valueOf(statusStr.toUpperCase());
            Airport departureAirport = new Airport(departure, "Location", "123");
            Airport arrivalAirport = new Airport(arrival, "Location", "321");
            Flight newFlight = new Flight(number, departureAirport, arrivalAirport, /*duration, departureTime,*/ status);
            newFlight.setDuration(duration);
            newFlight.setDeparture_time(departureTime);
            flightList.add(newFlight);
            test.Flights.add(newFlight);
            flightTable.refresh();
            clearInputFields();
             insertFlightIntoDatabase(newFlight);
    } catch (NumberFormatException e) {
        showAlert("Invalid Input", "Please ensure Flight Number and Duration are numbers.");
    } catch (IllegalArgumentException e) {
        showAlert("Invalid Status", "Invalid status provided. Use Scheduled, Delayed, In Flight, etc.");
    } catch (Exception e) {
        showAlert("Invalid Input", "Please check the departure time format: yyyy-MM-dd HH:mm.");
    }
    }
   public void insertFlightIntoDatabase(Flight flight) throws SQLException {
    String sql = "INSERT INTO flight (number, departure_airport, arrival_airport, status, duration, departure_time) " +
                 "VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {  
        pstmt.setInt(1, flight.getNumber()); 
        pstmt.setString(2, flight.getDeparture_airport().name()); 
        pstmt.setString(3, flight.getArrival_airport().name()); 
        pstmt.setString(4, flight.getStatus().toString()); 
        pstmt.setFloat(5, flight.getDuration()); 
        pstmt.setObject(6, flight.getDeparture_time()); 
        pstmt.executeUpdate();
        System.out.println("Flight inserted successfully!");
    } catch (SQLException e) {
        // Handle SQL exceptions
        System.err.println("Error inserting flight into the database: " + e.getMessage());
        e.printStackTrace(); // Print the full stack trace for debugging
    } catch (Exception e) {
        // Handle any other unexpected exceptions
        System.err.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace();
    }
}

    @FXML
public void handleAssignAircraftToFlight() {
    try {
        int flightNumber = Integer.parseInt(flightSelectionField.getText());
        String selectedAircraftName = aircraftComboBox.getSelectionModel().getSelectedItem();//selection

        if (selectedAircraftName == null || selectedAircraftName.isEmpty()) {
            showAlert("Error", "Please select an aircraft from the drop-down list");
            return;
        }
        Flight flight = flightList.stream()
                .filter(f -> f.getNumber() == flightNumber)
                .findFirst()
                .orElse(null);

        if (flight == null) {
            showAlert("Erreur", "Aucun vol trouvé avec ce numéro.");
            return;
        }  
        AirCraft selectedAircraft=test.Aircraft.stream()
                .filter(a ->a.getName().equals(selectedAircraftName))
                .findFirst()
                .orElse(null);
        /*if (selectedAircraft == null) {
            showAlert("Error", ".");
            return;
        }*/
        flight.setAirCraft(selectedAircraft);
        aircraftList.add(selectedAircraft.getName());
        flightTable.refresh();
        

        //showAlert("Success", "The aircraft has been successfully assigned to the flight.");
    } catch (NumberFormatException e) {
        showAlert("Error", "Please enter a valid flight number.");
    } catch (Exception e) {
        showAlert("Error", "An unexpected error has occurred.");
    }
}
      @FXML
    private void handelcancel(ActionEvent event) {
        
       
        Flight selectedFlight = flightTable.getSelectionModel().getSelectedItem();
        if (selectedFlight == null) {
            showAlert("Sélection", "Aucun vol sélectionné.");
            return;
        }
        selectedFlight.setStatus(FlightStatus.CANCELED);
        flightTable.refresh();   
    } 
    @FXML
    public void handleFilterByStatus() {
        String filterStatus = statusFilterField.getText().toUpperCase();
        ObservableList<Flight> filteredFlights = flightList.stream()
                .filter(flight -> flight.getStatus().toString().equalsIgnoreCase(filterStatus))
                .collect(FXCollections::observableArrayList, ObservableList::add, ObservableList::addAll);

        flightTable.setItems(filteredFlights);
    }
    private void clearInputFields() {
        flightNumberField.clear();
        departureField.clear();
        arrivalField.clear();
        statusField.clear();
        departureTimeField.clear();
        durationField.clear();
        flightSelectionField.clear();
    }
    @FXML
    public void handleResetTable() {
        flightTable.setItems(flightList);
        statusFilterField.clear();
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }  
}
