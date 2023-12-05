package app;

import controllers.UserPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

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

    public static void changeUserPage(String name, String fxml, int student_id) {
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
        controller.setUID(student_id);

        ScreenController.addScreen(name, scene);
        ScreenController.activate(name);
    }
}
