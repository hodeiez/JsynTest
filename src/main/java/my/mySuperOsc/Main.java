package my.mySuperOsc;


import com.jsyn.JSyn;
import com.jsyn.unitgen.*;
import my.mySuperOsc.midi.MidiSetup;
import my.mySuperOsc.midi.ReceiverAdapter;
import my.mySuperOsc.midiToSynth.MidiToVoices;

import javax.sound.midi.MidiUnavailableException;

import static com.jsyn.JSyn.createSynthesizer;

public class Main {
    public static void main(String[] args) throws MidiUnavailableException {


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
        m.getSelectedDevice().open();


        m.connectMidiKeyboard(m.getSelectedDevice(),rc);
        System.out.println(m.getSelectedDevice().getTransmitters().size());

        ms.start();

        while (true) {
            ms.lineOut.flattenOutputs();
        }


    }



}