package org.ql.gui;

import javafx.scene.layout.Pane;
import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;

public class GUIVisitor implements FormVisitor<Pane, Void>, StatementVisitor<Pane, Void> {

    private MainStage mainStage;

    public GUIVisitor(MainStage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public Pane visit(Form form, Void ignore) {
        System.out.println(form.getName().toString());
        mainStage.getStage().setTitle(form.getName().toString());

        for (Statement statement : form.getStatements()) {
            Pane pane = statement.accept(this, null);
            mainStage.addPaneToScene(pane);
        }

        return null;
    }

    @Override
    public Pane visit(IfThen ifThen, Void ignore) {
        return null;
    }

    @Override
    public Pane visit(IfThenElse ifThenElse, Void ignore) {
        return null;
    }

    @Override
    public Pane visit(Question question, Void ignore) {
        return null;
    }
}
