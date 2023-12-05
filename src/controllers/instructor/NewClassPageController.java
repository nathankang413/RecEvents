package controllers.instructor;

import app.ScreenController;
import controllers.UserPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewClassPageController implements UserPageController {

    @FXML
    private TextField classNameField, priceField;

    @FXML
    private Button backButton, createClassButton;

    private int instructor_id;

    @FXML
    protected void createClass(ActionEvent e) {
        // TODO
    }

    @FXML
    protected void back(ActionEvent e) {
        ScreenController.changeUserPage("New Event Page", "/fxml/instructor/NewEventPage.fxml", instructor_id);
    }

    public void setUID(int instructor_id) {
        this.instructor_id = instructor_id;
    }



}
