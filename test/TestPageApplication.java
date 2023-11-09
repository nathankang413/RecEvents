import app.pages.student.*;
import backend.SqlConnector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestPageApplication extends Application {
    public static void main(String[] args) {
        SqlConnector.init();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("RecEvents");
        SqlConnector.init();

        // NOTE: choose the page that you want to test
        Scene page = new NewStudentPage(primaryStage);

        primaryStage.setScene(page);
        primaryStage.show();
    }
}