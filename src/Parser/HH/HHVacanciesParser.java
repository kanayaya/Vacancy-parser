package Parser.HH;


import Parser.Pager;
import Parser.Picker;
import Parser.VacanciesParser;
import Parser.Viewable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HHVacanciesParser implements VacanciesParser {
    private final String link;

    public HHVacanciesParser(String link) {
       this.link = link;
    }

    @Override
    public List<Viewable> getVacancies() {
        Document wholePage = Picker.repeatedlyGetHtml(link);
        Elements vacancyBlocks = wholePage.getElementsByAttributeValue("class", "vacancy-serp-item");
        List<Viewable> vacancies = getVacanciesFrom(vacancyBlocks);
        if (! wholePage.getElementsByAttributeValue("data-qa", "pager-block").isEmpty()) {
            vacancies.add(getPager(wholePage.getElementsByAttributeValue("data-qa", "pager-block").get(0)));
        }
        return vacancies;
    }

    private List<Viewable> getVacanciesFrom(Elements vacancyBlocks) {
        return vacancyBlocks.stream().
                map(HHVacanciesParser::getVacancyBlock).
                collect(Collectors.toList());
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

    private Viewable getPager(Element block) {
        return new Pager(block);
    }
    public static String getLinkWithSearchText(String text) {
        text = text.trim().replace(' ', '+');
        return "https://hh.ru/search/vacancy?clusters=true&text=".concat(text.concat("&enable_snippets=true&L_save_area=True&area=1124&customDomain=1"));
    }
}
