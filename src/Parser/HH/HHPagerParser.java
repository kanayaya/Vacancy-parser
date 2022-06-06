package Parser.HH;

import Parser.Pager;
import Parser.PagerButton;
import Parser.Viewable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HHPagerParser {
    public static Pager getPager(Element block) {
        ArrayList<Viewable> components = new ArrayList<>();

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
    return new Pager(components);
    }

    private static Viewable getPressedButton(String text) {
        Label root = new Label(text);
        root.setAlignment(Pos.CENTER);
        root.setMinSize(60, 60);
        return () -> root;
    }
}
