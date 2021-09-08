package View;

import Parser.VacanciesThread;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class VacanciesListController implements Initializable {
    private String link;
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
        ArrayList<GridPane> vacanciesArray = vacanciesThread.getVacancies();

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

    public void initialize(URL var1, ResourceBundle var2) {

    }
}
