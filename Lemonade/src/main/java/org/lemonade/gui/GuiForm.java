package org.lemonade.gui;


import java.util.List;


import javafx.scene.control.Control;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.visitors.EvaluateVisitor;

public class GuiForm extends GuiBody {

    private GuiIdentifierValue identifier;
    private List<GuiBody> bodies;

    public GuiForm(GuiIdentifierValue identifier, List<GuiBody> bodies) {
        this.identifier = identifier;
        this.bodies = bodies;
    }

    public List<GuiBody> getBodies() {
        return this.bodies;
    }

    public GuiForm accept(EvaluateVisitor visitor) {
        return visitor.visit(this);
    }

}
