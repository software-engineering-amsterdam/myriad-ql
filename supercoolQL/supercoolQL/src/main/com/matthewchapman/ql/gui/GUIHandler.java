package com.matthewchapman.ql.gui;

import com.matthewchapman.ql.environment.FormEnvironment;
import com.matthewchapman.ql.environment.ValueTableObserver;
import com.matthewchapman.ql.environment.evaluation.ExpressionEvaluator;
import javafx.stage.Stage;

/**
 * Created by matt on 15/03/2017.
 */
public class GUIHandler {

    private ValueTableObserver valueObserver;
    private FormEnvironment environment;
    private ExpressionEvaluator evaluator;
    private Stage stage;

    public GUIHandler(FormEnvironment env, ValueTableObserver con, Stage stage) {
        this.valueObserver = con;
        this.environment = env;
        this.stage = stage;
        this.evaluator = new ExpressionEvaluator();

        generateInitialFormUI(stage, environment);
    }

    private void generateInitialFormUI(Stage stage, FormEnvironment env) {

    }
}
