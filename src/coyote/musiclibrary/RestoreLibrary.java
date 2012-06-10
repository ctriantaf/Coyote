package coyote.musiclibrary;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import coyote.musiclibrary.StoringFiles;
import coyote.musiclibrary.Save_Load_Files;

public class RestoreLibrary extends StoringFiles {
    
    public static MultiMap restoreLibrary() throws FileNotFoundException, IOException, ClassNotFoundException {
        // Add check about library view, only artists or artists and songs?
        Save_Load_Files slLibrary = new Save_Load_Files();
        MultiMap map = new MultiHashMap();
        map = slLibrary.ReadHashMap(artists_songsFile);
        return map;
    }
    
}
