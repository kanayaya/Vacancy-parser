package View;

import javafx.scene.Node;
import javafx.stage.Stage;

class ControllerStaticUtils {

    protected static Stage getStageOf(Node node) {
        return (Stage) node.getScene().getWindow();
    }

    protected static String getLinkFromSearchText(String text) {
        text = text.trim().replace(' ', '+');
        return "https://hh.ru/search/vacancy?fromSearchLine=true&st=searchVacancy&text=".concat(text.concat("&area=1124"));
    }
}
