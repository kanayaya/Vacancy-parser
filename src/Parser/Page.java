package Parser;

import javafx.scene.layout.AnchorPane;

public abstract class Page {
    public final String name;

    public Page(String name) {
        this.name = name;
    }

    public abstract AnchorPane getView();
}
