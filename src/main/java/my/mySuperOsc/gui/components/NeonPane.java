package my.mySuperOsc.gui.components;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NeonPane extends Pane implements INeonComponents {
    private final NeonText neonText;
    public NeonPane(String text) {
        super();
        this.neonText = new NeonText(text,14);
        styleIt();
    }

    public NeonText getNeonText() {
        return neonText;
    }

    @Override
    public void styleIt() {

        Rectangle gradientBackground = new Rectangle(780, 100);
        gradientBackground.setFill(Color.rgb(155, 200, 255, 0.5));

       // gradientBackground.setTranslateX(-50);

        neonText.getTextComp().setTranslateX(this.getPrefWidth()/2);
        neonText.getTextComp().setTranslateY(90);
        neonText.setTranslateX(this.getWidth()/2);
        neonText.setTranslateY(90);




        this.getChildren().addAll(gradientBackground,this.neonText.getTextComp());

        // Apply a drop shadow for a glowing effect
        this.setEffect(new DropShadow(10, 0, 0, Color.WHITE));
    }
}
