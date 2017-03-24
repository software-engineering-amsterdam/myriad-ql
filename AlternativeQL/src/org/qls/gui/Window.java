package org.qls.gui;

import javafx.scene.Node;
import javafx.stage.Stage;
import org.ql.gui.QuestionPane;

import java.util.List;

public class Window {
    private final QuestionPane questionPane;

    public Window(Stage stage, String title) {
        stage.setTitle(title);
        stage.setResizable(true);

        questionPane = new QuestionPane();
        stage.setScene(questionPane.createScene());
    }

    public void reset() {
        questionPane.reset();
    }

    public void addSections(List<Node> sectionPanes) {
        sectionPanes.forEach(questionPane::add);
    }

    public void addPane(Node node) {
        questionPane.add(node);
    }
}
