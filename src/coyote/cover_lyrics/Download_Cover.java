package coyote.cover_lyrics;

import java.awt.Image;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;

public class Download_Cover {
    
    static final String username = System.getProperty("user.name");
    static final String path = "/home/" + username + "/.config/coyote/covers";
    
    public static void downloadCover( String name, String imageurl ) throws IOException {     
        String imagefile = path + name + ".jpg"; 
        URL url = new URL(imageurl);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        
        int n = 0;
        while (-1!=(n=in.read(buf))) {
            out.write(buf, 0, n);
        }
        
        out.close();
        in.close();
        
        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream(imagefile);
        fos.write(response);
        fos.close();        
    }
    
}
