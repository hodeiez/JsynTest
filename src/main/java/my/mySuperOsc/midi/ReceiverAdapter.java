package my.mySuperOsc.midi;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class ReceiverAdapter implements Receiver {

    private ShortMessage midiMessage;

    public ShortMessage getMidiMessage() {
        return midiMessage;
    }


    @Override
    public void send(MidiMessage message, long timestamp) {
        if (message instanceof ShortMessage) {
            ShortMessage shortMsg = (ShortMessage) message;

            int command = shortMsg.getCommand();
            if (command == ShortMessage.NOTE_ON || command == ShortMessage.NOTE_OFF  ) {
                int noteNumber = shortMsg.getData1();
                int velocity = shortMsg.getData2();

                System.out.println("Received MIDI event: Command=" + command + ", Note=" + noteNumber + ", Velocity=" + velocity);
                this.midiMessage=shortMsg;
            }
        }
    }

    @Override
    public void close() {
    }

}