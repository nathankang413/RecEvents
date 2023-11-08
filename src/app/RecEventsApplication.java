package app;

import app.pages.WelcomePage;
import backend.SqlConnector;
import javafx.application.Application;
import javafx.stage.Stage;

public class RecEventsApplication extends Application {

    public static void main(String[] args) {
        SqlConnector.init();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("RecEvents");
        primaryStage.setScene(new WelcomePage(primaryStage));
        primaryStage.show();
    }
}
