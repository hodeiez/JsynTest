package my.mySuperOsc;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import my.mySuperOsc.gui.Controller;
import my.mySuperOsc.gui.MainWindow;
import my.mySuperOsc.gui.components.NeonButton;
import my.mySuperOsc.gui.components.NeonComboBox;


public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage)  {

        Controller controller = new Controller();

        NeonButton startButton = new NeonButton("Click Me to Start Synth");

        startButton.setOnAction(e -> controller.startSynth());

        NeonComboBox comboBox = controller.startDevicesComboBox();

        Pane root =MainWindow.start();
        root.getChildren().addAll(startButton,comboBox);
        Scene scene = new Scene(root);
        startButton.setTranslateX(root.getPrefWidth() / 2.4);
        startButton.setTranslateY(root.getPrefHeight() / 1.5);
        // Center the window on the screen
        stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(e->controller.closeSynth());
        stage.show();


    }

}