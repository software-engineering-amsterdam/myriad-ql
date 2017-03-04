package ql.view;

import javafx.scene.layout.VBox;

/**
 * Created by Erik on 28-2-2017.
 */
public class QLStatementsBox extends VBox {

    public QLStatementsBox(VBox[] statements) {
        this.getChildren().addAll(statements);
    }
}
