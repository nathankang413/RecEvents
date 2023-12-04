package deprecated.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static app.Constants.WINDOW_HEIGHT;
import static app.Constants.WINDOW_WIDTH;

public class TitledPage extends Scene {
    protected Stage primaryStage;

    public TitledPage(Stage primaryStage, String title) {
        super(new GridPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
        this.primaryStage = primaryStage;

        // set up grid
        GridPane grid = (GridPane) this.getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Title Text
        Text sceneTitle = new Text(title);
        sceneTitle.setFont(Font.font("Tahoma", 20));
        grid.add(sceneTitle, 0, 0, 3,1);
    }
}
