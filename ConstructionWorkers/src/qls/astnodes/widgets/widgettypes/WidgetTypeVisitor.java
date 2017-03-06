package qls.astnodes.widgets.widgettypes;

import ql.astnodes.LineNumber;
import qls.astnodes.widgets.*;

/**
 * Created by LGGX on 03-Mar-17.
 */
public class WidgetTypeVisitor implements WidgetTypeInterface<QLSWidget> {

    public QLSWidget visit(CheckBoxType widgetType) {
        return new QLSCheckBox();
    }

    public QLSWidget visit(RadioType widgetType) {
        return new QLSRadio();
    }

    public QLSWidget visit(DropdownType widgetType) {
        return new QLSDropdown();
    }

    public QLSWidget visit(SliderType widgetType) {
        return new QLSSlider();
    }

    public QLSWidget visit(SpinBoxType widgetType) {
        return new QLSSpinBox();
    }

    public QLSWidget visit(TextBoxType widgetType) {
        return new QLSTextBox();
    }

    public QLSWidget visit(UndefinedWidgetType widgetType) {
        return new QLSUndefinedWidget(new LineNumber(1));
    }
}
