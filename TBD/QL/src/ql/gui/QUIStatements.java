package ql.gui;

import javafx.scene.layout.VBox;

/**
 * Created by Erik on 28-2-2017.
 */
public class QUIStatements extends VBox {

    public QUIStatements(VBox[] statements) {
        this.getChildren().addAll(statements);
    }
}
