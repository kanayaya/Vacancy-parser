package Controllers;

import HHruParserApp.ApplicationContext;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.util.function.Supplier;

public class SpinnerPageController {
    @FXML
    AnchorPane circleGroup;
    @FXML
    private Circle leftCircle, centralCircle, rightCircle;
    private Circle[] circles = null;

    private volatile Parent root;

    public void switchSceneTo(Supplier<Parent> fabric) {
        Thread getter = new Thread(() -> root = fabric.get());
        getter.setDaemon(true);
        getter.start();

        initCircles();
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                spin();
                if (root != null) {
                    ApplicationContext.setRoot(root);
                    root = null;
                    this.stop();
                }
            }
        }.start();
    }

    private void initCircles() {
        circles = new Circle[]{leftCircle, centralCircle, rightCircle};
        leftCircle.setRadius(10);
        centralCircle.setRadius(15);
        rightCircle.setRadius(20);
    }

    private void spin() {
        double w = ApplicationContext.getStage().getWidth();
        double h = ApplicationContext.getStage().getHeight();

        circleGroup.setLayoutX((w / 2) - (circleGroup.getWidth()/2));
        circleGroup.setLayoutY((h / 2) - (circleGroup.getHeight()/1.5));

        for (Circle circle: circles) {
            circle.setRadius(circle.getRadius() - 0.2);
            if (circle.getRadius() <= 5.0) circle.setRadius(20);
        }
    }
}
