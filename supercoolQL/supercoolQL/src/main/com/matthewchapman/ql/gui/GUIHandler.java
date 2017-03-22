package com.matthewchapman.ql.gui;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.environment.FormEnvironment;
import com.matthewchapman.ql.environment.evaluation.ExpressionEvaluator;
import com.matthewchapman.ql.environment.observers.QuestionInputObserver;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by matt on 15/03/2017.
 */
public class GUIHandler implements Observer {

    private final QuestionInputObserver valueObserver;
    private final FormEnvironment environment;
    private final ExpressionEvaluator evaluator;
    private final Stage stage;
    private final FormWindow window;

    public GUIHandler(Form form, Stage stage) {
        this.environment = new FormEnvironment(form, this);
        this.stage = stage;
        this.evaluator = new ExpressionEvaluator();
        this.valueObserver = new QuestionInputObserver(environment);
        this.window = new FormWindow(environment, valueObserver);

        generateInitialUI();
    }

    public void generateInitialUI() {
        window.updateLayout();
        stage.setScene(new Scene(window));
        stage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        //redraw everything
        System.out.println("it works!");

    }
}
