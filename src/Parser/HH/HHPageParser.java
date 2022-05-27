package Parser.HH;

import Parser.Picker;
import Parser.Viewable;
import org.jsoup.nodes.Document;

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
                wholePage.getElementsByAttributeValue("data-qa", "vacancy-description").text(),
                "",
                "");
    }
}
