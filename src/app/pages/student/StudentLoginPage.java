package app.pages.student;

import app.components.TitledPage;
import app.pages.WelcomePage;
import backend.queries.StudentSignupsView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import backend.queries.StudentValidation;

public class StudentLoginPage extends TitledPage {

    public StudentLoginPage(Stage primaryStage) {
        super(primaryStage, "Student Login");
        GridPane grid = (GridPane) this.getRoot();

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

        // Alert text
        Label alertText = new Label("");
        grid.add(alertText, 1, 5);

        // Login Button
        Button loginButton = new Button("Login");
        loginButton.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(loginButton, 1, 3);
        loginButton.setOnAction(e -> {
            int student_id = StudentValidation.validateLoginCreds(usernameTextField.getText(), passwordTextField.getText());
            if (student_id >= 0) {
                primaryStage.setScene(new StudentHomePage(primaryStage, student_id));
            } else {
                alertText.setText("Invalid username or password");
            }
        });

        // New User Button
        Button newUserButton = new Button("New User");
        grid.add(newUserButton, 1, 4);
        newUserButton.setOnAction(e -> primaryStage.setScene(new NewStudentPage(primaryStage)));

        // Back Button
        Button backButton = new Button("Back");
        grid.add(backButton, 0, 3);
        backButton.setOnAction(e -> primaryStage.setScene(new WelcomePage(primaryStage)));
    }
}
