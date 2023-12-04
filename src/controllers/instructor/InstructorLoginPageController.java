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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InstructorLoginPageController {
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
        int Instructor_id = InstructorValidation.validateLoginCreds(usernameTextField.getText(), passwordTextField.getText());
        if (Instructor_id >= 0){
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/instructor/InstructorHomePage.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                InstructorHomePageController controller = loader.getController();
                controller.setSID(Instructor_id);


                ScreenController.addScreen("Instructor Home Page", scene);
                ScreenController.activate("Instructor Home Page");
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