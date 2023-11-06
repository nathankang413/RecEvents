package app;

import app.pages.LoginPage;
import javafx.application.Application;
import javafx.stage.Stage;

public class RecEventsApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("RecEvents");
        primaryStage.setScene(new LoginPage(primaryStage));
        primaryStage.show();
    }
}
