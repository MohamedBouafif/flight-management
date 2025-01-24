
import Person.Person;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class ViewAdminMenuController implements Initializable {

    @FXML
    private VBox id2;
    private Person person;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setPerson(Person person) {
        this.person = person;
     }
    @FXML
    private void flightManagement(ActionEvent event) {
        try{           
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightsManagement.fxml"));
        Parent root = loader.load();

        Stage st = new Stage();
        st.setScene(new Scene(root));
        st.setTitle("Flights");
        st.show();
    } catch (IOException ex) {
        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void airportManagement(ActionEvent event) {
         try{                 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AirportManagement.fxml"));
        Parent root = loader.load();
        Stage st = new Stage();
        st.setScene(new Scene(root));
        st.setTitle("Aircrafts");
        st.show();
    } catch (IOException ex) {
        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void reservationManagement(ActionEvent event) {
         try{                 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reservation.fxml"));
        Parent root = loader.load();
        Stage st = new Stage();
        st.setScene(new Scene(root));
        st.setTitle("Passengers");
        st.show();
    } catch (IOException ex) {
        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
    }

    }

    @FXML
    private void userManagement(ActionEvent event) {
        try{           
            
                Stage st = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("ViewUserManagement.fxml"));
                st.setScene(new Scene(root));
                st.setTitle("User Management");
                st.show();
    } catch (IOException ex) {
        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }



    @FXML
    private void Cklickreturn(ActionEvent event) {
        
        try {
            Stage currentStage = (Stage) id2.getScene().getWindow(); 
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(new Scene(root));
            stage.show();
    } catch (IOException e) {
        Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, e);
    } 
    }

    @FXML
     private void accountsettings(ActionEvent event) {
        try{     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountSettings.fxml"));
        Parent root = loader.load();
        AccountSettingsController controller = loader.getController();
        controller.setPerson(person);  // Set person data to the AccountSettingsController
        Stage st = new Stage();
        st.setScene(new Scene(root));
        st.setTitle("Settings");
        st.show();
        
    } catch (IOException ex) {
        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    

}
