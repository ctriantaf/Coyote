package yamp.ui;

import com.trolltech.qt.gui.QAction;
import java.util.ArrayList;
import yamp.ui.YampMainWindow;

public class CreateThemesEntry {

    static YampMainWindow ymw = new YampMainWindow(); 
    
    public static void createThemesEntry( ArrayList<String> themes ) {
        for ( String theme : themes ) {
            QAction action = new QAction(theme, ymw.themeMenu);
            action.setCheckable(true);
            ymw.themeGroup.addAction(action);
            ymw.themeMenu.addAction(action);
        }
    }
    
}