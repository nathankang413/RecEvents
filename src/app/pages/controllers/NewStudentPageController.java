package app.pages.controllers;
import app.ScreenController;
import backend.queries.StudentValidation;
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
import javafx.stage.Stage;

public class NewStudentPageController {
    @FXML
    private TextField usernameTextField;
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
        if (StudentValidation.usernameExists(usernameTextField.getText())) {
            errorLabel.setText("Username already exists");
            return;
        }
        if (!passwordTextField.getText().equals(confirmPasswordField.getText())) {
            errorLabel.setText("Passwords do not match");
            return;
        }
        // TODO: name field
        int student_id = StudentValidation.insertNewCreds("Example name", usernameTextField.getText(), passwordTextField.getText());
        if (student_id >= 0) {
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/StudentHomePage.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                StudentHomePageController controller = loader.getController();
                controller.setSID(student_id);
                ScreenController.addScreen("Student Home Page", scene);
                ScreenController.activate("Student Home Page");
            }
            catch(Exception exception){
                exception.printStackTrace();
            }
        } else {
            errorLabel.setText("Error creating account");
        }
    }

}