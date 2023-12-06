package controllers.instructor;

import app.ScreenController;
import database.ClassView;
import controllers.UserPageController;
import database.SqlConnector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static javafx.collections.FXCollections.observableArrayList;

public class NewEventPageController implements UserPageController {
    private ObservableList<ClassView> classOptions;
    @FXML
    protected ChoiceBox<ClassView> classSelection;
    @FXML
    protected Button newClassButton, addEventButton, backButton;
    @FXML
    protected TextField roomField, seatsField, startTimeField, endTimeField;
    @FXML
    protected DatePicker dateSelection;
    @FXML
    protected Label errorLabel;

    private int instructor_id;

    @FXML
    protected void newClass(ActionEvent e) {
        ScreenController.changeUserPage("New Class", "/fxml/instructor/NewClassPage.fxml", instructor_id);
    }

    @FXML
    protected void addEvent(ActionEvent e) {
        try {
            String SQL = "INSERT INTO Events (class_id, room, start_time, end_time, seats) VALUES (%s, '%s', '%s', '%s', %s)";

            int class_id = classSelection.getValue().getClassId();
            String room = roomField.getText();
            LocalDate date = dateSelection.getValue();
            int seats = Integer.parseInt(seatsField.getText());
            LocalTime start_time = LocalTime.parse(startTimeField.getText());
            LocalTime end_time = LocalTime.parse(endTimeField.getText());
            LocalDateTime start_datetime = LocalDateTime.of(date, start_time);
            LocalDateTime end_datetime = LocalDateTime.of(date, end_time);

            // Check if times are valid
            if (start_datetime.isAfter(end_datetime)) {
                errorLabel.setText("Times are invalid");
                return;
            }

            // Check if room is booked already
            String getConflicts = String.format("""
                SELECT COUNT(*) AS conflicts FROM Events
                WHERE room = '%s'
                AND NOT (
                    end_time < '%s'
                    OR start_time > '%s'
                )
                """, room, end_datetime, start_datetime);
            ResultSet rs = SqlConnector.runQuery(getConflicts);
            try {
                if (rs.next()) {
                    int conflicts = rs.getInt("conflicts");
                    if (conflicts > 0) {
                        errorLabel.setText("Room already booked");
                        return;
                    }
                } else {
                    System.out.println("Could not check for conflicts");
                    return;
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
                return;
            }

            String query = String.format(SQL, class_id, room, start_datetime, end_datetime, seats);
            SqlConnector.runUpdate(query);

            ScreenController.changeUserPage("Instructor Home Page", "/fxml/instructor/InstructorHomePage.fxml", instructor_id);
        } catch (Exception ex) {
            errorLabel.setText("Invalid input");
        }
    }

    @FXML
    protected void back(ActionEvent e) {
        ScreenController.changeUserPage("Instructor Home Page", "/fxml/instructor/InstructorHomePage.fxml", instructor_id);
    }

    @FXML
    protected void initialize() {
        classOptions = observableArrayList();
        classSelection.setItems(classOptions);
    }

    public void setUID(int instructor_id) {
        this.instructor_id = instructor_id;
        ClassView.fillList(classOptions, instructor_id);
    }


}
