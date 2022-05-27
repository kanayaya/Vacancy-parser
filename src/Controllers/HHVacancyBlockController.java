package Controllers;

import HHruParserApp.ApplicationCache;
import HHruParserApp.ApplicationContext;
import Parser.HH.HHPageParser;
import Parser.HH.HHVacancyBlock;
import Parser.PageThread;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class HHVacancyBlockController {
    private PageThread pageThread;

    @FXML
    public Label name, salary, employer, description;

    @FXML
    public void click() {
        startPageThread();

        ApplicationContext.setRoot("spinnerPage.fxml");

        SpinnerPageController controller = ApplicationCache.getCachedController("spinnerPage.fxml");
        controller.switchSceneTo(() -> pageThread.getPage().getView());
    }

    @FXML
    public void hover() {
        startPageThread();
    }
    private void startPageThread() {
        if (!pageThread.isStarted()) {
            pageThread.start();
        }
    }
    public void setProperties(HHVacancyBlock block) {
        this.name.setText(block.name);
        this.salary.setText(block.salary);
        this.employer.setText(block.employer);
        this.description.setText(block.description);

        this.pageThread = new PageThread(() -> new HHPageParser(block.link).getPage());
    }
}
