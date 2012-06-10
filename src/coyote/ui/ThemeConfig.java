package coyote.ui;

import java.io.*;
import java.util.ArrayList;
import coyote.musiclibrary.StoringFiles;

public class ThemeConfig {

    public static ArrayList<String> getThemeConfig( String themeConfig ) throws IOException {
        FileInputStream fstream = new FileInputStream(themeConfig);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        ArrayList<String> lines = new ArrayList<>();
        String[] splitString;
        while ((strLine = br.readLine()) != null)   {
            splitString = strLine.split(":");
            if ( splitString[1] != "none" ) {
                lines.add(strLine);
            }  
        }
        in.close();
        return lines;
    }
    
}