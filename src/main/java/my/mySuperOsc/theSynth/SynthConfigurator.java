package my.mySuperOsc.theSynth;

import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.UnitOscillator;
import my.mySuperOsc.soundEngines.MyVoice;
import my.mySuperOsc.soundEngines.OscillatorType;

import java.util.ArrayList;
import java.util.List;

public class SynthConfigurator {

    public static List<MyVoice> buildVoicesByAmount(int voicesAmount){
        List <MyVoice> voices = new ArrayList<>();
        for (int i = 0; i < voicesAmount; i++) {
            voices.add(new MyVoice());
        }
        return voices;
    }
    public static List<MyVoice>  buildOscillatorsToVoices(List<OscillatorType> oscTypes, List<MyVoice> voices){
         voices.forEach(v->v.setOscillators(oscTypes.stream().map(SynthConfigurator::createOscillatorByType).toList()));
         return voices;
    }

    private static UnitOscillator createOscillatorByType(OscillatorType type) {
        return switch (type) {
            case SAW_OSC -> new SawtoothOscillator();
            case SINE_OSC -> new SineOscillator();
            case SQUARE_OSC -> new SquareOscillator();
        };
    }

}
