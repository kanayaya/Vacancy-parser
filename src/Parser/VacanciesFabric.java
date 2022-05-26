package Parser;

import Controllers.VacanciesListController;
import HHruParserApp.ApplicationCache;
import javafx.scene.Parent;

public class VacanciesFabric {

    public Parent getScene(VacanciesParser parser) {

        System.out.println("Taking vacancies...");
        Parent[] result = parser.getVacancies().
                stream().
                map(Viewable::getView).
                toArray(Parent[]::new);

        ApplicationCache.<VacanciesListController>getCachedController("vacanciesList.fxml").setVacancies(result);

        return ApplicationCache.getRoot("vacanciesList.fxml");
    }
}

