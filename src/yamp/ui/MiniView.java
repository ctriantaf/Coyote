package yamp.ui;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.phonon.SeekSlider;
import yamp.ui.LoadStyles;


public class MiniView {

    QToolButton mplay_pauseButton = new QToolButton();
    QToolButton mstopButton = new QToolButton();
    QToolButton mnextButton = new QToolButton();
    QToolButton mpreviousButton = new QToolButton();
    QToolButton mreplayButton = new QToolButton();
    QToolButton mshuffleButton = new QToolButton();
    QToolButton mclearButton = new QToolButton();
    QToolButton malbumButton = new QToolButton();
    QToolButton mplaylistButton = new QToolButton();
    QToolButton minfoButton = new QToolButton();
    QToolButton closeButton = new QToolButton();
    QToolButton mainviewButton = new QToolButton();
    
    QSlider mvolumeSlider = new QSlider(Qt.Orientation.Horizontal);
    
    SeekSlider msongSeek = new SeekSlider();
    
    QFrame frame = new QFrame();
    
    QLabel song_name = new QLabel("Song name");
    QLabel artists_album_name = new QLabel("Album artists name");
    QLabel mvolumeLabel = new QLabel();
    QLabel albumIcon = new QLabel();
    QLabel timer = new QLabel("00:00:00");
    
    QFont font = new QFont();
    
    static LoadStyles css = new LoadStyles();
    
    public void miniView( QMainWindow miniview ) {
              
        font.setPointSize(15);
        song_name.setFont(font);
        
        // Icons
        QPixmap mplayPixmap = new QPixmap("classpath:/yamp/images/play.png");
        QPixmap mpausePixmap = new QPixmap("classpath:/yamp/images/pause.png");
        QPixmap mstopPixmap = new QPixmap("classpath:/yamp/images/stop.png");
        QPixmap mforwardPixmap = new QPixmap("classpath:/yamp/images/forward.png");
        QPixmap mbackPixmap = new QPixmap("classpath:/yamp/images/back.png");
        QPixmap mshufflePixmap = new QPixmap("classpath:/yamp/images/dice.png");
        QPixmap mreplayPixmap = new QPixmap("classpath:/yamp/images/replay_miniview.png");
        QPixmap mclear_playlistPixmap = new QPixmap("classpath:/yamp/images/clear_playlist.png");
        QPixmap micon = new QPixmap("classpath:/yamp/images/cover.png");
        QPixmap mplaylistPixmap = new QPixmap("classpath:/yamp/images/playlist_miniview.png");
        QPixmap closePixmap = new QPixmap("classpath:/yamp/images/miniview_close.png");
        QPixmap miniview_maximize = new QPixmap("classpath:/yamp/images/miniview_maximize.png");
        QPixmap mute = new QPixmap("classpath:/home/chris/.config/yamp/themes/light/icons/volume_off.png");
        QPixmap volume1 = new QPixmap("/home/chris/.config/yamp/themes/light/icons/volume_1line.png");
        QPixmap volume2 = new QPixmap("/home/chris/.config/yamp/themes/light/icons/volume_2lines.png");
        QPixmap volumefull = new QPixmap("classpath:/yamp.images/volume_full.png");
        
        QIcon mplayIcon = new QIcon(mplayPixmap);
        QIcon mpauseIcon = new QIcon(mpausePixmap);
        QIcon mnextIcon = new QIcon(mforwardPixmap);
        QIcon mpreviousIcon = new QIcon(mbackPixmap);
        QIcon mstopIcon = new QIcon(mstopPixmap);
        QIcon mshuffleIcon = new QIcon(mshufflePixmap);
        QIcon mreplayIcon = new QIcon(mreplayPixmap);
        QIcon mclear_playlistIcon = new QIcon(mclear_playlistPixmap);
        QIcon mplaylistIcon = new QIcon(mplaylistPixmap);
        QIcon closeIcon = new QIcon(closePixmap);
        QIcon miniview_maximizeIcon = new QIcon(miniview_maximize);

        // Buttons 
        mplay_pauseButton.setIcon(mplayIcon);
        mplay_pauseButton.setIconSize(mplayPixmap.rect().size());
        mplay_pauseButton.setAutoRaise(true);
        
        mstopButton.setIcon(mstopIcon);
        mstopButton.setIconSize(mstopPixmap.rect().size());
        mstopButton.setAutoRaise(true);
        
        mnextButton.setIcon(mnextIcon);
        mnextButton.setIconSize(mforwardPixmap.rect().size());
        mnextButton.setAutoRaise(true);
        
        mpreviousButton.setIcon(mpreviousIcon);
        mpreviousButton.setIconSize(mbackPixmap.rect().size());
        mpreviousButton.setAutoRaise(true);
        
        mreplayButton.setIcon(mreplayIcon);
        mreplayButton.setIconSize(mreplayPixmap.rect().size());
        mreplayButton.setAutoRaise(true);
        
        mshuffleButton.setIcon(mshuffleIcon);
        mshuffleButton.setIconSize(mshufflePixmap.rect().size());
        mshuffleButton.setAutoRaise(true);
        
        mclearButton.setIcon(mclear_playlistIcon);
        mclearButton.setIconSize(mclear_playlistPixmap.rect().size());
        mclearButton.setAutoRaise(true);
        
        mplaylistButton.setCheckable(true);
        mplaylistButton.setIcon(mplaylistIcon);
        mplaylistButton.setIconSize(mplaylistPixmap.rect().size());
        mplaylistButton.setAutoRaise(true);
        
        closeButton.setIcon(closeIcon);
        closeButton.setIconSize(closePixmap.rect().size());
        closeButton.setAutoRaise(true);
        
        mainviewButton.setIcon(miniview_maximizeIcon);
        mainviewButton.setIconSize(miniview_maximize.rect().size());
        mainviewButton.setAutoRaise(true);
        
        albumIcon.setPixmap(micon);
        mvolumeLabel.setPixmap(volumefull);
        
        QVBoxLayout controlButtonsLayout = new QVBoxLayout();
        controlButtonsLayout.addWidget(mainviewButton, 0, Qt.AlignmentFlag.AlignTop);
        controlButtonsLayout.addWidget(closeButton, 0, Qt.AlignmentFlag.AlignBottom);
        
        QHBoxLayout buttonsLayout = new QHBoxLayout();
        buttonsLayout.addWidget(mpreviousButton);
        buttonsLayout.addWidget(mplay_pauseButton);
        buttonsLayout.addWidget(mstopButton);
        buttonsLayout.addWidget(mnextButton);
        
        QVBoxLayout vlayout1 = new QVBoxLayout();
        vlayout1.addWidget(artists_album_name);
        vlayout1.addLayout(buttonsLayout);
        
        QHBoxLayout volumeLayout = new QHBoxLayout();
        volumeLayout.addWidget(mvolumeSlider);
        volumeLayout.addWidget(mvolumeLabel);
        
        QHBoxLayout othercontrolsLayout = new QHBoxLayout();
        othercontrolsLayout.addWidget(mplaylistButton, 0, Qt.AlignmentFlag.AlignCenter);
        othercontrolsLayout.addWidget(mreplayButton, 0, Qt.AlignmentFlag.AlignCenter);
        
        QVBoxLayout vlayout2 = new QVBoxLayout();
        vlayout2.addLayout(othercontrolsLayout);
        vlayout2.addLayout(volumeLayout);
        
        QHBoxLayout hlayout1 = new QHBoxLayout();
        hlayout1.addLayout(vlayout1);
        hlayout1.addLayout(vlayout2);
        
        QVBoxLayout vlayout3 = new QVBoxLayout();
        vlayout3.addWidget(song_name);
        vlayout3.addLayout(hlayout1);
        
        QHBoxLayout hlayout2 = new QHBoxLayout();
        hlayout2.addWidget(albumIcon);
        hlayout2.addLayout(vlayout3);
        
        QHBoxLayout songLayout = new QHBoxLayout();
        songLayout.addWidget(msongSeek);
        songLayout.addWidget(timer);
        
        QVBoxLayout vlayout4 = new QVBoxLayout();
        vlayout4.addLayout(hlayout2);
        vlayout4.addLayout(songLayout);

        QHBoxLayout finalLayout = new QHBoxLayout();
        finalLayout.addLayout(vlayout4);
        finalLayout.addLayout(controlButtonsLayout);
        
        frame.setLayout(finalLayout);
        miniview.setCentralWidget(frame);
        
        
        /*
         * 
         * 
         * 
         * 
         */
        
        closeButton.clicked.connect(QApplication.instance(), "quit()");
        
        
    } 
    
    public static void main( String[] args ) {
        QApplication.initialize(args);
        QMainWindow miniview = new QMainWindow();
        MiniView mview = new MiniView();
        mview.miniView( miniview );
        
        miniview.setWindowFlags(Qt.WindowType.FramelessWindowHint);
//        miniview.setStyleSheet(css.loadStyles("yamp/themes/miniview.css"));
        miniview.setAttribute(Qt.WidgetAttribute.WA_TranslucentBackground);
        miniview.show();
        QApplication.exec();
    } 
   
}