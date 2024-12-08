package my.mySuperOsc;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.UnitOscillator;
import my.mySuperOsc.midi.ReceiverAdapter;

import javax.sound.midi.ShortMessage;

public class Runner {
    UnitOscillator oscillator;
    ReceiverAdapter receiverAdapter;

    public Runner() {

    }


    public void setOscillator(UnitOscillator oscillator) {
        this.oscillator = oscillator;
    }


    public void setReceiverAdapter(ReceiverAdapter receiverAdapter) {
        this.receiverAdapter = receiverAdapter;
    }

    public void run () {
     this.midiToSound();

    }

    public void midiToSound(){

        var message = receiverAdapter.getMidiMessage();

        if(message != null) {
        oscillator.frequency = new UnitInputPort("note", message.getData1()*10);
        if(message.getCommand() == ShortMessage.NOTE_OFF)
        {
            oscillator.amplitude = new UnitInputPort("Velocity", 0.);
        } else if (message.getCommand() == ShortMessage.NOTE_ON) {
            oscillator.amplitude = new UnitInputPort("Velocity", message.getData2()/50);
        }
        }
        }
}
