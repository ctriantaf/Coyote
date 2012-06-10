package coyote.ui;

import com.trolltech.qt.gui.QAction;
import java.util.ArrayList;
import coyote.ui.CoyoteMainWindow;

public class CreateThemesEntry {

    static CoyoteMainWindow ymw = new CoyoteMainWindow(); 
    
    public static void createThemesEntry( ArrayList<String> themes ) {
        for ( String theme : themes ) {
            QAction action = new QAction(theme, ymw.themeMenu);
            action.setCheckable(true);
            ymw.themeGroup.addAction(action);
            ymw.themeMenu.addAction(action);
        }
    }
    
}