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
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/student/StudentProfilePage.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            StudentProfilePageController controller = loader.getController();
            controller.setSID(student_id);


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
            loader.setLocation(getClass().getResource("/fxml/student/EventSearchPage.fxml"));
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