package org.ql.gui;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.ql.ast.Expression;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.expression.ExpressionVisitor;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.ast.type.BooleanType;
import org.ql.evaluator.Evaluator;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.CheckBoxWidget;
import org.ql.gui.widgets.TextWidget;
import org.ql.gui.widgets.Widget;
import org.ql.gui.widgets.WidgetGridPane;

public class GUIVisitor implements FormVisitor<Pane, Void>, StatementVisitor<GridPane, Void> {
    private MainStage mainStage;
    private ValueTable valueTable;
    private WidgetEventHandler widgetEventHandler;

    public GUIVisitor(MainStage mainStage, ValueTable valueTable) {
        this.mainStage = mainStage;
        this.valueTable = valueTable;
        this.widgetEventHandler = new WidgetEventHandler(valueTable, mainStage);
    }

    @Override
    public Pane visitForm(Form form, Void ignore) {
        mainStage.getStage().setTitle(form.getName().toString());

        for (Statement statement : form.getStatements()) {
            GridPane pane = statement.accept(this, null);
            mainStage.addPaneToRootPane(pane);
        }

        return null;
    }

    @Override
    public GridPane visitIfThen(IfThen ifThen, Void ignore) {
        Identifier ifThenCondition = new Identifier(ifThen.getCondition().toString());
        WidgetGridPane widgetGridPane = new WidgetGridPane(widgetEventHandler, ifThenCondition);

        Value value = valueTable.lookup(ifThenCondition);
        if(value != null) {
            widgetGridPane.getPane().setVisible((Boolean) value.getPlainValue());
        }

        int i = 0;
        for(Statement statement : ifThen.getThenStatements()) {
            GridPane statementPane = statement.accept(this, null);
            widgetGridPane.getPane().add(statementPane, 0, i++);
        }

        widgetEventHandler.addWidget(widgetGridPane);

        return widgetGridPane.getPane();
    }

    @Override
    public GridPane visitIfThenElse(IfThenElse ifThenElse, Void ignore) {
        ifThenElse.accept(this, null);

        return null;
    }

    @Override
    public GridPane visitQuestion(Question question, Void ignore) {
        GridPane pane = new GridPane();
        Text questionText = new Text(question.getQuestionText().toString());
        pane.add(questionText, 0, 0);

        // TODO: Dynamic dispatch (add type visitor)
        if (question.getType().isBoolean()) {
            CheckBoxWidget checkBoxWidget = new CheckBoxWidget(widgetEventHandler, question.getId());
            Value defaultValue = valueTable.lookup(question.getId());
            if(defaultValue != null) {
                checkBoxWidget.getCheckBox().setSelected((Boolean) defaultValue.getPlainValue());
            }
            widgetEventHandler.addWidget(checkBoxWidget);
            pane.add(checkBoxWidget.getCheckBox(), 1, 0);
        }

        if (question.getType().isString()) {
            TextWidget textWidget = new TextWidget(widgetEventHandler, question.getId());
            Value defaultValue = valueTable.lookup(question.getId());
            if(defaultValue != null) {
                textWidget.getTextField().setText((String) defaultValue.getPlainValue());
            }
            widgetEventHandler.addWidget(textWidget);
            pane.add(textWidget.getTextField(), 1, 0);
        }

        return pane;
    }

}
