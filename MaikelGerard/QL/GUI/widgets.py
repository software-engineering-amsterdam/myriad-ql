class Widget(object):
    def __init__(self, form_gui, identifier, question, row=0):
        self.form_gui = form_gui
        self.main = form_gui.main
        self.main.addLabel(identifier, question, row=row, column=0)
        self.identifier = identifier
        self.hidden = False

    def hide(self):
        self.main.hideLabel(self.identifier)
        self.hidden = True

    def show(self):
        self.main.showLabel(self.identifier)
        self.hidden = False

    def add_listener(self, tkinter_obj):
        tkinter_obj.bind("<Key>", self.form_gui.redraw)
        tkinter_obj.bind("<FocusOut>", self.form_gui.redraw)
        tkinter_obj.bind("<ButtonRelease-1>", self.form_gui.redraw)


class EntryWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(EntryWidget, self).__init__(form_gui, identifier,
                                          question, row=row)

        self.main.addEntry(self.identifier, row=row, column=1)
        self.add_listener(self.main.getEntryWidget(self.identifier))
        self.main.setEntry(identifier, '')

    def get_entry(self):
        return self.main.getEntry(self.identifier)

    def set_entry(self, value):
        self.main.setEntry(self.identifier, value)

    def hide(self):
        super(EntryWidget, self).hide()
        self.main.hideEntry(self.identifier)

    def show(self):
        super(EntryWidget, self).show()
        self.main.showEntry(self.identifier)


class NumericWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(NumericWidget, self).__init__(form_gui, identifier,
                                            question, row=row)

        self.main.addNumericEntry(identifier, row=row, column=1)
        self.add_listener(self.main.getEntryWidget(identifier))

        # Unsupported by AppJar; do not return NumericEntry value as float.
        entry = self.main._gui__verifyItem(self.main.n_entries, identifier)
        entry.isNumeric = False

    def get_entry(self):
        return self.main.getEntry(self.identifier)

    def set_entry(self, value):
        self.main.setEntry(self.identifier, value)

    def hide(self):
        super(NumericWidget, self).hide()
        self.main.hideEntry(self.identifier)

    def show(self):
        super(NumericWidget, self).show()
        self.main.showEntry(self.identifier)


class SpinBoxWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(SpinBoxWidget, self).__init__(form_gui, identifier, question, row)
        self.main.addSpinBoxRange(identifier, 0, 100000, row=row, column=1)
        self.add_listener(self.main.getSpinBoxWidget(identifier))

        # Set the entry's value and save the retrieval method.
        self.main.setSpinBox(identifier, 0)

    def get_entry(self):
        return self.main.getSpinBox(self.identifier)

    def set_entry(self, value):
        if value == '':
            value = 0
        self.main.setSpinBox(self.identifier, value)

    def hide(self):
        super(SpinBoxWidget, self).hide()
        self.main.hideSpinBox(self.identifier)

    def show(self):
        super(SpinBoxWidget, self).show()
        self.main.showSpinBox(self.identifier)


class CheckBoxWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(CheckBoxWidget, self).__init__(form_gui, identifier,
                                             question, row)

        # Add the question fields; change the checkbox text to 'Yes'.
        self.main.addCheckBox(identifier, row=row, column=1)
        self.main.getCheckBoxWidget(identifier).config(text="Yes")
        self.add_listener(self.main.getCheckBoxWidget(identifier))
        self.main.setCheckBox(identifier, ticked=False)

    def get_entry(self):
        return self.main.getCheckBox(self.identifier)

    def set_entry(self, value):
        self.main.setCheckBox(self.identifier, value)

    def hide(self):
        super(CheckBoxWidget, self).hide()
        self.main.hideCheckBox(self.identifier)

    def show(self):
        super(CheckBoxWidget, self).show()
        self.main.showCheckBox(self.identifier)


class DateWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(DateWidget, self).__init__(form_gui, identifier,
                                         question, row)

        self.main.addDatePicker(identifier, row=row, column=1)
        self.add_listener(self.main.getFrameWidget(identifier))

    def get_entry(self):
        return self.main.getDatePicker(self.identifier)

    def set_entry(self, value):
        self.main.setDatePicker(self.identifier, value)

    def hide(self):
        super(DateWidget, self).hide()
        self.main.hideFrame(self.identifier)

    def show(self):
        super(DateWidget, self).show()
        self.main.showFrame(self.identifier)


class ComputedLabelWidget(Widget):
    def __init__(self, form_gui, identifier, question, row=0):
        super(ComputedLabelWidget, self).__init__(form_gui, identifier,
                                                  question, row=row)

        self.computed_identifier = "@computed_" + self.identifier
        self.main.addLabel(self.computed_identifier, row=row, column=1)
        self.main.setLabel(self.computed_identifier, '')

    def get_entry(self):
        return self.main.getLabel(self.computed_identifier)

    def set_entry(self, value):
        self.main.setLabel(self.computed_identifier, value)

    def hide(self):
        super(ComputedLabelWidget, self).hide()
        self.main.hideLabel(self.computed_identifier)

    def show(self):
        super(ComputedLabelWidget, self).show()
        self.main.showLabel(self.computed_identifier)
