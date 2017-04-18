package org.qls.ast.widget;

public interface WidgetVisitor<T, C> {
    T visitCheckbox(Checkbox widget, C context);
    T visitDropdown(Dropdown widget, C context);
    T visitRadio(Radio widget, C context);
    T visitSlider(Slider widget, C context);
    T visitSpinbox(Spinbox widget, C context);
    T visitText(Text widget, C context);
}
