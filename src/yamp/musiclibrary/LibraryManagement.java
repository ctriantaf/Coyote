package yamp.musiclibrary;

import java.io.File;
import java.lang.NullPointerException;
import java.io.IOException;
import java.util.*;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections.MultiHashMap;
import org.jaudiotagger.tag.Tag;
import yamp.tags.Tags;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.jaudiotagger.tag.FieldKey;

import yamp.musiclibrary.Save_Load_Files;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.KeyNotFoundException;
import yamp.musiclibrary.Save_Load_Files;
import yamp.musiclibrary.StoringFiles;

public class LibraryManagement extends StoringFiles {
    
    // ArrayList's to make Lists with various informations
    ArrayList<String> filesNames = new ArrayList<String>();
    ArrayList<String> filesLocations = new ArrayList<String>();
    ArrayList<String> artistsList = new ArrayList<String>();
    ArrayList<String> albumsList = new ArrayList<String>();
   
    // Music files extensions
    final String[] Extensions = { "mp3", "mp4", "ogg", "flac", "wma", "wav" };
    
    // MultiMap's for storing informations
    //
    // I made them MultiHashMap because i wanted
    // a serializable object to be able to store it 
    HashSet artistHash = new HashSet();
    MultiMap artists_songs = new MultiHashMap();
    MultiMap songs_locations = new MultiHashMap();
    MultiMap locations_songs = new MultiHashMap();
    MultiMap artists_albums = new MultiHashMap();
    
    // Variables used to scan library
    final ArrayList<File> all = new ArrayList<File>();
    final ArrayList<File> songs = new ArrayList<File>();
    
    
    // Constructors
    Tags tags = new Tags();    
    static LibraryManagement Library = new LibraryManagement();
    static Save_Load_Files slLibrary = new Save_Load_Files();
     
    
    private static ArrayList addFilesRecursively(File file, ArrayList<File> all) {
        final File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                all.add(child);
                addFilesRecursively(child, all);
            }
        }
        return all;
    }
    
    public File[] splitFiles(ArrayList<File> all) {
        File[] allArray = new File[all.size()];
        all.toArray(allArray);
        for ( int i = 0; i < allArray.length; i++ ) {
            if ( allArray[i].isDirectory() == false) {
                String ext = Library.GetFileExtension(allArray[i].getName());
                if ( Arrays.asList(Extensions).contains(ext) == true ) {
                    songs.add(allArray[i]);
                }
            }
        }
        File[] songsArray = new File[songs.size()];
        songs.toArray(songsArray);
        return songsArray;
    }
    
    public String[] getInfo(File[] songs) throws IOException {
        for ( int i = 0; i < songs.length; i++ ) {
            filesNames.add(songs[i].getName());
            filesLocations.add(songs[i].getAbsolutePath());
            songs_locations.put(songs[i].getName(), songs[i].getAbsolutePath());
            locations_songs.put(songs[i].getAbsolutePath(), songs[i].getName());
            }      
        saveArray(locationsFile, filesLocations);
        saveArray(namesFile, filesNames);
        
        slLibrary.WriteHashMap( songs_locationsFile, songs_locations );
        
        String[] locations = new String[filesLocations.size()];
        filesLocations.toArray(locations);
        return locations;
    }
    
    public MultiMap categorize( String[] songs ) throws IOException {
        for ( int i = 0; i < songs.length; i++ ) {
            Tag songtags = tags.getTags( songs[i] );
            if (songtags == null) {
                continue;
            }
            String artist;
            String album;
            artist = songtags.getFirst(FieldKey.ARTIST);
            if ( artist == "" ) {
                artist = "Unknown";
            }
            album = songtags.getFirst(FieldKey.ALBUM);
            if ( album == "" ) {
                album = "Unknown";
            }
            artists_songs.put(artist, locations_songs.get(songs[i]));
            artistsList.add(artist);
            artists_albums.put(artist, album);
            albumsList.add(album);
        }
        artistHash.addAll(artistsList);
        artistsList.clear();
        artistsList.addAll(artistHash);
        Collections.sort(artistsList);
                
        saveArray( artistsFile, artistsList );
        saveArray( albumsFile, albumsList);
        slLibrary.WriteHashMap(artists_songsFile, artists_songs);
        slLibrary.WriteHashMap(artists_albumsFile, artists_albums);
        
        return artists_songs;
    }
    

    public String GetFileExtension ( String filename ) {
        int pos = filename.lastIndexOf('.');
        String ext = filename.substring(pos+1);
        
        return ext;
    }
    
    static private void saveArray( String file, ArrayList list ) {
        String[] array = new String[list.size()];
        list.toArray(array);
        slLibrary.WriteArray(file, array);
    }
    
    public ArrayList library() throws IOException {
        String dir = "/home/chris/yamp";
        final ArrayList<File> all = new ArrayList<File>();
        Library.addFilesRecursively(new File(dir), all);
        return all;
    }
}
