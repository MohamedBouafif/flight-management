
import Person.Account;
import Person.Admin;
import Person.Person;
import Person.Role;
import Person.PersonStatus;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class AccountSettingsController  {

    @FXML
    private Label ID;
    @FXML
    private Label mail;
    @FXML
    private Label phone;
     private Person person;
    @FXML
    private TextField newEmailField;
    @FXML
    private TextField newPhoneField;
    @FXML
    private TextField textpassword;
    @FXML
    private VBox id10;
    @FXML
    private Label AccountSettings;
    
     public void setPerson(Person person) {
        this.person = person;
        updateFields();
    }
    private void updateFields() {
        if (person != null) {
            ID.setText(String.valueOf(person.getId()));
            mail.setText(person.getMail());
            phone.setText(String.valueOf(person.getPhoneNumber()));
        }
    }
    @FXML
    public void resetpassword(ActionEvent event) {
        String newpassword = textpassword.getText();
        if (newpassword != null && !newpassword.isEmpty()) {
            person.setpassword(newpassword);  
            showAlert("Success", "password has been successfully reset.");
        } else {
            showAlert("Error", "Please enter a valid password.");
        }
    }
    @FXML
    private void resetmail(ActionEvent event) {
        String newEmail = newEmailField.getText();
        
        if (newEmail != null && !newEmail.isEmpty()) {
            person.setMail(newEmail);
            mail.setText(newEmail);  
            showAlert("Success", "Email has been successfully reset.");
        } else {
            showAlert("Error", "Please enter a valid email.");
        }
    }
    @FXML
    private void resetphone(ActionEvent event) {
        String phoneText = newPhoneField.getText();
        try {
            int newPhoneNumber = Integer.parseInt(phoneText);  
            person.setPhoneNum(newPhoneNumber);
            phone.setText(String.valueOf(newPhoneNumber)); 
            showAlert("Success", "Phone number has been successfully reset.");
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid phone number.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }   
}
