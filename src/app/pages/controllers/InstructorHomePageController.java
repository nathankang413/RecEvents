package app.pages.controllers;
import app.ScreenController;
import backend.queries.ColumnInfoTriple;
import backend.queries.InstructorClassesView;
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
public class InstructorHomePageController {
    @FXML
    private Button logOutButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button eventSearchButton;
    @FXML
    TableView<InstructorClassesView> table;
    ObservableList<InstructorClassesView> list;
    int Instructor_id;

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
            loader.setLocation(getClass().getResource("/fxml/InstructorProfilePage.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            InstructorProfilePageController controller = loader.getController();
            controller.setSID(Instructor_id);


            ScreenController.addScreen("Instructor Profile Page", scene);
            ScreenController.activate("Instructor Profile Page");
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
            controller.setSID(Instructor_id);


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
        for (ColumnInfoTriple info : InstructorClassesView.getColumnInfo()) {
            TableColumn<InstructorClassesView, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }
    }


    public void setSID(int Instructor_id) {
        this.Instructor_id = Instructor_id;
        InstructorClassesView.fillList(list, Instructor_id);
    }
}