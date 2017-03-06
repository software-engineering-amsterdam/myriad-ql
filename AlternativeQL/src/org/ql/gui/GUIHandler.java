package org.ql.gui;

import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.ast.statement.Question;
import org.ql.collection.visitor.QuestionCollectVisitor;
import org.ql.evaluator.ValueTable;
import org.ql.gui.elements.QuestionElement;
import org.ql.gui.elements.QuestionElementBuilder;

import java.util.List;

// TODO: Only the GUI related stuff should be here (extract main start/load application from this class)
public class GUIHandler {

    private MainStage primaryStage;
    private ValueTable valueTable;
    private GUIEval guiEval;

    public GUIHandler(Stage primaryStage, Form form) {
        valueTable = new ValueTable();
        guiEval = new GUIEval(valueTable);

        primaryStage.show();
        runGUI(form);
    }

    public void runGUI(Form form) {
        QuestionElementBuilder questionElementBuilder = new QuestionElementBuilder(valueTable);
        createQuestionWidgets(form, questionElementBuilder);
    }

    public void createQuestionWidgets(Form form, QuestionElementBuilder questionElementBuilder) {
        QuestionCollectVisitor questionCollectVisitor = new QuestionCollectVisitor();
        List<Question> questions = questionCollectVisitor.collect(form);

        for (Question question : questions) {
            QuestionElement questionElement = questionElementBuilder.visitQuestion(question, null);
            valueTable.declare(questionElement.getQuestion().getId(), questionElement.getValue());
        }

        System.out.println(questions.size());
    }
}
