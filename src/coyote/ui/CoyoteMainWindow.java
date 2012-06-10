package coyote.ui;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.phonon.SeekSlider;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QAction;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;

import coyote.ui.LoadStyles;
import coyote.musiclibrary.LibraryManagement;
import coyote.CoyoteLibrary;
import coyote.musiclibrary.Check_Files;
import coyote.musiclibrary.RestoreLibrary;
import coyote.ui.FindThemes;
import coyote.ui.SetThemes;
import coyote.musiclibrary.StoringFiles;


public class CoyoteMainWindow extends StoringFiles {
        
    static public QPixmap playPixmap;
    static public QPixmap pausePixmap;
    static public QPixmap stopPixmap;
    static public QPixmap forwardPixmap;
    static public QPixmap backPixmap;
    static public QPixmap shufflePixmap;
    static public QPixmap replayPixmap;
    static public QPixmap clear_playlistPixmap;
    static public QPixmap albumsPixmap;
    static public QPixmap playlistPixmap;
    static public QPixmap infoPixmap;
    
    static QIcon playIcon;
    static QIcon pauseIcon;
    static QIcon nextIcon;
    static QIcon previousIcon;
    static QIcon stopIcon;
    static QIcon shuffleIcon;
    static QIcon replayIcon;
    static QIcon clear_playlistIcon;
    static QIcon albumsIcon;
    static QIcon infoIcon;
    static QIcon playlistIcon;
    
    static QToolButton play_pauseButton;
    static QToolButton stopButton;
    static QToolButton nextButton;
    static QToolButton previousButton;
    static QPushButton replayButton;
    static QPushButton shuffleButton;
    static QPushButton clearButton;
    static QPushButton albumButton;
    static QPushButton playlistButton;
    static QPushButton infoButton;
    
    static QFrame buttonsFrame;
    
    static QSlider volumeSlider;
    QLabel volumeLabel;
    static SeekSlider songSeek;
    
    static QProgressBar progressBar;
    
    QLabel searchLabel;
    static QLineEdit searchEdit;
    
    public static QTreeWidget libraryTree;
    public static QTableWidget playlistTable;
    public static QTableWidget albumsTable;
    
    QTextEdit infoText;
    
    QStackedLayout stackedLayout;
    static QVBoxLayout vboxFinal;
    static QSplitter splitter;
    
    static QMenuBar menuBar;
    static QMenu themeMenu;
    static QActionGroup themeGroup;
    
    static QSettings settings;
    
    static QMainWindow coyote;
    
    static QWidget widget;

    LoadStyles css = new LoadStyles();

    
    public void coyote( QMainWindow coyote ) throws FileNotFoundException {   
        
                
        // Icons
        
//        QIcon playIcon = new QIcon(playPixmap);
//        QIcon pauseIcon = new QIcon(pausePixmap);
//        QIcon nextIcon = new QIcon(forwardPixmap);
//        QIcon previousIcon = new QIcon(backPixmap);
//        QIcon stopIcon = new QIcon(stopPixmap);
//        QIcon shuffleIcon = new QIcon(shufflePixmap);
//        QIcon replayIcon = new QIcon(replayPixmap);
//        QIcon clear_playlistIcon = new QIcon(clear_playlistPixmap);
//        QIcon albumsIcon = new QIcon(albumsPixmap);
//        QIcon infoIcon = new QIcon(infoPixmap);
//        QIcon playlistIcon = new QIcon(playlistPixmap);
        
        // Control Buttons   

        buttonsFrame = new QFrame();
        
        play_pauseButton = new QToolButton();
//        play_pauseButton.setIcon(playIcon);
//        play_pauseButton.setIconSize(playPixmap.rect().size());
        play_pauseButton.setAutoRaise(true);
        
        stopButton = new QToolButton();
//        stopButton.setIcon(stopIcon);
//        stopButton.setIconSize(stopPixmap.rect().size());
        stopButton.setAutoRaise(true);
        
        nextButton = new QToolButton();
//        nextButton.setIcon(nextIcon);
//        nextButton.setIconSize(forwardPixmap.rect().size());
        nextButton.setAutoRaise(true);
        
        previousButton = new QToolButton();
//        previousButton.setIcon(previousIcon);
//        previousButton.setIconSize(backPixmap.rect().size());
        previousButton.setAutoRaise(true);
        
        replayButton = new QPushButton();
//        replayButton.setIcon(replayIcon);
//        replayButton.setIconSize(replayPixmap.rect().size());
        replayButton.setFlat(true);
        
        shuffleButton = new QPushButton();
//        shuffleButton.setIcon(shuffleIcon);
//        shuffleButton.setIconSize(shufflePixmap.rect().size());
        shuffleButton.setFlat(true);
        
        clearButton = new QPushButton();
//        clearButton.setIcon(clear_playlistIcon);
//        clearButton.setIconSize(clear_playlistPixmap.rect().size());
        clearButton.setFlat(true);
        
        // Toogle Buttons
        
        albumButton = new QPushButton();
        albumButton.setCheckable(true);
//        albumButton.setIcon(albumsIcon);
//        albumButton.setIconSize(albumsPixmap.rect().size());
        albumButton.setFlat(true);
        albumButton.setChecked(true);
        
        playlistButton = new QPushButton();
        playlistButton.setCheckable(true);
//        playlistButton.setIcon(playlistIcon);
//        playlistButton.setIconSize(playlistPixmap.rect().size());
        playlistButton.setFlat(true);
        
        infoButton = new QPushButton();
        infoButton.setCheckable(true);
//        infoButton.setIcon(infoIcon);
//        infoButton.setIconSize(infoPixmap.rect().size());
        infoButton.setFlat(true);
        
        // Volume 
        
        volumeLabel = new QLabel();
        volumeSlider = new QSlider(Qt.Orientation.Horizontal);
        volumeSlider.setFixedSize(90, 20);
                
        // Progress Bar
        
        progressBar = new QProgressBar();
        progressBar.setVisible(false);
        progressBar.setRange(0, 100);
        progressBar.setMaximum(0);
        progressBar.setMinimum(0);
        
        // Search Bar
        
        searchLabel = new QLabel("Search");
        searchEdit = new QLineEdit();
        searchEdit.setFixedSize(150, 20);
                
        // Library TreeWidget
        
        libraryTree = new QTreeWidget();
        libraryTree.setColumnCount(1);
        ArrayList<String> librarycolumn = new ArrayList(); 
        librarycolumn.add("Library");
        libraryTree.setHeaderLabels(librarycolumn);
        libraryTree.setItemsExpandable(true);        
        
        // Playlist TableWidget
        
        playlistTable = new QTableWidget();
        playlistTable.setColumnCount(4);
        ArrayList<String> tablecolumns = new ArrayList();
        tablecolumns.add("Number");
        tablecolumns.add("Title");
        tablecolumns.add("Artist");
        tablecolumns.add("Album");
        playlistTable.setHorizontalHeaderLabels(tablecolumns);
        playlistTable.horizontalHeader().setResizeMode(QHeaderView.ResizeMode.Stretch);
        // Albums TableWidget
        
        albumsTable = new QTableWidget();
        albumsTable.setColumnCount(1);
        ArrayList<String> albumscolumn = new ArrayList();
        albumscolumn.add("Albums");
        albumsTable.setHorizontalHeaderLabels(albumscolumn);
        albumsTable.horizontalHeader().setResizeMode(QHeaderView.ResizeMode.Stretch);
        // Info Text
        
        infoText = new QTextEdit();
        
        // Song seeker
        
        songSeek = new SeekSlider();
        
        /*
         *  Containers!!
         */
        splitter = new QSplitter(Qt.Orientation.Horizontal);
        QWidget rightvboxWidget = new QWidget();
        QWidget leftvboxWidget = new QWidget();
        
        QSpacerItem rightSpacer = new QSpacerItem(100, 50);
        QSpacerItem upSpacer2 = new QSpacerItem(100, 10);
        QSpacerItem upSpacer3 = new QSpacerItem(510, 10);
        
        stackedLayout = new QStackedLayout();
        stackedLayout.addWidget(albumsTable);
        stackedLayout.addWidget(playlistTable);
        stackedLayout.addWidget(infoText);

        QHBoxLayout searchBox = new QHBoxLayout();
        searchBox.addWidget(searchLabel);
        searchBox.addWidget(searchEdit);
        searchBox.addStretch();
        
        QHBoxLayout upBox = new QHBoxLayout();
        upBox.addWidget(albumButton);
        upBox.addWidget(playlistButton);
        upBox.addWidget(infoButton);
        upBox.addWidget(replayButton);
        upBox.addWidget(shuffleButton);
        upBox.addWidget(clearButton);   
        upBox.addStretch();
        upBox.addWidget(progressBar);
        
        QHBoxLayout buttonsBox = new QHBoxLayout();
        buttonsBox.addWidget(stopButton);
        buttonsBox.addWidget(previousButton);
        buttonsBox.addWidget(play_pauseButton);
        buttonsBox.addWidget(nextButton);
        buttonsBox.addWidget(songSeek);
        buttonsBox.addSpacerItem(rightSpacer);
        buttonsBox.addWidget(volumeSlider);
        buttonsBox.addWidget(volumeLabel);
        
        QVBoxLayout vbox = new QVBoxLayout();
        vbox.addLayout(upBox);
        vbox.addLayout(stackedLayout);
        vbox.addWidget(buttonsFrame);
       
        
        QVBoxLayout leftvBox = new QVBoxLayout();
        leftvBox.addLayout(searchBox);
        leftvBox.addWidget(libraryTree);
        
        leftvboxWidget.setLayout(leftvBox);
        splitter.addWidget(leftvboxWidget);
        rightvboxWidget.setLayout(vbox);
        splitter.addWidget(rightvboxWidget);
        
        buttonsFrame.setLayout(buttonsBox);
        buttonsFrame.setFrameShape(QFrame.Shape.StyledPanel);
        buttonsFrame.setFrameShadow(QFrame.Shadow.Sunken);
        
        vboxFinal = new QVBoxLayout();
        vboxFinal.addWidget(splitter);
        
        /*
         *  Menus and Actions!
         */
        
        menuBar = new QMenuBar();
        QMenu coyoteMenu = menuBar.addMenu("&coyote");
        QMenu extrasMenu = menuBar.addMenu("&Extras");
        themeMenu = menuBar.addMenu("&Theme");
        
        themeGroup = new QActionGroup(themeMenu);

        widget = new QWidget();
        widget.setObjectName("mainwidget");
        widget.setLayout(vboxFinal);
        coyote.setCentralWidget(widget);
        coyote.setWindowState(Qt.WindowState.WindowMaximized);
        
        /*****************************************************
         *                                                   *
         *                                                   *
         *                                                   *
         *                                                   *
         *                                                   *
         *                                                   *
         *****************************************************/
        
        albumButton.clicked.connect(this, "albumButtonClicked()");
        playlistButton.clicked.connect(this, "playlistButtonClicked()");
        infoButton.clicked.connect(this, "infoButtonClicked()");
        libraryTree.itemDoubleClicked.connect(this, "libraryTreeSongSelected()");
        themeGroup.triggered.connect(this, "themeGroupTriggered()");
               
        
    }
    
    private void libraryTreeSongSelected() {
        int index = libraryTree.currentColumn();
        String item = libraryTree.currentItem().text(index);        
        
    }
    
    private void albumButtonClicked() {
       if (albumButton.isChecked()) {
            playlistButton.setChecked(false);
            infoButton.setChecked(false);
            stackedLayout.setCurrentIndex(0);
       }
       else {
            albumButton.setChecked(true);
       }
    }
    
    private void playlistButtonClicked() {
        if (playlistButton.isChecked()) {
            albumButton.setChecked(false);
            infoButton.setChecked(false);
            stackedLayout.setCurrentIndex(1);
        }
        else {
            playlistButton.setChecked(true);
        }
    }
    
    private void infoButtonClicked() {
        if (infoButton.isChecked()) {
            albumButton.setChecked(false);
            playlistButton.setChecked(false);
            stackedLayout.setCurrentIndex(2);
        }
        else {
            infoButton.setChecked(true);
        }
    }
    
    private void themeGroupTriggered() throws FileNotFoundException, IOException {
        QAction action = themeGroup.checkedAction();
        String theme = action.text();
        SetThemes.setPixmaps(theme);
        SetThemes.setIcons();
        SetThemes.setTheme(theme, coyote);
    }
    
    /************************************************
     *                                              *
     *                                              *
     *                                              *
     *                                              *
     ************************************************/
    
    public void setLibrary( MultiMap map ) {
        int i = 1;
        Set artists = map.keySet();
        TreeSet artistsSorted = new TreeSet(artists);
        QTreeWidgetItem allItem = new QTreeWidgetItem(libraryTree);
        allItem.setText(0, "All");
        libraryTree.insertTopLevelItem(0, allItem);
        for (Iterator it = artistsSorted.iterator(); it.hasNext();) {
            Object artistname = it.next();
            QTreeWidgetItem item = new QTreeWidgetItem(libraryTree);
            item.setText(0, String.valueOf(artistname));
            libraryTree.insertTopLevelItem(i, item);
            i++;
        }
    }

    public static void main( String[] args ) throws IOException, FileNotFoundException, ClassNotFoundException {
        QApplication.initialize(args);
        LoadStyles css = new LoadStyles();
        
        CoyoteLibrary m = new CoyoteLibrary();
        coyote = new QMainWindow();
        CoyoteMainWindow window = new CoyoteMainWindow();
        Check_Files checkF = new Check_Files();
        RestoreLibrary rLibrary = new RestoreLibrary();
        
        settings = new QSettings("", "coyote");
        //settings.setValue("theme", "light");
        //String theme = (String) settings.value("theme");
        String theme = "dark";
        SetThemes.setPixmaps(theme);
        window.coyote( coyote );
        SetThemes.setIcons();
        SetThemes.setTheme(theme, coyote);
        checkF.check_files();
        MultiMap artists_songs = m.coyoteLibrary();
        window.setLibrary(artists_songs);
        MultiMap map = new MultiHashMap();
        
        //map = rLibrary.restoreLibrary();
        window.setLibrary(map);
        ArrayList themes = FindThemes.findThemes();
        CreateThemesEntry.createThemesEntry(themes);
        coyote.setMenuBar(menuBar);

        coyote.show();
        QApplication.exec();
    }
}
