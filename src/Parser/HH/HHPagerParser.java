package Parser.HH;

import Parser.Pager;
import Parser.PagerButton;
import Parser.Viewable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HHPagerParser {
    public static Pager getPager(Element block) {
        ArrayList<Viewable> components = new ArrayList<>();

        Elements firstButton = block.getElementsByAttributeValue("data-qa", "first-page");
        if (! firstButton.isEmpty()) {
            Element firstButtonBlock = firstButton.get(0);
            components.add(getButton(firstButtonBlock, 120));
            components.add(getGap());
        }

        Elements numberButtons = block.getElementsByAttributeValue("data-qa", "pager-page");
        for (int i = 0; i < numberButtons.size(); i++) {
            Element numberButton = numberButtons.get(i);
            if ("span".equals(numberButton.nodeName())) {
                components.add(getFillerElement(numberButton.text()));
                continue;
            }
            if (i == numberButtons.size() - 1 && block.getElementsByAttributeValue("data-qa", "pager-block-dots").size() == 1) {
                components.add(getFillerElement("···"));
            }
            components.add(getButton(numberButton, 60));
        }

        Elements nextPageButton = block.getElementsByAttributeValue("data-qa", "pager-next");
        if (! nextPageButton.isEmpty()) {
            components.add(getGap());
            Element nextPageButtonBlock = nextPageButton.get(0);
            components.add(getButton(nextPageButtonBlock, 120));
        }
    return new Pager(components);
    }

    private static PagerButton getButton(Element buttonBlock, double width) {
        return new PagerButton(" https://hh.ru/" + buttonBlock.attr("href"), buttonBlock.text(), width);
    }

    private static Viewable getGap() {
        Label root = new Label("");
        root.setMinSize(30, 60);
        return () -> root;
    }

    private static Viewable getFillerElement(String text) {
        Label root = new Label(text);
        root.setAlignment(Pos.CENTER);
        root.setMinSize(60, 60);
        root.setBorder(new Border(new BorderStroke(Paint.valueOf("#b0b0b0"), BorderStrokeStyle.SOLID, new CornerRadii(3.0), BorderStroke.THIN)));
        return () -> root;
    }
}
