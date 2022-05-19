package HHruParserApp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApplicationCache {
    static final Map<String, FXMLLoader> cachedLoaders = new HashMap<>();
    static String partPath = "../View/FXML/";

    public static Parent getRoot(String name) {
        if (getCached(name).getRoot() == null) {
            try {
                return cachedLoaders.get(name).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return cachedLoaders.get(name).getRoot();
    }

    public static <T> T getCachedController(String name) {
        return getCached(name).getController();
    }

    public static FXMLLoader getCached(String name) {
        if (cachedLoaders.get(name) == null) {
            FXMLLoader loader = new FXMLLoader(ApplicationContext.class.getResource(partPath + name));
            cachedLoaders.put(name, loader);
        }

        return cachedLoaders.get(name);
    }

    public static Scene makeScene(String name) {
        return new Scene(getRoot(name));
    }
}
