package Parser;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

public abstract class VacanciesParser {
    protected String link;

    public VacanciesParser(String link) {
        this.link = link;
    }

    public abstract ArrayList<VacancyBlock> getVacancies() throws InterruptedException;

    public abstract ArrayList<VacancyBlock> getVacanciesFrom(Document wholePage);
}
