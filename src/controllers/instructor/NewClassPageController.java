package controllers.instructor;

import app.ScreenController;
import controllers.UserPageController;
import javafx.fxml.FXML;

import java.awt.*;
import java.awt.event.ActionEvent;

public class NewClassPageController implements UserPageController {

    @FXML
    private TextField classNameField, priceField;

    @FXML
    private Button backButton, createClassButton;

    private int instructor_id;

    public void setUID(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    protected void back(ActionEvent e) {
        ScreenController.changeUserPage("Instructor Home Page", "/fxml/instructor/InstructorHomePage.fxml", instructor_id);
    }



}
