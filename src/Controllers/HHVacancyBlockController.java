package Controllers;

import Parser.HH.HHPageParser;
import Parser.HH.HHVacancyBlock;
import Parser.PageThread;
import Utils.ControllerStaticUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class HHVacancyBlockController {
    private HHVacancyBlock block;
    private PageThread pageThread;

    @FXML
    public Label name, salary, employer, description;

    @FXML
    public void click() throws IOException {
        if (pageThread.isStarted() == false) {
            pageThread.start();
        }
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../View/FXML/HHvacancyPageContent.fxml"));

        AnchorPane root = loader.load();
        Scene showPage = new Scene(root);
        ControllerStaticUtils.getStageOf(name).setScene(showPage);

        while (pageThread.getPage() == null) ;//wait
        HHVacancyPageController controller = loader.getController();
        controller.setProperties(pageThread.getPage());
    }

    @FXML
    public void hover() {
        if (pageThread.isStarted() == false) {
            pageThread.start();
        }
    }

    public void setProperties(HHVacancyBlock block) {
        this.block = block;
        this.name.setText(block.name);
        this.salary.setText(block.salary);
        this.employer.setText(block.employer);
        this.description.setText(block.description);

        try {
            this.pageThread = new PageThread(new HHPageParser(block));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
