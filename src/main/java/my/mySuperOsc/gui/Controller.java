package my.mySuperOsc.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import my.mySuperOsc.gui.components.NeonComboBox;
import my.mySuperOsc.soundEngines.OscillatorType;
import my.mySuperOsc.theSynth.MySynth;
import my.mySuperOsc.theSynth.config.ConfigModel;
import my.mySuperOsc.theSynth.config.ConfigRepo;
import my.mySuperOsc.theSynth.config.SynthConfigurator;

import java.io.FileNotFoundException;
import java.util.EnumSet;
import java.util.List;

public class Controller {

    private MySynth mySynth;
    private ConfigModel configModel;

    public Controller() {
        this.mySynth = new MySynth();
        this.configModel = new ConfigModel();
    }

    public void getConfigInfo() {

        mySynth.setVoices(SynthConfigurator.buildOscillatorsToVoices(List.of(configModel.oscillatorType(configModel.getOscType1()),configModel.oscillatorType(configModel.getOscType2()))
                ,SynthConfigurator.buildVoicesByAmount(configModel.getVoiceAmount())));
        mySynth.getMs().listMidiDevices();

            new Thread(()-> {
                try {
                    ConfigRepo.saveConfigToXML(configModel);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }).start();


    }
    public void selectVoiceAmount(int amount) {
        //mySynth.setVoices(SynthConfigurator.buildOscillatorsToVoices(oscillatorTypes, SynthConfigurator.buildVoicesByAmount(amount)));
        configModel.setVoiceAmount(amount);
    }
    public void startSynth() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
              getConfigInfo();
                mySynth.runSynth();
                return null;
            }
        };
        new Thread(task).start();
    }
    public void closeSynth(){
        mySynth.stop();
    }


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
    public NeonComboBox startOscillatorsComboBox (String numb) {
        NeonComboBox comboBox = fillOscillatorsInCombo();
        comboBox.getSelectionModel().selectedItemProperty().addListener((obsv,oldv,newv)->{
            if(newv!=null){
                handleOscillatorSelection((String) newv,numb);
            }
        });
        return comboBox;
    }

    public void handleOscillatorSelection(String selected, String oscillatorNumb){
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                System.out.println(selected);
                System.out.println(oscillatorNumb);

                if(oscillatorNumb.equals("1")) {
                    configModel.setOscType1(selected);
                }else {
                    configModel.setOscType2(selected);
                }
                return null;
            }
        };
        new Thread(task).start();
        task.setOnSucceeded(e-> Platform.runLater(()-> System.out.println(selected + " selected")));
        task.setOnFailed(e-> System.out.println("fucked up selecting"));
    }


}
