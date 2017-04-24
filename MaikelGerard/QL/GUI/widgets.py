class Widget(object):
    def __init__(self, form_gui, identifier, question, row=0):
        self.form_gui = form_gui
        self.main = form_gui.main
        self.main.addLabel(identifier, question, row=row, column=0)
        self.identifier = identifier
        self.hidden = False

        self.tk_ref = None

    def hide(self):
        self.main.hideLabel(self.identifier)
        self.tk_ref.grid_remove()
        self.hidden = True

    def show(self):
        self.main.showLabel(self.identifier)
        self.tk_ref.grid()
        self.hidden = False

    def add_listener(self, tkinter_obj):
        tkinter_obj.bind("<KeyRelease>", self.form_gui.redraw)
        tkinter_obj.bind("<FocusOut>", self.form_gui.redraw)
        tkinter_obj.bind("<ButtonRelease-1>", self.form_gui.redraw)


class EntryWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(EntryWidget, self).__init__(
            form_gui, identifier, question, row=row
        )

        self.main.addEntry(self.identifier, row=row, column=1)
        self.add_listener(self.main.getEntryWidget(self.identifier))
        self.main.setEntry(identifier, '')
        self.tk_ref = self.main.getEntryWidget(self.identifier)

    def get_entry(self):
        return self.main.getEntry(self.identifier)

    def set_entry(self, value):
        self.main.setEntry(self.identifier, value)


class NumericWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(NumericWidget, self).__init__(form_gui, identifier,
                                            question, row=row)

        self.main.addNumericEntry(identifier, row=row, column=1)
        self.add_listener(self.main.getEntryWidget(self.identifier))

        # Unsupported by AppJar; do not return NumericEntry value as float.
        entry = self.main._gui__verifyItem(self.main.n_entries, identifier)
        entry.isNumeric = False

        self.tk_ref = self.main.getEntryWidget(self.identifier)

    def get_entry(self):
        value = self.main.getEntry(self.identifier)
        if value == "-":
            value = "-0"
        return value

    def set_entry(self, value):
        self.main.setEntry(self.identifier, value)


class SpinBoxWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(SpinBoxWidget, self).__init__(form_gui, identifier, question, row)
        self.main.addSpinBoxRange(identifier, 0, 100000, row=row, column=1)
        self.add_listener(self.main.getSpinBoxWidget(identifier))

        # Set the entry's value and save the retrieval method.
        self.main.setSpinBox(identifier, 0)
        self.tk_ref = self.main.getSpinBoxWidget(self.identifier)

    def get_entry(self):
        return self.main.getSpinBox(self.identifier)

    def set_entry(self, value):
        if value == '':
            value = 0
        self.main.setSpinBox(self.identifier, value)


class CheckBoxWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(CheckBoxWidget, self).__init__(form_gui, identifier,
                                             question, row)

        # Add the question fields; change the checkbox text to 'Yes'.
        self.main.addCheckBox(identifier, row=row, column=1)
        self.main.getCheckBoxWidget(identifier).config(text="Yes")
        self.add_listener(self.main.getCheckBoxWidget(identifier))
        self.main.setCheckBox(identifier, ticked=False)

        self.tk_ref = self.main.getCheckBoxWidget(self.identifier)

    def get_entry(self):
        return self.main.getCheckBox(self.identifier)

    def set_entry(self, value):
        self.main.setCheckBox(self.identifier, value)


class DateWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(DateWidget, self).__init__(form_gui, identifier,
                                         question, row)

        self.main.addDatePicker(self.identifier, row=row, column=1)
        self.add_listener(self.main.getFrameWidget(self.identifier))
        self.tk_ref = self.main.getFrameWidget(self.identifier)

    def get_entry(self):
        return self.main.getDatePicker(self.identifier)

    def set_entry(self, value):
        self.main.setDatePicker(self.identifier, value)


class ComputedLabelWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(ComputedLabelWidget, self).__init__(form_gui, identifier,
                                                  question, row=row)

        self.computed_identifier = "@computed_" + self.identifier
        self.main.addLabel(self.computed_identifier, row=row, column=1)
        self.main.setLabel(self.computed_identifier, '')

        self.tk_ref = self.main.getLabelWidget(self.computed_identifier)

    def get_entry(self):
        return self.main.getLabel(self.computed_identifier)

    def set_entry(self, value):
        self.main.setLabel(self.computed_identifier, str(value))
