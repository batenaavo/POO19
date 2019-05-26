package umcarro.Controllers;


import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class DataBaseSaver {


    public DataBaseSaver() {
    }
    public static void serializeToXML (UMCarro settings) throws IOException
    {
        FileOutputStream fos = new FileOutputStream("./src/estado.xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(e -> System.out.println("Exception! :"+e.toString()));
        encoder.writeObject(settings);
        encoder.close();
        fos.close();
    }

    public static UMCarro deserializeFromXML() throws IOException {
        FileInputStream fis = new FileInputStream("./src/estado.xml");
        XMLDecoder decoder = new XMLDecoder(fis);
        UMCarro decodedState = (UMCarro) decoder.readObject();
        decoder.close();
        fis.close();
        return decodedState;
    }
}


