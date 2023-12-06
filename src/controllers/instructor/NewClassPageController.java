package controllers.instructor;

import app.ScreenController;
import controllers.UserPageController;
import database.SqlConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewClassPageController implements UserPageController {

    @FXML
    private TextField classNameField, priceField;

    @FXML
    private Button backButton, createClassButton;
    @FXML
    private Label errorLabel;

    private int instructor_id;

    @FXML
    protected void createClass(ActionEvent e) {
        String insertSQL = "INSERT INTO Classes (name, instructor_id, price) VALUES ('%s', '%d', '%.2f')";

        try {
            SqlConnector.runUpdate(String.format(insertSQL, classNameField.getText(), instructor_id, Double.parseDouble(priceField.getText())));
        } catch (NumberFormatException ex) {
            errorLabel.setText("Invalid price");
        }

        ScreenController.changeUserPage("New Event Page", "/fxml/instructor/NewEventPage.fxml", instructor_id);
    }

    @FXML
    protected void back(ActionEvent e) {
        ScreenController.changeUserPage("New Event Page", "/fxml/instructor/NewEventPage.fxml", instructor_id);
    }

    public void setUID(int instructor_id) {
        this.instructor_id = instructor_id;
    }



}
