package controllers;
import app.ScreenController;
import deprecated.instructor.InstructorLoginPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomePageController {
    @FXML
    private Button instructorButton;
    @FXML
    private Button studentButton;

    @FXML
    protected void instructorButtonClick(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/instructor/InstructorLoginPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ScreenController.addScreen("Instructor Login Page", scene);
            ScreenController.activate("Instructor Login Page");
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }

    @FXML
    protected void studentButtonClick(ActionEvent e) {
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