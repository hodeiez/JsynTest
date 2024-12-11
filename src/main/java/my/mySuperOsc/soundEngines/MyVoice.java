package my.mySuperOsc.soundEngines;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.unitgen.UnitVoice;
import com.softsynth.shared.time.TimeStamp;

import java.util.List;
import java.util.UUID;

public class MyVoice implements UnitVoice {
    List<UnitOscillator> oscillators;
    List<EnvelopeDAHDSR> envelopes;
    String uuid;

    public int getOscillatorAmount() {
        return oscillators.size();
    }

    public MyVoice() {
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public List<UnitOscillator> getOscillators() {
        return oscillators;
    }

    public void setOscillators(List<UnitOscillator> oscillators) {
        this.oscillators = oscillators;
    }

    public List<EnvelopeDAHDSR> getEnvelopes() {
        return envelopes;
    }

    public void setEnvelopes(List<EnvelopeDAHDSR> envelopes) {
        this.envelopes = envelopes;
    }

    @Override
    public void noteOn(double frequency, double amplitude, TimeStamp timeStamp) {
        this.oscillators.forEach(osc->{
            osc.amplitude.set(amplitude);
            osc.frequency.set(frequency);
        });
    }

    @Override
    public void noteOff(TimeStamp timeStamp) {
        this.oscillators.forEach(osc->osc.frequency.set(0.));
        this.oscillators.forEach(UnitGenerator::stop);
    }

    @Override
    public UnitOutputPort getOutput() {
        return null;
    }

    @Override
    public UnitGenerator getUnitGenerator() {
            return null;
    }

    @Override
    public void setPort(String portName, double value, TimeStamp timeStamp) {

    }

    @Override
    public void usePreset(int presetIndex) {

    }
}
