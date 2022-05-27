package Parser.HH;

import Controllers.HHVacancyBlockController;
import Parser.Viewable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class HHVacancyBlock implements Viewable {
    public final String name, link, salary, employer, description;
    public final URL employerImageURL;

    public HHVacancyBlock(String name, String link, String salary, String employer, String description, String employerImageURL) {
        this.name = name;
        this.link = link;
        this.salary = salary;
        this.employer = employer;
        this.description = description;


        try {
            if ("".equals(employerImageURL)) this.employerImageURL = new URL("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.vecteezy.com%2Fvector-art%2F4949668-simple-abstract-real-estate-building-in-the-circle-swoosh&psig=AOvVaw27-WNr-G2_4aeKpqqkrD_B&ust=1652534777352000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCKjlv_vJ3PcCFQAAAAAdAAAAABAD");
            else this.employerImageURL = new URL(employerImageURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Parent getView() {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(this.getClass().getResource("../../View/FXML/HHvacancyBlock.fxml")));
        GridPane root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HHVacancyBlockController controller = loader.getController();
        setPropertiesTo(controller);

        return root;
    }
    private void setPropertiesTo(HHVacancyBlockController controller) {
        controller.name.setText(name);
        controller.salary.setText(salary);
        controller.employer.setText(employer);
        controller.description.setText(description);
        controller.setLink(link);
    }
}
