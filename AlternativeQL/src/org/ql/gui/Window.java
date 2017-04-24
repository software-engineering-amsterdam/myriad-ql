package org.ql.gui;

import javafx.stage.Stage;
import org.ql.gui.widgets.GUIWidget;

public class Window {

    private final QuestionPane mainPane;

    public Window(Stage stage, String title) {
        stage.setTitle(title);
        stage.setResizable(true);

        mainPane = new QuestionPane();
        stage.setScene(mainPane.createScene());
    }

    public void reset() {
        mainPane.reset();
    }

    public void attachWidget(GUIWidget widget) {
        mainPane.add(widget.createGridPane());
    }
}
