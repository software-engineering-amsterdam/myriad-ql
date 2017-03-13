import re
from ql.datatypes import *
from tkinter.font import Font
from abc import abstractmethod


class Widget:

    LABELPREFIX = "label_"
    ENTRYPREFIX = "entry_"

    def __init__(self, gui, question):
        self.label_id = self.LABELPREFIX + question.name
        self.entry_id = self.ENTRYPREFIX + question.name

        self.gui = gui
        self.gui.addLabel(self.label_id, question.label)
        self.font = Font()

    def set_listener(self, listener):
        pass

    @staticmethod
    def get_datatype():
        return None

    def get_tkinter_label(self):
        return self.gui.getLabelWidget(self.label_id)

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


class EntryWidget(Widget):

    def __init__(self, gui, question):
        super().__init__(gui, question)
        self.gui.addEntry(self.entry_id)
        self.register_validator(self.validate)

    def register_validator(self, validate):
        widget = self.gui.getEntryWidget(self.entry_id)
        command = widget.register(validate)
        widget.config(validate="key", validatecommand=(command, "%P"))

    @staticmethod
    def validate(text):
        return True

    def show(self):
        self.gui.showLabel(self.label_id)
        self.gui.showEntry(self.entry_id)

    def hide(self):
        self.gui.hideLabel(self.label_id)
        self.gui.hideEntry(self.entry_id)

    def disable(self):
        self.gui.disableEntry(self.entry_id)

    def set_value(self, value):
        if value is not None:
            self.gui.setEntry(self.entry_id, str(value))
        else:
            self.gui.setEntry(self.entry_id, "")

    def get_value(self):
        return self.gui.getEntry(self.entry_id)

    @staticmethod
    def get_datatype():
        return StringDatatype()

    def get_tkinter_widget(self):
        return self.gui.getEntryWidget(self.entry_id)


class IntegerEntryWidget(EntryWidget):

    @staticmethod
    def validate(text):
        return bool(re.match(r"^(-|\+)?[0-9]*$", text))

    def get_value(self):
        try:
            return int(self.gui.getEntry(self.entry_id))
        except ValueError:
            return None

    @staticmethod
    def get_datatype():
        return IntegerDatatype()


class DecimalEntryWidget(EntryWidget):

    @staticmethod
    def validate(text):
        return bool(re.match(r"^(-|\+)?[0-9]*\.?[0-9]*$", text))

    def get_value(self):
        try:
            return float(self.gui.getEntry(self.entry_id))
        except ValueError:
            return None

    @staticmethod
    def get_datatype():
        return DecimalDatatype()


class CheckBoxWidget(Widget):

    def __init__(self, gui, question):
        super().__init__(gui, question)
        self.gui.addCheckBox(self.entry_id)
        self.get_tkinter_widget().config(text="")

    def set_listener(self, listener):
        self.gui.setCheckBoxFunction(self.entry_id, listener)

    def show(self):
        self.gui.showLabel(self.label_id)
        self.gui.showCheckBox(self.entry_id)

    def hide(self):
        self.gui.hideLabel(self.label_id)
        self.gui.hideCheckBox(self.entry_id)

    def disable(self):
        self.gui.disableCheckBox(self.entry_id)

    def set_value(self, value):
        self.gui.setCheckBox(self.entry_id, ticked=bool(value))

    def get_value(self):
        return self.gui.getCheckBox(self.entry_id)

    @staticmethod
    def get_datatype():
        return BooleanDatatype()

    def get_tkinter_widget(self):
        return self.gui.getCheckBoxWidget(self.entry_id)


class SpinBoxWidget(Widget):
    def __init__(self, gui, question, lower=0, upper=100):
        super().__init__(gui, question)
        self.lower = lower
        self.upper = upper
        self.gui.addSpinBoxRange(self.entry_id, lower, upper)
        self.set_value(lower)

    def set_listener(self, listener):
        self.gui.setSpinBoxFunction(self.entry_id, listener)

    def show(self):
        self.gui.showLabel(self.label_id)
        self.gui.showSpinBox(self.entry_id)

    def hide(self):
        self.gui.hideLabel(self.label_id)
        self.gui.hideSpinBox(self.entry_id)

    def disable(self):
        self.gui.disableSpinBox(self.entry_id)

    def set_value(self, value):
        if value is not None:
            value = max(self.lower, min(self.upper, value))
            self.gui.setSpinBox(self.entry_id, value)
        else:
            self.gui.setSpinBox(self.entry_id, self.lower)

    def get_value(self):
        try:
            return int(self.gui.getSpinBox(self.entry_id))
        except ValueError:
            return None

    @staticmethod
    def get_datatype():
        return IntegerDatatype()

    def get_tkinter_widget(self):
        return self.gui.getSpinBoxWidget(self.entry_id)


class RadioWidget(Widget):
    def __init__(self, gui, question, true_text="Yes", false_text="No"):
        super().__init__(gui, question)
        self.true_text = true_text
        self.false_text = false_text
        self.gui.addRadioButton(self.entry_id, false_text)
        self.gui.addRadioButton(self.entry_id, true_text)

    def set_listener(self, listener):
        self.gui.setRadioButtonFunction(self.entry_id, listener)

    def show(self):
        self.gui.showLabel(self.label_id)
        for widget in self.get_tkinter_widget():
            widget.grid()

    def hide(self):
        self.gui.hideLabel(self.label_id)
        for widget in self.get_tkinter_widget():
            widget.grid_remove()

    def disable(self):
        self.gui.disableRadioButton(self.entry_id)

    def set_value(self, value):
        if value:
            self.gui.setRadioButton(self.entry_id, self.true_text)
        else:
            self.gui.setRadioButton(self.entry_id, self.false_text)

    def get_value(self):
        return self.gui.getRadioButton(self.entry_id) == self.true_text

    @staticmethod
    def get_datatype():
        return BooleanDatatype()

    def get_tkinter_widget(self):
        return self.gui.getRadioButtonWidget(self.entry_id)

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
    def __init__(self, gui, question, true_text="Yes", false_text="No"):
        super().__init__(gui, question)
        self.true_text = true_text
        self.false_text = false_text
        self.gui.addOptionBox(self.entry_id, [false_text, true_text])

    def set_listener(self, listener):
        self.gui.setOptionBoxFunction(self.entry_id, listener)

    def show(self):
        self.gui.showLabel(self.label_id)
        self.gui.showOptionBox(self.entry_id)

    def hide(self):
        self.gui.hideLabel(self.label_id)
        self.gui.hideOptionBox(self.entry_id)

    def disable(self):
        self.gui.disableOptionBox(self.entry_id)

    def set_value(self, value):
        if value:
            self.gui.setOptionBox(self.entry_id, self.true_text)
        else:
            self.gui.setOptionBox(self.entry_id, self.false_text)

    def get_value(self):
        return self.gui.getOptionBox(self.entry_id) == self.true_text

    @staticmethod
    def get_datatype():
        return BooleanDatatype()

    def get_tkinter_widget(self):
        return self.gui.getOptionBoxWidget(self.entry_id)


class SliderWidget(Widget):
    def __init__(self, gui, question, lower=0, upper=100):
        super().__init__(gui, question)
        self.lower = lower
        self.upper = upper
        self.gui.addScale(self.entry_id)
        self.gui.showScaleValue(self.entry_id)
        self.gui.setScaleRange(self.entry_id, lower, upper)
        self.set_value(lower)

    def set_listener(self, listener):
        self.gui.setScaleFunction(self.entry_id, listener)

    def show(self):
        self.gui.showLabel(self.label_id)
        self.gui.showScale(self.entry_id)

    def hide(self):
        self.gui.hideLabel(self.label_id)
        self.gui.hideScale(self.entry_id)

    def disable(self):
        self.gui.disableScale(self.entry_id)

        """ Due to a Tkinter bug, the slider must be enabled again to allow it
            to be moved by set_value. However, after being enabled, the user
            will still be unable to move the slider manually. """
        self.gui.enableScale(self.entry_id)

    def set_value(self, value):
        if value is not None:
            value = max(self.lower, min(self.upper, value))
            self.gui.setScale(self.entry_id, value)
        else:
            self.gui.setScale(self.entry_id, self.lower)

    def get_value(self):
        try:
            return int(self.gui.getScale(self.entry_id))
        except ValueError:
            return None

    @staticmethod
    def get_datatype():
        return IntegerDatatype()

    def get_tkinter_widget(self):
        return self.gui.getScaleWidget(self.entry_id)
