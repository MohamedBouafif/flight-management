 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Person.Account;
import Person.Role;
import static Person.Role.Admin;
import static Person.Role.Crew;
import static Person.Role.Pilot;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController implements Initializable {
    public static Role r;
    @FXML
    private Button logAdmin;
    @FXML
    private Button logCrew;
    @FXML
    private Button logPilote;
    @FXML
    private VBox id0;

    LinkedList<Account> list;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    private void logasadmin(ActionEvent event) {
        openLoginView(Role.Admin);
    }
    @FXML
    private void logascrew(ActionEvent event) {
        openLoginView(Role.Crew);
    }
    @FXML
    private void logaspilote(ActionEvent event) {
        openLoginView(Role.Pilot);
    }
    private void openLoginView(Role role) {
    try {
        id0.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        Parent root = loader.load();
        LoginViewController controller = loader.getController();
        controller.setRole(role);
        Stage st = new Stage();
        st.setTitle("Log in as " + role);
        st.setScene(new Scene(root));
        st.show();
    } catch (IOException ex) {
        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
}    
}
