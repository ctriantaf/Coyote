package yamp.musiclibrary;

public class StoringFiles {
    
    // Files for storing information
    final public static String username = System.getProperty("user.name");
    final public static String path = "/home/" + username + "/.config/yamp/";
    final public static String coverFolder = path + "covers";
    final public static String lyricsFolder = path + "lyrics";
    final public static String themesFolder = path + "themes";
    final public static String songs_locationsFile = path + "songs_locationsFile.data";
    final public static String artists_songsFile = path + "artists_songsFile.data";
    final public static String artists_albumsFile = path + "artists_albumsFile.data";
    final public static String namesFile = path + "namesFile.data";
    final public static String locationsFile = path + "locationsFile.data";
    final public static String artistsFile = path + "artistsFile.data";
    final public static String albumsFile = path + "albumsFile.data";
    final public static String coverPath = path + "coverexample.png";
    
}