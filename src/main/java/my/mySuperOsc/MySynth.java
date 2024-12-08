package my.mySuperOsc;

import com.jsyn.Synthesizer;
import com.jsyn.unitgen.*;
import my.mySuperOsc.midi.ReceiverAdapter;

import java.util.List;

public class MySynth{
    ReceiverAdapter receiver;
    Synthesizer synth;
    LineOut lineOut;
    MonoMidiToSynth monoMidiToSynth;
    List<UnitOscillator> oscillators;

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
        this.oscillators = List.of(new SawtoothOscillator(), new SquareOscillator());
        this.oscillators.forEach(osc ->synth.add(osc));
        var lag = new LinearRamp();
        this.monoMidiToSynth.setLag(lag);
        synth.add( lag);

        this.monoMidiToSynth.setOscillator(this.oscillators);

//        this.monoRunner.setReceiverAdapter(receiver);
        this.oscillators.forEach(osc->{
        osc.output.connect( 0, lineOut.input, 0 );
        osc.output.connect( 0, lineOut.input, 1 );
        });
    }
}
