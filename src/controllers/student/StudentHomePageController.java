package controllers.student;
import app.ScreenController;
import backend.queries.ColumnInfoTriple;
import backend.queries.StudentSignupsView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static javafx.collections.FXCollections.observableArrayList;

import javafx.scene.control.Button;
public class StudentHomePageController implements StudentController {
    @FXML
    private Button logOutButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button eventSearchButton;
    @FXML
    TableView<StudentSignupsView> table;
    ObservableList<StudentSignupsView> list;
    int student_id;

    @FXML
    protected void logout(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ScreenController.activate("Welcome Page");
    }
    @FXML
    protected void profileButtonClick(ActionEvent e) {
        ScreenController.changeStudentPage("Student Profile Page", "/fxml/student/StudentProfilePage.fxml", student_id);
    }
    @FXML
    protected void eventSearch(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ScreenController.changeStudentPage("Event Search Page", "/fxml/student/EventSearchPage.fxml", student_id);
    }
    @FXML
    public void initialize(){
        list = observableArrayList();
        table.setItems(list);
        // Create columns and populate table
        for (ColumnInfoTriple info : StudentSignupsView.getColumnInfo()) {
            TableColumn<StudentSignupsView, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }
    }

    public void setSID(int student_id) {
        this.student_id = student_id;
        StudentSignupsView.fillList(list, student_id);
    }
}