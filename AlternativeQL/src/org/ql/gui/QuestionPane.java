package org.ql.gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class QuestionPane {
    private final GridPane gridPane;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private int row = 0;

    public QuestionPane() {
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setMinWidth(WIDTH);
        gridPane.setMinHeight(HEIGHT);
        gridPane.setVgap(10);
    }

    public void add(Node pane) {
        gridPane.add(pane, 0, row++);
    }

    public Scene createScene() {
        return new Scene(gridPane, WIDTH, HEIGHT);
    }

    public void reset() {
        row = 0;
        gridPane.getChildren().clear();
    }
}
