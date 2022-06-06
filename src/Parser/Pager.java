package Parser;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class Pager implements Viewable {
    private final List<Viewable> components;

    public Pager(ArrayList<Viewable> components) {
        this.components = components;
    }

    @Override
    public Parent getView() {
        Parent[] buttons = components.stream().map(Viewable::getView).toArray(Parent[]::new);
        return new HBox(buttons);
    }
}
