package app.pages.controllers;
import app.ScreenController;
import backend.queries.AvailableEventView;
import backend.queries.ColumnInfoTriple;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static javafx.collections.FXCollections.observableArrayList;

public class EventSearchPageController {
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
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            AvailableEventView event = table.getSelectionModel().getSelectedItem();
            System.out.println("Clicked on " + event.getEventId() + " " + event.getClassName());
            String errorMessage = AvailableEventView.addSignup(student_id, Integer.parseInt(event.getEventId()));
            if (errorMessage != null) {
                errorLabel.setText(errorMessage);
            } else {
                if (student_id >= 0) {
                    try {

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/fxml/student/StudentHomePage.fxml"));
                        Parent root = loader.load();

                        Scene scene = new Scene(root);
                        StudentHomePageController controller = loader.getController();
                        controller.setSID(student_id);


                        ScreenController.addScreen("Student Home Page", scene);
                        ScreenController.activate("Student Home Page");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }

    }

    @FXML
    protected void back(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        if (student_id >= 0){
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/student/StudentHomePage.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                StudentHomePageController controller = loader.getController();
                controller.setSID(student_id);


                ScreenController.addScreen("Student Home Page", scene);
                ScreenController.activate("Student Home Page");
            }
            catch(Exception exception){
                exception.printStackTrace();
            }
        }
        else{
//            alertText.setText("Invalid username or password");
            System.out.println("todo: add failed login alert");
        }
    }
    public void setSID(int student_id) {
        this.student_id = student_id;
    }

}