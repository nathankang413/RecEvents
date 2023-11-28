package app;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class ScreenController {
    private static HashMap<String, Scene> sceneMap = new HashMap<>();
    public static Stage mainStage;

//    public ScreenController(Scene main) {
//        this.main = main;
//    }

    public static void addScreen(String name, Scene scene) {
        sceneMap.put(name, scene);
    }

    public static void removeScene(String name) {
        sceneMap.remove(name);
    }

    public static void activate(String name) {
        mainStage.setScene(sceneMap.get(name));
        mainStage.show();
    }

//    public static Pane getPane(String name){
//        sc
//    }
}
