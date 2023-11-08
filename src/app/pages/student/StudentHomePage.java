package app.pages.student;

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

public class StudentHomePage extends Scene {

    public StudentHomePage(Stage primaryStage) {
        super(new GridPane(), WINDOW_WIDTH, WINDOW_HEIGHT);

        GridPane grid = (GridPane) this.getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        // Title text
        Text sceneTitle = new Text("Student Page");
        sceneTitle.setFont(Font.font("Tahoma", 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        // Event Search Button
        Button eventSearchButton = new Button("Event Search");
        grid.add(eventSearchButton, 0, 1);
        eventSearchButton.setOnAction(e -> {
            primaryStage.setScene(new EventsPage(primaryStage));
        });

        // Profile button
        Button profileButton = new Button("Profile");
        grid.add(profileButton, 0, 2);
        profileButton.setOnAction(e -> {
//            primaryStage.setScene(new StudentProfilePage(primaryStage));
        });

    }
}
