package Controllers;

import HHruParserApp.ApplicationCache;
import HHruParserApp.ApplicationContext;
import Parser.HH.HHVacanciesParser;
import Parser.VacanciesFabric;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SearchPageController {
    @FXML
    private TextField searchField;

    @FXML
    private void click() throws IOException {
        System.out.println("Starting search thread  \"" + searchField.getText() + "\"");

        ApplicationContext.setRoot("spinnerPage.fxml");

        searchField.setText("");  //or else if you get back last text will still be in search field

        ApplicationCache.<SpinnerPageController>getCachedController("spinnerPage.fxml")
                .switchSceneTo(() -> new VacanciesFabric().getScene(new HHVacanciesParser(searchField.getText())));
    }
}
