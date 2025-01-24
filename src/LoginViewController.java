/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Person.Account;
import Person.Admin;
import Person.Crew;
import Person.Person;
import Person.Pilot;
import Person.Role;
import static Person.Role.Admin;
import Person.PersonStatus;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LoginViewController implements Initializable {

    @FXML
    private TextField textid;
    @FXML
    private PasswordField textpassword;
    @FXML
    private Label Id;
    @FXML
    private Label Password;
    @FXML
    private Label msg;
    @FXML
    private VBox id1;
    @FXML
    private Button previous;

    private Role role; 
    
    public void setRole(Role role) {
        this.role = role;
        if (Id != null) {
            Id.setText(role + " Id :");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    private Object loggedUser; // Peut être Admin, Crew ou Pilot

@FXML
private void clickedok(ActionEvent event) throws IOException {
    if (textid.getText().isEmpty() || textpassword.getText().isEmpty()) {
        msg.setText("Please fill both fields");
        return;
    }
    try {
        int id = Integer.parseInt(textid.getText());
        String password = textpassword.getText();
        boolean ok=false;
        if (role == Role.Admin) {
            loggedUser = test.list1.stream()
                    .filter(admin -> admin.VerifLogin(id, password))
                    .findFirst()
                    .orElse(null);
                 ok = loggedUser != null;
                if (ok) {
                id1.getScene().getWindow().hide();
                
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAdminMenu.fxml"));
                Parent root = loader.load();
                ViewAdminMenuController controller = loader.getController();
                controller.setPerson((Person) loggedUser);        
                Stage st = new Stage();
                st.setScene(new Scene(root));
                st.setTitle("Admin Menu");
                st.show();
            } else {
                msg.setText("ID or password not valid");
            }
        } else if (role == Role.Crew) {
            loggedUser = test.list2.stream()
                    .filter(crew -> crew.VerifLogin(id, password))
                    .findFirst()
                    .orElse(null);
            ok = test.list2.stream().anyMatch(crew -> crew.VerifLogin(id, password));
                if (ok) {
                    id1.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CrewMenuView.fxml"));
                Parent root = loader.load();
                CrewMenuViewController controller = loader.getController();
                controller.setPerson((Person) loggedUser);        
                Stage st = new Stage();
                st.setScene(new Scene(root));
                st.setTitle("Crew Menu");
                st.show();
            } else {
                msg.setText("ID or password not valid");
            }
        } else if (role == Role.Pilot) {
            loggedUser = test.list3.stream()
                    .filter(pilot -> pilot.VerifLogin(id, password))
                    .findFirst()
                    .orElse(null);
            ok = test.list3.stream().anyMatch(pilot -> pilot.VerifLogin(id, password));
                if (ok) {
                id1.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PilotMenuView.fxml"));
                Parent root = loader.load();
                PiloteMenuViewController controller = loader.getController();
                controller.setPerson((Person) loggedUser);        
                Stage st = new Stage();
                st.setScene(new Scene(root));
                st.setTitle("Pilot Menu");
                st.show();
            } else {
                msg.setText("ID or password not valid");
            }
        } 
    } catch (NumberFormatException e) {
        msg.setText("ID must be a valid number");
    }
}
    @FXML
    private void clickreturn(ActionEvent event) {
         try {
            Stage currentStage = (Stage) id1.getScene().getWindow(); 
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(new Scene(root));
            stage.show();
    } catch (IOException e) {
        Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, e);
        msg.setText("Error loading the previous page");
    }
    }
    @FXML
    private void clickedreset(ActionEvent event) {
        textid.clear();
        textpassword.clear();
        msg.setText("");
    }
}
