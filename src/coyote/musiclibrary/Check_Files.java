package coyote.musiclibrary;

import java.io.File;
import java.io.IOException;
import coyote.musiclibrary.StoringFiles;

public class Check_Files extends StoringFiles {
    
    public static void check_files() throws IOException {

        String[] files = { songs_locationsFile, artists_songsFile, artists_albumsFile,
            namesFile, locationsFile, artistsFile, albumsFile };
        
        File dir = new File(path);
        boolean direxists = dir.exists();
        if (!direxists) {
            File directory = new File(path);
            directory.mkdir();
        }
        
        for ( int i = 0; i < files.length; i++ ) {
            File file = new File(files[i]);
            boolean fileexists = file.exists();
            
            if (!fileexists) {
                file.createNewFile();
            }
        }

    }
    
}
