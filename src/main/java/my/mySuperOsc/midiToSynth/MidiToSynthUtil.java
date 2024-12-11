package my.mySuperOsc.midiToSynth;

public class MidiToSynthUtil {
    static double noteToFreq(int note) {
        return 440f * Math.pow(2, (double) (note - 69) /12);
    }
    static double velToAmp(int vel,int oscillatorAmount) {
        return Math.max(0, Math.min(1, vel* 1.0f / 127)/oscillatorAmount);
    }
}
