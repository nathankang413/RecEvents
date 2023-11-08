package app.pages;

import app.pages.instructor.InstructorLoginPage;
import app.pages.student.StudentLoginPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static app.Constants.WINDOW_HEIGHT;
import static app.Constants.WINDOW_WIDTH;

public class WelcomePage extends Scene {
    private Stage primaryStage;

    public WelcomePage(Stage primaryStage) {
        super(new GridPane(), WINDOW_WIDTH, WINDOW_HEIGHT);

        this.primaryStage = primaryStage;
        GridPane grid = (GridPane) this.getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Title Text
        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", 20));
        grid.add(sceneTitle, 0, 0);

        // Instructor Button
        Button instructorButton = new Button("Instructor");
        grid.add(instructorButton, 0, 1);
        instructorButton.setOnAction(e -> {
            primaryStage.setScene(new InstructorLoginPage(primaryStage));
        });

        // Student Button
        Button studentButton = new Button("Student");
        grid.add(studentButton, 0, 2);
        studentButton.setOnAction(e -> {
            primaryStage.setScene(new StudentLoginPage(primaryStage));
        });

    }

}
