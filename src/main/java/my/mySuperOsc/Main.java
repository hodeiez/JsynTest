package my.mySuperOsc;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import my.mySuperOsc.gui.Controller;
import my.mySuperOsc.gui.MainWindow;
import my.mySuperOsc.gui.components.NeonButton;
import my.mySuperOsc.gui.components.NeonComboBox;
import my.mySuperOsc.gui.components.NeonPane;
import my.mySuperOsc.gui.components.NeonNumberInput;
import my.mySuperOsc.theSynth.config.ConfigModel;

import java.math.BigDecimal;


public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage)  {

        Controller controller = new Controller();

        NeonButton startButton = new NeonButton("Click Me to Start Synth");
        NeonNumberInput selectVoiceAmount = new NeonNumberInput(new BigDecimal(0));


        startButton.setOnAction(e -> controller.startSynth());
        selectVoiceAmount.setOnMouseClicked(e->controller.selectVoiceAmount(selectVoiceAmount.getNumber().intValue()));
        NeonComboBox devicesComboBox = controller.startDevicesComboBox();
        NeonComboBox oscillatorsComboBox = controller.startOscillatorsComboBox("1");
        NeonComboBox oscillators2ComboBox = controller.startOscillatorsComboBox("2");



        Pane root =MainWindow.start();
        NeonPane config = new NeonPane("Config");

        config.getChildren().addAll(devicesComboBox,selectVoiceAmount,oscillatorsComboBox,oscillators2ComboBox);

        root.getChildren().addAll(startButton,config);
        Scene scene = new Scene(root);

        selectVoiceAmount.setTranslateX(root.getPrefWidth()/4);
        selectVoiceAmount.setTranslateY(config.getPrefHeight()+10);
        devicesComboBox.setTranslateY(config.getPrefHeight()+10);
        oscillators2ComboBox.setTranslateY(config.getPrefHeight()+10);
        oscillators2ComboBox.setTranslateX(300);
        oscillatorsComboBox.setTranslateY(config.getPrefHeight()+10);
        oscillatorsComboBox.setTranslateX(450);

        config.setTranslateX(root.getPrefWidth()/70);
        config.setTranslateY(root.getPrefHeight()/70);
        //neonNumberInput.setTranslateY(root.getPrefHeight() / 12);


        startButton.setTranslateX(root.getPrefWidth() / 2.4);
        startButton.setTranslateY(root.getPrefHeight() / 1.5);
        // Center the window on the screen
        stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(e->controller.closeSynth());
        stage.show();


    }

}