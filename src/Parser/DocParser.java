package Parser;

import javafx.scene.layout.GridPane;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class DocParser {

    public static ArrayList<GridPane> getVacancies(String link) throws InterruptedException {
        ArrayList<GridPane> vacancies = new ArrayList<>(20);
        System.out.println("Going to picker...");
        Document wholePage = Picker.repeatedlyGetPage(link);
        Elements vacancyBlocks = wholePage.getElementsByAttributeValue("class", "vacancy-serp-item");
        for (Element vacancyBlock: vacancyBlocks) {
            vacancies.add(HHVacancyFabric.getVacancyBlock(vacancyBlock).getView());
            for (Element element: vacancyBlock.getElementsByAttribute("data-qa")) {
                System.out.println(element.attr("data-qa") + ':');
                System.out.println(element.text());
            }
            System.out.println("\n\n");
        }
        return vacancies;
    }

}
