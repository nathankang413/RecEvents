package app.pages.instructor;

import app.components.TitledPage;
import app.pages.WelcomePage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InstructorLoginPage extends TitledPage {

    public InstructorLoginPage(Stage primaryStage) {
        super(primaryStage, "Instructor Login");
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

        // Login Button
        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 3);
        loginButton.setOnAction(e -> {
//            primaryStage.setScene(new InstructorMainPage(primaryStage));
        });

        // Back Button
        Button backButton = new Button("Back");
        grid.add(backButton, 0, 3);
        backButton.setOnAction(e -> {
            primaryStage.setScene(new WelcomePage(primaryStage));
        });
    }
}