package coyote.cover_lyrics;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import coyote.musiclibrary.StoringFiles;

public class Cover_not_found extends StoringFiles {

    public static void cover_not_found( String album ) throws IOException {
        File src = new File(coverPath);
        String coverpath = coverFolder + album;
        File destination = new File(coverpath);
        FileUtils.copyFile(src, destination);
    }
    
}