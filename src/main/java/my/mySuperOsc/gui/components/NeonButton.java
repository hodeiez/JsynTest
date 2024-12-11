package my.mySuperOsc.gui.components;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class NeonButton extends Button {
    private String text;

    public NeonButton(String text) {
        super(text);
       this.setStyle("-fx-background-color: radial-gradient(center 50% -25%, radius 50%, #000000, #00f0ff)");
        DropShadow ds =new DropShadow();
        ds.setSpread(0.3);
        ds.setColor(Color.VIOLET);
        this.setEffect(ds);
        this.setTextFill(Color.BLACK);

    }
}
