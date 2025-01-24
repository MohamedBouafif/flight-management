import AirCraft.AirCraft;
import AirCraft.SeatAvailability;
import AirCraft.SeatType;
import Reservation.AvailabilityException;
import Reservation.FlightSeat;
import Reservation.Passenger;
import com.mycompany.flights.Flight;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightSeatController {

    @FXML
    private ComboBox<Integer> flightComboBox;

    @FXML
    private ComboBox<String> seatTypeComboBox;

    @FXML
    private ComboBox<String> availabilityComboBox;

    @FXML
    private TextField priceField;

    @FXML
    private TextField seatNumberField;

    @FXML
    private ComboBox<String> PassengerComboBox;

    @FXML
    private TableView<FlightSeat> flightSeatTable;

    @FXML
    private TableColumn<FlightSeat, String> seatNumberColumn;

    @FXML
    private TableColumn<FlightSeat, String> seatTypeColumn;

    @FXML
    private TableColumn<FlightSeat, String> availabilityColumn;

    @FXML
    private TableColumn<FlightSeat, String> priceColumn;

    @FXML
    private TableColumn<FlightSeat, String> passengerColumn;
    

    private final ObservableList<FlightSeat> flightSeatList = FXCollections.observableArrayList();
    private final ObservableList<Integer> flightList = FXCollections.observableArrayList();
    private final ObservableList<String> passengerList = FXCollections.observableArrayList();
    @FXML
    private Button handelreturn;
    @FXML
    private VBox id55;
    public void initialize() {
        // Populate ComboBoxes with flight numbers, seat types, availability, and passengers
        for (Flight flight : test.Flights) {
            flightList.add(flight.getNumber());
        }
        flightComboBox.setItems(flightList);

        seatTypeComboBox.setItems(FXCollections.observableArrayList("ECONOMY", "BUSINESS"));
        availabilityComboBox.setItems(FXCollections.observableArrayList("Available", "Not Available"));
        for (Passenger passenger : test.Passengers) {
            passengerList.add(passenger.getPasseportNumber());
        }
        PassengerComboBox.setItems(passengerList);

        
        
        // Set TableView columns
        seatNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getSeatNumber())));
        seatTypeColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getSeatType())));
        availabilityColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getSeatAvailability())));
        priceColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getPrice())));
        priceColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getPrice())));
    
        

        // Set passenger data in the table dynamically
        passengerColumn.setCellValueFactory(data -> {
            for (Passenger p : test.Passengers) {
                if (p.getFlightSeat().equals(data.getValue())) {
                    return new SimpleStringProperty(p.getPasseportNumber());
                }
            }
            return new SimpleStringProperty("N/A");
        });

        // Add all flight seats initially
        flightSeatList.addAll(test.FlightSeats);
        flightSeatTable.setItems(flightSeatList);
    }

    @FXML
    public void addFlightSeat(ActionEvent event) throws AvailabilityException, IOException {
        // Validate flight selection
        Integer selectedFlight = flightComboBox.getValue();
        if (selectedFlight == null) {
            showAlert("Error", "Please select a flight.", Alert.AlertType.ERROR);
            return;
        }

        // Validate seat type selection
        String selectedSeatType = seatTypeComboBox.getValue();
        if (selectedSeatType == null) {
            showAlert("Error", "Please select a seat type.", Alert.AlertType.ERROR);
            return;
        }
        SeatType st = SeatType.valueOf(selectedSeatType.toUpperCase());

        // Validate availability selection
        String selectedAvailability = "Available";
        if (selectedAvailability == null) {
            showAlert("Error", "Please select seat availability.", Alert.AlertType.ERROR);
            return;
        }
        SeatAvailability sa = SeatAvailability.valueOf(selectedAvailability.toUpperCase());

        // Validate seat number
        String seatNumber = seatNumberField.getText();
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            showAlert("Error", "Please enter a seat number.", Alert.AlertType.ERROR);
            return;
        }
        int seatN = Integer.parseInt(seatNumber);

        // Validate price
        String passNb = PassengerComboBox.getValue();
        String priceText = priceField.getText();
        float price;
        try {
            price = Float.parseFloat(priceText);
            if (price < 0) {
                showAlert("Error", "Price cannot be negative.", Alert.AlertType.ERROR);
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid price.", Alert.AlertType.ERROR);
            return;
        }

        // Find the corresponding flight
        Flight flightconserne = test.Flights.stream().filter(flight -> flight.getNumber() == selectedFlight).findFirst().orElse(null);

        // Create and add the FlightSeat
        FlightSeat newSeat = new FlightSeat(seatN, st, sa, price, "1", flightconserne);

        // Find the corresponding passenger and assign the seat
        Passenger p = test.Passengers.stream().filter(passenger -> passenger.getPasseportNumber().equals(passNb)).findFirst().orElse(null);
        if (p != null) {
            p.assignFlightSeatToPassenger(newSeat);
        }

        // Add new seat to the flight seat list
        flightSeatList.add(newSeat);

        // Load the ReservationController and call refresh1()
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reservation.fxml"));
        Parent root = loader.load();  // Ensure correct path to FXML
        ReservationController controller = loader.getController();
        controller.refresh1();  // Call refresh1() to refresh the reservation table

        // Clear input fields
        seatNumberField.clear();
        priceField.clear();
        seatTypeComboBox.getSelectionModel().clearSelection();
        availabilityComboBox.getSelectionModel().clearSelection();
        flightComboBox.getSelectionModel().clearSelection();

        // Show success message
        showAlert("Success", "Flight seat added successfully.", Alert.AlertType.INFORMATION);

        // Optionally, update the global list (test.FlightSeats) with the new seat
        test.FlightSeats.add(newSeat);
    }
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void handelreturn(ActionEvent event) throws IOException {
            Stage currentStage = (Stage) id55.getScene().getWindow(); 
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reservation.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Reservation");
            stage.setScene(new Scene(root));
            stage.show();
    }
}
