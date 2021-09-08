package View;

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

    public void init() {
        vacanciesArray = searchVacancies();

        if (vacanciesArray.isEmpty()) { // Maybe user wrote search text with wrong keyboard layout
            vacanciesArray = searchVacanciesWithAnotherLayout();
        }

        VBox vacanciesList = new VBox(10, getViewedVacancies(vacanciesArray));
        mainPane.setContent(vacanciesList);
    }
    private ArrayList<VacancyBlock> searchVacancies() {
        return startSearch(ControllerStaticUtils.getLinkWithSearchText(searchText))
                .getVacancies();
    }
    private ArrayList<VacancyBlock> searchVacanciesWithAnotherLayout() {
        return startSearch(ControllerStaticUtils.getLinkWithReversedText(searchText))
                .getVacancies();
    }
    private VacanciesThread startSearch(String link) {
        VacanciesThread vacanciesThread = new VacanciesThread(ControllerStaticUtils.getLinkWithReversedText(searchText));
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("mainPage.fxml")));
        Scene scene = new Scene(root);
        ControllerStaticUtils.getStageOf(backButton).setScene(scene);
    }
}
