package it.BruttiF27.balanceManager.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override // Method inherited from Application - JavaFX entry point
    public void start (Stage primaryStage) {

        // Set stage name
        primaryStage.setTitle("Balance Manager");

        // Set the scene
        Label label = new Label("Jesus Christ this is actually working");
        StackPane layout = new StackPane(label);

        // Create the scene with the layout
        Scene scene = new Scene(layout, 600, 400);

        // Set and print the stage
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}