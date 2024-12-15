package my.mySuperOsc.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import my.mySuperOsc.gui.components.NeonComboBox;
import my.mySuperOsc.soundEngines.OscillatorType;
import my.mySuperOsc.theSynth.MySynth;
import my.mySuperOsc.theSynth.SynthConfigurator;

import java.util.EnumSet;
import java.util.List;

public class Controller {

    private MySynth mySynth;

    public Controller() {
        this.mySynth = new MySynth();
    }

    public void getInternalInfo() {

        mySynth.setVoices(SynthConfigurator.buildOscillatorsToVoices(List.of(OscillatorType.SAW_OSC,OscillatorType.SINE_OSC)
                ,SynthConfigurator.buildVoicesByAmount(7)));
        mySynth.getMs().listMidiDevices();

    }
    public void selectVoiceAmount(int amount, List<OscillatorType> oscillatorTypes) {
        mySynth.setVoices(SynthConfigurator.buildOscillatorsToVoices(oscillatorTypes, SynthConfigurator.buildVoicesByAmount(amount)));

    }
    public void startSynth() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
              getInternalInfo();
                mySynth.runSynth();
                return null;
            }
        };
        new Thread(task).start();
    }
    public void closeSynth(){
        mySynth.stop();
    }

    //TODO: Move functions to component

    public NeonComboBox fillMidiDevicesInCombo(){
        ObservableList<String> devices = FXCollections.observableArrayList();
                devices.clear();
                mySynth.getMs().listMidiDevices().forEach((integer, midiDevice) -> devices.add(integer + "-" + midiDevice.getDeviceInfo().toString()));
        return new NeonComboBox(devices);
    }
    public NeonComboBox fillOscillatorsInCombo(){
        ObservableList<String> oscillators = FXCollections.observableArrayList();
        EnumSet.allOf(OscillatorType.class).stream().map(Enum::name).forEach(oscillators::add);
        return new NeonComboBox(oscillators);
    }

    public NeonComboBox startDevicesComboBox () {
        NeonComboBox comboBox = fillMidiDevicesInCombo();
        comboBox.getSelectionModel().selectedItemProperty().addListener((obsv,oldv,newv)->{
            if(newv!=null){
                comboBox.handleDeviceSelection((String) newv,mySynth);
            }
        });
        return comboBox;
    }
    public NeonComboBox startOscillatorsComboBox () {
        NeonComboBox comboBox = fillOscillatorsInCombo();
        comboBox.getSelectionModel().selectedItemProperty().addListener((obsv,oldv,newv)->{
            if(newv!=null){
                comboBox.handleOscillatorSelection((String) newv,mySynth);
            }
        });
        return comboBox;
    }




}
