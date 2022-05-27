package Parser.HH;

import Parser.Picker;
import Parser.Viewable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Objects;

public class HHPageParser {
    private final String link;
    public HHPageParser(String link) {
        this.link = link;
    }

    public Viewable getPage()  {
        Document wholePage = Picker.repeatedlyGetHtml(link);
        return new HHPage(
                wholePage.getElementsByAttributeValue("data-qa", "vacancy-title").text(),
                wholePage.getElementsByAttributeValue("data-qa", "vacancy-salary").text(),
                "",
                "",
                "",
                "",
                getDescription(wholePage),
                "",
                "");
    }

    private String getDescription(Document wholePage) {
        StringBuilder result = new StringBuilder();

        for (Element element : wholePage.getElementsByAttributeValue("data-qa", "vacancy-description").get(0).children()) {
            if ("strong".equals(element.nodeName()) || element.children().size() > 0 && "strong".equals(element.child(0).nodeName())) {
                result.append("\n");
            }
            result.append(element.text());
            result.append("\n");
        }
        return result.toString();
    }
}
