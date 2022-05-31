package Parser;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Pager implements Viewable {
    private final List<Viewable> components;

    public Pager(Element block) {
        components = new ArrayList<>();

        Elements firstButton = block.getElementsByAttributeValue("data-qa", "first-page");
        if (! firstButton.isEmpty()) {
            components.add(new PagerButton(firstButton.get(0).attr("href"), firstButton.get(0).text()));
        }

        Elements numberButtons = block.getElementsByAttributeValue("data-qa", "pager-page");
        for (int i = 0; i < numberButtons.size(); i++) {
            if ("span".equals(numberButtons.get(i).nodeName())) {
                components.add(getPressedButton(numberButtons.get(i).text()));
                continue;
            }
            if (i == numberButtons.size() - 1 && block.getElementsByAttributeValue("data-qa", "pager-block-dots").size() == 1) {
                components.add(getPressedButton("···"));
            }
            components.add(new PagerButton(numberButtons.get(i).attr("href"), numberButtons.get(i).text()));
        }

        Elements nextPageButton = block.getElementsByAttributeValue("data-qa", "pager-next");
        if (! nextPageButton.isEmpty()) {
            components.add(new PagerButton(nextPageButton.get(0).attr("href"), nextPageButton.get(0).text()));
        }

    }

    private Viewable getPressedButton(String text) {
        Label root = new Label(text);
        root.setAlignment(Pos.CENTER);
        root.setMinSize(60, 60);
        return () -> root;
    }

    @Override
    public Parent getView() {
        Parent[] buttons = components.stream().map(Viewable::getView).toArray(Parent[]::new);
        return new HBox(buttons);
    }
}
