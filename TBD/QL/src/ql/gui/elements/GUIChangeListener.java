package ql.gui.elements;

import javafx.beans.value.ChangeListener;
import ql.gui.evaluator.GUIEvaluator;

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
