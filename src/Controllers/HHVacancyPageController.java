package Controllers;

import HHruParserApp.ApplicationCache;
import HHruParserApp.ApplicationContext;
import Parser.HH.HHPage;
import Parser.Page;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HHVacancyPageController {
    @FXML
    public Label name, salary, description;

    @FXML
    public void back() {
        ApplicationContext.setRoot(ApplicationCache.getRoot("vacanciesList.fxml"));
    }

    public void setProperties(Page page) {
        HHPage hhpage = (HHPage) page;
        this.name.setText(hhpage.name);
        this.salary.setText(hhpage.salary);
        this.description.setText(hhpage.description);
    }
}
