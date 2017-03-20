package ql.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ql.visistor.environment.Env;
import ql.values.*;

/**
 * Created by rico on 27-2-17.
 */
public abstract class GUIChangeListener<T> implements ChangeListener<T> {
    private final GUIEvaluator evaluator;

    public GUIChangeListener(GUIEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public void evaluate() {
        this.evaluator.evaluate();
    }
}
