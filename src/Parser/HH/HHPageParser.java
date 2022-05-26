package Parser.HH;

import Parser.PageParser;
import Parser.Picker;
import Parser.Viewable;
import org.jsoup.nodes.Document;

public class HHPageParser extends PageParser {
    private final String link;
    public HHPageParser(HHVacancyBlock block) throws InterruptedException {
        this.link = block.link;
    }

    @Override
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
