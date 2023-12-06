package app;

import database.SqlConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RecEventsApplication extends Application {
    public ScreenController screenController;

    public static void main(String[] args) {
        SqlConnector.init();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("RecEvents");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/WelcomePage.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            ScreenController.mainStage = primaryStage;
            ScreenController.addScreen("Welcome Page", scene);
            ScreenController.activate("Welcome Page");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
