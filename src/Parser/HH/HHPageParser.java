package Parser.HH;

import Parser.Page;
import Parser.PageParser;
import Parser.Picker;
import org.jsoup.nodes.Document;

public class HHPageParser extends PageParser {
    private final String link, name;
    public HHPageParser(HHVacancyBlock block) throws InterruptedException {
        this.link = block.link;
        this.name = block.name;
    }

    @Override
    public Page getPage()  {
        try {
            Document wholePage = Picker.repeatedlyGetHtml(link);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HHPage(
                name,
                "noSalary",
                "",
                "",
                "",
                "",
                "",
                "",
                "");
    }
}
