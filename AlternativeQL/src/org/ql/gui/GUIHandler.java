package org.ql.gui;

import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.elements.Element;
import org.ql.gui.elements.visitor.QuestionElementFactory;
import org.ql.gui.elements.ElementContainer;
import org.ql.gui.elements.visitor.QuestionValueVisitor;
import org.ql.gui.elements.visitor.BranchVisitor;
import org.ql.gui.mediator.GUIMediator;

import java.util.List;

public class GUIHandler implements GUIMediator {

    private final Window window;
    private final BranchVisitor branchVisitor;
    private final QuestionValueVisitor questionValueVisitor;
    private final Form form;
    private final ValueTable valueTable;

    public GUIHandler(Window window, Form form) {
        this.form = form;
        this.window = window;

        valueTable = new ValueTable();

        ElementContainer questionElementContainer = new ElementContainer(
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
        window.resetStage();
        addWidgets(valueTable);
    }

    public void addWidgets(ValueTable valueTable) {
        for(Element questionElement : branchVisitor.visitForm(form, valueTable)) {
            window.attachElementToPane(questionElement);
        }
    }

}
