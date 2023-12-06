package deprecated.student;

import deprecated.components.TitledPage;
import deprecated.WelcomePage;
import backend.queries.ColumnInfoTriple;
import database.StudentSignupsView;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static deprecated.Constants.WINDOW_WIDTH;
import static javafx.collections.FXCollections.observableArrayList;

public class StudentHomePage extends TitledPage {
    public StudentHomePage(Stage primaryStage, int student_id) {
        super(primaryStage, "Student Home");
        GridPane grid = (GridPane) this.getRoot();

        // TODO: display full name and/or username

        // Event Search Button
        Button eventSearchButton = new Button("Event Search");
        grid.add(eventSearchButton, 0, 1);
        eventSearchButton.setOnAction(e -> primaryStage.setScene(new EventSearchPage(primaryStage, student_id)));

        // Profile button
        Button profileButton = new Button("Profile");
        grid.add(profileButton, 0, 2);
        profileButton.setOnAction(e -> {
            primaryStage.setScene(new StudentProfilePage(primaryStage, student_id));
        });

        // Logout button
        Button logoutButton = new Button("Log out");
        grid.add(logoutButton, 1, 2);
        logoutButton.setOnAction(e -> {
            primaryStage.setScene(new WelcomePage(primaryStage));
        });

        // Signups table
        TableView<StudentSignupsView> table = new TableView<>();
        ObservableList<StudentSignupsView> list = observableArrayList();
        table.setItems(list);
        table.setPrefWidth(WINDOW_WIDTH);
        grid.add(table, 0, 3, 3, 1);

        // Create columns and populate table
        for (ColumnInfoTriple info : StudentSignupsView.getColumnInfo()) {
            TableColumn<StudentSignupsView, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }
        StudentSignupsView.fillList(list, student_id);

        // Handle clicks
        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                StudentSignupsView signup = table.getSelectionModel().getSelectedItem();
                System.out.println("Clicked on " + signup.getEventId() + " " + signup.getClassName());
//                StudentSignupsView.removeSignup(student_id, Integer.parseInt(signup.getEventId()));
                primaryStage.setScene(new StudentHomePage(primaryStage, student_id));
            }
        });

    }
}
