package app.pages.student;

import app.components.TitledPage;
import app.pages.WelcomePage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import backend.queries.StudentValidation;

public class NewStudentPage extends TitledPage {

    public NewStudentPage(Stage primaryStage) {
        super(primaryStage, "New Student");
        GridPane grid = (GridPane) this.getRoot();

        // Name label and field
        Label nameLabel = new Label("Full Name:");
        grid.add(nameLabel, 0, 0);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 0);

        // Username Label and Field
        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 1);
        TextField usernameTextField = new TextField();
        grid.add(usernameTextField, 1, 1);

        // Password Label and Field
        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);
        PasswordField passwordTextField = new PasswordField();
        grid.add(passwordTextField, 1, 2);

        // Confirm Password Label and Field
        Label confirmPasswordLabel = new Label("Confirm Password:");
        grid.add(confirmPasswordLabel, 0, 3);
        PasswordField confirmPasswordField = new PasswordField();
        grid.add(confirmPasswordField, 1, 3);

        // Error Label
        Label errorLabel = new Label("");
        grid.add(errorLabel, 1, 6);

        // Signup Button
        Button signupButton = new Button("Sign Up");
        signupButton.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(signupButton, 1, 4);
        signupButton.setOnAction(e -> { // TODO: abstract this into a method
            if (usernameTextField.getLength() == 0 || passwordTextField.getLength() == 0 || confirmPasswordField.getLength() == 0) {
                errorLabel.setText("Please fill out all fields");
                return;
            }
            if (StudentValidation.usernameExists(usernameTextField.getText())) {
                errorLabel.setText("Username already exists");
                return;
            }
            if (!passwordTextField.getText().equals(confirmPasswordField.getText())) {
                errorLabel.setText("Passwords do not match");
                return;
            }
            int student_id = StudentValidation.insertNewCreds(nameTextField.getText(), usernameTextField.getText(), passwordTextField.getText());
            if (student_id >= 0) {
                primaryStage.setScene(new StudentHomePage(primaryStage, student_id));
            } else {
                errorLabel.setText("Error creating account");
            }
        });

        // Existing User Button
        Button existingUserButton = new Button("Existing User");
        grid.add(existingUserButton, 1, 5);
        existingUserButton.setOnAction(e -> primaryStage.setScene(new StudentLoginPage(primaryStage)));

        // Back Button
        Button backButton = new Button("Back");
        grid.add(backButton, 0, 4);
        backButton.setOnAction(e -> primaryStage.setScene(new WelcomePage(primaryStage)));
    }
}
