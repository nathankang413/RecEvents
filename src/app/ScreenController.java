package app;

import controllers.UserPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

import controllers.instructor.EventViewPageController;

public class ScreenController {
    private static HashMap<String, Scene> sceneMap = new HashMap<>();
    public static Stage mainStage;

    public static void addScreen(String name, Scene scene) {
        sceneMap.put(name, scene);
    }

    public static void activate(String name) {
        mainStage.setScene(sceneMap.get(name));
        mainStage.show();
    }

    public static void changePage(String name, String fxml) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ScreenController.class.getResource(fxml));
        Parent root;
        try {
            root = loader.load();
        }
        catch(Exception exception){
            System.out.println("Failed to load fxml: " + fxml);
            exception.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);

        ScreenController.addScreen(name, scene);
        ScreenController.activate(name);
    }

    public static void changeUserPage(String name, String fxml, int user_id) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ScreenController.class.getResource(fxml));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException exception) {
            System.out.println("Failed to load fxml: " + fxml);
            exception.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);
        UserPageController controller = loader.getController();
        controller.setUID(user_id);

        ScreenController.addScreen(name, scene);
        ScreenController.activate(name);
    }

    public static void changeToEventViewPage(int instructor_id, int event_id) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ScreenController.class.getResource("/fxml/instructor/EventViewPage.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException exception) {
            System.out.println("Failed to load fxml: /fxml/instructor/EventViewPage.fxml");
            exception.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);
        EventViewPageController controller = loader.getController();
        controller.setIDs(instructor_id, event_id);

        ScreenController.addScreen("Event View Page", scene);
        ScreenController.activate("Event View Page");
    }
}
