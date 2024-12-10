package my.mySuperOsc.midiToSynth;

import com.jsyn.unitgen.LinearRamp;
import com.softsynth.shared.time.TimeStamp;
import my.mySuperOsc.soundEngines.MyVoice;

import javax.sound.midi.ShortMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static my.mySuperOsc.midiToSynth.MidiToSynthUtil.noteToFreq;
import static my.mySuperOsc.midiToSynth.MidiToSynthUtil.velToAmp;

public class MidiToVoices {
    List<MyVoice> voices;
    Map<Integer,MyVoice> activeVoices = new HashMap<>();
    int maxVoices;
    private LinearRamp lag = new LinearRamp();


    public MidiToVoices() {

    }

    public void setLag(LinearRamp lag) {
        this.lag = lag;
      lag.time.set(  0.05 );
    }

    public List<MyVoice> getVoices() {
        return voices;
    }

    public void setVoices(List<MyVoice> voices) {
        this.voices = voices;
        this.maxVoices = voices.size();
    }

    public void midiToSound(ShortMessage message) {
        if (message != null) {

            if (message.getCommand() == ShortMessage.NOTE_OFF) {
                this.noteOff(message.getData1());
            }
            if (message.getCommand() == ShortMessage.NOTE_ON) {

                this.noteOn(message.getData1(),message.getData2());

            }
        }
    }


    private void noteOn(int note,int vel) {
        if(!isFreeVoiceSlot()){
            voices.get(0).noteOn(noteToFreq(note),velToAmp(vel), new TimeStamp(System.currentTimeMillis()));
            activeVoices.values().removeIf(v->v.getUuid().equals(voices.get(0).getUuid()));
            activeVoices.put(note,voices.get(0));

        } else {
          var voicez =  voices.stream().filter(v -> activeVoices.values().stream().map(MyVoice::getUuid).toList().contains(v.getUuid())).toList();
          if(!voicez.isEmpty()){
              voicez.get(0).noteOn(noteToFreq(note),velToAmp(vel), new TimeStamp(System.currentTimeMillis()));
          } else {
              voices.get(0).noteOn(noteToFreq(note),velToAmp(vel), new TimeStamp(System.currentTimeMillis()));

          }
        }

    }

    private void noteOff(int note) {
        if (!activeVoices.isEmpty()) {
            findVoiceById(getActiveVoiceByNote(note).getUuid()).noteOff(new TimeStamp(System.currentTimeMillis()));
        } else {
            voices.get(0).noteOff(new TimeStamp(System.currentTimeMillis()));
        }

        activeVoices.remove(note);
    }
    private boolean isFreeVoiceSlot(){
        return this.maxVoices>=this.activeVoices.size();
    }
    private MyVoice getActiveVoiceByNote(int note) {
        return activeVoices.get(note);
    }
    private MyVoice findVoiceById(String id) {
        return voices.stream().filter(v -> v.getUuid().equals(id)).findFirst().orElse(null);
    }


}
