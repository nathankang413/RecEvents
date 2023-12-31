package controllers.student;
import app.ScreenController;
import database.AvailableEventView;
import database.ColumnInfoTriple;
import controllers.UserPageController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static javafx.collections.FXCollections.observableArrayList;

public class EventSearchPageController implements UserPageController {
    @FXML
    private Button backButton;
    @FXML
    private Label errorLabel;
    int student_id;
    @FXML
    TableView<AvailableEventView> table;
    ObservableList<AvailableEventView> list;

    @FXML
    public void initialize() {
        list = observableArrayList();
        table.setItems(list);

        // Create the columns and populate table
        for (ColumnInfoTriple info : AvailableEventView.getColumnInfo()) {
            TableColumn<AvailableEventView, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }
        AvailableEventView.fillList(list);

    }

    @FXML
    protected void mouseClicked(MouseEvent e){
        if (e.getClickCount() == 2) {
            AvailableEventView event = table.getSelectionModel().getSelectedItem();
            String errorMessage = AvailableEventView.addSignup(student_id, Integer.parseInt(event.getEventId()));
            if (errorMessage != null) {
                errorLabel.setText(errorMessage);
            } else if (student_id >= 0) {
                ScreenController.changeUserPage("Student Home Page", "/fxml/student/StudentHomePage.fxml", student_id);
            }
        }

    }

    @FXML
    protected void back(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        if (student_id >= 0){
            ScreenController.changeUserPage("Student Home Page", "/fxml/student/StudentHomePage.fxml", student_id);
        }
        else{
            System.out.println("student_id lost in EventSearchPageController");
            ScreenController.activate("Welcome Page");
        }
    }
    public void setUID(int student_id) {
        this.student_id = student_id;
    }

}