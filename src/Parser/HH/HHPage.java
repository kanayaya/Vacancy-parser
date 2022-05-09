package Parser.HH;

import Parser.Page;
import javafx.scene.layout.AnchorPane;

public class HHPage extends Page {
    public final String
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
        super(name);
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
    public AnchorPane getView() {
        return null;
    }
}
