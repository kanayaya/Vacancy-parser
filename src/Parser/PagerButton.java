package Parser;

import Controllers.PagerButtonController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class PagerButton implements Viewable {
    private final String link;
    private final String text;

    public PagerButton(String link, String text) {
        this.link = link;
        this.text = text;
    }

    public String getLink() {
        return link;
    }
    public String getText() {
        return text;
    }

    @Override
    public Parent getView() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../View/FXML/pagerButton.fxml"));
        Parent result;
        try {
            result = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = result;
        PagerButtonController controller = loader.getController();
        controller.setProperties(this);
        return root;
    }

}
