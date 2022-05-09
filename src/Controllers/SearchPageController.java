package Controllers;

import HHruParserApp.ApplicationCache;
import HHruParserApp.ApplicationContext;
import Parser.HH.HHVacanciesParser;
import Parser.VacanciesFabric;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SearchPageController {
    @FXML
    private Button showVacanciesButton;
    @FXML
    private TextField searchField;

    @FXML
    private void click() throws IOException {

        String searchFieldText = searchField.getText();
        System.out.println("Starting search thread  \"" + searchFieldText + "\"");

        ApplicationContext.setRoot("spinnerPage.fxml");
        SpinnerPageController controller = ApplicationCache.getCachedController("spinnerPage.fxml");

        Thread searchThread = new Thread(() -> controller.setRoot(new VacanciesFabric().getScene(new HHVacanciesParser(searchFieldText))));
        searchThread.setDaemon(true);
        searchThread.start();

        searchField.setText("");  //or else if you get back last text will still be in search field

        controller.waitForRoot();
    }
}
