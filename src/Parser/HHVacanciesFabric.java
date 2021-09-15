package Parser;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HHVacanciesFabric extends VacanciesFabric{

    public HHVacanciesFabric(String searchText) throws InterruptedException {
        super(getLinkWithSearchText(searchText));
    }
    @Override
    public ArrayList<VacancyBlock> getVacanciesFrom(Document wholePage) {
        Elements vacancyBlocks = wholePage.getElementsByAttributeValue("class", "vacancy-serp-item");
        return HHVacanciesFabric.makeVacanciesArray(vacancyBlocks);
    }

    private static ArrayList<VacancyBlock> makeVacanciesArray(Elements vacancyBlocks) {
        ArrayList<VacancyBlock> vacancies = new ArrayList<>(20);
        for (Element vacancyBlock: vacancyBlocks) {
            vacancies.add(HHVacanciesFabric.getVacancyBlock(vacancyBlock));
        }
        return vacancies;
    }

    private static HHVacancyBlock getVacancyBlock(Element vacancyBlock) {
        return new HHVacancyBlock(
                vacancyBlock.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text(),
                vacancyBlock.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"),
                getSalary(vacancyBlock),
                vacancyBlock.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text(),
                getDescription(vacancyBlock)
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
        String link = "https://hh.ru/search/vacancy?clusters=true&text=".concat(text.concat("&enable_snippets=true&L_save_area=True&area=1124&customDomain=1"));
        System.out.println("Going to    " + link);
        return link;
    }
}
