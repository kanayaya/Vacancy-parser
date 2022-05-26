package Parser.HH;

import Parser.Viewable;
import javafx.scene.Parent;

public class HHPage implements Viewable {
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
    public Parent getView() {
        return null;
    }
}
