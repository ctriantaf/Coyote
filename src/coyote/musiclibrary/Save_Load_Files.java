package coyote.musiclibrary;

import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import org.apache.commons.collections.MultiMap;

public class Save_Load_Files {
    
    public static void WriteArray(String file, String[] array) {
        try {
            Formatter output = new Formatter(file);
            for (int i = 0; i < array.length; i++) {
               output.format("%s \n", array[i]);
            }
            output.close();
        } catch (SecurityException se) {
             System.err.println("You dont have the permission to open the file.");
        } catch (FileNotFoundException fe) {
            System.err.println("File not found.");
        }
    }

    public static String[] ReadArray(String path) throws FileNotFoundException {
        ArrayList< String> rarray = new ArrayList();
        Scanner input = new Scanner(new File(path));
        while (input.hasNext()) {
            rarray.add(input.nextLine());
        }
        input.close();
        return (String[]) rarray.toArray();
    }
        
    public static void WriteHashMap( String file, MultiMap hashmap ) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream( 
                new FileOutputStream(file));
        output.writeObject(hashmap);
        output.close();
    }
    
    public static MultiMap ReadHashMap( String path ) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(path));
        MultiMap content = (MultiMap) input.readObject();
        return content;
    }
    
}
