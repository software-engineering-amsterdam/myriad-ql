package ql.view;

import javafx.scene.layout.VBox;

/**
 * Created by Erik on 28-2-2017.
 */
public class QLStatementsBox extends VBox {

    public void addStatements(VBox[] statements) {
        this.getChildren().addAll(statements);
    }

    public void addStatement(VBox statement) {
        this.getChildren().add(statement);
    }
}
