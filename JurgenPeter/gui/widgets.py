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
