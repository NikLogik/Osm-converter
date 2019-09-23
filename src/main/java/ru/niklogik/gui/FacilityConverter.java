package ru.niklogik.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FacilityConverter extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final String APP_NAME = "Facility converter";

    Group root = new Group();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle(APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
