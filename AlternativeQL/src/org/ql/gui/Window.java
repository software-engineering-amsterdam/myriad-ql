package org.ql.gui;

import javafx.stage.Stage;
import org.ql.gui.elements.Element;

public class Window {

    private QuestionPane mainPane;

    public Window(Stage stage, String title) {
        stage.setTitle(title);
        stage.setResizable(false);

        mainPane = new QuestionPane();
        stage.setScene(mainPane.createScene());
    }

    public void resetStage() {
        mainPane.reset();
    }

    public void attachElementToPane(Element question) {
        question.attachToPane(mainPane);
    }
}
