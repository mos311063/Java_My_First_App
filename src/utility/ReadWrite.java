package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import obj.User;

/**
 * class to deal with create and save bin file that store Appointment object
 *
 * @author Moss
 */
public class ReadWrite {

    static final String FILE = "data.bin";

    /**
     * read Appointment list to data.bin
     *
     * @param list
     * @return
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws NotSerializableException
     */
    public static ArrayList<User> readData(ArrayList<User> list) throws FileNotFoundException,
            ClassNotFoundException, IOException, NotSerializableException {
        try {

            if (new File(FILE).exists()) { //if file not exist create one
                FileInputStream fis = new FileInputStream(FILE);
                ObjectInputStream ois = new ObjectInputStream(fis);
                list = (ArrayList<User>) ois.readObject();
                ois.close();
            } else {
                return list;
            }

        } catch (FileNotFoundException fnfE) {
            System.err.print("problem with file not found");
        } catch (NotSerializableException nsE) {
            System.err.print("problem with serialised");
        } catch (ClassNotFoundException cnfE) {
            System.err.print("problem with Car Class");
        } catch (IOException ioE) {
            System.err.print("problem with reading data from file");
        }
        return list;

    }

    /**
     * save Appointment list to data.bin
     *
     * @param list
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NotSerializableException
     */
    public static void saveData(ArrayList<User> list) throws FileNotFoundException, IOException,
            NotSerializableException {

        try {
            FileOutputStream fos = new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
        } catch (FileNotFoundException fnfE) {
            System.err.print("problem with file not found");
        } catch (NotSerializableException nsE) {
            System.err.print("problem with serialised");
        } catch (IOException ioE) {
            System.err.print("problem with saving data to file");
        }

    }

}
