/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class PiloteMenuViewController implements Initializable {

    @FXML
    private VBox id4;
    private Person person;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void checkModify(ActionEvent event) {
         try{                 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CancelFlight.fxml"));
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
    private void checkreturn(ActionEvent event) {
        try {
            Stage currentStage = (Stage) id4.getScene().getWindow(); 
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
     public void setPerson(Person person) {
        this.person = person;
     }

    @FXML
    private void accountsettings(MouseEvent event) {
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

    @FXML
    private void reservation(ActionEvent event) {
        
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
    }

    
 
    

