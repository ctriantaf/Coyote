package coyote.cover_lyrics;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import org.apache.commons.collections.MultiMap;


public class URLReader {
    
    public static String urlReader( String artist, int method, String albumFinal ) throws Exception {
        artist = artist.replace(" ", "+");
        albumFinal = albumFinal.replace(" ", "+");
        String address;
        String end;
        int beginelement;
        int endelement;
        String url = null;
                
        if ( method == 0 ) {
            address = "http://www.last.fm/music/" + artist + "/" + albumFinal;
            end = " class=\"art";
        } else {
            address = "http://www.last.fm/music/" + artist;
            end = "\" /></a>";
        }
        URL site = new URL(address);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(site.openStream()));

        String inputLine;
        ArrayList< String > lines = new ArrayList<String>();
        String begin = "http://";
        
        while ((inputLine = in.readLine()) != null)
            lines.add(inputLine);

        String[] urllines = { lines.get(404), lines.get(405), lines.get(406), 
            lines.get(407), lines.get(408), lines.get(409) };
        for ( String line : urllines ) {
            beginelement = line.indexOf(begin);
            endelement = line.indexOf(end);
            if ( beginelement == -1 || endelement == -1 ) {
                continue;
            }
            try {
                url = line.substring(beginelement, endelement);
            } catch ( StringIndexOutOfBoundsException ob ) {
                Cover_not_found.cover_not_found(albumFinal);
            }  
        }
        
        in.close();
        if ( url.startsWith("http://cdn") ) {
            url = null;
        }
        return url;
    }
}
