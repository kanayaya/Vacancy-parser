package Parser;

import HHruParserApp.ApplicationCache;
import Controllers.VacanciesListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class VacanciesFabric {
    public static Parent[] get(VacanciesParser parser) {
        try {
            System.out.println("Taking vacancies...");
            return getViewedVacancies(parser.getVacancies());
        } catch (NoSuchElementException e) {
            System.out.println("Error in " + parser.getClass().getName() + ". Maybe key/value are wrong or target changed attribute:  " + e);
        }
        return new GridPane[0];
    }
    private static Parent[] getViewedVacancies(List<Viewable> vacanciesArray) {
        return vacanciesArray.stream()
                .map(Viewable::getView)
                .collect(Collectors.toList())
                .toArray(Parent[]::new);
    }

    public Parent getScene(VacanciesParser parser) {
        FXMLLoader loader = ApplicationCache.getCached("vacanciesList.fxml");

        VacanciesListController controller = loader.getController();
        controller.setVacancies(get(parser));

        return ApplicationCache.getRoot("vacanciesList.fxml");
    }
}

