package Parser;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

public abstract class VacanciesFabric {
    private final ArrayList<VacancyBlock> vacancies;

    public VacanciesFabric(String link) throws InterruptedException {
        this.vacancies = getVacanciesFrom(Picker.repeatedlyGetPage(link));
    }

    public ArrayList<VacancyBlock> getVacancies() {
        return vacancies;
    }

    public abstract ArrayList<VacancyBlock> getVacanciesFrom(Document wholePage);
}
