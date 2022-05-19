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

    public void switchSceneTo(RootFabric fabric) {
        Thread getter = new Thread(() -> root = fabric.getRoot());
        getter.setDaemon(true);
        getter.start();
        setRootWhen(() -> root != null);
    }


    public void setRootWhen(Supplier<Boolean> flag) {
        initCircles();

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                spin();
                if (flag.get()) {
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

        circleGroup.setLayoutX(magicNumber(w));
        circleGroup.setLayoutY(magicNumber(h) + 35);

        for (Circle circle: circles) {
            circle.setRadius(circle.getRadius() - 0.2);
            if (circle.getRadius() <= 5.0) circle.setRadius(20);
        }
    }

    private static double magicNumber(double w) {
        return (w / 2) - 75;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }
}
