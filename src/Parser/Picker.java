package Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.UnknownHostException;

public class Picker {
    public static Document repeatedlyGetHtml(String link)  {
        int counter = 1;
        System.out.println("going to " + link);
        Document wholePage;
        while (counter < 20) {
            try {
                wholePage = Jsoup.connect(link).get();
                System.out.println("Successfully got HTML elements!");
                return wholePage;
            }
            catch (UnknownHostException e) {
                System.out.println("MissingUrl  " + counter);
                counter += 1;
                sleep(1000);
            } catch (IOException e) {
                throw new RuntimeException("Internet problem", e);
            }
        }
        throw new RuntimeException("Error: Connection timed out");
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
