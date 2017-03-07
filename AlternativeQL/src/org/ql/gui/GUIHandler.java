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

    public GUIHandler(Stage primaryStage, Form form) {
        this.mainstage = new MainStage(primaryStage);
        this.form = form;

        QuestionElementContainer questionElementContainer = new QuestionElementContainer(new QuestionElementBuilder());

        branchVisitor = new BranchVisitor(questionElementContainer);
        questionValueVisitor = new QuestionValueVisitor(questionElementContainer);

        primaryStage.show();
    }

    public void runGUI() {
        ValueTable valueTable = questionValueVisitor.makeValueTable(form);
        List<QuestionElement> visibleElements = branchVisitor.visitForm(form, questionValueVisitor.makeValueTable(form));

        mainstage.cleanRootPane();

        for(QuestionElement questionElement : visibleElements) {
            questionElement.getWidget().setValue(valueTable.lookup(questionElement.getQuestion().getId()));
            mainstage.addWidgetToRootPane(questionElement.getWidget());
        }

    }

}
