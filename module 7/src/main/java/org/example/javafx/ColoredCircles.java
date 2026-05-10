//Dan Rojas
//Mod 7
//03-May-26

//Write a JavaFX program that displays four circles and uses the style class and ID.

package org.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Objects;

public class ColoredCircles extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane,200,200);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("circles.css")).toExternalForm());

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Circle c1 = new Circle(30);
        Circle c2 = new Circle(30);
        Circle c3 = new Circle(30);
        Circle c4 = new Circle(30);

        gridPane.add(c1,0,0);
        gridPane.add(c2,1,0);
        gridPane.add(c3,0,1);
        gridPane.add(c4,1,1);

        c1.getStyleClass().addAll("whiteCircle", "circleBorder");
        c2.getStyleClass().addAll("whiteCircle", "circleBorder");
        c3.getStyleClass().add("circleBorder");
        c3.setId("redCircle");
        c4.getStyleClass().add("circleBorder");
        c4.setId("greenCircle");


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
