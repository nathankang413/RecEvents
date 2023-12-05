package controllers.instructor;
import app.ScreenController;
import backend.queries.InstructorValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class NewInstructorPageController {
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
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        if (usernameTextField.getLength() == 0 || passwordTextField.getLength() == 0 || confirmPasswordField.getLength() == 0) {
            errorLabel.setText("Please fill out all fields");
            return;
        }
        if (InstructorValidation.usernameExists(usernameTextField.getText())) {
            errorLabel.setText("Username already exists");
            return;
        }
        if (!passwordTextField.getText().equals(confirmPasswordField.getText())) {
            errorLabel.setText("Passwords do not match");
            return;
        }
        int instructor_id = InstructorValidation.insertNewCreds(nameTextField.getText(), usernameTextField.getText(), passwordTextField.getText());
        if (instructor_id >= 0) {
            // TODO
            ScreenController.changeUserPage("Instructor Home Page", "/fxml/instructor/InstructorHomePage.fxml", instructor_id);
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
        ScreenController.changePage("Instructor Login Page", "/fxml/instructor/InstructorLoginPage.fxml");
    }
}