from QL.GUI.widgets import CheckBoxWidget as QLCheckBox
from QL.GUI.widgets import SpinBoxWidget as QLSpinBox
from QL.GUI.widgets import EntryWidget as QLEntry
from QL.GUI.widgets import NumericWidget as QLNumeric
from QL.GUI.widgets import ComputedLabelWidget as QLComputed
from QL.GUI.widgets import Widget as QLWidget


class Widget(QLWidget):
    def __init__(self, form_gui, identifier, question, row):
        super(Widget, self).__init__(form_gui, identifier, question, row)

    def set_width(self, width):
        self.main.setLabelWidth(self.identifier, width)
        self.tk_ref.config(width=width)

    def set_height(self, height):
        self.main.setLabelWidth(self.identifier, height)
        self.tk_ref.config(height=height)

    def set_font_size(self, size):
        widget = self.main.getLabelWidget(self.identifier)
        family = widget["font"]

        font_config = "{} {}".format(family, size)
        widget.config(font=font_config)
        self.tk_ref.config(font=font_config)

    def set_font_family(self, family):
        widget = self.main.getLabelWidget(self.identifier)
        widget.config(font=family)
        self.tk_ref.config(font=family)

    def set_font_color(self, color):
        self.main.setLabelFg(self.identifier, color)
        self.tk_ref.config(fg=color)


class EntryWidget(QLEntry, Widget):
    def __init__(self, form_gui, identifier, question, row):
        super(EntryWidget, self).__init__(form_gui, identifier, question, row)
        self.tk_ref = self.main.getEntryWidget(self.identifier)


class SpinBoxWidget(QLSpinBox, Widget):
    def __init__(self, form_gui, identifier, question, row):
        super(SpinBoxWidget, self).__init__(form_gui, identifier, question, row)
        self.tk_ref = self.main.getSpinBoxWidget(self.identifier)


class NumericWidget(QLNumeric, Widget):
    def __init__(self, form_gui, identifier, question, row):
        super(NumericWidget, self).__init__(form_gui, identifier, question, row)
        self.tk_ref = self.main.getEntryWidget(self.identifier)


class CheckBoxWidget(QLCheckBox, Widget):
    def __init__(self, form_gui, identifier, question, row):
        super(CheckBoxWidget, self).__init__(
            form_gui, identifier, question, row
        )
        self.tk_ref = self.main.getCheckBoxWidget(self.identifier)


class ComputedLabelWidget(QLComputed, Widget):
    def __init__(self, form_gui, identifier, question, row):
        super(ComputedLabelWidget, self).__init__(
            form_gui, identifier, question, row
        )
        self.tk_ref = self.main.getLabelWidget(self.identifier)


class SliderWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(SliderWidget, self).__init__(
            form_gui, identifier, question, row=row
        )
        self.tk_ref = self.main.getScaleWidget(self.identifier)
        self.main.addScale(self.identifier, row=row, column=1)
        self.main.setScaleRange(self.identifier, 0, 100000, curr=None)
        self.main.showScaleIntervals(identifier, 25000)
        self.main.showScaleValue(identifier, show=True)
        self.add_listener(self.tk_ref)

    def get_entry(self):
        return self.main.getScale(self.identifier)

    def set_entry(self, value):
        self.main.setScale(self.identifier, value)


class RadioButtonWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):

        form_gui.main.startFrame(identifier)
        # Create label
        super(RadioButtonWidget, self).__init__(
            form_gui, identifier, question, row=row
        )
        self.main.addRadioButton(self.identifier, "Yes", row=row, column=1)
        self.main.addRadioButton(self.identifier, "No",  row=row, column=2)
        self.main.stopFrame()

        self.add_listener(self.main.getRadioButtonWidget(self.identifier)[0])
        self.add_listener(self.main.getRadioButtonWidget(self.identifier)[1])
        self.tk_ref = self.main.getFrameWidget(self.identifier)

    def get_entry(self):
        value = self.main.getRadioButton(self.identifier)
        return True if value == "Yes" else False

    def set_entry(self, value):
        value = "Yes" if value else "No"
        self.main.setRadioButton(self.identifier, value)

    def set_font_size(self, size):
        super(RadioButtonWidget, self).set_font_size(size)
        radio_widgets = self.main.getRadioButtonWidget(self.identifier)
        family = radio_widgets[0]["font"]
        for widget in radio_widgets:
            widget.config(font="{} {}".format(family, size))

    def set_font_family(self, family):
        super(RadioButtonWidget, self).set_font_family(family)
        radio_widgets = self.main.getRadioButtonWidget(self.identifier)
        for widget in radio_widgets:
            widget.config(font="{}".format(family))


class DropDownWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(DropDownWidget, self).__init__(
            form_gui, identifier, question, row=row
        )
        self.main.addOptionBox(self.identifier, ["Yes", "No"],
                               row=row, column=1)
        self.tk_ref = self.main.getOptionBoxWidget(self.identifier)
        self.add_listener(self.tk_ref)

    def get_entry(self):
        value = self.main.getOptionBox(self.identifier)
        return True if value == "Yes" else False

    def set_entry(self, value):
        value = "Yes" if value else "No"
        self.main.setOptionBox(self.identifier, value)
