package controllers.student;
import app.ScreenController;
import database.StudentValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewStudentPageController {
    @FXML
    private TextField usernameTextField, nameTextField;
    @FXML
    private PasswordField passwordTextField, confirmPasswordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button backButton, signupButton, existingUserButton;

    @FXML
    protected void signup(ActionEvent e) {
        if (usernameTextField.getLength() == 0 || passwordTextField.getLength() == 0 || confirmPasswordField.getLength() == 0) {
            errorLabel.setText("Please fill out all fields");
            return;
        }
        if (StudentValidation.usernameExists(usernameTextField.getText())) {
            errorLabel.setText("Username already exists");
            return;
        }
        if (!passwordTextField.getText().equals(confirmPasswordField.getText())) {
            errorLabel.setText("Passwords do not match");
            return;
        }

        int student_id = StudentValidation.insertNewCreds(nameTextField.getText(), usernameTextField.getText(), passwordTextField.getText());
        if (student_id >= 0) {
            ScreenController.changeStudentPage("Student Home Page", "/fxml/student/StudentHomePage.fxml", student_id);
        } else {
            errorLabel.setText("Error creating account");
        }
    }

    @FXML
    protected  void back(ActionEvent e){
        ScreenController.activate("Welcome Page");
    }

    @FXML
    protected void existingUser(ActionEvent e){
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ScreenController.changePage("Student Login Page", "/fxml/student/StudentLoginPage.fxml");
    }
}