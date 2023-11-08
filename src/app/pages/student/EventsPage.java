package app.pages.student;

import backend.ColumnInfoTriple;
import backend.SqlConnector;
import backend.views.AvailableEvent;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.ResultSet;

import static app.Constants.WINDOW_HEIGHT;
import static app.Constants.WINDOW_WIDTH;
import static javafx.collections.FXCollections.observableArrayList;

public class EventsPage extends Scene {
    public EventsPage (Stage primaryStage) {
        super(new GridPane(), WINDOW_WIDTH, WINDOW_HEIGHT);

        GridPane grid = (GridPane) this.getRoot();

        // Create the table
        TableView<AvailableEvent> table = new TableView<>();
        ObservableList<AvailableEvent> list = observableArrayList();
        table.setItems(list);
        grid.add(table, 0, 0);

        // Create the columns
        for (ColumnInfoTriple info : AvailableEvent.getColumnInfo()) {
            TableColumn<AvailableEvent, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }

        // Populate the table
        ResultSet rs = SqlConnector.runSql("SELECT * FROM AvailableEvents");
        if (rs != null) AvailableEvent.fillList(rs, list);

        // Handle clicks
        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                AvailableEvent event = table.getSelectionModel().getSelectedItem();
                System.out.println(event.getEventId());
            }
        });
    }
}
