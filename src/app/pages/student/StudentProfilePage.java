package app.pages.student;

import app.components.TitledPage;
import backend.queries.StudentProfile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentProfilePage extends TitledPage {
    public StudentProfilePage(Stage primaryStage, int student_id) {
        super(primaryStage, "Student Profile");
        GridPane grid = (GridPane) this.getRoot();

        StudentProfile data = new StudentProfile(student_id);

        // Error label
        Label errorLabel = new Label("");
        grid.add(errorLabel, 0, 5);

        // Full name label and field
        Label nameLabel = new Label("Full Name:");
        grid.add(nameLabel, 0, 1);
        TextField nameTextField = new TextField();
        nameTextField.setText(data.getFull_name());
        grid.add(nameTextField, 1, 1);
        // Change name button
        Button nameButton = new Button("Change Name");
        grid.add(nameButton, 2, 1);
        nameButton.setOnAction(e -> {
            if (data.changeName(nameTextField.getText())) {
                errorLabel.setText("Successfully changed name");
            } else {
                errorLabel.setText("Error changing name");
            }
        });

        // Username label and field
        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 2);
        TextField usernameTextField = new TextField();
        usernameTextField.setText(data.getUsername());
        grid.add(usernameTextField, 1, 2);
        // Change username button
        Button usernameButton = new Button("Change Username");
        grid.add(usernameButton, 2, 2);
        usernameButton.setOnAction(e -> {
            if (data.changeUsername(usernameTextField.getText())) {
                errorLabel.setText("Successfully changed username");
            } else {
                errorLabel.setText("Error changing username");
            }
        });

        // Change Password label and field
        Label passwordLabel = new Label("Change Password:");
        grid.add(passwordLabel, 0, 3);
        TextField passwordTextField = new PasswordField();
        grid.add(passwordTextField, 1, 3);
        // Confirm Password label and field
        Label confirmPasswordLabel = new Label("Confirm Password:");
        grid.add(confirmPasswordLabel, 0, 4);
        TextField confirmPasswordTextField = new PasswordField();
        grid.add(confirmPasswordTextField, 1, 4);
        // Change password button
        Button passwordButton = new Button("Change Password");
        grid.add(passwordButton, 2, 4);
        passwordButton.setOnAction(e -> {
            if (data.changePassword(passwordTextField.getText(), confirmPasswordTextField.getText())) {
                errorLabel.setText("Successfully changed password");
            } else {
                errorLabel.setText("Error changing password");
            }
        });

        // Back button
        Button backButton = new Button("Back");
        grid.add(backButton, 0, 6);
        backButton.setOnAction(e -> {
            primaryStage.setScene(new StudentHomePage(primaryStage, student_id));
        });
    }

}
