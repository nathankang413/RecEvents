package app.pages.instructor;

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

import static app.Constants.WINDOW_HEIGHT;
import static app.Constants.WINDOW_WIDTH;


public class InstructorLoginPage extends Scene {

    public InstructorLoginPage(Stage primaryStage) {
        super(new GridPane(), WINDOW_WIDTH, WINDOW_HEIGHT);

        GridPane grid = (GridPane) this.getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        // Title Text
        Text sceneTitle = new Text("Instructor Login");
        sceneTitle.setFont(Font.font("Tahoma", 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        // Username Label
        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 1);

        // Username Text Field
        TextField usernameTextField = new TextField();
        grid.add(usernameTextField, 1, 1);

        // Password Label
        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);

        // Password Text Field
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
