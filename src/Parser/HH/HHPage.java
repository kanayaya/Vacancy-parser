package Parser.HH;

import Controllers.HHVacancyPageController;
import Parser.Viewable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.io.IOException;

public final class HHPage implements Viewable {
    public final String
            name,
            salary,
            employer,
            town,
            experience,
            employmentMode,
            description,
            abilities,
            date;

    public HHPage(String name,
                  String salary,
                  String employer,
                  String town,
                  String experience,
                  String employmentMode,
                  String description,
                  String abilities,
                  String date) {
        this.name = name;
        this.salary = salary;
        this.employer = employer;
        this.town = town;
        this.experience = experience;
        this.employmentMode = employmentMode;
        this.description = description;
        this.abilities = abilities;
        this.date = date;

    }
    @Override
    public Region getView() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../../View/FXML/HHvacancyPageContent.fxml"));

        AnchorPane root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HHVacancyPageController controller = loader.getController();
        controller.setProperties(this);
        return root;
    }
}
