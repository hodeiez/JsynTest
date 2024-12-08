package my.mySuperOsc;

import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SawtoothOscillatorBL;
import my.mySuperOsc.midi.ReceiverAdapter;

public class MySynth{
    ReceiverAdapter receiver;
    Synthesizer synth;
    LineOut lineOut;
    Runner runner;

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
        this.runner = new Runner();
        var osc = new SawtoothOscillatorBL();
        synth.add(osc);
        this.runner.setOscillator(osc);
        this.runner.setReceiverAdapter(receiver);
        osc.output.connect( 0, lineOut.input, 0 );
        osc.output.connect( 0, lineOut.input, 1 );
    }
}
