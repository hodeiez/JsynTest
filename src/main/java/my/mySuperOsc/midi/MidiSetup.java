package my.mySuperOsc.midi;

import javax.sound.midi.*;

import java.util.HashMap;
import java.util.Map;

public class MidiSetup {
    MidiDevice selectedDevice;

    public MidiDevice getSelectedDevice() {
        return selectedDevice;
    }

    public Map<Integer,MidiDevice> listMidiDevices(){

        Map<Integer,MidiDevice> devices = new HashMap<>();
        MidiDevice.Info[] info = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < info.length; i++) {
            try {
                MidiDevice device = MidiSystem.getMidiDevice(info[i]);
                devices.put(i,device);
                System.out.println((i + 1) + ". Name: " + device.getDeviceInfo().getName());
            } catch (MidiUnavailableException e) {
                System.out.println("Error accessing device: " + e.getMessage());
            }
        }
        return devices;
    }

    public void setMidiDevice(MidiDevice device){
        this.selectedDevice = device;
    }
    public void selectMidiDevice(Integer index,Map<Integer,MidiDevice> devices) {
        setMidiDevice(devices.get(index));
    }
    public void connectMidiKeyboard(MidiDevice midiDevice, ReceiverAdapter rc) throws MidiUnavailableException {
        midiDevice.open();

        Transmitter transmitter = midiDevice.getTransmitter();

        transmitter.setReceiver(rc);

        System.out.println("Connected MIDI keyboard controller successfully.");
    }





}
