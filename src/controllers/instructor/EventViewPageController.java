package controllers.instructor;

import app.ScreenController;
import database.ColumnInfoTriple;
import database.EventSignupsView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import static javafx.collections.FXCollections.observableArrayList;

public class EventViewPageController {
    @FXML
    protected Label classNameLabel, roomLabel, startTimeLabel;
    @FXML
    protected Button backButton, deleteSignupButton;
    @FXML
    protected TableView<EventSignupsView> table;
    private ObservableList<EventSignupsView> list;

    private int instructor_id;
    private int event_id;

    @FXML
    protected void deleteSignup(MouseEvent e) {
        if (e.getClickCount() == 2) {
            EventSignupsView signup = table.getSelectionModel().getSelectedItem();
            EventSignupsView.removeSignup(Integer.parseInt(signup.getSignupId()));
            table.getItems().remove(signup);
        }
    }

    @FXML
    protected void deleteEvent(ActionEvent e) {
        EventSignupsView.removeEvent(event_id);
        ScreenController.changeUserPage("Instructor Home Page", "/fxml/instructor/InstructorHomePage.fxml", instructor_id);
    }

    @FXML
    protected void back(ActionEvent e) {
        ScreenController.changeUserPage("Instructor Home Page", "/fxml/instructor/InstructorHomePage.fxml", instructor_id);
    }

    @FXML
    protected void initialize() {
        list = observableArrayList();
        table.setItems(list);

        for (ColumnInfoTriple info : EventSignupsView.getColumnInfo()) {
            TableColumn<EventSignupsView, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }
    }

    public void setIDs(int instructor_id, int event_id) {
        this.instructor_id = instructor_id;
        this.event_id = event_id;

        EventSignupsView.fillList(this.list, event_id);
        classNameLabel.setText(EventSignupsView.getEventDetail(event_id, "class_name"));
        roomLabel.setText("in " + EventSignupsView.getEventDetail(event_id, "room"));
        startTimeLabel.setText("at " + EventSignupsView.getEventDetail(event_id, "start_time"));
    }
}
