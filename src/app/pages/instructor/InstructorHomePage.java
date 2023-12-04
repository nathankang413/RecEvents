package app.pages.instructor;

import app.components.TitledPage;
import app.pages.WelcomePage;
import backend.queries.AvailableEventView;
import backend.queries.ColumnInfoTriple;
import backend.queries.InstructorClassesView;
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

public class InstructorHomePage extends TitledPage {
    public InstructorHomePage(Stage primaryStage, int id) {
        super(primaryStage, "Instructor Home");
        GridPane grid = (GridPane) this.getRoot();

        /*
        // Event Search Button
        Button eventSearchButton = new Button("Event Search");
        grid.add(eventSearchButton, 0, 1);
        eventSearchButton.setOnAction(e -> primaryStage.setScene(new EventSearchPage(primaryStage, id)));
    */
        // Profile button
        Button profileButton = new Button("Profile");
        grid.add(profileButton, 0, 2);
        profileButton.setOnAction(e -> {
            primaryStage.setScene(new InstructorProfilePage(primaryStage, id));
        });

        // Logout button
        Button logoutButton = new Button("Log out");
        grid.add(logoutButton, 1, 2);
        logoutButton.setOnAction(e -> {
            primaryStage.setScene(new WelcomePage(primaryStage));
        });

        // New Event Button
        Button eventButton = new Button("New Event");
        grid.add(eventButton, 1, 2);
        eventButton.setOnAction(e -> {
            primaryStage.setScene(new CreateEvent(primaryStage, id));
        });

        // Signups table
        TableView<InstructorClassesView> table = new TableView<>();
        ObservableList<InstructorClassesView> list = observableArrayList();
        table.setItems(list);
        table.setPrefWidth(WINDOW_WIDTH);
        grid.add(table, 0, 3, 3, 1);

        // Create columns and populate table
        for (ColumnInfoTriple info : InstructorClassesView.getColumnInfo()) {
            TableColumn<InstructorClassesView, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }
        InstructorClassesView.fillList(list, id);

        // Handle clicks
        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                InstructorClassesView signup = table.getSelectionModel().getSelectedItem();
                System.out.println("Clicked on " + signup.getEventId() + " " + signup.getClassName());
                InstructorClassesView.removeClass(Integer.parseInt(signup.getEventId()));
                primaryStage.setScene(new InstructorHomePage(primaryStage, id));
            }
        });

    }
}
