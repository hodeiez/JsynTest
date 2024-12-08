package my.mySuperOsc;

import com.jsyn.Synthesizer;
import com.jsyn.unitgen.*;
import my.mySuperOsc.midi.ReceiverAdapter;

public class MySynth{
    ReceiverAdapter receiver;
    Synthesizer synth;
    LineOut lineOut;
    MonoMidiToSynth monoMidiToSynth;
    UnitOscillator oscillator;

    public MySynth(ReceiverAdapter receiver, Synthesizer synth, LineOut lineOut) {
        this.receiver = receiver;
        this.synth = synth;
        this.lineOut = lineOut;
        this.synth.add(lineOut);
        this.setUp();
    }


    public void start(){
        this.lineOut.start();
        this.synth.start();
    }
    public void setUp () {
        this.monoMidiToSynth = new MonoMidiToSynth();
        var osc = new SawtoothOscillator();
        this.oscillator = osc;
        synth.add(osc);
        var lag = new LinearRamp();
        this.monoMidiToSynth.setLag(lag);
        synth.add( lag);

        this.monoMidiToSynth.setOscillator(osc);

//        this.monoRunner.setReceiverAdapter(receiver);
        osc.output.connect( 0, lineOut.input, 0 );
        osc.output.connect( 0, lineOut.input, 1 );
    }
}
