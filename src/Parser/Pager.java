package Parser;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;

public final class Pager implements Viewable {
    private final List<Viewable> components;

    public Pager(ArrayList<Viewable> components) {
        this.components = components;
    }

    @Override
    public Region getView() {
        Parent[] buttons = components.stream().map(Viewable::getView).toArray(Parent[]::new);
        HBox hBox = new HBox(buttons);
        hBox.setPadding(new Insets(0, 0, 25, 0));
        return hBox;
    }
}
