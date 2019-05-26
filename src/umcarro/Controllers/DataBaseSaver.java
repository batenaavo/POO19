package umcarro.Controllers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class DataBaseSaver {

    public DataBaseSaver() {
    }

    public void saveData(String filename, Object o) {
        try {
            FileOutputStream fos = new FileOutputStream(new File("./estado.xml"));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(o);
            encoder.flush();
            encoder.close();
        }
        catch (IOException e) {
            System.out.println("Falha ao gravar dados");
        }
    }

    public int loadData() throws FileNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(new File("./estado.xml"));
            XMLDecoder decoder = new XMLDecoder(fis);
            UMCarro p = (UMCarro)decoder.readObject();
            decoder.close();
            fis.close();
            return 1;

        } catch (IOException e) {
            System.out.println("Falha ao ler dados");
            return 0;
        }
    }

}
