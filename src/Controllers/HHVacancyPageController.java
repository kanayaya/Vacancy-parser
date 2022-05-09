package Controllers;

import Parser.HH.HHPage;
import Parser.Page;
import Parser.VacancyBlock;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

import java.util.ArrayList;

public class HHVacancyPageController {
    private ArrayList<VacancyBlock> vacanciesArray;
    @FXML
    public Label name, salary, description;

    @FXML
    public void back() {

    }

    public void setProperties(Page page) {
        HHPage hhpage = (HHPage) page;
        this.name.setText(hhpage.name);
        this.salary.setText(hhpage.salary);
        this.description.setText(hhpage.description);
    }
}
