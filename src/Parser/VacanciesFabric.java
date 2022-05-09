package Parser;

import HHruParserApp.ApplicationCache;
import Controllers.VacanciesListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class VacanciesFabric {
    public static GridPane[] get(VacanciesParser parser) {
        try {
            System.out.println("Taking vacancies...");
            return getViewedVacancies(parser.getVacancies());
        } catch (NoSuchElementException e) {
            System.out.println("Error in " + parser.getClass().toString() + ". Maybe wrong key/value or target changed attribute:  " + e);
        } catch (InterruptedException e) {
            System.out.println("thread interrupted");
        }
        return new GridPane[0];
    }
    private static GridPane[] getViewedVacancies(ArrayList<VacancyBlock> vacanciesArray) {
        return vacanciesArray.stream()
                .map(VacancyBlock::getView)
                .collect(Collectors.toList())
                .toArray(GridPane[]::new);
    }

    public Parent getScene(VacanciesParser parser) {
        FXMLLoader loader = ApplicationCache.getCached("vacanciesList.fxml");
        Parent root = null;
        try {
            root = ApplicationCache.getRoot("vacanciesList.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        VacanciesListController controller = loader.getController();
        controller.setVacancies(get(parser));

        return root;
    }
}

