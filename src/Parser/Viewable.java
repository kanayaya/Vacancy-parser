package Parser;


import javafx.scene.Parent;
import javafx.scene.layout.Region;

@FunctionalInterface
public interface Viewable {
    Region getView();
}
