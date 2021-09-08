package View;

import Parser.VacanciesThread;
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

public class VacanciesListController {
    private String link;
    private ArrayList<GridPane> vacanciesArray;
    @FXML
    ScrollPane mainPane;
    @FXML
    Button backButton;

    public void setLink(String link) {
        this.link = link;
    }

    public void init() {
        VacanciesThread vacanciesThread = new VacanciesThread(ControllerStaticUtils.getLinkWithSearchText(link));
        vacanciesThread.start();
        vacanciesArray = vacanciesThread.getVacancies();

        if (vacanciesArray.isEmpty()) {
            vacanciesThread = new VacanciesThread(ControllerStaticUtils.getLinkWithReversedText(link));
            vacanciesThread.start();
            vacanciesArray.addAll(vacanciesThread.getVacancies());
        }

        GridPane[] vacancies = vacanciesArray.toArray(new GridPane[0]);
        VBox vacanciesList = new VBox(10, vacancies);
        mainPane.setContent(vacanciesList);
    }

    public void backToSearch() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("mainPage.fxml")));
        Scene scene = new Scene(root);
        ControllerStaticUtils.getStageOf(backButton).setScene(scene);
    }
}
