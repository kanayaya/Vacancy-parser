package test;

import Parser.HH.HHVacanciesParser;

public class HHVacanciesParserTest {
    public static void main(String[] args) {
        new HHVacanciesParser("https://angarsk.hh.ru/search/vacancy?text=&salary=&clusters=true&area=1124&ored_clusters=true&enable_snippets=true&page=4&hhtmFrom=vacancy_search_list").
                getVacancies();
        System.out.println("____________________________________________\n");

        new HHVacanciesParser("https://angarsk.hh.ru/search/vacancy?text=&salary=&clusters=true&area=1124&ored_clusters=true&enable_snippets=true&page=0&hhtmFrom=vacancy_search_list").
                getVacancies();
        System.out.println("____________________________________________\n");

        new HHVacanciesParser("https://angarsk.hh.ru/search/vacancy?text=&salary=&clusters=true&area=1124&ored_clusters=true&enable_snippets=true&page=39&hhtmFrom=vacancy_search_list").
                getVacancies();
        System.out.println("____________________________________________\n");

        new HHVacanciesParser("https://angarsk.hh.ru/search/vacancy?text=&salary=&clusters=true&area=1124&ored_clusters=true&enable_snippets=true&page=37&hhtmFrom=vacancy_search_list").
                getVacancies();
    }
}
