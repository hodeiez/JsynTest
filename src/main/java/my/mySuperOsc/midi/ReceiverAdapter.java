package my.mySuperOsc.midi;

import my.mySuperOsc.MonoMidiToSynth;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class ReceiverAdapter implements Receiver {

    private ShortMessage midiMessage;
    private MonoMidiToSynth runner;
    public ShortMessage getMidiMessage() {
        return midiMessage;
    }

    public void setRunner(MonoMidiToSynth runner) {
        this.runner = runner;
    }

    @Override
    public void send(MidiMessage message, long timestamp) {
        if (message instanceof ShortMessage) {
            ShortMessage shortMsg = (ShortMessage) message;

            int command = shortMsg.getCommand();
            if (command == ShortMessage.NOTE_ON || command == ShortMessage.NOTE_OFF  ) {
               // System.out.println("Received MIDI event: Command=" + command + ", Note=" + noteNumber + ", Velocity=" + velocity);
                this.midiMessage=shortMsg;
                this.runner.midiToSound(shortMsg);
            }
        }
    }

    @Override
    public void close() {
    }

}