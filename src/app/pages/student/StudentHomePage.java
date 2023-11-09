package app.pages.student;

import app.components.TitledPage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentHomePage extends TitledPage {

    public StudentHomePage(Stage primaryStage) {
        super(primaryStage, "Student Home");
        GridPane grid = (GridPane) this.getRoot();

        // Event Search Button
        Button eventSearchButton = new Button("Event Search");
        grid.add(eventSearchButton, 0, 1);
        eventSearchButton.setOnAction(e -> primaryStage.setScene(new EventSearchPage(primaryStage)));

        // Profile button
        Button profileButton = new Button("Profile");
        grid.add(profileButton, 0, 2);
        profileButton.setOnAction(e -> {
//            primaryStage.setScene(new StudentProfilePage(primaryStage));
        });

    }
}
