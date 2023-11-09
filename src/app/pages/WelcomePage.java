package app.pages;

import app.components.TitledPage;
import app.pages.instructor.InstructorLoginPage;
import app.pages.student.StudentLoginPage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class WelcomePage extends TitledPage {

    public WelcomePage(Stage primaryStage) {
        super(primaryStage, "Welcome");
        GridPane grid = (GridPane) this.getRoot();

        // Instructor Button
        Button instructorButton = new Button("Instructor");
        grid.add(instructorButton, 0, 1);
        instructorButton.setOnAction(e -> primaryStage.setScene(new InstructorLoginPage(primaryStage)));

        // Student Button
        Button studentButton = new Button("Student");
        grid.add(studentButton, 0, 2);
        studentButton.setOnAction(e -> primaryStage.setScene(new StudentLoginPage(primaryStage)));
    }
}
