package controllers.instructor;
import app.ScreenController;
import database.InstructorValidation;
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

public class InstructorLoginPageController {
    @FXML
    private Button loginButton, backButton, newUserButton;
    @FXML
    private Label alertLabel;
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;

    @FXML
    protected void login(ActionEvent e) {
        int instructor_id = InstructorValidation.validateLoginCreds(usernameTextField.getText(), passwordTextField.getText());
        if (instructor_id >= 0) {
            ScreenController.changeUserPage("Instructor Home Page", "/fxml/instructor/InstructorHomePage.fxml", instructor_id);
        }
        else {
            alertLabel.setText("Invalid login");
        }
    }

    @FXML
    protected void newUser(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/instructor/NewInstructorPage.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);


            ScreenController.addScreen("New Instructor Page", scene);
            ScreenController.activate("New Instructor Page");
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