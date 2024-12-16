package my.mySuperOsc.theSynth.config;

import com.thoughtworks.xstream.XStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigRepo {

    public static void saveConfigToXML (ConfigModel config) throws FileNotFoundException {
        XStream xstream = new XStream();
        var xml =xstream.toXML(config);
        try (FileOutputStream fos = new FileOutputStream("myConfig.xml")) {
            fos.write(xml.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void readConfigXML () throws FileNotFoundException {
        XStream xstream = new XStream();
        ConfigModel deserialized= (ConfigModel) xstream.fromXML(new FileInputStream("myConfig.xml"));


    }
}
