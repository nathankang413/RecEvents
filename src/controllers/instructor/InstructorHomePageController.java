package controllers.instructor;
import app.ScreenController;
import backend.queries.ColumnInfoTriple;
import database.InstructorEventsView;
import controllers.UserPageController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static javafx.collections.FXCollections.observableArrayList;

import javafx.scene.control.Button;
public class InstructorHomePageController implements UserPageController {
    @FXML
    private Button logOutButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button newEventButton;
    @FXML
    TableView<InstructorEventsView> table;
    ObservableList<InstructorEventsView> list;
    int instructor_id;

    @FXML
    protected void logout(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ScreenController.activate("Welcome Page");
    }
    @FXML
    protected void profileButtonClick(ActionEvent e) {
        ScreenController.changeUserPage("Instructor Profile Page", "/fxml/instructor/InstructorProfilePage.fxml", instructor_id);
    }
    @FXML
    protected void newEvent(ActionEvent e) {
        ScreenController.changeUserPage("New Event Page", "/fxml/instructor/NewEventPage.fxml", instructor_id);
    }

    @FXML
    protected void viewEvent(MouseEvent e) {
        if (e.getClickCount() == 2) {
            InstructorEventsView event = table.getSelectionModel().getSelectedItem();
            ScreenController.changeToEventViewPage(instructor_id, Integer.parseInt(event.getEventId()));
        }
    }

    @FXML
    public void initialize(){
        list = observableArrayList();
        table.setItems(list);
        // Create columns and populate table
        for (ColumnInfoTriple info : InstructorEventsView.getColumnInfo()) {
            TableColumn<InstructorEventsView, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }
    }

    public void setUID(int Instructor_id) {
        this.instructor_id = Instructor_id;
        InstructorEventsView.fillList(list, Instructor_id);
    }
}