package umcarro.Controllers;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class DataBaseSaver {


    public DataBaseSaver() {
    }

    public int loadData(String fileName, UMCarro o) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            o = (UMCarro)ois.readObject();
            System.out.println("Loaded Saved Status");
            ois.close();
            return 1;
        }
        catch (IOException|java.lang.ClassNotFoundException e) {
            } return 0;
        }


    public void saveData(String fileName, UMCarro o) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream(fileName)));
            oos.writeObject(o);
            oos.flush();
            oos.close();
        }
        catch (IOException e) {
            System.out.println("Failed to Save Status");
        }
    }}


