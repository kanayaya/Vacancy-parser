package Utils;

import javafx.scene.Node;
import javafx.stage.Stage;

public class ControllerStaticUtils {
    public static Stage getStageOf(Node node) {
        return (Stage) node.getScene().getWindow();
    }
}
