package my.mySuperOsc.midiToSynth;

import com.jsyn.unitgen.LinearRamp;
import com.softsynth.shared.time.TimeStamp;
import my.mySuperOsc.soundEngines.MyVoice;

import javax.sound.midi.ShortMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static my.mySuperOsc.midiToSynth.MidiToSynthUtil.noteToFreq;
import static my.mySuperOsc.midiToSynth.MidiToSynthUtil.velToAmp;

public class MidiToVoices {
    List<MyVoice> voices;
    Map<NoteAndOrder,MyVoice> activeVoices = new HashMap<>();
    int maxVoices;
    int voiceIndexStart = 0;
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

        if(isFreeVoiceSlot()){
            var firstFound = findFirstFreeVoice();
            firstFound.noteOn(noteToFreq(note),velToAmp(vel), new TimeStamp(System.currentTimeMillis()));
            activeVoices.put(new NoteAndOrder(note,voiceIndexStart++),firstFound);
        } else {
            var firstFound =findFirstActiveVoice(note);
            removeSelectedFromActive(firstFound);
            firstFound.noteOn(noteToFreq(note),velToAmp(vel), new TimeStamp(System.currentTimeMillis()));
            activeVoices.put(new NoteAndOrder(note,0),firstFound);

        }

    }

    private void noteOff(int note) {
        activeVoices.forEach((k,v)-> System.out.println(k.note +" " + k.order + " " + v.getUuid()));
        var foundActive =getActiveVoiceByNote(note);
        if(foundActive!=null) {
            if (!activeVoices.isEmpty() && isFreeVoiceSlot()) {
                    voiceIndexStart--;
                    findVoiceById(getActiveVoiceByNote(note).getValue().getUuid()).noteOff(new TimeStamp(System.currentTimeMillis()));
            } else {
                voiceIndexStart--;
                voices.forEach(v -> v.noteOff(new TimeStamp(System.currentTimeMillis())));
            }
            activeVoices.remove(getActiveVoiceByNote(note).getKey());

        }



    }
    private boolean isFreeVoiceSlot(){
        return this.maxVoices>this.activeVoices.size();
    }
    private Map.Entry<NoteAndOrder,MyVoice >getActiveVoiceByNote(int note) {
        return activeVoices.entrySet().stream().filter(e -> e.getKey().note == note).findFirst().orElse(null);
    }
    private MyVoice findVoiceById(String id) {
        return voices.stream().filter(v -> v.getUuid().equals(id)).findFirst().orElse(null);
    }
    private MyVoice findFirstFreeVoice(){
       return this.voices.stream().filter(v -> !activeVoices.values().stream().map(MyVoice::getUuid).toList().contains(v.getUuid())).findFirst().orElse(voices.get(1));
    }
    private void removeSelectedFromActive(MyVoice voice){
        activeVoices.entrySet().removeIf(e->e.getValue().getUuid().equals(voice.getUuid()));
    }
    private MyVoice findFirstActiveVoice(int note){
        return activeVoices.entrySet().stream().filter(e -> e.getKey().order <= voiceIndexStart && e.getKey().note!=note).findFirst().orElse(null).getValue();
    }
public class NoteAndOrder {
        int note;
        int order;

    public NoteAndOrder(int note, int order) {
        this.note = note;
        this.order = order;
    }
}
}
