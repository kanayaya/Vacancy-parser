package Parser;

import javafx.scene.layout.GridPane;

public abstract class VacancyBlock {
    public final String name;
    public final String link;

    public VacancyBlock(String name, String link) {
        this.name = name;
        this.link = link;
    }
    public abstract GridPane getView();
}
