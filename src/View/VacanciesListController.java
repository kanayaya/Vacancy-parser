package View;

import Parser.HHVacanciesFabric;
import Parser.VacanciesThread;
import Parser.VacancyBlock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class VacanciesListController {
    private String searchText;
    private ArrayList<VacancyBlock> vacanciesArray;

    @FXML
    ScrollPane mainPane;
    @FXML
    Button backButton;

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void init() throws InterruptedException {
        vacanciesArray = searchVacancies(searchText);

        if (vacanciesArray.isEmpty()) { // Maybe user wrote search text with wrong keyboard layout
            vacanciesArray = searchVacanciesWithAnotherLayout();
        }

        VBox vacanciesList = new VBox(10, getViewedVacancies(vacanciesArray));
        mainPane.setContent(vacanciesList);
    }
    private ArrayList<VacancyBlock> searchVacancies(String searchText) throws InterruptedException {
        return startSearchThread(searchText)
                .getVacancies();
    }
    private ArrayList<VacancyBlock> searchVacanciesWithAnotherLayout() throws InterruptedException {
        return searchVacancies(LayoutChanger.changeLayout(searchText));
    }
    private VacanciesThread startSearchThread(String link) throws InterruptedException {
        VacanciesThread vacanciesThread = new VacanciesThread(new HHVacanciesFabric(link));
        vacanciesThread.start();
        return vacanciesThread;
    }
    private GridPane[] getViewedVacancies(ArrayList<VacancyBlock> vacanciesArray) {
        return vacanciesArray.stream()
                .map(VacancyBlock::getView)
                .collect(Collectors.toList())
                .toArray(GridPane[]::new);
    }

    public void backToSearch() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("FXML/mainPage.fxml")));
        Scene scene = new Scene(root);
        ControllerStaticUtils.getStageOf(backButton).setScene(scene);
    }
}
