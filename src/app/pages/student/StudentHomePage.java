package app.pages.student;

import app.components.TitledPage;
import backend.queries.ColumnInfoTriple;
import backend.queries.StudentSignupsView;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static app.Constants.WINDOW_WIDTH;
import static javafx.collections.FXCollections.observableArrayList;

public class StudentHomePage extends TitledPage {
    public StudentHomePage(Stage primaryStage, int student_id) {
        super(primaryStage, "Student Home");
        GridPane grid = (GridPane) this.getRoot();

        // Event Search Button
        Button eventSearchButton = new Button("Event Search");
        grid.add(eventSearchButton, 0, 1);
        eventSearchButton.setOnAction(e -> primaryStage.setScene(new EventSearchPage(primaryStage)));

        // Profile button
        Button profileButton = new Button("Profile");
        grid.add(profileButton, 0, 2);
        profileButton.setOnAction(e -> {
//            primaryStage.setScene(new StudentProfilePage(primaryStage));
        });

        // Signups table
        TableView<StudentSignupsView> table = new TableView<>();
        ObservableList<StudentSignupsView> list = observableArrayList();
        table.setItems(list);
        table.setPrefWidth(WINDOW_WIDTH);
        grid.add(table, 0, 3);

        // Create columns and populate table
        for (ColumnInfoTriple info : StudentSignupsView.getColumnInfo()) {
            TableColumn<StudentSignupsView, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }
        StudentSignupsView.fillList(list, student_id);

    }
}
