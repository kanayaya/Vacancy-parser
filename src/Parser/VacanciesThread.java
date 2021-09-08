package Parser;


import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class VacanciesThread extends Thread {
    private volatile ArrayList<VacancyBlock> vacancies = null;
    private final String link;

    public VacanciesThread(String link) {
        this.link = link;
        this.setPriority(2);
    }
    public VacanciesThread() {
        String searchInIrkutsk = "https://hh.ru/search/vacancy?fromSearchLine=true&st=searchVacancy&text=java&area=1124";
        this.link = searchInIrkutsk;
        this.setPriority(2);
    }
    public ArrayList<VacancyBlock> getVacancies() {
        while (vacancies == null) ;//wait
        return vacancies;
    }
    @Override
    public void run() {
        synchronized (this) {
            try {
                System.out.println("Taking vacancies...");
                this.vacancies = DocParser.getVacancies(this.link);
            } catch (NoSuchElementException | InterruptedException e) {
                System.out.println("Error in BlockParser. Maybe wrong key/value or target changed attribute:  " + e);
            }
        }
    }
}

