package yamp.tags;

/**
 *
 * @author Chris Triantafillis <christriant1995@gmail.com> 2012
 * 
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagNotFoundException;

       
public class Tags {
    private AudioHeader AudioHeader;
    
    public Tag getTags( String songs ) {
        Tag tags;
            try {
                File song = new File( songs);
                AudioFile file;
                file = AudioFileIO.read(song);
                tags = file.getTag();
                AudioHeader = file.getAudioHeader();
                return tags;
            } catch (CannotReadException ex) {
                Logger.getLogger(Tags.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Tags.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TagException ex) {
                Logger.getLogger(Tags.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ReadOnlyFileException ex) {
                Logger.getLogger(Tags.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidAudioFrameException ex) {
                Logger.getLogger(Tags.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    
    public static void writeTags( String song, String key, String tag ) {
        try {
                File rsong = new File( song ); //song it the location of the selected file
                AudioFile file;
                file = AudioFileIO.read(rsong);
                Tag tags = file.getTag();
                if ( tag.toLowerCase() == "artist" ) {
                        tags.setField(FieldKey.ARTIST, tag);
                } else if ( tag.toLowerCase() == "album" ) {
                        tags.setField(FieldKey.ALBUM, tag);
                } else if ( tag.toLowerCase() == "title" ) {
                        tags.setField(FieldKey.TITLE, tag);
                } else if ( tag.toLowerCase() == "album artists" ) {
                        tags.setField(FieldKey.ALBUM_ARTIST, tag);
                } else if ( tag.toLowerCase() == "composer" ) {
                	tags.setField(FieldKey.COMPOSER, tag); 
                } else if ( tag.toLowerCase() == "genre" ) {
                        tags.setField(FieldKey.GENRE, tag);
                } else if ( tag.toLowerCase() == "year" ) {
                        tags.setField(FieldKey.YEAR, tag);
                }
                
            } catch (CannotReadException ex) {
                Logger.getLogger(Tags.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Tags.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TagException ex) {
                Logger.getLogger(Tags.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ReadOnlyFileException ex) {
                Logger.getLogger(Tags.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidAudioFrameException ex) {
                Logger.getLogger(Tags.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
