package my.mySuperOsc;

import com.jsyn.Synthesizer;
import com.jsyn.unitgen.*;
import my.mySuperOsc.midi.ReceiverAdapter;
import my.mySuperOsc.midiToSynth.MidiToVoices;
import my.mySuperOsc.soundEngines.MyVoice;

import java.util.List;

public class MySynth{
    ReceiverAdapter receiver;
    Synthesizer synth;
    LineOut lineOut;
    MidiToVoices midiToVoices;
    List<MyVoice> voices;

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
        this.midiToVoices = new MidiToVoices();
        var voice = new MyVoice();
        voice.setOscillators( List.of(new SawtoothOscillator(), new SquareOscillator(), new SineOscillator()));
        this.voices=List.of(voice);
        this.voices.forEach(v->v.getOscillators().forEach(osc ->synth.add(osc)));
        var lag = new LinearRamp();
        this.midiToVoices.setLag(lag);

        this.voices.forEach(v->v.getOscillators().forEach(osc->{
        osc.output.connect( 0, lineOut.input, 0 );
        osc.output.connect( 0, lineOut.input, 1 );
        }));
    }
}
