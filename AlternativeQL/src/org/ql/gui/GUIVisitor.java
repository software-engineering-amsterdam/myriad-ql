package org.ql.gui;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.evaluator.ValueTable;

public class GUIVisitor implements FormVisitor<Pane, Void>, StatementVisitor<Pane, Void> {

    private MainStage mainStage;
    private ValueTable valueTable;

    public GUIVisitor(MainStage mainStage, ValueTable valueTable) {
        this.mainStage = mainStage;
        this.valueTable = valueTable;
    }

    @Override
    public Pane visitForm(Form form, Void ignore) {
        mainStage.getStage().setTitle(form.getName().toString());

        for (Statement statement : form.getStatements()) {
            Pane pane = statement.accept(this, null);
            mainStage.addPaneToScene(pane);
        }

        return null;
    }

    @Override
    public Pane visitIfThen(IfThen ifThen, Void ignore) {
        ifThen.accept(this, null);

        return null;
    }

    @Override
    public Pane visitIfThenElse(IfThenElse ifThenElse, Void ignore) {
        ifThenElse.accept(this, null);

        return null;
    }

    @Override
    public Pane visitQuestion(Question question, Void ignore) {
        Pane pane = new Pane();
        pane.setVisible(true);
        pane.setMinWidth(600);
        pane.setMinHeight(100);
        Text questionText = new Text(question.getQuestionText().toString());
        pane.getChildren().add(questionText);

        return pane;
    }
}
