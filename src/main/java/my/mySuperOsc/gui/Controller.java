package my.mySuperOsc.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import my.mySuperOsc.gui.components.NeonComboBox;
import my.mySuperOsc.theSynth.MySynth;

public class Controller {

    private MySynth mySynth;

    public Controller() {
        this.mySynth = new MySynth();
    }

    public void getInternalInfo() {

        mySynth.getMs().listMidiDevices();

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


    public NeonComboBox startDevicesComboBox () {
        NeonComboBox comboBox = fillMidiDevicesInCombo();
        comboBox.getSelectionModel().selectedItemProperty().addListener((obsv,oldv,newv)->{
            if(newv!=null){
                comboBox.handleDeviceSelection((String) newv,mySynth);
            }
        });
        return comboBox;
    }




}
