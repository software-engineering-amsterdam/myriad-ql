# -*- coding: utf-8 -*-
from appJar import gui
from TypeEnums import TypeEnums as Type


class FormGUI(object):
    def __init__(self, evaluator):
        # Random comment; the 'cget' function can be used to retrieve
        # properties of the Tkinter object reference.
        self.main = gui("QL Language Form - © 2017")
        self.evaluator = evaluator

        # Save a reference to each question's input field.
        self.default_types = self.gui_defaults()
        self.questions = {}
        self.row = 0

        self.add_header()

    def gui_defaults(self):
        # TODO: For NumericEntry, disable 'entry.isNumeric', to disable
        # it returning a float automatically.
        return {
            Type.BOOLEAN: self.main.addCheck,
            Type.INTEGER: "Spinbox",
            Type.DECIMAL: "NumericEntry",
            Type.MONEY: "NumericEntry",
            Type.DATE: "DatePicker",
            Type.STRING: "Entry"
        }

    def start(self):
        # TODO: find a more suitable place to add the buttons.
        self.main.addButtons(
            ["Save details", "Exit"], self.button_action, self.row, 0, 2
        )
        self.main.go()

    def add_header(self):
        self.main.addLabel("header", "Form 'hoi'", self.row, 0, 2)
        self.row += 1

    def add_entry_question(self, identifier, question):
        # Start a frame to put the question widget in.
        self.main.startFrame(identifier, row=self.row, colspan=2)

        # Add the question fields; change the checkbox text to 'Yes'.
        self.main.addLabel(identifier, question, row=0, column=0)
        self.main.addEntry(identifier, row=0, column=1)
        self.add_listener(self.main.getEntryWidget(identifier))

        # 'Stop' the frame and save the method to retrieve its value.
        self.main.stopFrame()
        self.row += 1
        self.questions[identifier] = self.main.getEntry

    def add_numeric_entry_question(self):
        pass

    def add_datepicker_question(self):
        pass

    def add_spinbox_question(self):
        pass

    def add_checkbox_question(self, identifier, question):
        # Start a frame to put the question widget in.
        self.main.startFrame(identifier, row=self.row, colspan=2)

        # Add the question fields; change the checkbox text to 'Yes'.
        self.main.addLabel(identifier, question, row=0, column=0)
        self.main.addCheckBox(identifier, row=0, column=1)
        self.main.getCheckBoxWidget(identifier).config(text="Yes")
        self.add_listener(self.main.getCheckBoxWidget(identifier))

        # 'Stop' the frame and save the method to retrieve its value.
        self.main.stopFrame()
        self.row += 1
        self.questions[identifier] = self.main.getCheckBox

    def add_radiobutton_question(self):
        pass

    def add_listener(self, tkinter_obj):
        tkinter_obj.bind("<FocusOut>", self.force_redraw)
        tkinter_obj.bind("<Key>", self.force_redraw)
        tkinter_obj.bind("<ButtonRelease-1>", self.force_redraw)

    def force_redraw(self, event):
        # TODO: request all form values and call the evaluator.
        print "Oh, I'm so busy redrawing stuff!"

    def button_action(self, button_pressed):
        if button_pressed == "Save details":
            self.save_data()
        else:
            self.main.stop()

    def save_data(self):
        # TODO: Output the data as JSON.
        for identifier, get_value in self.questions.iteritems():
            value = get_value(identifier)
            print "{}: {}".format(identifier, value)

if __name__ == '__main__':
    form = FormGUI(None)
    form.add_entry_question("inputQuestion", "How much money do you have?")
    form.add_checkbox_question("coolQuestion", "Are you cool?")
    form.start()
