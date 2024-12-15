package my.mySuperOsc.gui;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import my.mySuperOsc.gui.components.NeonText;

public class MainWindow {


    public static Pane start(){
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        // Create a neon background rectangle
        Rectangle bg = new Rectangle(0, 0, 800, 600);
        bg.setFill(Color.BLACK);
        root.getChildren().add(bg);

        // Create the text
        NeonText neonText = new NeonText("PROCASTRINATOR-SYNTH 2024",48);

        neonText.setTranslateX(root.getPrefWidth() / 12);
        neonText.setTranslateY(0);
        neonText.getTextComp().setTranslateX(root.getPrefWidth() / 16);
        neonText.getTextComp().setTranslateY(root.getPrefHeight() / 2);

        root.getChildren().add(neonText.getTextComp());

        return root;
    }
}
