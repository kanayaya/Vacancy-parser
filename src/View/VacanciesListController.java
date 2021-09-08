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
    private String link;
    private ArrayList<VacancyBlock> vacanciesArray;

    @FXML
    ScrollPane mainPane;
    @FXML
    Button backButton;

    public void setLink(String link) {
        this.link = link;
    }

    public void init() {
        vacanciesArray = tryToGetVacancies();

        if (vacanciesArray.isEmpty()) {
            vacanciesArray = searchVacanciesWithAnotherLayout();
        }

        VBox vacanciesList = new VBox(10, getViewedVacancies(vacanciesArray));
        mainPane.setContent(vacanciesList);
    }
    private ArrayList<VacancyBlock> tryToGetVacancies() {
        VacanciesThread vacanciesThread = new VacanciesThread(ControllerStaticUtils.getLinkWithSearchText(link));
        vacanciesThread.start();
        return vacanciesThread.getVacancies();
    }
    private ArrayList<VacancyBlock> searchVacanciesWithAnotherLayout() {
        VacanciesThread vacanciesThread = new VacanciesThread(ControllerStaticUtils.getLinkWithReversedText(link));
        vacanciesThread.start();
        return vacanciesThread.getVacancies();
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
