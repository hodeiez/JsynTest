package my.mySuperOsc.gui;


import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MainWindow {


    public static Pane start(){
        Pane root = new Pane();
        root.setPrefSize(800, 200);

        // Create a neon background rectangle
        Rectangle bg = new Rectangle(0, 0, 800, 200);
        bg.setFill(Color.BLACK);
        root.getChildren().add(bg);

        // Create the text
        Text text = new Text(0, 0, "PROCASTRINATOR-SYNTH 2024");
        text.setFont(Font.font("Courier New", 48));
        text.setFill(Color.YELLOW);
        // Create the text
        TextFlow textFlow = new TextFlow();
        textFlow.setTranslateX(root.getPrefWidth() / 12);
        textFlow.setTranslateY(0);
        textFlow.getChildren().add(text);
        // Apply effects
        Glow g = new Glow(0.9);

        text.setEffect(g);
        text.setCache(true);
        DropShadow ds =new DropShadow();
        ds.setSpread(0.9);
        ds.setColor(Color.BLUEVIOLET);
        text.setEffect(ds);
        text.setCache(true);

        text.setTranslateX(root.getPrefWidth() / 16);
        text.setTranslateY(root.getPrefHeight() / 2);

        root.getChildren().add(text);

        return root;
    }
}
