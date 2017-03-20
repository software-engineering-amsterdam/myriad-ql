package ql.gui.elements;

import javafx.scene.layout.VBox;
import ql.gui.evaluator.BaseEvaluator;

/**
 * Created by Erik on 20-3-2017.
 */
public abstract class GUIElement extends VBox {
    public abstract <T> T accept(BaseEvaluator<T> visitor);
}
