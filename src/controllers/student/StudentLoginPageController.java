package controllers.student;
import app.ScreenController;
import database.StudentValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class StudentLoginPageController {
    @FXML
    private Button loginButton, backButton, newUserButton;
    @FXML
    Label alertLabel;
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;

    @FXML
    protected void login(ActionEvent e) {
        int student_id = StudentValidation.validateLoginCreds(usernameTextField.getText(), passwordTextField.getText());
        if (student_id >= 0){
            ScreenController.changeUserPage("Student Home Page", "/fxml/student/StudentHomePage.fxml", student_id);
        }
        else{
            alertLabel.setText("Invalid login");
        }
    }
    @FXML
    protected void newUser(ActionEvent e) {
        ScreenController.changePage("New Student Page", "/fxml/student/NewStudentPage.fxml");
    }

    @FXML
    protected void back(ActionEvent e) {
        ScreenController.activate("Welcome Page");
    }
}