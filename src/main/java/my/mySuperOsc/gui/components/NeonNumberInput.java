package my.mySuperOsc.gui.components;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;


import java.math.BigDecimal;

public class NeonNumberInput extends HBox implements INeonComponents{
    private TextArea textField;
    private BigDecimal stepWidth = BigDecimal.ONE;
    private BigDecimal number = BigDecimal.ZERO;

    @Override
    public void styleIt(){

        this.textField.setMaxHeight(0.001);
        this.textField.setPrefHeight(0.001);
        this.textField.setPrefWidth(0.001);
        this.textField.setMaxWidth(0.001);


        this.setMaxHeight(0.02);
        this.setMaxWidth(0.02);
    }

    public NeonNumberInput (BigDecimal initialValue) {
        super();

        this.textField = new TextArea();
        this.initHandlers();
        this.styleIt();
        this.setNumber(initialValue);
        this.stepWidth = new BigDecimal(1);

        Button increment = new Button("+");
        Button decrement = new Button("-");

        increment.setOnAction(e -> setNumber(number.add(stepWidth)));
        decrement.setOnAction(e -> {
            if(number.compareTo(BigDecimal.ZERO) > 0) {
                setNumber(number.subtract(stepWidth));
            }
        });

        getChildren().addAll(textField, increment, decrement);
    }

    public BigDecimal getValue() {
        return getNumber();
    }
    private void initHandlers() {
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                BigDecimal parsed = new BigDecimal(newValue);
                setNumber(parsed);
            } catch (NumberFormatException e) {
            }
        });
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal value) {
        this.number = value;
        this.textField.textProperty().setValue(value.toString());
    }
}
