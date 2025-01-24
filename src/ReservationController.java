import AirCraft.AirCraft;
import AirCraft.Status;
import Reservation.FlightSeat;
import Reservation.Passenger;
import com.mycompany.flights.Flight;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MARIEM
 */
public class ReservationController implements Initializable {

    @FXML
    private TableColumn<Passenger, String> FirstNameColumn;
    @FXML
    private TableColumn<Passenger, String> SecondNameColumn;
    @FXML
    private TableColumn<Passenger, Date> DateOfBirthColumn;
    @FXML
    private TableColumn<Passenger, String> PasseportNumberColumn;
    @FXML
    private TableView<Passenger> PassengerTable;
    private final ObservableList<Passenger> PassengersTable = FXCollections.observableArrayList();
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField SecondNameField;
    @FXML
    private TextField DateOfBirthField;
    @FXML
    private TextField PasseportNumberField;
    @FXML
    private Button next;
    @FXML
    private TableColumn<Passenger,String> seatNumber;
    @FXML
    private AnchorPane id22;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize columns
        FirstNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        SecondNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSecondName()));
        DateOfBirthColumn.setCellValueFactory(data -> {
            Date birthDate = data.getValue().getBirthDate(); // Assuming getBirthDate() returns a Date object
            return new SimpleObjectProperty<>(birthDate);
        });
        
        PasseportNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPasseportNumber()));
        seatNumber.setCellValueFactory(data -> {
        FlightSeat flightSeat = data.getValue().getFlightSeat();
        return new SimpleStringProperty(flightSeat != null ? String.valueOf(flightSeat.getResevationNumber()) : "No Seat");
    });
        if (test.Passengers != null) {
            PassengersTable.addAll(test.Passengers);
        }
        PassengerTable.setItems(PassengersTable);
     
    }
    @FXML
    private void handleAddPassenger(ActionEvent event) {
        try {
            String firstName = FirstNameField.getText().trim();
            String secondName = SecondNameField.getText().trim();
            String birthDateString = DateOfBirthField.getText().trim();
            String passeportNumber = PasseportNumberField.getText().trim();

            if (firstName.isEmpty() || secondName.isEmpty() || birthDateString.isEmpty() || passeportNumber.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled.");
                return;
            }

            // Parse date
            LocalDate localDate = LocalDate.parse(birthDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Date birthDate = java.sql.Date.valueOf(localDate);

            Passenger newPassenger = new Passenger(firstName, secondName, birthDate, passeportNumber);
            PassengersTable.add(newPassenger);
            test.Passengers.add(newPassenger);

            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Passenger added successfully.");
             insertPassengerIntoDatabase(newPassenger);
        } catch (DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format. Please use yyyy-MM-dd.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while adding the passenger.");
        }
    }

    @FXML
    private void HandleEditPassenger(ActionEvent event) {
        Passenger selectedPassenger = PassengerTable.getSelectionModel().getSelectedItem();

        if (selectedPassenger != null) {
            FirstNameField.setText(selectedPassenger.getFirstName());
            SecondNameField.setText(selectedPassenger.getSecondName());
            DateOfBirthField.setText(selectedPassenger.getBirthDate().toString());
            PasseportNumberField.setText(selectedPassenger.getPasseportNumber());
        } else {
            showAlert(Alert.AlertType.ERROR, "No Selection", "Please select a passenger to edit.");
        }
    }
    public void insertPassengerIntoDatabase(Passenger p) throws SQLException {
    String sql = "INSERT INTO passenger (firstName, secondName, birthDate,passportNumber, seatNumber) " +
                 "VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {  
        pstmt.setString(1, p.getFirstName()); 
        pstmt.setString(2, p.getSecondName()); 
        pstmt.setObject(3, p.getBirthDate()); 
        pstmt.setString(4, p.getPasseportNumber()); 
        pstmt.setFloat(5, 1); 
        System.out.println("Passenger inserted successfully!");
    } catch (SQLException e) {  
        System.err.println("Error inserting flight into the database: " + e.getMessage());
        e.printStackTrace(); 
    } catch (Exception e) {
        System.err.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace();
    }
    }
    @FXML
    private void UpdatePassenger(ActionEvent event) {
        Passenger selectedPassenger = PassengerTable.getSelectionModel().getSelectedItem();

        if (selectedPassenger != null) {
            try {
                String firstName = FirstNameField.getText().trim();
                String secondName = SecondNameField.getText().trim();
                String birthDateString = DateOfBirthField.getText().trim();
                String passeportNumber = PasseportNumberField.getText().trim();

                if (firstName.isEmpty() || secondName.isEmpty() || birthDateString.isEmpty() || passeportNumber.isEmpty()) {
                    showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled.");
                    return;
                }
                LocalDate localDate = LocalDate.parse(birthDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Date birthDate = java.sql.Date.valueOf(localDate);

                selectedPassenger.setFirstName(firstName);
                selectedPassenger.setSecondName(secondName);
                selectedPassenger.setBirthDate(birthDate);
                selectedPassenger.setPasseportNumber(passeportNumber);

                PassengerTable.refresh();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Passenger updated successfully.");
            } catch (DateTimeParseException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format. Please use yyyy-MM-dd.");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while updating the passenger.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a passenger to update.");
        }
    }

    private void clearFields() {
        FirstNameField.clear();
        SecondNameField.clear();
        DateOfBirthField.clear();
        PasseportNumberField.clear();
    }
    public void refresh1(){
       PassengerTable.refresh();
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

   @FXML
    private void Next(ActionEvent event) throws IOException {
        try {
            id22.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightSeat.fxml"));
            Parent root = loader.load();
            Stage st = new Stage();
            st.setScene(new Scene(root));
            st.setTitle("Flight Seat");
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, "Error loading FlightSeat.fxml", ex);
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading the Flight Seat view.");
        }
    }




}
