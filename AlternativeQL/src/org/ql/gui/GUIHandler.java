package org.ql.gui;

import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.elements.QuestionElement;
import org.ql.gui.elements.visitor.QuestionElementFactory;
import org.ql.gui.elements.QuestionElementContainer;
import org.ql.gui.elements.visitor.QuestionValueVisitor;
import org.ql.gui.elements.visitor.BranchVisitor;
import org.ql.gui.mediator.GUIMediator;

import java.util.List;

public class GUIHandler implements GUIMediator {

    private final MainStage mainstage;
    private final BranchVisitor branchVisitor;
    private final QuestionValueVisitor questionValueVisitor;
    private final Form form;

    private ValueTable valueTable;

    public GUIHandler(Stage primaryStage, Form form) {
        this.mainstage = new MainStage(primaryStage, form.getName().toString());
        this.form = form;

        QuestionElementContainer questionElementContainer = new QuestionElementContainer(
                new QuestionElementFactory());

        branchVisitor = new BranchVisitor(questionElementContainer);
        questionValueVisitor = new QuestionValueVisitor(questionElementContainer);

        primaryStage.show();
    }

    public void runGUI() {
        this.valueTable = questionValueVisitor.makeValueTable(form);
        addWidgets(valueTable);
    }

    public void addWidgets(ValueTable valueTable) {
        List<QuestionElement> visibleElements = branchVisitor.visitForm(form, valueTable);

        for(QuestionElement questionElement : visibleElements) {
            questionElement.setValue(valueTable.lookup(questionElement.getQuestion().getId()));
            mainstage.addWidgetToMainPane(questionElement.getWidget());
        }
    }

    @Override
    public void actualizeValue(Identifier identifier, Value newValue) {
        valueTable.declare(identifier, newValue);
        mainstage.resetStage();
        addWidgets(valueTable);
    }
}
