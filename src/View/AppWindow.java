package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AppWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        GridPane root = (GridPane) loadFXML("FXML/mainPage.fxml");
        Scene scene = new Scene(root);
        primaryStage.setTitle("HHruParser");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    private Parent loadFXML(String fileName) throws IOException {
            return FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource(fileName)));
    }

    public static void launchApplication(String[] args) {
        launch(args);
    }
}
