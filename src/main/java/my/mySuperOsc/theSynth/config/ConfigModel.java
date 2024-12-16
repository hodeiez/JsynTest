package my.mySuperOsc.theSynth.config;

import my.mySuperOsc.soundEngines.OscillatorType;

public class ConfigModel {
    private int selectedDevice;
    private int voiceAmount;
    private String oscType1;
    private String oscType2;

    public ConfigModel() {
    }

    public int getSelectedDevice() {
        return selectedDevice;
    }

    public void setSelectedDevice(int selectedDevice) {
        this.selectedDevice = selectedDevice;
    }

    public int getVoiceAmount() {
        return voiceAmount;
    }

    public void setVoiceAmount(int voiceAmount) {
        this.voiceAmount = voiceAmount;
    }

    public String getOscType1() {
        return oscType1;
    }

    public void setOscType1(String oscType1) {
        this.oscType1 = oscType1;
    }

    public String getOscType2() {
        return oscType2;
    }

    public void setOscType2(String oscType2) {
        this.oscType2 = oscType2;
    }
    public OscillatorType oscillatorType(String oscType){
        return switch (oscType) {
            case "SAW_OSC" -> OscillatorType.SAW_OSC;
            case "SINE_OSC" -> OscillatorType.SINE_OSC;
            case "SQUARE_OSC" -> OscillatorType.SQUARE_OSC;

            default -> throw new IllegalStateException("Unexpected value: " + oscType);
        };
    }
}
