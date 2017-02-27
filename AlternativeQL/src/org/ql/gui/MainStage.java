package org.ql.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class MainStage extends Stage {

    private Stage stage;
    private List<Pane> panes;

    public MainStage(Stage stage) {
        this.stage = stage;
    }

    public void addPaneToScene(Pane pane) {
        Scene scene = new Scene(pane, 800, 600);
        stage.setScene(scene);
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
