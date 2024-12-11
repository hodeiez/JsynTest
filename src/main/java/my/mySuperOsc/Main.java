package my.mySuperOsc;


import com.sun.javafx.scene.shape.PolygonHelper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;
import my.mySuperOsc.gui.Controller;
import my.mySuperOsc.gui.MainWindow;
import my.mySuperOsc.theSynth.MySynth;


public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage)  {

        Controller controller = new Controller();

        //start button
        Button startButton = new Button("Click Me to Start Synth");
        startButton.setOnAction(e -> controller.startSynth());
        startButton.setStyle("-fx-background-color: radial-gradient(center 50% -25%, radius 50%, #00ff29, #0000ff)");
        DropShadow ds =new DropShadow();
        ds.setSpread(0.5);
        ds.setColor(Color.BLUEVIOLET);
        startButton.setEffect(ds);
        startButton.setTextFill(Color.WHITE);



        controller.setPrimaryStage(stage);

        Pane root =MainWindow.start();
        root.getChildren().addAll(startButton);
        Scene scene = new Scene(root);
        startButton.setTranslateX(root.getPrefWidth() / 2.4);
        startButton.setTranslateY(root.getPrefHeight() / 1.5);
        // Center the window on the screen
        stage = new Stage();
        stage.setScene(scene);
        stage.show();

        // Calculate and set the initial position
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

}