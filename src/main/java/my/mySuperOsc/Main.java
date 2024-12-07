package my.mySuperOsc;


import com.jsyn.Synthesizer;
import com.jsyn.devices.AudioDeviceManager;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitVariablePort;
import com.jsyn.swing.JAppletFrame;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.WhiteNoise;

import static com.jsyn.JSyn.createSynthesizer;

public class Main {
    public static void main(String[] args) {
        SawFaders applet = new SawFaders();
        JAppletFrame frame = new JAppletFrame( "SawFaders", applet );
        frame.setSize( 440, 200 );
        frame.setVisible( true );
        frame.test();
        /*
       Synthesizer synth =createSynthesizer();
        MyOsc osc = new MyOsc(new SawtoothOscillator());


        osc.saw.frequency = new UnitInputPort("a",4400.);
        osc.saw.amplitude = new UnitInputPort("b",100.0);
        osc.saw.phase = new UnitVariablePort("b",100.0);
      synth.add(osc.saw);
        WhiteNoise whit = new WhiteNoise();

        synth.add(whit);
        whit.start();
        osc.saw.start();
        synth.start(44100, AudioDeviceManager.USE_DEFAULT_DEVICE, 2, AudioDeviceManager.USE_DEFAULT_DEVICE,
                2);

*/
    }
}