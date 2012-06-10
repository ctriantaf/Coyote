package yamp.cover_lyrics;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.io.FileUtils;
import java.net.SocketException;
import java.lang.StringIndexOutOfBoundsException;
import yamp.cover_lyrics.URLReader;
import yamp.cover_lyrics.Download_Cover;
import yamp.musiclibrary.Save_Load_Files;
import yamp.musiclibrary.StoringFiles;
import yamp.cover_lyrics.Cover_not_found;

public class GetCovers extends StoringFiles {
    
    static URLReader reader = new URLReader();
    static Download_Cover Dcover = new Download_Cover(); 
    static MultiMap map = new MultiHashMap();
    
    
    public static void getCovers() throws Exception {
        map = Save_Load_Files.ReadHashMap(artists_albumsFile);
        Iterator iter = map.keySet().iterator();
        while( iter.hasNext() ) {
            Object artist = iter.next();
            if ( artist == "Unknown" ) {
                Cover_not_found.cover_not_found("Uknown");
                continue;
            }
            Collection values = (Collection) map.get(artist);
            Iterator valuesIterator = values.iterator();
            while( valuesIterator.hasNext() ) {
                Object album = valuesIterator.next();
                String albumString = String.valueOf(album);
                int last = albumString.length() - 5;
                String albumFinal = (albumString.substring(1, last));
                String coverUrl;
                try {
                    coverUrl = reader.urlReader(String.valueOf(artist), 0, albumFinal);
                    if ( coverUrl == null) {
                        Cover_not_found.cover_not_found(albumFinal);
                    } else {
                        Dcover.downloadCover(String.valueOf(albumFinal), coverUrl);
                    }
                } catch ( SocketException e ) {
                    try {
                        coverUrl = reader.urlReader(String.valueOf(artist), 1, albumFinal);
                        if ( coverUrl == null) {
                            Cover_not_found.cover_not_found(albumFinal);
                        } else {
                            Dcover.downloadCover(String.valueOf(albumFinal), coverUrl);
                        }
                    } catch ( SocketException ex){
                        Cover_not_found.cover_not_found(albumFinal); 
                    }
                }
            }
        } 
    }
    
    public static void main(String[] args) throws Exception {
        getCovers();
    }
}
