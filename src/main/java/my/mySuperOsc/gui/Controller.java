package my.mySuperOsc.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import my.mySuperOsc.theSynth.MySynth;

public class Controller {
    private Stage primaryStage;
    private MySynth mySynth;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() {

        mySynth = new MySynth();

        mySynth.getMs().listMidiDevices();

    }

    public void startSynth() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
              initialize();
                mySynth.runSynth();
                return null;
            }
        };
        new Thread(task).start();
    }

}
