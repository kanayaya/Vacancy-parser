package Parser;

import org.jsoup.nodes.Document;

import java.util.List;

public abstract class VacanciesParser {
    protected String link;

    public VacanciesParser(String link) {
        this.link = link;
    }

    public abstract List<Viewable> getVacancies() ;

    public abstract List<Viewable> getVacanciesFrom(Document wholePage);
}
