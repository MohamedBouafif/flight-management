import AirCraft.AirCraft;
import AirCraft.Status;
import com.mycompany.flights.Flight;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
public class AirportManagementController implements Initializable {

    @FXML
    private TableView<AirCraft> AircraftTable;
    @FXML
    private TableColumn<AirCraft, String> name;
    @FXML
    private TableColumn<AirCraft, String> model;
    @FXML
    private TableColumn<AirCraft, String> dateOfFabrication;
    @FXML
    private TableColumn<AirCraft, String> status;
    @FXML
    private TableColumn<AirCraft, String> seatNumber;
    @FXML
    private TextField AircrafName;
    @FXML
    private TextField AircraftModel;
    @FXML
    private TextField AircraftDateOfFabrication;
    @FXML
    private ComboBox<String> AircraftStatus;
    private TextField deparAircraftNulberOfseats;
    private TextField aircraftSelectionField;
    private ComboBox<String> flightComboBox;
    @FXML
    private TextField statusFilterField;

    private final ObservableList<AirCraft> aircraftList = FXCollections.observableArrayList();
    
    @FXML
    private VBox id11;
    @FXML
    private TextField departureAircraftNumberOfSeats;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize columns
        name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        model.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModel()));
          dateOfFabrication.setCellValueFactory(data -> {
        Date fabricationDate = data.getValue().getDateFabrication(); 
        String formattedDate = (fabricationDate != null) 
                ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(fabricationDate) 
                : "N/A"; // Handle null dates
        return new SimpleStringProperty(formattedDate);
    });
        
        status.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus().getDisplayName()));
        seatNumber.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getSeatNumber())));

        // Populate data
        AircraftStatus.setItems(FXCollections.observableArrayList("Working", "Old","BROKEN", "REFACTORING"));
       if (test.Aircraft != null) {
        aircraftList.addAll(test.Aircraft); 
    }
    AircraftTable.setItems(aircraftList);
    }

    @FXML
private void handleAddAircraft(ActionEvent event) throws SQLException {
    try {
        String name = AircrafName.getText().trim();
        String model = AircraftModel.getText().trim();
        LocalDate localDate = LocalDate.parse(AircraftDateOfFabrication.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date dateOfFabrication = java.sql.Date.valueOf(localDate);
        String statusStr = AircraftStatus.getValue();
        int numberOfSeats = Integer.parseInt(departureAircraftNumberOfSeats.getText().trim()); 

        if (name.isEmpty() || model.isEmpty() || statusStr == null || departureAircraftNumberOfSeats.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled.");
            return;
        }

        Status status = Status.valueOf(statusStr.toUpperCase());
        AirCraft newAircraft = new AirCraft(name, model, dateOfFabrication, status);
        newAircraft.setNumberSeat(numberOfSeats);
        aircraftList.add(newAircraft);
        test.Aircraft.add(newAircraft);
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Success", "Aircraft added successfully.");
        insertAircraftIntoDatabase(newAircraft);
    } catch (IllegalArgumentException | DateTimeParseException e) {
        showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Please check your data.");
    }
}

@FXML
private void handleFilterByStatus(ActionEvent event) {
    String filterStatus = statusFilterField.getText().trim().toLowerCase();
    ObservableList<AirCraft> filteredList = aircraftList.filtered(aircraft -> 
        aircraft.getStatus().name().toLowerCase().contains(filterStatus));
    AircraftTable.setItems(filteredList);
}

@FXML
private void handleResetTable(ActionEvent event) {
    AircraftTable.setItems(aircraftList);
    statusFilterField.clear();
}

private void clearFields() {
    AircrafName.clear();
    AircraftModel.clear();
    AircraftDateOfFabrication.clear();
    AircraftStatus.setValue(null);
    departureAircraftNumberOfSeats.clear(); 
}

private void showAlert(Alert.AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

@FXML
private void handleEditAircraft(ActionEvent event) {
    AirCraft selectedAircraft = AircraftTable.getSelectionModel().getSelectedItem();

    if (selectedAircraft != null) {
        AircrafName.setText(selectedAircraft.getName());
        AircraftModel.setText(selectedAircraft.getModel());
      
        AircraftDateOfFabrication.setText(
    selectedAircraft.getDateFabrication() != null 
        ? new SimpleDateFormat("yyyy-MM-dd").format(selectedAircraft.getDateFabrication()) 
        : ""
);
        AircraftStatus.setValue(selectedAircraft.getStatus().getDisplayName());
        departureAircraftNumberOfSeats.setText(String.valueOf(selectedAircraft.getSeatNumber())); // Corrected field name
    } else {
        showAlert(Alert.AlertType.ERROR, "No Selection", "Please select an aircraft to edit.");
    }
}

     public void insertAircraftIntoDatabase(AirCraft aircraft) throws SQLException {
    String sql = "INSERT INTO aircraft (name,model, date_fabrication, status, seat_number) " +
                 "VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {  
        pstmt.setString(1, aircraft.getName()); 
        pstmt.setString(2, aircraft.getModel()); 
        pstmt.setObject(3, aircraft.getDateFabrication()); 
        pstmt.setString(4, aircraft.getStatus().toString()); 
        pstmt.setInt(5, aircraft.getSeatNumber()); 
        pstmt.executeUpdate();
        System.out.println("Aircraft inserted successfully!");
    } catch (SQLException e) {
       
        System.err.println("Error inserting flight into the database: " + e.getMessage());
        e.printStackTrace(); 
    } catch (Exception e) {
        System.err.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace();
    }
}
    @FXML
    private void handleUpdateAircraft(ActionEvent event) {
        
    AirCraft selectedAircraft = AircraftTable.getSelectionModel().getSelectedItem();

    if (selectedAircraft != null) {
        try {
           
            String name = AircrafName.getText().trim();
            String model = AircraftModel.getText().trim();
            LocalDate localDate = LocalDate.parse(AircraftDateOfFabrication.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Date dateOfFabrication = java.sql.Date.valueOf(localDate); 
            String statusStr = AircraftStatus.getValue();
            int numberOfSeats = Integer.parseInt(departureAircraftNumberOfSeats.getText().trim());

          
            if (name.isEmpty() || model.isEmpty() || statusStr == null || departureAircraftNumberOfSeats.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled.");
                return;
            }
            Status status = Status.valueOf(statusStr.toUpperCase());
            selectedAircraft.setName(name);
            selectedAircraft.setModel(model);
            selectedAircraft.setDateFabrication(dateOfFabrication);
            selectedAircraft.setStatus(status);
            selectedAircraft.setNumberSeat(numberOfSeats);

            AircraftTable.refresh();
            clearFields(); // Vider les champs
            showAlert(Alert.AlertType.INFORMATION, "Success", "Aircraft updated successfully.");
        } catch (IllegalArgumentException | DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Please check your data.");
        }
    }

    }
}