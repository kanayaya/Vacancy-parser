package Parser.HH;

import Parser.VacancyBlock;
import Controllers.HHVacancyBlockController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;

public class HHVacancyBlock extends VacancyBlock {
    public final String salary, employer, description;

    public HHVacancyBlock(String name, String link, String salary, String employer, String description) {
        super(name, link);
        this.salary = salary;
        this.employer = employer;
        this.description = description;
    }

    public GridPane getView() {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(this.getClass().getResource("../../View/FXML/HHvacancyBlock.fxml")));
        GridPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HHVacancyBlockController controller = loader.getController();
        controller.setProperties(this);

        return root;
    }
}
