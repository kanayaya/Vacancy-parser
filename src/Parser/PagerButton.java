package Parser;

import Controllers.PagerButtonController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Region;

import java.io.IOException;

public class PagerButton implements Viewable {
    private final String link;
    private final String text;
    private final double width;

    public PagerButton(String link, String text, double width) {
        this.link = link;
        this.text = text;
        this.width = width;
    }

    public String getLink() {
        return link;
    }
    public String getText() {
        return text;
    }

    @Override
    public Region getView() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../View/FXML/pagerButton.fxml"));
        Region result;
        try {
            result = loader.load();
            result.setMinWidth(width);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Region root = result;
        PagerButtonController controller = loader.getController();
        controller.setProperties(this);
        return root;
    }

}
