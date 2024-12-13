package my.mySuperOsc.theSynth;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.*;
import my.mySuperOsc.midi.MidiSetup;
import my.mySuperOsc.midi.ReceiverAdapter;
import my.mySuperOsc.midiToSynth.MidiToVoices;
import my.mySuperOsc.soundEngines.MyVoice;
import my.mySuperOsc.soundEngines.OscillatorType;

import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MySynth{
    private ReceiverAdapter receiver;
    private final Synthesizer synth;
    private final LineOut lineOut;
    private MidiToVoices midiToVoices;
    private List<MyVoice> voices = new ArrayList<>();
    private MidiSetup ms;

    public MySynth() {
        this.synth = JSyn.createSynthesizer();
        this.lineOut = new LineOut();
        this.receiver= new ReceiverAdapter();
        this.synth.add(this.lineOut);
        this.ms =new MidiSetup();
    }
    public void setVoices(List<MyVoice> voices){
        this.voices = voices;

    }
    public void addVoice(MyVoice voice){
       this.voices.add(voice);
        System.out.println(voice.getUuid());
    }
    public void stop(){
        synth.stop();
        lineOut.stop();
    }
    public MidiSetup getMs() {
        return ms;
    }

    public void setMs(MidiSetup ms) {
        this.ms = ms;
    }

    public LineOut getLineOut() {
        return lineOut;
    }

    public List<MyVoice> getVoices() {
        return voices;
    }

    public void setOscillatorsToVoices(List<UnitOscillator> oscillators){
        this.getVoices().forEach(v->v.setOscillators(oscillators));
    }

    public void start(){
        this.lineOut.start();
        this.synth.start();
    }
    public void setUp () {
        //we select on gui
       // ms.selectMidiDevice(6);

        this.midiToVoices = new MidiToVoices();
       this.voices.forEach(v->v.getOscillators().forEach(synth::add));

        var lag = new LinearRamp();
        this.midiToVoices.setLag(lag);

        this.voices.forEach(v->v.getOscillators().forEach(osc->{
        osc.output.connect( 0, lineOut.input, 0 );
        osc.output.connect( 0, lineOut.input, 1 );
        }));


        //set all oscillators gain 0. to start without noise :)
        this.voices.forEach(v->v.getOscillators().forEach(osc->osc.amplitude.set(0.)));
    }
    public void runSynth() {
        this.setUp();

        var runner = new MidiToVoices();
        runner.setVoices(getVoices());
        receiver.setRunner(runner);
        try {
            ms.getSelectedDevice().open();
            ms.connectMidiKeyboard(ms.getSelectedDevice(), receiver);
            System.out.println(ms.getSelectedDevice().getTransmitters().size());
        } catch (MidiUnavailableException e){
            System.out.println("WRONG SELECTED MIDI");
        }
        start();

        while (true) {
            getLineOut().flattenOutputs();
        }
    }
}
