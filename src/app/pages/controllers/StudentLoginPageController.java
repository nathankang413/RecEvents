package app.pages.controllers;
import app.RecEventsApplication;
import app.ScreenController;
import app.pages.instructor.InstructorLoginPage;
import app.pages.student.NewStudentPage;
import app.pages.student.StudentLoginPage;
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
        }
        else{
//            alertText.setText("Invalid username or password");
            System.out.println("todo: add failed login alert");
        }
    }
    @FXML
    protected void newUser(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/NewStudentPage.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);


            ScreenController.addScreen("New Student Page", scene);
            ScreenController.activate("New Student Page");
        }
        catch(Exception exception){
            exception.printStackTrace();
        }

    }

    @FXML
    protected void back(ActionEvent e) {
//        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ScreenController.activate("Welcome Page");

    }
}