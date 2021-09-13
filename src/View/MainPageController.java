package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class MainPageController{

    @FXML
    private Button showVacanciesButton;

    @FXML
    private TextField searchField;

    @FXML
    private void click(ActionEvent event) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(this.getClass().getResource("vacanciesList.fxml")));

        VBox root = loader.load();
        VacanciesListController controller = loader.getController();

        controller.setSearchText(searchField.getText());
        controller.init();

        Scene showVacancies = new Scene(root);
        ControllerStaticUtils.getStageOf(showVacanciesButton).setScene(showVacancies);
    }
}
