package Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.UnknownHostException;

public class Picker {
    public static Document repeatedlyGetHtml(String link) throws InterruptedException {
        int counter = 1;
        System.out.println("going to " + link);
        Document wholePage;
        while (true) {
            try {
                System.out.println(link);
                wholePage = Jsoup.connect(link).get();
                break;
            }
            catch (UnknownHostException e) {
                System.out.println("MissingUrl  " + counter);
                counter += 1;
                Thread.sleep(1000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Successfully got HTML elements!");
        return wholePage;
    }
}
