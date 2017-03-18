package org.ql.gui;

import javafx.stage.Stage;
import org.ql.gui.widgets.Widget;

class Window {

    private final QuestionPane mainPane;

    Window(Stage stage, String title) {
        stage.setTitle(title);
        stage.setResizable(false);

        mainPane = new QuestionPane();
        stage.setScene(mainPane.createScene());
    }

    void reset() {
        mainPane.reset();
    }

    void attachWidget(Widget widget) {
        mainPane.add(widget.createGridPane());
    }
}
