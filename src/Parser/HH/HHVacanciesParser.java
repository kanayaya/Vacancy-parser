package Parser.HH;


import Parser.Picker;
import Parser.VacanciesParser;
import Parser.Viewable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HHVacanciesParser extends VacanciesParser {

    public HHVacanciesParser(String link) {
        super(link);
    }

    @Override
    public List<Viewable> getVacancies() {
        return getVacanciesFrom(Picker.repeatedlyGetHtml(getLinkWithSearchText(link)));
    }

    @Override
    public List<Viewable> getVacanciesFrom(Document wholePage) {
        Elements vacancyBlocks = wholePage.getElementsByAttributeValue("class", "vacancy-serp-item");
        return HHVacanciesParser.makeVacanciesArray(vacancyBlocks);
    }

    private static List<Viewable> makeVacanciesArray(Elements vacancyBlocks) {
        ArrayList<Viewable> vacancies = new ArrayList<>(20);
        for (Element vacancyBlock: vacancyBlocks) {
            vacancies.add(HHVacanciesParser.getVacancyBlock(vacancyBlock));
        }
        return vacancies;
    }

    private static HHVacancyBlock getVacancyBlock(Element vacancyBlock) {
        return new HHVacancyBlock(
                vacancyBlock.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text(),
                vacancyBlock.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"),
                getSalary(vacancyBlock),
                vacancyBlock.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text(),
                getDescription(vacancyBlock),
                vacancyBlock.getElementsByAttributeValue("class", "vacancy-serp-item-logo").attr("src")
        );
    }
    private static String getDescription(Element block) {
        String responsibility = block.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy_snippet_responsibility").text();
        String requirement = block.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy_snippet_requirement").text();
        return "Обязанности:  " + responsibility + "\nТребования  :  " + requirement;
    }
    private static String getSalary(Element block) {
        if (isEmpty(block)) {
            return "Зарплата не указана";
        }
        return block.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text();
    }
    private static boolean isEmpty(Element block) {
        return block.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text().equals("");
    }
    private static String getLinkWithSearchText(String text) {
        text = text.trim().replace(' ', '+');
        return "https://hh.ru/search/vacancy?clusters=true&text=".concat(text.concat("&enable_snippets=true&L_save_area=True&area=1124&customDomain=1"));
    }
}
