package org.lemonade.gui.elements;

<<<<<<< Updated upstream
=======
import javafx.scene.control.TextField;
import org.lemonade.gui.GuiElement;
>>>>>>> Stashed changes
import org.lemonade.gui.values.GuiStringValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;

public class GuiStringElement implements GuiElement {

    private GuiValue<?> value;
    private TextField textField;

    public GuiStringElement() {
        value = new GuiUndefinedValue();
        textField = new TextField();
        textField.setOnKeyReleased(e -> update());
    }

    @Override
    public GuiValue<?> getValue() {
        return value;
    }

    @Override
    public void update() {
        String textFieldValue = textField.getText();
        if (textFieldValue.equals(""))
            this.value = new GuiUndefinedValue();
        else if (this.value.isDefined())
            ((GuiStringValue) this.value).update(textFieldValue);
        else
            this.value = new GuiStringValue(textFieldValue);
    }

    @Override
    public TextField getWidget() {
        return textField;
    }

}
