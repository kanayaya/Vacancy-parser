package HHruParserApp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApplicationCache {
    static final Map<String, FXMLLoader> cachedLoaders = new HashMap<>();
    static String partPath = "../View/FXML/";

    private static FXMLLoader getCached(String name) {
        if (cachedLoaders.get(name) == null) {
            FXMLLoader loader = new FXMLLoader(ApplicationContext.class.getResource(partPath + name));
            tryToLoad(loader);
            cachedLoaders.put(name, loader);
        }

        return cachedLoaders.get(name);
    }

    private static void tryToLoad(FXMLLoader loader) {
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Region getRoot(String name) {
        return getCached(name).getRoot();
    }

    public static <T> T getCachedController(String name) {
        return getCached(name).getController();
    }

    public static Scene makeScene(String name) {
        return new Scene(getRoot(name));
    }
}
