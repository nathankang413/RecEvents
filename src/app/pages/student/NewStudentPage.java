package app.pages.student;

import app.pages.WelcomePage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import backend.queries.StudentValidation;

import static app.Constants.WINDOW_HEIGHT;
import static app.Constants.WINDOW_WIDTH;

public class NewStudentPage extends Scene {

    public NewStudentPage(Stage primaryStage) {
        super(new GridPane(), WINDOW_WIDTH, WINDOW_HEIGHT);

        GridPane grid = (GridPane) this.getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Title Text
        Text sceneTitle = new Text("New Student");
        sceneTitle.setFont(Font.font("Tahoma", 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

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
        signupButton.setOnAction(e -> {
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
            // TODO: name field
            if (StudentValidation.insertNewCreds("Example name", usernameTextField.getText(), passwordTextField.getText())) {
                primaryStage.setScene(new StudentHomePage(primaryStage));
            } else {
                errorLabel.setText("Error creating account");
            }
        });

        // Existing User Button
        Button newUserButton = new Button("Existing User");
        grid.add(newUserButton, 1, 5);
        newUserButton.setOnAction(e -> {
            primaryStage.setScene(new StudentLoginPage(primaryStage));
        });

        // Back Button
        Button backButton = new Button("Back");
        grid.add(backButton, 0, 4);
        backButton.setOnAction(e -> {
            primaryStage.setScene(new WelcomePage(primaryStage));
        });
    }
}
