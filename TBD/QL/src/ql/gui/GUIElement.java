package ql.gui;

import javafx.scene.layout.VBox;

/**
 * Created by Erik on 20-3-2017.
 */
public abstract class GUIElement extends VBox {
    public abstract <T> T accept(BaseEvaluator<T> visitor);
}
