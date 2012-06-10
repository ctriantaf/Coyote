package yamp.ui;

import java.io.File;
import java.util.ArrayList;
import yamp.musiclibrary.StoringFiles;

public class FindThemes extends StoringFiles {

    public static ArrayList<String> themes = new ArrayList();
    
    public static ArrayList<String> findThemes() {
        File folder = new File(themesFolder);
        final File[] children = folder.listFiles();
        for ( File file : children ) {
            if ( file.isDirectory() ) {
                themes.add(String.valueOf(file.getName()));
            }
        }
        return themes;
    }
    
}