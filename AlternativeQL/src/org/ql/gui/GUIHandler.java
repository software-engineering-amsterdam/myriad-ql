package org.ql.gui;

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

    private final MainStage stage;
    private final BranchVisitor branchVisitor;
    private final QuestionValueVisitor questionValueVisitor;
    private final Form form;
    private final ValueTable valueTable;

    public GUIHandler(MainStage mainStage, Form form) {
        this.form = form;

        stage = mainStage;
        valueTable = new ValueTable();

        QuestionElementContainer questionElementContainer = new QuestionElementContainer(
                new QuestionElementFactory(this));

        branchVisitor = new BranchVisitor(questionElementContainer);
        questionValueVisitor = new QuestionValueVisitor(questionElementContainer);

    }

    public void runGUI() {
        questionValueVisitor.updateValues(form, valueTable);
        addWidgets(valueTable);
    }

    @Override
    public void actualizeValue(Identifier identifier, Value newValue) {
        valueTable.declare(identifier, newValue);
        questionValueVisitor.updateValues(form, valueTable);
        stage.resetStage();
        addWidgets(valueTable);
    }

    public void addWidgets(ValueTable valueTable) {
        List<QuestionElement> visibleElements = branchVisitor.visitForm(form, valueTable);

        for(QuestionElement questionElement : visibleElements) {
            stage.addWidgetToMainPane(questionElement.getWidget());
        }
    }

}
