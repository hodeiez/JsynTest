package my.mySuperOsc;


import com.jsyn.JSyn;
import com.jsyn.unitgen.*;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import my.mySuperOsc.gui.MainWindow;
import my.mySuperOsc.midi.MidiSetup;
import my.mySuperOsc.midi.ReceiverAdapter;
import my.mySuperOsc.midiToSynth.MidiToVoices;

import javax.sound.midi.MidiUnavailableException;



public class Main extends Application {
    public static void main(String[] args) {
        //just for testing javafx works
        Thread synth = new Thread(Main::runSynth);
        synth.start();
        launch();


    }


    @Override
    public void start(Stage stage) throws Exception {
        MainWindow.start();
    }
    private static void runSynth() {


        MidiSetup m = new MidiSetup();
        // select from list
        m.selectMidiDevice(6,m.listMidiDevices());


        var synth = JSyn.createSynthesizer();

        var lineOut = new LineOut();
        ReceiverAdapter rc= new ReceiverAdapter();
        MySynth ms = new MySynth(rc,synth, lineOut);
        var runner = new MidiToVoices();
        runner.setVoices(ms.voices);
        rc.setRunner(runner);
        try {
            m.getSelectedDevice().open();


            m.connectMidiKeyboard(m.getSelectedDevice(), rc);
            System.out.println(m.getSelectedDevice().getTransmitters().size());
        } catch (MidiUnavailableException e){
            System.out.println("WRONG SELECTED MIDI");
        }
        ms.start();

        while (true) {
            ms.lineOut.flattenOutputs();
        }
    }
}