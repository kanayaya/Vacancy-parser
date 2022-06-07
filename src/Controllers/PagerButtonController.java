package Controllers;

import HHruParserApp.ApplicationCache;
import HHruParserApp.ApplicationContext;
import Parser.HH.HHVacanciesParser;
import Parser.PagerButton;
import Parser.VacanciesFabric;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PagerButtonController {
    private String link;

    public void setProperties(PagerButton button) {
        this.link = button.getLink();
        pagerButton.setText(button.getText());
    }

    @FXML
    Button pagerButton;
    @FXML
    private void click() {
        if (link == null) return;
        ApplicationContext.setRoot("spinnerPage.fxml");
        SpinnerPageController controller = ApplicationCache.getCachedController("spinnerPage.fxml");
        controller.switchSceneTo(() -> new VacanciesFabric().getScene(new HHVacanciesParser(link)));
    }
}
