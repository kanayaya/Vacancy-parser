package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private Button showVacanciesButton;

    @FXML
    private TextField searchField;

    @FXML
    private void click(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(this.getClass().getResource("vacanciesList.fxml")));

        VBox root = loader.load();
        VacanciesListController controller = loader.getController();

        controller.setLink(searchField.getText());
        controller.init();

        Scene showVacancies = new Scene(root);
        ControllerStaticUtils.getStageOf(showVacanciesButton).setScene(showVacancies);
    }

    @Override
    public void initialize(URL var1, ResourceBundle var2) {

    }
}
