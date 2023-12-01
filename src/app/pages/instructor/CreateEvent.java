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
    public CreateEvent(Stage primaryStage) {
        super(primaryStage, "Create New Event");
        GridPane grid = (GridPane) this.getRoot();


        Button NewClassButton = new Button("Change Username");
        grid.add(NewClassButton, 0, 1);
        NewClassButton.setOnAction(e -> {});

        // Error label
        Label errorLabel = new Label("");
        grid.add(errorLabel, 0, 6);

        // Full name label and field
        Label nameLabel = new Label("Full Name:");
        grid.add(nameLabel, 0, 2);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 2);
        // Change name button
        Button nameButton = new Button("Change Name");
        grid.add(nameButton, 2, 2);
        nameButton.setOnAction(e -> {
        });

        // Username label and field
        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 3);
        TextField usernameTextField = new TextField();
        grid.add(usernameTextField, 1, 3);
        // Change username button
        Button usernameButton = new Button("Change Username");
        grid.add(usernameButton, 2, 3);
        usernameButton.setOnAction(e -> {
        });

        // Change Password label and field
        Label passwordLabel = new Label("Change Password:");
        grid.add(passwordLabel, 0, 4);
        TextField passwordTextField = new PasswordField();
        grid.add(passwordTextField, 1, 4);
        // Confirm Password label and field
        Label confirmPasswordLabel = new Label("Confirm Password:");
        grid.add(confirmPasswordLabel, 0, 5);
        TextField confirmPasswordTextField = new PasswordField();
        grid.add(confirmPasswordTextField, 1, 5);
        // Change password button
        Button passwordButton = new Button("Change Password");
        grid.add(passwordButton, 2, 5);
        passwordButton.setOnAction(e -> {
        });

    }
}
