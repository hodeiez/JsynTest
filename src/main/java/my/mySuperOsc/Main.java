package my.mySuperOsc;


import com.jsyn.JSyn;
import com.jsyn.unitgen.*;
import my.mySuperOsc.midi.MidiSetup;
import my.mySuperOsc.midi.ReceiverAdapter;

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
        var runner = new MonoMidiToSynth();
        runner.setOscillator(ms.oscillator);
        rc.setRunner(runner);
        m.getSelectedDevice().open();


        m.connectMidiKeyboard(m.getSelectedDevice(),rc);
        System.out.println(m.getSelectedDevice().getTransmitters().size());

        ms.start();

        while (true) {
            ms.lineOut.flattenOutputs();
        }

        /*
        var mid  = new MidiKeyboard();

        mid.run();


 */

        /*

        SawFaders applet = new SawFaders();
        JAppletFrame frame = new JAppletFrame( "SawFaders", applet );
        frame.setSize( 440, 200 );
        frame.setVisible( true );
        frame.test();


         */
        // my simple example, a white noise and a low saw osc
        /*
       Synthesizer synth =createSynthesizer();
        MyOsc osc = new MyOsc(new SawtoothOscillator());


        osc.saw.frequency = new UnitInputPort("a",44.);
        osc.saw.amplitude = new UnitInputPort("b",50.0);
        osc.saw.phase = new UnitVariablePort("b",10.0);
      synth.add(osc.saw);
        WhiteNoise whit = new WhiteNoise();
        var lineOut = new LineOut();
        synth.add( lineOut );
        osc.saw.output.connect( 0, lineOut.input, 1 );
        whit.output.connect( 0, lineOut.input, 0 );

        synth.add(whit);
        whit.start();
        osc.saw.start();
        synth.start(44100, AudioDeviceManager.USE_DEFAULT_DEVICE, 2, AudioDeviceManager.USE_DEFAULT_DEVICE,
                2);
        lineOut.start();
    */
    }



}