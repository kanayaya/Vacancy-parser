package View;

import javafx.scene.Node;
import javafx.stage.Stage;

class ControllerStaticUtils {
    protected static Stage getStageOf(Node node) {
        return (Stage) node.getScene().getWindow();
    }
}
