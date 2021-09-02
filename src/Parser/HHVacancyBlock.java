package Parser;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HHVacancyBlock extends VacancyBlock {
    private final String salary;
    private final String employer;
    private final String description;


    public HHVacancyBlock(String name, String link, String salary, String employer, String description) {
        super(name, link);
        this.salary = salary;
        this.employer = employer;
        this.description = description;
    }

    public GridPane getView() {
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(700.0);
        gridPane.setStyle("-fx-border-color: blue;" +
                "-fx-border-width: 5;");
        gridPane.setPadding(new Insets(10, 0, 0, 0));

        gridPane.add(wrapper(this.name),        1, 1, 3, 1);
        gridPane.add(wrapper(this.description), 1, 2, 3, 1);
        gridPane.add(wrapper(this.employer),    1, 3, 3, 1);
        gridPane.add(salaryStyler(this.salary), 2, 4, 3, 1);

        return gridPane;
    }
    private Label wrapper(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setMaxWidth(700.0);
        label.setPadding(new Insets(5, 10, 10, 5));
        return label;
    }
    private Label salaryStyler(String salaryValue) {
        Label label = wrapper(salaryValue);
        label.setStyle("-fx-background-color: #f0f0d0");
        return label;
    }

}
