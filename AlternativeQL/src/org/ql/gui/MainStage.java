package org.ql.gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class MainStage extends Stage {

    private Stage stage;
    private List<Pane> panes;
    private Pane rootPane;

    public MainStage(Stage stage) {
        this.stage = stage;

        createScene();
    }

    public void createScene() {
        rootPane = new Pane();
        rootPane.setMinHeight(800);
        rootPane.setMinWidth(800);
        Scene scene = new Scene(rootPane, 800, 600);
        stage.setScene(scene);
    }

    public void addPaneToScene(Pane pane) {
        rootPane.getChildren().add(pane);
    }

    public Stage getStage() {
        return stage;
    }

    public List<Pane> getPanes() {
        return panes;
    }

    public void setPanes(List<Pane> panes) {
        this.panes = panes;
    }
}
