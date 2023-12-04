package app.pages.instructor;

import app.components.TitledPage;
import app.pages.student.StudentHomePage;
import backend.queries.StudentProfile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateEvent extends TitledPage {
    public CreateEvent(Stage primaryStage, int instructorid) {
        super(primaryStage, "Create New Event");
        GridPane grid = (GridPane) this.getRoot();

        // Error label
        Label errorLabel = new Label("");
        grid.add(errorLabel, 0, 6);
        //newclass button
        Button NewClassButton = new Button("New Class");
        grid.add(NewClassButton, 0, 1);
        NewClassButton.setOnAction(e -> {
            primaryStage.setScene(new CreateClass(primaryStage, instructorid));
        });
        // Full name label and field
        Label nameLabel = new Label("Class Name:");
        grid.add(nameLabel, 0, 2);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 2);

        // Username label and field
        Label usernameLabel = new Label("Room:");
        grid.add(usernameLabel, 0, 3);
        TextField usernameTextField = new TextField();
        grid.add(usernameTextField, 1, 3);

        // Change Password label and field
        Label passwordLabel = new Label("Start Time:");
        grid.add(passwordLabel, 0, 4);
        TextField passwordTextField = new PasswordField();
        grid.add(passwordTextField, 1, 4);
        // Confirm Password label and field
        Label confirmPasswordLabel = new Label("End Time:");
        grid.add(confirmPasswordLabel, 0, 5);
        TextField confirmPasswordTextField = new PasswordField();
        grid.add(confirmPasswordTextField, 1, 5);

        Label price = new Label("Price:");
        grid.add(price, 0, 6);
        TextField priceField = new TextField();
        grid.add(priceField, 1, 6);

        Button AddEventButton = new Button("Add Event");
        grid.add(AddEventButton, 0, 7);
        AddEventButton.setOnAction(e -> {});

    }
}
