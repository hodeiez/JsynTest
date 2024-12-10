package my.mySuperOsc.gui;


import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainWindow {


    public static void start(){
        Pane root = new Pane();
        root.setPrefSize(800, 200);

        // Create a neon background rectangle
        Rectangle bg = new Rectangle(0, 0, 800, 200);
        bg.setFill(Color.color(0.8f, 0.2f, 0.6f));
        root.getChildren().add(bg);

        // Create the text
        Text text = new Text(0, 0, "PROCASTRINATOR-SYNTH 2024");
        text.setFont(Font.font("Courier New", 48));
        text.setFill(Color.YELLOW);

        // Apply effects
        text.setEffect(new Glow(0.9f));
        text.setCache(true);


        text.setTranslateX(root.getPrefWidth() / 12);
        text.setTranslateY(root.getPrefHeight() / 2);

        root.getChildren().add(text);

        Scene scene = new Scene(root);

        // Center the window on the screen
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        // Calculate and set the initial position
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);

    }
}
