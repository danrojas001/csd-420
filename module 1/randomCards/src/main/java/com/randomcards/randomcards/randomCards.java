//Dan Rojas
//Mod 1.3
//29-Mar-26


package com.randomcards.randomcards;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class randomCards extends Application {

    public void start(Stage stage) throws Exception {

        File images =  new File("src/main/resources/images");
        File[] files = images.listFiles();
        if (files == null) {
            System.out.println("No images found");
            return;
        }

        List<File> cardList = Arrays.asList(files);

        HBox cardsContainer = new HBox(10);
        cardsContainer.setAlignment(Pos.CENTER);

        Button shuffleButton = new Button("Refresh");

        VBox root = new VBox(10, cardsContainer, shuffleButton);
        root.setAlignment(Pos.CENTER);

        //first four cards displayed
        randomFour(cardsContainer, cardList);

        //button click action
        shuffleButton.setOnAction(e -> {
            randomFour(cardsContainer, cardList);
        });

        Scene scene1 = new Scene(root, 750, 500);
        stage.setScene(scene1);
        stage.show();

    }

    public static void randomFour(HBox cardsContainer, List<File> cardList) {

        Collections.shuffle(cardList);
        List<File> chosenCards = cardList.stream().limit(4).toList();
        cardsContainer.getChildren().clear();

        for (File card : chosenCards) {
            Image image = new Image(card.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setPreserveRatio(true);
            cardsContainer.getChildren().add(imageView);
        }
    }

    public static void main(String[] args) {

        launch(args);

    }
}
