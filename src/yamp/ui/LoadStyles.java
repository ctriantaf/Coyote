package yamp.ui;

import java.io.*;

public class LoadStyles {
    
    public String loadStyles( String cssPath ) throws FileNotFoundException {
        FileInputStream fstream = new FileInputStream(cssPath);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder s = new StringBuilder();
        String line;

        try {
             while( (line = r.readLine()) != null ) {
                s.append(line);
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return s.toString();
    }

    
}
