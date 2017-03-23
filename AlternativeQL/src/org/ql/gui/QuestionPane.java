package org.ql.gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class QuestionPane {
    private final FormPane gridPane;

    private int row = 0;

    public QuestionPane() {
        gridPane = new FormPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
    }

    public void add(Node pane) {
        gridPane.add(pane, 0, row++);
    }

    public Scene createScene() {
        return new Scene(gridPane);
    }

    public void reset() {
        row = 0;
        gridPane.getChildren().clear();
    }
}
