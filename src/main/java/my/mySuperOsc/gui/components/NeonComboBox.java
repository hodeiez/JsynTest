package my.mySuperOsc.gui.components;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.ComboBox;
import my.mySuperOsc.theSynth.MySynth;

public class NeonComboBox extends ComboBox {

    public NeonComboBox(ObservableList items) {
        super(items);
        styleIt();
    }
    public void styleIt(){

        this.setStyle("-fx-base: #800080; " +
                "-fx-text-fill: white; " +
                "-fx-background-color: #800080, transparent, transparent, #800080;");

    }

    public void handleDeviceSelection(String selected, MySynth mySynth){
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                mySynth.getMs().selectMidiDevice(Integer.parseInt(selected.substring(0,selected.indexOf("-"))));
                return null;
            }
        };
        new Thread(task).start();
        task.setOnSucceeded(e-> Platform.runLater(()-> System.out.println(selected + " selected")));
        task.setOnFailed(e-> System.out.println("fucked up selecting"));
    }
}
