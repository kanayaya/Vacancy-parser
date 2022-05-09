package View;

import HHruParserApp.ApplicationCache;
import HHruParserApp.ApplicationContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        Scene scene = ApplicationCache.makeScene("mainPage.fxml");

        primaryStage.setTitle("HHruParser");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        ApplicationContext.setCachedStage(primaryStage);
        primaryStage.show();
    }

    public static void launchApplication(String[] args) {
        launch(args);
    }
}
