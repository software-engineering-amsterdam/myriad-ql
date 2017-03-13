from QL.GUI.widgets import CheckBoxWidget as QLCheckBox
from QL.GUI.widgets import EntryWidget as QLEntry
from QL.GUI.widgets import NumericWidget as QLNumeric
from QL.GUI.widgets import ComputedLabelWidget as QLComputed
from QL.GUI.widgets import Widget as QLWidget


class Widget(QLWidget):
    def __init__(self, form_gui, identifier, question, row):
        super(Widget, self).__init__(form_gui, identifier, question, row)

    def set_width(self, width):
        self.main.setLabelWidth(self.identifier, width)

    def set_height(self, height):
        self.main.setLabelWidth(self.identifier, height)

    def set_font(self, family, size):
        widget = self.main.getLabelWidget(self.identifier)
        widget.config(font="{} {}".format(family, size))

    def set_font_color(self, color):
        self.main.setLabelFg(self.identifier, color)


class EntryWidget(QLEntry, Widget):
    def __init__(self, form_gui, identifier, question, row):
        super(EntryWidget, self).__init__(form_gui, identifier, question, row)

    def set_width(self, width):
        super(EntryWidget, self).set_width(width)
        self.main.setEntryWidth(self.identifier, width)

    def set_height(self, height):
        super(EntryWidget, self).set_height(height)
        self.main.setEntryHeight(self.identifier, height)

    def set_font(self, family, size):
        super(EntryWidget, self).set_font(family, size)
        widget = self.main.getEntryWidget(self.identifier)
        widget.config(font="{} {}".format(family, size))

    def set_font_color(self, color):
        super(EntryWidget, self).set_font_color(color)
        self.main.setEntryFg(self.identifier)


class NumericWidget(QLNumeric, Widget):
    def __init__(self, form_gui, identifier, question, row):
        super(NumericWidget, self).__init__(form_gui, identifier, question, row)

    def set_width(self, width):
        super(NumericWidget, self).set_width(width)
        self.main.setEntryWidth(self.identifier, width)

    def set_height(self, height):
        super(NumericWidget, self).set_height(height)
        self.main.setEntryHeight(self.identifier, height)

    def set_font(self, family, size):
        super(NumericWidget, self).set_font(family, size)
        widget = self.main.getEntryWidget(self.identifier)
        widget.config(font="{} {}".format(family, size))

    def set_font_color(self, color):
        super(NumericWidget, self).set_font_color(color)
        self.main.setEntryFg(self.identifier)


class CheckBoxWidget(QLCheckBox, Widget):
    def __init__(self, form_gui, identifier, question, row):
        super(CheckBoxWidget, self).__init__(
            form_gui, identifier, question, row
        )

    def set_width(self, width):
        super(CheckBoxWidget, self).set_width(width)
        self.main.setCheckBoxWidth(self.identifier, width)

    def set_height(self, height):
        super(CheckBoxWidget, self).set_height(height)
        self.main.setCheckBoxHeight(self.identifier, height)

    def set_font(self, family, size):
        super(CheckBoxWidget, self).set_font(family, size)
        widget = self.main.getCheckBoxWidget(self.identifier)
        widget.config(font="{} {}".format(family, size))

    def set_font_color(self, color):
        super(CheckBoxWidget, self).set_font_color(color)
        self.main.setCheckBoxFg(self.identifier)


class ComputedLabelWidget(QLComputed, Widget):
    def __init__(self, form_gui, identifier, question, row):
        super(ComputedLabelWidget, self).__init__(
            form_gui, identifier, question, row
        )

    def set_width(self, width):
        super(ComputedLabelWidget, self).set_width(width)
        self.main.setLabelWidth(self.computed_identifier, width)

    def set_height(self, height):
        super(ComputedLabelWidget, self).set_height(height)
        self.main.setLabelHeight(self.computed_identifier, height)

    def set_font(self, family, size):
        super(ComputedLabelWidget, self).set_font(family, size)
        widget = self.main.getLabelWidget(self.computed_identifier)
        widget.config(font="{} {}".format(family, size))

    def set_font_color(self, color):
        super(ComputedLabelWidget, self).set_font_color(color)
        self.main.setLabelFg(self.computed_identifier)


class SliderWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(SliderWidget, self).__init__(
            form_gui, identifier, question, row=row
        )

        self.main.addScale(self.identifier, row=row, column=1)
        self.main.setScaleRange(self.identifier, 0, 100, curr=None)
        self.add_listener(self.main.getScaleWidget(self.identifier))

    def get_entry(self):
        return self.main.getScale(self.identifier)

    def set_entry(self, value):
        self.main.setScale(self.identifier, value)

    def hide(self):
        super(SliderWidget, self).hide()
        self.main.hideScale(self.identifier)

    def show(self):
        super(SliderWidget, self).show()
        self.main.showScale(self.identifier)

    def set_width(self, width):
        super(SliderWidget, self).set_width(width)
        self.main.setScaleWidth(self.identifier, width)

    def set_height(self, height):
        super(SliderWidget, self).set_height(height)
        self.main.setScaleHeight(self.identifier, height)

    def set_font(self, family, size):
        super(SliderWidget, self).set_font(family, size)
        widget = self.main.getScaleWidget(self.identifier)
        widget.config(font="{} {}".format(family, size))

    def set_font_color(self, color):
        super(SliderWidget, self).set_font_color(color)
        self.main.setScaleFg(self.identifier)


class RadioButtonWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(RadioButtonWidget, self).__init__(
            form_gui, identifier, question, row=row
        )

        self.main.startFrame(self.identifier)
        self.main.addRadioButton(self.identifier, "Yes", row=row, column=1)
        self.main.addRadioButton(self.identifier, "No",  row=row, column=2)
        self.main.stopFrame()
        self.add_listener(self.main.getRadioButtonWidget(self.identifier))

    def get_entry(self):
        value = self.main.getRadioButton(self.identifier)
        return True if value == "Yes" else False

    def set_entry(self, value):
        value = "Yes" if value else "No"
        self.main.setRadioButton(self.identifier, value)

    def hide(self):
        super(RadioButtonWidget, self).hide()
        self.main.hideFrame(self.identifier)

    def show(self):
        super(RadioButtonWidget, self).show()
        self.main.showFrame(self.identifier)

    def set_width(self, width):
        super(RadioButtonWidget, self).set_width(width)
        self.main.setRadioButtonWidth(self.identifier, width)

    def set_height(self, height):
        super(RadioButtonWidget, self).set_height(height)
        self.main.setRadioButtonHeight(self.identifier, height)

    def set_font(self, family, size):
        super(RadioButtonWidget, self).set_font(family, size)
        widget = self.main.getRadioButtonWidget(self.identifier)
        widget.config(font="{} {}".format(family, size))

    def set_font_color(self, color):
        super(RadioButtonWidget, self).set_font_color(color)
        self.main.setRadioButtonFg(self.identifier)


class DropDownWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(DropDownWidget, self).__init__(
            form_gui, identifier, question, row=row
        )

        self.main.addOptionBox(self.identifier, ["Yes", "No"],
                               row=row, column=1)
        self.add_listener(self.main.getOptionBoxWidget(self.identifier))

    def get_entry(self):
        value = self.main.getOptionBox(self.identifier)
        return True if value == "Yes" else False

    def set_entry(self, value):
        value = "Yes" if value else "No"
        self.main.setOptionBox(self.identifier, value)

    def hide(self):
        super(DropDownWidget, self).hide()
        self.main.hideOptionBox(self.identifier)

    def show(self):
        super(DropDownWidget, self).show()
        self.main.showOptionBox(self.identifier)

    def set_width(self, width):
        super(DropDownWidget, self).set_width(width)
        self.main.setOptionBoxWidth(self.identifier, width)

    def set_height(self, height):
        super(DropDownWidget, self).set_height(height)
        self.main.setOptionBoxHeight(self.identifier, height)

    def set_font(self, family, size):
        super(DropDownWidget, self).set_font(family, size)
        widget = self.main.getOptionBoxWidget(self.identifier)
        widget.config(font="{} {}".format(family, size))

    def set_font_color(self, color):
        super(DropDownWidget, self).set_font_color(color)
        self.main.setOptionBoxFg(self.identifier)
