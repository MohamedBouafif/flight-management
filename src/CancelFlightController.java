import AirCraft.AirCraft;
import com.mycompany.flights.Flight;
import com.mycompany.flights.FlightStatus;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CancelFlightController implements Initializable {
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

    private final ObservableList<Flight> flightList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configuration des colonnes
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
    private void cancel(ActionEvent event) {
        Flight selectedFlight = flightTable.getSelectionModel().getSelectedItem();
        if (selectedFlight == null) {
            showAlert(Alert.AlertType.WARNING, "Selection", "No flight selected.");
            return;
        }
        selectedFlight.setStatus(FlightStatus.CANCELED);
        flightTable.refresh();
        showAlert(Alert.AlertType.INFORMATION, "", "Flight canceled.");
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
