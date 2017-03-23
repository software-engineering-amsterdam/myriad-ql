package com.matthewchapman.ql.gui;

import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.FormEnvironment;
import com.matthewchapman.ql.environment.evaluation.ExpressionEvaluator;
import com.matthewchapman.ql.environment.values.Value;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by matt on 15/03/2017.
 */
public class GUIHandler {

    private final FormEnvironment environment;
    private final ExpressionEvaluator evaluator;
    private final Stage stage;
    private final QuestionChangeObserver questionChangeObserver;

    public GUIHandler(Stage stage, FormEnvironment environment) {
        this.environment = environment;
        this.stage = stage;
        this.evaluator = new ExpressionEvaluator();
        this.questionChangeObserver = new QuestionChangeObserver(this);
        testUI();
    }

    public void onQuestionChange(String id, Value value) {
        System.out.println(id + " changed to " + value.toString());
    }

    private void testUI() {
        VBox box = new VBox();

        QuestionWidgetFactory factory = new QuestionWidgetFactory();

        for(Question question : environment.getQuestionsAsList()) {
            box.getChildren().add(factory.getQuestionWidget(question, environment.getValueByName(question.getName()), this.questionChangeObserver));
        }

        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
    }

}
