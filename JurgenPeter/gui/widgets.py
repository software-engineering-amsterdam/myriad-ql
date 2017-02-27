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


class IntegerEntryWidget(EntryWidget):
    def __init__(self, app, question):
        super().__init__(app, question)
        v = self.app.getEntryWidget(self.entry_id).register(self.validate)
        self.app.getEntryWidget(self.entry_id).config(validate="key",
                                                      validatecommand=(v, "%P"))

    @staticmethod
    def validate(text):
        if re.match("^(-|\+)?[0-9]*$", text):
            return True
        return False

    def set_value(self, value):
        if value is not None:
            self.app.setEntry(self.entry_id, str(value))
        else:
            self.app.setEntry(self.entry_id, "0")

    def get_value(self):
        value = self.app.getEntry(self.entry_id)
        if re.match("^(-|\+)?[0-9]+$", value):
            return int(value)
        return None


class DecimalEntryWidget(EntryWidget):
    def __init__(self, app, question):
        super().__init__(app, question)
        v = self.app.getEntryWidget(self.entry_id).register(self.validate)
        self.app.getEntryWidget(self.entry_id).config(validate="key",
                                                      validatecommand=(v, "%P"))

    @staticmethod
    def validate(text):
        if re.match("^(-|\+)?[0-9]*\.?[0-9]*$", text):
            return True
        return False

    def set_value(self, value):
        if value is not None:
            self.app.setEntry(self.entry_id, str(value))
        else:
            self.app.setEntry(self.entry_id, "0.0")

    def get_value(self):
        value = self.app.getEntry(self.entry_id)
        if re.match("^(-|\+)?[0-9]+\.?[0-9]*$", value):
            return float(value)
        if re.match("^(-|\+)?[0-9]*\.?[0-9]+$", value):
            return float(value)
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
