package coyote.ui;

import com.trolltech.qt.core.QSize;
import com.trolltech.qt.gui.QPixmap;
import coyote.ui.CoyoteMainWindow;

public class ResizeImages extends CoyoteMainWindow {

    public static QPixmap resizeImages( String widget, String image ) {
        int height;
        int width;
        QSize size;
        QPixmap px = null;
        switch (widget) {
            case "albumsTable":
                {
                    QPixmap original = new QPixmap(image);
                    size = new QSize(albumsTable.width(), albumsTable.height());
                    px = new QPixmap(original.scaled(size));
                    break;
                }
            case "playlistTable":
                {
                    QPixmap original = new QPixmap(image);
                    size = new QSize(playlistTable.width(), playlistTable.height());
                    px = new QPixmap(original.scaled(size));
                    break;
                }
            case "libraryTree":
                {
                    QPixmap original = new QPixmap(image);
                    size = new QSize(libraryTree.width(), libraryTree.height());
                    px = new QPixmap(original.scaled(size));
                    break;
                }
        }
        
        return px;
    }
    
}