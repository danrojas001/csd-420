// Dan Rojas
// Mod 10.2
// 17-May-26

package org.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FansGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    ManipulateData manipulateData = new ManipulateData();

    @Override
    public void start(Stage primaryStage) {

        // open DB connection
        manipulateData.openConnection();

        // fields
        Label idLabel = new Label("Record ID");
        TextField idField = new TextField();
        idField.setPromptText("Enter ID to search");

        Label firstNameLabel = new Label("First Name");
        TextField firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name");
        TextField lastNameField = new TextField();

        Label favTeamLabel = new Label("Fav Team");
        TextField favTeamField = new TextField();

        // buttons
        Button displayBtn = new Button("Display Record");
        displayBtn.setOnAction(e -> manipulateData.displayRecord(idField.getText(), firstNameField, lastNameField, favTeamField));
        Button updateBtn = new Button("Update Record");
        updateBtn.setOnAction(e -> manipulateData.updateRecord(idField.getText(), firstNameField.getText(),
                lastNameField.getText(), favTeamField.getText()));

        // layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(firstNameLabel, 0, 1);
        gridPane.add(firstNameField, 1, 1);
        gridPane.add(lastNameLabel, 0, 2);
        gridPane.add(lastNameField, 1, 2);
        gridPane.add(favTeamLabel, 0, 3);
        gridPane.add(favTeamField, 1, 3);

        HBox btnBox = new HBox(10, displayBtn, updateBtn);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setPadding(new Insets(25, 25, 25, 25));

        BorderPane root = new BorderPane();
        root.setCenter(gridPane);
        root.setBottom(btnBox);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        //close DB connection
        primaryStage.setOnCloseRequest(e -> {
            manipulateData.closeConnection();
        });
    }
}
