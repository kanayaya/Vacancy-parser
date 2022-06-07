package Parser;

import Controllers.VacanciesListController;
import HHruParserApp.ApplicationCache;
import javafx.scene.layout.Region;

public class VacanciesFabric {

    public Region getScene(VacanciesParser parser) {

        System.out.println("Taking vacancies...");
        Region[] result = parser.getVacancies().
                stream().
                map(Viewable::getView).
                toArray(Region[]::new);

        ApplicationCache.<VacanciesListController>getCachedController("vacanciesList.fxml").setVacancies(result);

        return ApplicationCache.getRoot("vacanciesList.fxml");
    }
}

