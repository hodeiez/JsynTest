package my.mySuperOsc.gui;

import javafx.concurrent.Task;
import my.mySuperOsc.theSynth.MySynth;

public class Controller {

    private MySynth mySynth;


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
