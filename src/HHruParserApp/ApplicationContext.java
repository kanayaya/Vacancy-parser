package HHruParserApp;

import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationContext {

    private static Stage cachedStage;

    public static Stage getStage() {
        return cachedStage;
    }
    public static void setCachedStage(Stage stage) {
        if (cachedStage == null) {
            cachedStage = stage;
        }
    }

    public static void setRoot(String name) {
        cachedStage.getScene().setRoot(ApplicationCache.getRoot(name));
    }
    public static void setRoot(Parent root) {
        cachedStage.getScene().setRoot(root);
    }

}
