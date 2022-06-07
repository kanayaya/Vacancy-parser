package Controllers;

import HHruParserApp.ApplicationContext;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class VacanciesListController {
    private VBox vacanciesList;
    private final AnimationTimer reactionTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            vacanciesList.getChildren().forEach(block -> ((Region) block).setMaxWidth(mainPane.getWidth() - 50));
        }
    };

    @FXML
    ScrollPane mainPane;
    @FXML
    Button backButton;

    public void setVacancies(Region[] vacancies) {
        vacanciesList = new VBox(10, vacancies);

        reactionTimer.start();
        mainPane.setContent(vacanciesList);
        mainPane.setVvalue(0);
    }

    public void backToSearch() {
        mainPane.setVvalue(0.0);
        ApplicationContext.setRoot("mainPage.fxml");
        reactionTimer.stop();
    }
}
