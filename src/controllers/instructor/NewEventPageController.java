package controllers.instructor;

import app.ScreenController;
import controllers.UserPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class NewEventPageController implements UserPageController {
    @FXML
    protected ComboBox classSelection;  // TODO: type
    @FXML
    protected Button newClassButton, addEventButton, backButton;
    @FXML
    protected TextField roomField, startTimeField, endTimeField;
    @FXML
    protected DatePicker dateSelection;

    private int instructor_id;

    @FXML
    protected void newClass(ActionEvent e) {
        // TODO: change to new class page
    }

    @FXML
    protected void addEvent(ActionEvent e) {
        // TODO: add event to database
    }

    @FXML
    protected void back(ActionEvent e) {
        ScreenController.changeUserPage("Instructor Home Page", "/fxml/instructor/InstructorHomePage.fxml", instructor_id);
    }

    public void setUID(int instructor_id) {
        this.instructor_id = instructor_id;
    }


}
