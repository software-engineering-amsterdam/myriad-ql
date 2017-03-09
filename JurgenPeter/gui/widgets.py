import re
from ql.datatypes import *
from tkinter.font import Font
from abc import abstractmethod


class Widget:

    LABELPREFIX = "label_"
    ENTRYPREFIX = "entry_"

    def __init__(self, app, question):
        self.label_id = self.LABELPREFIX + question.name
        self.entry_id = self.ENTRYPREFIX + question.name

        self.app = app
        self.app.addLabel(self.label_id, question.label)
        self.font = Font()

    def set_listener(self, listener):
        pass

    @property
    def datatype(self):
        return None

    def get_tkinter_label(self):
        return self.app.getLabelWidget(self.label_id)

    @abstractmethod
    def get_tkinter_widget(self):
        return None

    def set_font_family(self, family):
        self.font["family"] = family
        self.get_tkinter_label().config(font=self.font)
        self.get_tkinter_widget().config(font=self.font)

    def set_font_size(self, size):
        self.font["size"] = size
        self.get_tkinter_label().config(font=self.font)
        self.get_tkinter_widget().config(font=self.font)

    def set_font_weight(self, weight):
        self.font["weight"] = weight
        self.get_tkinter_label().config(font=self.font)
        self.get_tkinter_widget().config(font=self.font)

    def set_color(self, color):
        self.get_tkinter_label().config(fg=color)
        self.get_tkinter_widget().config(fg=color)

    def set_width(self, width):
        self.get_tkinter_label().config(width=width)
        self.get_tkinter_widget().config(width=width)

    def apply(self, styling):
        if styling.applicable(self.datatype):
            for attribute in styling.attributes:
                attribute.apply_on(self)


class EntryWidget(Widget):

    def __init__(self, app, question):
        super().__init__(app, question)
        self.app.addEntry(self.entry_id)
        self.register_validator(self.validate)

    def register_validator(self, validate):
        widget = self.app.getEntryWidget(self.entry_id)
        command = widget.register(validate)
        widget.config(validate="key", validatecommand=(command, "%P"))

    @staticmethod
    def validate(text):
        return True

    def show(self):
        self.app.showLabel(self.label_id)
        self.app.showEntry(self.entry_id)

    def hide(self):
        self.app.hideLabel(self.label_id)
        self.app.hideEntry(self.entry_id)

    def disable(self):
        self.app.disableEntry(self.entry_id)

    def set_value(self, value):
        if value is not None:
            self.app.setEntry(self.entry_id, str(value))
        else:
            self.app.setEntry(self.entry_id, "")

    def get_value(self):
        return self.app.getEntry(self.entry_id)

    @property
    def datatype(self):
        return StringDatatype()

    def get_tkinter_widget(self):
        return self.app.getEntryWidget(self.entry_id)


class IntegerEntryWidget(EntryWidget):

    @staticmethod
    def validate(text):
        return bool(re.match("^(-|\+)?[0-9]*$", text))

    def get_value(self):
        try:
            return int(self.app.getEntry(self.entry_id))
        except ValueError:
            return None

    @property
    def datatype(self):
        return IntegerDatatype()


class DecimalEntryWidget(EntryWidget):

    @staticmethod
    def validate(text):
        return bool(re.match("^(-|\+)?[0-9]*\.?[0-9]*$", text))

    def get_value(self):
        try:
            return float(self.app.getEntry(self.entry_id))
        except ValueError:
            return None

    @property
    def datatype(self):
        return DecimalDatatype()


class CheckBoxWidget(Widget):

    def __init__(self, app, question):
        super().__init__(app, question)
        self.app.addCheckBox(self.entry_id)
        self.get_tkinter_widget().config(text="")

    def set_listener(self, listener):
        self.app.setCheckBoxFunction(self.entry_id, listener)

    def show(self):
        self.app.showLabel(self.label_id)
        self.app.showCheckBox(self.entry_id)

    def hide(self):
        self.app.hideLabel(self.label_id)
        self.app.hideCheckBox(self.entry_id)

    def disable(self):
        self.app.disableCheckBox(self.entry_id)

    def set_value(self, value):
        self.app.setCheckBox(self.entry_id, ticked=bool(value))

    def get_value(self):
        return self.app.getCheckBox(self.entry_id)

    @property
    def datatype(self):
        return BooleanDatatype()

    def get_tkinter_widget(self):
        return self.app.getCheckBoxWidget(self.entry_id)


class SpinBoxWidget(Widget):
    def __init__(self, app, question, lower=0, upper=100):
        super().__init__(app, question)
        self.lower = lower
        self.upper = upper
        self.app.addSpinBoxRange(self.entry_id, lower, upper)
        self.set_value(lower)

    def set_listener(self, listener):
        self.app.setSpinBoxFunction(self.entry_id, listener)

    def show(self):
        self.app.showLabel(self.label_id)
        self.app.showSpinBox(self.entry_id)

    def hide(self):
        self.app.hideLabel(self.label_id)
        self.app.hideSpinBox(self.entry_id)

    def disable(self):
        self.app.disableSpinBox(self.entry_id)

    def set_value(self, value):
        if value is not None:
            value = max(self.lower, min(self.upper, value))
            self.app.setSpinBox(self.entry_id, value)
        else:
            self.app.setSpinBox(self.entry_id, self.lower)

    def get_value(self):
        try:
            return int(self.app.getSpinBox(self.entry_id))
        except ValueError:
            return None

    @property
    def datatype(self):
        return IntegerDatatype()

    def get_tkinter_widget(self):
        return self.app.getSpinBoxWidget(self.entry_id)


class RadioWidget(Widget):
    def __init__(self, app, question, true_text="Yes", false_text="No"):
        super().__init__(app, question)
        self.true_text = true_text
        self.false_text = false_text
        self.app.addRadioButton(self.entry_id, false_text)
        self.app.addRadioButton(self.entry_id, true_text)

    def set_listener(self, listener):
        self.app.setRadioButtonFunction(self.entry_id, listener)

    def show(self):
        self.app.showLabel(self.label_id)
        for widget in self.get_tkinter_widget():
            widget.grid()

    def hide(self):
        self.app.hideLabel(self.label_id)
        for widget in self.get_tkinter_widget():
            widget.grid_remove()

    def disable(self):
        self.app.disableRadioButton(self.entry_id)

    def set_value(self, value):
        if value:
            self.app.setRadioButton(self.entry_id, self.true_text)
        else:
            self.app.setRadioButton(self.entry_id, self.false_text)

    def get_value(self):
        return self.app.getRadioButton(self.entry_id) == self.true_text

    @property
    def datatype(self):
        return BooleanDatatype()

    def get_tkinter_widget(self):
        return self.app.getRadioButtonWidget(self.entry_id)

    def set_font_familiy(self, family):
        self.font["family"] = family
        self.get_tkinter_label().config(font=self.font)
        for widget in self.get_tkinter_widget():
            widget.config(font=self.font)

    def set_font_size(self, size):
        self.font["size"] = size
        self.get_tkinter_label().config(font=self.font)
        for widget in self.get_tkinter_widget():
            widget.config(font=self.font)

    def set_font_weight(self, weight):
        self.font["weight"] = weight
        self.get_tkinter_label().config(font=self.font)
        for widget in self.get_tkinter_widget():
            widget.config(font=self.font)

    def set_color(self, color):
        for widget in self.get_tkinter_widget():
            widget.config(fg=color)
        self.get_tkinter_label().config(fg=color)


class DropDownWidget(Widget):
    def __init__(self, app, question, true_text="Yes", false_text="No"):
        super().__init__(app, question)
        self.true_text = true_text
        self.false_text = false_text
        self.app.addOptionBox(self.entry_id, [false_text, true_text])

    def set_listener(self, listener):
        self.app.setOptionBoxFunction(self.entry_id, listener)

    def show(self):
        self.app.showLabel(self.label_id)
        self.app.showOptionBox(self.entry_id)

    def hide(self):
        self.app.hideLabel(self.label_id)
        self.app.hideOptionBox(self.entry_id)

    def disable(self):
        self.app.disableOptionBox(self.entry_id)

    def set_value(self, value):
        if value:
            self.app.setOptionBox(self.entry_id, self.true_text)
        else:
            self.app.setOptionBox(self.entry_id, self.false_text)

    def get_value(self):
        return self.app.getOptionBox(self.entry_id) == self.true_text

    @property
    def datatype(self):
        return BooleanDatatype()

    def get_tkinter_widget(self):
        return self.app.getOptionBoxWidget(self.entry_id)


class SliderWidget(Widget):
    def __init__(self, app, question, lower=0, upper=100):
        super().__init__(app, question)
        self.lower = lower
        self.upper = upper
        self.app.addScale(self.entry_id)
        self.app.showScaleValue(self.entry_id)
        self.app.setScaleRange(self.entry_id, lower, upper)
        self.set_value(lower)

    def set_listener(self, listener):
        self.app.setScaleFunction(self.entry_id, listener)

    def show(self):
        self.app.showLabel(self.label_id)
        self.app.showScale(self.entry_id)

    def hide(self):
        self.app.hideLabel(self.label_id)
        self.app.hideScale(self.entry_id)

    def disable(self):
        self.app.disableScale(self.entry_id)

        """ Due to a Tkinter bug, the slider must be enabled again to allow it
            to be moved by set_value. However, after being enabled, the user
            will still be unable to move the slider manually. """
        self.app.enableScale(self.entry_id)

    def set_value(self, value):
        if value is not None:
            value = max(self.lower, min(self.upper, value))
            self.app.setScale(self.entry_id, value)
        else:
            self.app.setScale(self.entry_id, self.lower)

    def get_value(self):
        try:
            return int(self.app.getScale(self.entry_id))
        except ValueError:
            return None

    @property
    def datatype(self):
        return IntegerDatatype()

    def get_tkinter_widget(self):
        return self.app.getScaleWidget(self.entry_id)
