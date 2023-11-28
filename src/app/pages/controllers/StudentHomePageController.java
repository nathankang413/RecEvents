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
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StudentHomePageController {
    @FXML
    private Button logOutButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button eventSearchButton;
    int student_id;

    @FXML
    protected void logout(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ScreenController.activate("Welcome Page");
    }
    @FXML
    protected void profileButtonClick(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/StudentProfilePage.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            StudentHomePageController controller = loader.getController();
//            controller.setSID(student_id);


            ScreenController.addScreen("Student Profile Page", scene);
            ScreenController.activate("Student Profile Page");
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }
    @FXML
    protected void eventSearch(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/EventSearchPage.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            EventSearchPageController controller = loader.getController();
            controller.setSID(student_id);


            ScreenController.addScreen("Event Search Page", scene);
            ScreenController.activate("Event Search Page");
        }
        catch(Exception exception){
            exception.printStackTrace();
        }

    }


    public void setSID(int student_id) {
        this.student_id = student_id;
    }
}