package my.mySuperOsc.gui.components;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class NeonText extends TextFlow implements INeonComponents {
    private  String text;
    private int textSize;
    private Text textComp;
    public NeonText(String text,int textSize) {
        super();
        this.text = text;
        this.textSize = textSize;
        this.styleIt();
    }

    public Text getTextComp() {
        return textComp;
    }

    @Override
    public void styleIt() {
        this.textComp = new Text(0, 0, this.text);
        textComp.setFont(Font.font("Courier New", this.textSize));
        textComp.setFill(Color.YELLOW);
        // Create the text
        TextFlow textFlow = new TextFlow();
       // textFlow.setTranslateX(root.getPrefWidth() / 12);
        textFlow.setTranslateY(0);
        textFlow.getChildren().add(textComp);
        // Apply effects
        Glow g = new Glow(0.9);

        textComp.setEffect(g);
        textComp.setCache(true);
        DropShadow ds =new DropShadow();
        ds.setSpread(0.9);
        ds.setColor(Color.BLUEVIOLET);
        textComp.setEffect(ds);
        textComp.setCache(true);

    }
}
