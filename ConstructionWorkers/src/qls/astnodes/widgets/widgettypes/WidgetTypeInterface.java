package qls.astnodes.widgets.widgettypes;

/**
 * Created by LGGX on 03-Mar-17.
 */

interface WidgetTypeInterface<T> {
    T visit(CheckBoxType widgetType);
    T visit(RadioType widgetType);
    T visit(DropdownType widgetType);
    T visit(SliderType widgetType);
    T visit(SpinBoxType widgetType);
    T visit(TextBoxType widgetType);
    T visit(UndefinedWidgetType widgetType);
}
