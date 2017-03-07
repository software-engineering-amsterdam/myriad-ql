package org.ql.gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import org.ql.gui.widgets.Widget;

import java.util.List;

public class MainStage extends Stage {

    private Stage stage;
    private GridPane rootPane;
    private int currentRow = 0;

    public MainStage(Stage stage) {
        this.stage = stage;

        createScene();
    }

    public void createScene() {
        rootPane = new GridPane();
        rootPane.setAlignment(Pos.TOP_CENTER);
        rootPane.setMinHeight(600);
        rootPane.setMinWidth(800);
        rootPane.setVgap(10);
        Scene scene = new Scene(rootPane, 800, 600);
        stage.setScene(scene);
    }

    public void cleanRootPane() {
        rootPane.getChildren().clear();
    }

    public void addWidgetToRootPane(Widget widget) {
        rootPane.add(widget.getGridPane(), 0, currentRow++);
    }

    public Stage getStage() {
        return stage;
    }

    public GridPane getRootPane() {
        return rootPane;
    }

}
