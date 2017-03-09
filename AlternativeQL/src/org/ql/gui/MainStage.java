package org.ql.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.ql.gui.widgets.Widget;

//
public class MainStage extends Stage {

    private Stage stage;
    private GridPane mainPane;
    private int currentRow = 0;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    public MainStage(Stage stage, String title) {
        this.stage = stage;
        stage.setTitle(title);
        createScene();
    }

    public void createScene() {
        mainPane = new GridPane();
        mainPane.setAlignment(Pos.TOP_CENTER);
        mainPane.setMinWidth(WINDOW_WIDTH);
        mainPane.setMinHeight(WINDOW_HEIGHT);
        mainPane.setVgap(10);
        stage.setResizable(false);
        Scene scene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
    }

    public void cleanMainPane() {
        mainPane.getChildren().clear();
    }

    public void resetStage() {
        currentRow = 0;
        cleanMainPane();
    }

    public void addWidgetToMainPane(Widget widget) {
        mainPane.add(widget.getGridPane(), 0, getNewRow());
    }

    public int getNewRow() {
        return currentRow++;
    }
}
