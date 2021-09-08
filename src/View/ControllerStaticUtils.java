package View;

import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.HashMap;

class ControllerStaticUtils {

    protected static Stage getStageOf(Node node) {
        return (Stage) node.getScene().getWindow();
    }

    protected static String getLinkWithSearchText(String text) {
        text = text.trim().replace(' ', '+');
        String link = "https://hh.ru/search/vacancy?clusters=true&text=".concat(text.concat("&enable_snippets=true&L_save_area=True&area=1124&customDomain=1"));
        System.out.println("Going to    " + link);
        return link;
    }
    protected static String getLinkWithReversedText(String text) {
        return getLinkWithSearchText(LayoutChanger.changeLayout(text.split(" ")));
    }


}
