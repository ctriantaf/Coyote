package coyote;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.collections.MultiMap;
import coyote.ui.CoyoteMainWindow;
import coyote.musiclibrary.LibraryManagement;

public class CoyoteLibrary {
    
    static CoyoteMainWindow coyoteMainWindow = new CoyoteMainWindow();
    static LibraryManagement mlibrary = new LibraryManagement();
    
        
    public static MultiMap coyoteLibrary() throws IOException {
        ArrayList<File> all = mlibrary.library();
        File[] songsArray = mlibrary.splitFiles(all);
        String[] locations = mlibrary.getInfo(songsArray);
        MultiMap artists_songs = mlibrary.categorize(locations);
        return artists_songs;
    }
    
}