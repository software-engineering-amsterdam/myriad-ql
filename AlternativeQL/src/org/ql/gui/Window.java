package org.ql.gui;

import javafx.stage.Stage;
import org.ql.gui.widgets.Widget;

public class Window {

    private final QuestionPane mainPane;

    public Window(Stage stage, String title) {
        stage.setTitle(title);
        stage.setResizable(false);

        mainPane = new QuestionPane();
        stage.setScene(mainPane.createScene());
    }

    public void resetStage() {
        mainPane.reset();
    }

    public void attachWidgetToPane(Widget widget) {
        mainPane.add(widget.createGridPane());
    }
}
