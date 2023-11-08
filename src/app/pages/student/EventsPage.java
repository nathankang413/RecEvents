package app.pages.student;

import backend.ColumnInfoTriple;
import backend.queries.AvailableEventView;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static app.Constants.WINDOW_HEIGHT;
import static app.Constants.WINDOW_WIDTH;
import static javafx.collections.FXCollections.observableArrayList;

public class EventsPage extends Scene {
    public EventsPage (Stage primaryStage) {
        super(new GridPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
        GridPane grid = (GridPane) this.getRoot();

        // Create the table
        TableView<AvailableEventView> table = new TableView<>();
        ObservableList<AvailableEventView> list = observableArrayList();
        table.setItems(list);
        grid.add(table, 0, 0);

        // Create the columns and populate table
        for (ColumnInfoTriple info : AvailableEventView.getColumnInfo()) {
            TableColumn<AvailableEventView, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }
        AvailableEventView.fillList(list);

        // Handle clicks
        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                AvailableEventView event = table.getSelectionModel().getSelectedItem();
                System.out.println(event.getEventId());
            }
        });
    }
}
