package org.ql.gui;

import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.evaluator.ValueTable;
import org.ql.gui.elements.QuestionElement;
import org.ql.gui.elements.visitor.VisibleQuestionsVisitor;

import java.util.ArrayList;

// TODO: Only the GUI related stuff should be here (extract main start/load application from this class)
public class GUIHandler {

    private final MainStage mainstage;
    private final VisibleQuestionsVisitor visibleQuestionsVisitor;

    public GUIHandler(Stage primaryStage, Form form) {
        this.mainstage = new MainStage(primaryStage);

        primaryStage.show();
        runGUI(form);
        visibleQuestionsVisitor = new VisibleQuestionsVisitor(new ValueTable());
    }

    public void runGUI(Form form) {
        ArrayList<QuestionElement> visibleElements = new ArrayList<>();
        visibleQuestionsVisitor.visitForm(form, visibleElements);
    }
}
