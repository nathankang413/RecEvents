package deprecated.instructor;

import deprecated.components.TitledPage;
import backend.queries.AddClass;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateClass extends TitledPage {
    public CreateClass(Stage primaryStage, int id) {
        super(primaryStage, "Create New Class");
        GridPane grid = (GridPane) this.getRoot();

        // Error label
        Label errorLabel = new Label("");
        grid.add(errorLabel, 0, 6);

        // Full name label and field
        Label nameLabel = new Label("Class Name:");
        grid.add(nameLabel, 0, 2);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 2);

        // Username label and field
        Label priceLabel = new Label("Price:");
        grid.add(priceLabel, 0, 3);
        TextField priceField = new TextField();
        grid.add(priceField, 1, 3);

        Button AddEventButton = new Button("Add Class");
        grid.add(AddEventButton, 0, 5);
        AddEventButton.setOnAction(e -> {
            AddClass.addClass(nameTextField.getText(), id,Double.parseDouble(priceField.getText()));
            primaryStage.setScene(new CreateEvent(primaryStage, id));

        });

        Button backButton = new Button("Back");
        grid.add(backButton, 1, 5);
        backButton.setOnAction(e -> {
            primaryStage.setScene(new CreateEvent(primaryStage, id));
        });
    }
}
