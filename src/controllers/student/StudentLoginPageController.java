package controllers.student;
import app.ScreenController;
import backend.queries.StudentValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StudentLoginPageController {
    @FXML
    private Button loginButton;
    @FXML
    private Button backButton;
    @FXML
    private Button newUserButton;
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;

    @FXML
    protected void login(ActionEvent e) {
        int student_id = StudentValidation.validateLoginCreds(usernameTextField.getText(), passwordTextField.getText());
        if (student_id >= 0){
            ScreenController.changeStudentPage("Student Home Page", "/fxml/student/StudentHomePage.fxml", student_id);
        }
        else{
//            TODO: alertText.setText("Invalid username or password");
            System.out.println("todo: add failed login alert");
        }
    }
    @FXML
    protected void newUser(ActionEvent e) {
        ScreenController.changePage("New Student Page", "/fxml/student/NewStudentPage.fxml");
    }

    @FXML
    protected void back(ActionEvent e) {
        ScreenController.activate("Welcome Page");
        // TODO: SceneController.changePage("Welcome Page", "/fxml/WelcomePage.fxml");
    }
}