package ru.niklogik.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class FacilityConverter extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final String APP_NAME = "Facility converter";

    Group root = new Group();

    @Override
    public void start(Stage primaryStage) throws Exception {

        FileChooser chooser = new FileChooser();

        Button start = new Button("Choose files");

        TextArea area = new TextArea();
        area.setMinHeight(90);

        start.setOnAction(event -> {
            File file = chooser.showOpenDialog(primaryStage);
            if (file != null){
                openFile();
            }});

        VBox box = new VBox();
        box.getChildren().addAll(start, area);

        Scene scene = new Scene(box, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openFile() {
    }

    public static void main(String[] args) {
        launch(args);
    }
}
