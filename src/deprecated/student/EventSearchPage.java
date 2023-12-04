package deprecated.student;

import deprecated.components.TitledPage;
import backend.queries.ColumnInfoTriple;
import backend.queries.AvailableEventView;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static app.Constants.WINDOW_WIDTH;
import static javafx.collections.FXCollections.observableArrayList;

public class EventSearchPage extends TitledPage {
    public EventSearchPage(Stage primaryStage, int student_id) {
        super(primaryStage, "Events");
        GridPane grid = (GridPane) this.getRoot();

        // Back Button
        Button backButton = new Button("Back");
        grid.add(backButton, 0, 4);
        backButton.setOnAction(e -> primaryStage.setScene(new StudentHomePage(primaryStage, student_id)));

        // Create the table
        TableView<AvailableEventView> table = new TableView<>();
        ObservableList<AvailableEventView> list = observableArrayList();
        table.setItems(list);
        table.setPrefWidth(WINDOW_WIDTH);
        grid.add(table, 0, 1);

        // Create the columns and populate table
        for (ColumnInfoTriple info : AvailableEventView.getColumnInfo()) {
            TableColumn<AvailableEventView, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }
        AvailableEventView.fillList(list);

        // Error messaging
        Label errorLabel = new Label("");
        grid.add(errorLabel, 0, 2);

        // Handle clicks
        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                AvailableEventView event = table.getSelectionModel().getSelectedItem();
                System.out.println("Clicked on " + event.getEventId() + " " + event.getClassName());
                String errorMessage = AvailableEventView.addSignup(student_id, Integer.parseInt(event.getEventId()));
                if (errorMessage != null) {
                    errorLabel.setText(errorMessage);
                } else {
                    primaryStage.setScene(new StudentHomePage(primaryStage, student_id));
                }
            }
        });
    }
}
