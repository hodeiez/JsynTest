package my.mySuperOsc;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import my.mySuperOsc.gui.Controller;
import my.mySuperOsc.gui.MainWindow;
import my.mySuperOsc.gui.components.NeonButton;


public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage)  {

        Controller controller = new Controller();

        NeonButton startButton = new NeonButton("Click Me to Start Synth");
        startButton.setOnAction(e -> controller.startSynth());

        Pane root =MainWindow.start();
        root.getChildren().addAll(startButton);
        Scene scene = new Scene(root);
        startButton.setTranslateX(root.getPrefWidth() / 2.4);
        startButton.setTranslateY(root.getPrefHeight() / 1.5);
        // Center the window on the screen
        stage = new Stage();
        stage.setScene(scene);

        stage.show();


    }

}