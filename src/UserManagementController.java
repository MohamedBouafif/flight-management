import Person.Account;
import Person.Person;
import Person.Role;
import Person.PersonStatus;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class UserManagementController {
    private static final String URL = "jdbc:postgresql://localhost:5432/airport_db"; // URL de votre base de données
    private static final String USER = "postgres"; // Nom de l'utilisateur PostgreSQL
    private static final String PASSWORD = "nfcyy662"; // Mot de passe de l'utilisateur

    private Button viewAccountsButton;
    private Button addAccountButton;
    private Button modifyAccountButton;
    private Button deleteAccountButton;
    @FXML
    private Button cancelButton;

    @FXML
    private TextField textName;
    @FXML
    private TextField textAddress;
    @FXML
    private TextField textMail;
    @FXML
    private TextField textPhone;
    @FXML
    private Button returnButton;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private ListView<String> accountsListView;
    @FXML
    private AnchorPane id8;
    private ObservableList<String> accountsList= FXCollections.observableArrayList();//display as String
    private ObservableList<Person> persons=FXCollections.observableArrayList();//save as Person type 
    @FXML
    private Button viewPersons;
    @FXML
    private Button addPerson;
    @FXML
    private Button modifyPerson;
    @FXML
    private Button deletePerson;
    @FXML
    private void initialize() {
        viewPersons.setOnAction(this::viewPersons);
        addPerson.setOnAction(this::addPerson);
        modifyPerson.setOnAction(this::modifyPerson);
        deletePerson.setOnAction(this::deletePerson);
        cancelButton.setOnAction(this::cancelAction);
        roleComboBox.getItems().addAll("Admin", "Crew", "Pilot");
        accountsList = FXCollections.observableArrayList();
        accountsListView.setItems(accountsList);
        accountsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    });
 }
    @FXML
    private void viewPersons(ActionEvent event) {//display persons 
    accountsList.clear();
    for (Person person : test.persons) {
        accountsList.add(person.toString());
    }
}
    @FXML
    private void addPerson(ActionEvent event) {
    if (validateInputs()) {
        Random random = new Random();
        int id=1;
        id = random.nextInt(10000);
       /* do {
            id = random.nextInt(10000);
        } while (test.persons.stream().anyMatch(p -> p.getId() == id));
*/
        Account account = new Account(id, "123", PersonStatus.ACTIVE, getRole1());
        Person person = new Person(
            id,
            textName.getText(),
            textAddress.getText(),
            textMail.getText(),
            Integer.parseInt(textPhone.getText()),
            account
        );
        test.persons.add(person);
        persons.add(person);
        accountsList.add("ID: " + person.getId());
        showAlert(Alert.AlertType.INFORMATION, "Success", "Person added successfully!\nID: " + id);
        viewPersons(null);
    }
}
    private TextField getName(){
        return textName;
    }
    private TextField getPhone(){
        return textPhone;
    }
    private TextField getMail(){
        return textMail;
    }
    private TextField getAdress(){
        return textAddress;
    }
@FXML
private void modifyPerson(ActionEvent event) {
    String selectedPerson = accountsListView.getSelectionModel().getSelectedItem(); 
    if (selectedPerson == null ||selectedPerson.isBlank()) {
        showAlert(Alert.AlertType.WARNING, "Selection", "No person selected.");
        return;
    }
    Person personToModify = test.persons.stream()
            .filter(p -> selectedPerson.equals(p.toString()))
            .findFirst()
            .orElse(null);
    if (personToModify != null) {
        personToModify.setNom(textName.getText());
        personToModify.setAddress(textAddress.getText());
        personToModify.setMail(textMail.getText());
        personToModify.setPhoneNum(Integer.parseInt(textPhone.getText()));
        personToModify.getAccount().setRole(getRole1());
        showAlert(Alert.AlertType.INFORMATION, "Success", "Person modified successfully!");
        viewPersons(null);  
    } else {
        showAlert(Alert.AlertType.ERROR, "Error", "Person not found.");
    }
}
    @FXML

private void deletePerson(ActionEvent event) {
    String selected = accountsListView.getSelectionModel().getSelectedItem();  
    if (selected == null || selected.isBlank()) {
        showAlert(Alert.AlertType.WARNING, "Sélection", "Aucune personne sélectionnée.");
        return;  
    }
    boolean removed = test.persons.removeIf(p -> selected.equals(p.toString()));
    if (removed) {
        showAlert(Alert.AlertType.INFORMATION, "Succès", "La personne a été supprimée avec succès !");
        viewPersons(null);  
    } else {
        showAlert(Alert.AlertType.ERROR, "Erreur", "La personne n'a pas été trouvée.");
    }
}
    private void cancelAction(ActionEvent event) {
        showAlert(AlertType.INFORMATION, "Cancelled", "Action cancelled.");
    }
    private boolean validateInputs() {
        if (textName.getText().isEmpty() || textAddress.getText().isEmpty() || textMail.getText().isEmpty() || textPhone.getText().isEmpty()) {
            showAlert(AlertType.WARNING, "Error", "All fields must be filled.");
            return false;
        }
        return true;
    }
    private int generateUniqueId() {
        return test.persons.stream().mapToInt(Person::getId).max().orElse(0) + 1;
    }
    private Role getRole1() {
        String role = roleComboBox.getSelectionModel().getSelectedItem();
        if ("Admin".equals(role)) return Role.Admin;
        if ("Crew".equals(role)) return Role.Crew;
        if ("Pilot".equals(role)) return Role.Pilot;
        return null;
    }
    private PersonStatus getStatus() {
        return PersonStatus.ACTIVE;
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void handleReturnButton(ActionEvent event) {
        try {
            Stage currentStage = (Stage) id8.getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAdminMenu.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(UserManagementController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        showAlert(AlertType.INFORMATION, "Cancelled", "Action cancelled.");
    }

    @FXML
    private void roleComboBox(ActionEvent event) {
    }
}