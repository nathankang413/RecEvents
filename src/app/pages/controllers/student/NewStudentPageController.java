package app.pages.controllers.student;
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
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/student/StudentHomePage.fxml"));
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

    @FXML
    protected  void back(ActionEvent e){
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ScreenController.activate("Welcome Page");
    }

    @FXML
    protected void existingUser(ActionEvent e){
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/student/StudentLoginPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ScreenController.addScreen("Student Login Page", scene);
            ScreenController.activate("Student Login Page");
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }
}