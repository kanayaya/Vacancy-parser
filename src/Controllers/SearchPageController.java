package Controllers;

import HHruParserApp.ApplicationCache;
import HHruParserApp.ApplicationContext;
import Parser.HH.HHVacanciesParser;
import Parser.VacanciesFabric;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class SearchPageController {
    @FXML
    private TextField searchField;

    @FXML
    private void click() {
        System.out.println("Starting search thread  \"" + searchField.getText() + "\"");

        ApplicationContext.setRoot("spinnerPage.fxml");

        System.out.println("---------------" + searchField.getText());

        ApplicationCache.<SpinnerPageController>getCachedController("spinnerPage.fxml")
                .switchSceneTo(this::getScene);
    }

    private Parent getScene() {
        String searchText = searchField.getText();
        searchField.setText("");  //or else if you get back last text will still be in search field
        return new VacanciesFabric().getScene(new HHVacanciesParser(HHVacanciesParser.getLinkWithSearchText(searchText)));
    }
}
