package yamp.ui;

import com.trolltech.qt.core.QSize;
import com.trolltech.qt.gui.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import yamp.ui.YampMainWindow;
import yamp.ui.LoadStyles;
import yamp.ui.ResizeImages;
import yamp.ui.ThemeConfig;

public class SetThemes extends YampMainWindow {    
    
    final static String username = System.getProperty("user.name");
    
    public static void setPixmaps( String theme) {
        String path =  "/home/" + username + "/.config/yamp/themes/" + theme;
        // Set Pixmaps for QIcons
        String playp = path + "/icons/play.png";
        String pausep = path + "/icons/pause.png";
        String stopp = path + "/icons/stop.png";
        String forwardp = path + "/icons/forward.png";
        String backp = path + "/icons/back.png";
        String shufflep = path + "/icons/shuffle.png";
        String replayp = path + "/icons/replay.png";
        String clear_playlistp = path + "/icons/clear_playlist.png";
        String albumsp = path + "/icons/albums.png";
        String playlistp = path + "/icons/playlist.png";
        String infop = path + "/icons/info.png";
          
        playPixmap = new QPixmap(playp);
        pausePixmap = new QPixmap(pausep);
        stopPixmap = new QPixmap(stopp);
        forwardPixmap = new QPixmap(forwardp);
        backPixmap = new QPixmap(backp);
        shufflePixmap = new QPixmap(shufflep);
        replayPixmap = new QPixmap(replayp);
        clear_playlistPixmap = new QPixmap(clear_playlistp);
        albumsPixmap = new QPixmap(albumsp);
        playlistPixmap = new QPixmap(playlistp);
        infoPixmap = new QPixmap(infop);
        
        playIcon = new QIcon(playPixmap);
        pauseIcon = new QIcon(pausePixmap);
        nextIcon = new QIcon(forwardPixmap);
        previousIcon = new QIcon(backPixmap);
        stopIcon = new QIcon(stopPixmap);
        shuffleIcon = new QIcon(shufflePixmap);
        replayIcon = new QIcon(replayPixmap);
        clear_playlistIcon = new QIcon(clear_playlistIcon);
        albumsIcon = new QIcon(albumsPixmap);
        infoIcon = new QIcon(infoPixmap);
        playlistIcon = new QIcon(playlistPixmap);
        
    }
    
    public static void setIcons() {
        play_pauseButton.setIcon(playIcon);
        play_pauseButton.setIconSize(playPixmap.rect().size());
        stopButton.setIcon(stopIcon);
        stopButton.setIconSize(stopPixmap.rect().size());
        nextButton.setIcon(nextIcon);
        nextButton.setIconSize(forwardPixmap.rect().size());
        previousButton.setIcon(previousIcon);
        previousButton.setIconSize(backPixmap.rect().size());
        replayButton.setIcon(replayIcon);
        replayButton.setIconSize(replayPixmap.rect().size());
        shuffleButton.setIcon(shuffleIcon);
        shuffleButton.setIconSize(shufflePixmap.rect().size());
        clearButton.setIcon(clear_playlistIcon);
        clearButton.setIconSize(clear_playlistPixmap.rect().size());
        albumButton.setIcon(albumsIcon);
        albumButton.setIconSize(albumsPixmap.rect().size());
        playlistButton.setIcon(playlistIcon);
        playlistButton.setIconSize(playlistPixmap.rect().size());
        infoButton.setIcon(infoIcon);
        infoButton.setIconSize(infoPixmap.rect().size());
        
    }
    
    public static void setTheme( String theme, QMainWindow mainwindow ) throws FileNotFoundException, IOException {
        LoadStyles css = new LoadStyles();
        String path =  "/home/" + username + "/.config/yamp/themes/" + theme;
        // Set Style Sheets for widgets
        String progressbarSheet = path + "/css/progressbar.css";
        String searchSheet = path + "/css/search.css";
        String volumeSliderSheet = path + "/css/volumeSlider.css";
        String splitterSheet = path + "/css/splitter.css";
        String seekSliderSheet = path + "/css/seekSlider.css";
        String controlsSheet = path + "/css/controls.css";
        String mainb = path + "/css/mainbackground.css";
        String library = path + "/css/library.css";
        String playlist = path + "/css/playlist.css";
        String header = path + "/css/headerview.css";
                
        // Check if stylesheet of playlistTable, albumsTable, libraryTree use images
        // and if if does resize the images
        String configpath = path + "/theme.config";
        
        String progress = css.loadStyles(progressbarSheet);
        String volume = css.loadStyles(volumeSliderSheet);
        String splitters = css.loadStyles(splitterSheet);
        String seek = css.loadStyles(seekSliderSheet);
        String controls = css.loadStyles(controlsSheet);
                
        ArrayList<String> config = ThemeConfig.getThemeConfig(configpath);
        if ( config != null ) {
            String[] configArray = new String[config.size()];
            config.toArray(configArray);
            for ( int i = 0; i < 3; i++ ) {
                String[] splitconfig = configArray[i].split(":");
                switch (splitconfig[0]) {
                    case "albumsTable":
                        {
                            QPalette palette = albumsTable.palette();
                            QPixmap a = ResizeImages.resizeImages("albumsTable", splitconfig[1]);
                            QBrush brush = new QBrush(a);
                            palette.setBrush(QPalette.ColorRole.Base, brush);
                            albumsTable.setPalette(palette);
                            break;
                        }
                    case "playlistTable":
                        {
                            QPalette palette = playlistTable.palette();
                            QPixmap a = ResizeImages.resizeImages("playlistTable", splitconfig[1]);
                            QBrush brush = new QBrush(a);
                            palette.setBrush(QPalette.ColorRole.Base, brush);
                            playlistTable.setPalette(palette);
                            break;
                        }
                    case "libraryTree":
                        {
                            System.out.println("ok");
                            QPalette palette = libraryTree.palette();
                            QPixmap a = ResizeImages.resizeImages("libraryTree", splitconfig[1]);
                            QBrush brush = new QBrush(a);
                            palette.setBrush(QPalette.ColorRole.Base, brush);
                            libraryTree.setPalette(palette);
                            break;
                        }
                }
            }
            String finalpath;
            for ( int i = 3; i < configArray.length; i++ ) {
                String[] splitconfig = configArray[i].split(":");
                finalpath = path + splitconfig[1];
                switch (splitconfig[0]) {
                    case "progressbar_image":
                        progress.replace("%image%", finalpath);
                        break;
                    case "seekSlider_cursor":
                        seek.replace("%cursor%", finalpath);
                        System.out.println("1");
                        break;
                    case "seekSlider_full":
                        seek.replaceAll("%full%", finalpath);
                        System.out.println("2");
                        break;
                    case "seekSlider_empty":
                        seek.replaceAll("empty", finalpath);
                        System.out.println("3");
                        System.out.println(seek);
                        break;
                    case "volumeSlider_cursor":
                        volume.replace("%cursor%", finalpath);
                        break;
                    case "volumeSlider_full":
                        volume.replace("%full%", finalpath);
                        break;
                    case "volumeSlider_empty":
                        volume.replace("%empty%", finalpath);
                        break;
                    case "splitter":
                        splitters.replace("%image%", finalpath);
                        break;
                    case "controls":
                        controls.replace("%image%", finalpath);
                        break;
                }
                
            }
        }
        
//        progressBar.setStyleSheet(css.loadStyles(progressbarSheet));
//        searchEdit.setStyleSheet(css.loadStyles(searchSheet));
//        volumeSlider.setStyleSheet(css.loadStyles(volumeSliderSheet));
//        splitter.setStyleSheet(css.loadStyles(splitterSheet));
//        songSeek.setStyleSheet(css.loadStyles(seekSliderSheet));
//        buttonsFrame.setStyleSheet(css.loadStyles(controlsSheet));
    }
    
}