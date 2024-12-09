package my.mySuperOsc;

import com.jsyn.unitgen.LinearRamp;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitOscillator;

import javax.sound.midi.ShortMessage;
import java.util.Arrays;
import java.util.List;

public class MonoMidiToSynth {
    List<UnitOscillator> oscillators;
    private final double[] activeNotes = new double[129];
    private int activeNoteCount = 0;
    private LinearRamp lag = new LinearRamp();


    public MonoMidiToSynth() {

    }

    public void setLag(LinearRamp lag) {
        this.lag = lag;
      lag.time.set(  0.05 );
    }
    public void setOscillators(List<UnitOscillator> oscillators) {
        this.oscillators = oscillators;
    }

    public void midiToSound(ShortMessage message) {


        if (message != null) {

            if (message.getCommand() == ShortMessage.NOTE_OFF) {
                this.noteOff(message.getData1());
               // this.lag.input.setup( 0.0, 0., 1.0 );
            }
            if (message.getCommand() == ShortMessage.NOTE_ON) {

                this.noteOn(message.getData1(),message.getData2());

            }
        }
    }

    private void setOsc(int note,int vel){
        this.oscillators.forEach(oscillator ->{
            oscillator.frequency.set(noteToFreq(note));
            oscillator.amplitude.set(velToAmp(vel));
        });

    }

    private void noteOn(int note,int vel) {
        System.out.println(activeNoteCount);
        if (activeNoteCount < activeNotes.length) {
            activeNotes[activeNoteCount] = note;

            activeNoteCount++;
            setOsc(note,vel);
        }
    }

    private void noteOff(int note) {
        System.out.println(activeNoteCount);
        setOsc(note,0);
       Arrays.stream(activeNotes).forEach(System.out::println);
        for (int i = 0; i < activeNoteCount; i++) {
            if (activeNotes[i] == note) {

                activeNotes[i] = -1;
                break;
            }
        }
        int newCount = 0;
        for (double n : activeNotes) {
            if (n != -1) {
                activeNotes[newCount++] = n;
            }
        }
        System.arraycopy(activeNotes, 0, activeNotes, 0, newCount);
        activeNoteCount = newCount;

        if (activeNoteCount == 0) {
            this.oscillators.forEach(UnitGenerator::stop);
        }
    }
    private double noteToFreq(int note) {
        return 440f * Math.pow(2, (double) (note - 69) /12);
    }
    private double velToAmp(int vel) {
        return Math.max(0, Math.min(1, vel* 1.0f / 127));
    }
}
