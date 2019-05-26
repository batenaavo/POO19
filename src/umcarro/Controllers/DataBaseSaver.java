package umcarro.Controllers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class DataBaseSaver {

    public DataBaseSaver() {
    }

    public void saveData(Object o) {
        try {
            FileOutputStream fos = new FileOutputStream(new File("./src/state.xml"));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(o);
            encoder.flush();
            encoder.close();
        }
        catch (IOException e) {
            System.out.println("Falha ao gravar estado");
        }
    }

    public int loadData() throws FileNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(new File("./src/estado.xml"));
            XMLDecoder decoder = new XMLDecoder(fis);
            UMCarro p = (UMCarro) decoder.readObject();
            decoder.close();
            fis.close();
            return 1;

        } catch (IOException e) {
            System.out.println("Falha ao ler estado gravado anteriormente");
            return 0;
        }
    }

}
