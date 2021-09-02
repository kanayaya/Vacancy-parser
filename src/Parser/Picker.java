package Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Picker {

    public static Document getPageOf(HHVacancyBlock vacancy) throws InterruptedException {
        return repeatedlyGetPage(vacancy.link);
    }
    public static Document repeatedlyGetPage(String link) throws InterruptedException {
        Document wholePage;
        while (true) {
            try {
                wholePage = Jsoup.connect(link).get();
                break;
            }
            catch (Exception e) {
                System.out.println("MissingUrl");
                Thread.sleep(1000);
            }
        }
        System.out.println("Successfully got HTML elements!\n\n");
        return wholePage;
    }
}
