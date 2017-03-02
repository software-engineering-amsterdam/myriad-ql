import re


class Widget:
    LABELPREFIX = "label_"
    ENTRYPREFIX = "entry_"

    def __init__(self, app, question):
        self.app = app
        self.label_id = self.LABELPREFIX + question.name
        self.entry_id = self.ENTRYPREFIX + question.name

    def set_listener(self, listener):
        pass


class EntryWidget(Widget):

    def __init__(self, app, question):
        super().__init__(app, question)
        self.app.addLabel(self.label_id, question.label)
        self.app.addEntry(self.entry_id)
        widget = self.app.getEntryWidget(self.entry_id)
        command = widget.register(self.validate)
        widget.config(validate="key", validatecommand=(command, "%P"))

    def show(self):
        self.app.showLabel(self.label_id)
        self.app.showEntry(self.entry_id)

    def hide(self):
        self.app.hideLabel(self.label_id)
        self.app.hideEntry(self.entry_id)

    def disable(self):
        self.app.disableEntry(self.entry_id)

    @staticmethod
    def validate(text):
        return True

    def set_value(self, value):
        if value is not None:
            self.app.setEntry(self.entry_id, str(value))
        else:
            self.app.setEntry(self.entry_id, "")

    def get_value(self):
        return self.app.getEntry(self.entry_id)

    def set_properties(self, properties):
        self.app.getEntryWidget(self.entry_id).config(**properties)

class IntegerEntryWidget(EntryWidget):

    @staticmethod
    def validate(text):
        if re.match("^(-|\+)?[0-9]*$", text):
            return True
        return False

    def get_value(self):
        try:
            return int(self.app.getEntry(self.entry_id))
        except ValueError:
            return None


class DecimalEntryWidget(EntryWidget):

    @staticmethod
    def validate(text):
        if re.match("^(-|\+)?[0-9]*\.?[0-9]*$", text):
            return True
        return False

    def get_value(self):
        try:
            return float(self.app.getEntry(self.entry_id))
        except ValueError:
            return None


class CheckBoxWidget(Widget):

    def __init__(self, app, question):
        super().__init__(app, question)
        self.app.addLabel(self.label_id, question.label)
        self.app.addCheckBox(self.entry_id)
        self.app.getCheckBoxWidget(self.entry_id).config(text="")

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


class SpinBoxWidget(Widget):
    def __init__(self, app, question, lower=0, upper=100):
        super().__init__(app, question)
        self.lower = lower
        self.upper = upper

        self.app.addLabel(self.label_id, question.label)
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


class RadioWidget(Widget):
    def __init__(self, app, question, true_text="Yes", false_text="No"):
        super().__init__(app, question)
        self.true_text = true_text
        self.false_text = false_text

        self.app.addLabel(self.label_id, question.label)
        self.app.addRadioButton(self.entry_id, false_text)
        self.app.addRadioButton(self.entry_id, true_text)

    def set_listener(self, listener):
        self.app.setRadioButtonFunction(self.entry_id, listener)

    def show(self):
        self.app.showLabel(self.label_id)
        for widget in self.app.getRadioButtonWidget(self.entry_id):
            widget.grid()

    def hide(self):
        self.app.hideLabel(self.label_id)
        for widget in self.app.getRadioButtonWidget(self.entry_id):
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


class DropDownWidget(Widget):
    def __init__(self, app, question, true_text="Yes", false_text="No"):
        super().__init__(app, question)
        self.true_text = true_text
        self.false_text = false_text

        self.app.addLabel(self.label_id, question.label)
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


class SliderWidget(Widget):
    def __init__(self, app, question, lower=0, upper=100):
        super().__init__(app, question)
        self.lower = lower
        self.upper = upper

        self.app.addLabel(self.label_id, question.label)
        self.app.addScale(self.entry_id)
        self.app.setScaleRange(self.entry_id, lower, upper)
        self.app.showScaleValue(self.entry_id)
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

        # Due to Tkinter bug, the slider must be enabled again to allow it to
        # be moved by set_value. The user will still be unable to move it.
        self.app.enableScale(self.entry_id)

    def set_value(self, value):
        if value is not None:
            value = max(self.lower, min(self.upper, value))
            self.app.getScaleWidget(self.entry_id).set(value)
        else:
            self.app.setScale(self.entry_id, self.lower)

    def get_value(self):
        try:
            return int(self.app.getScale(self.entry_id))
        except ValueError:
            return None
