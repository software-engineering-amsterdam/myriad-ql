package org.ql.gui;

import javafx.stage.Stage;
import org.ql.ast.Form;

// Acts as a wrapper, only for testing purposes.
public class GUIHandlerSingleton {
    public static GUIHandler guiHandler;

    public GUIHandlerSingleton(Stage stage, Form form) {
        if(guiHandler == null) {
            //this.guiHandler = new GUIHandler(stage, form);
            System.out.println("Initialized the GUI handler.");
        }
    }
}