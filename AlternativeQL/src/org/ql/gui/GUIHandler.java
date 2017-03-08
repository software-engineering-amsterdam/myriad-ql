package org.ql.gui;

import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.evaluator.ValueTable;
import org.ql.gui.elements.QuestionElement;
import org.ql.gui.elements.visitor.QuestionElementBuilder;
import org.ql.gui.elements.QuestionElementContainer;
import org.ql.gui.elements.visitor.QuestionValueVisitor;
import org.ql.gui.elements.visitor.BranchVisitor;

import java.util.List;

public class GUIHandler {

    private final MainStage mainstage;
    private final BranchVisitor branchVisitor;
    private final QuestionValueVisitor questionValueVisitor;
    private final Form form;
    public ValueTable valueTable;

    public GUIHandler(Stage primaryStage, Form form) {
        this.mainstage = new MainStage(primaryStage, form.getName().toString());
        this.form = form;

        QuestionElementContainer questionElementContainer = new QuestionElementContainer(new QuestionElementBuilder());

        branchVisitor = new BranchVisitor(questionElementContainer);
        questionValueVisitor = new QuestionValueVisitor(questionElementContainer);

        primaryStage.show();
    }

    public void runGUI() {
        this.valueTable = questionValueVisitor.makeValueTable(form);
        addWidgets(questionValueVisitor.makeValueTable(form));
    }

    public void updateGUI() {;
        mainstage.resetStage();
        addWidgets(valueTable);
    }

    public void addWidgets(ValueTable valueTable) {
        List<QuestionElement> visibleElements = branchVisitor.visitForm(form, valueTable);

        for(QuestionElement questionElement : visibleElements) {
            questionElement.setValue(valueTable.lookup(questionElement.getQuestion().getId()));
            mainstage.addWidgetToMainPane(questionElement.getWidget());
        }
    }

}
