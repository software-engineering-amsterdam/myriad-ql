# -*- coding: utf-8 -*-
from appJar import gui
from collections import OrderedDict
from Undefined import Undefined
import json


class FormGUI(object):
    def __init__(self, built_gui):
        # Random comment; the 'cget' function can be used to retrieve
        # properties of the Tkinter object reference.
        self.main = gui("QL Language Form - © 2017")
        self.row = 0
        self.add_header()
        self.redraw_function = None

        self.questions = OrderedDict()

    def get_question_functions(self, identifier):
        return self.questions[identifier]

    def start(self):
        self.add_buttons()
        self.main.go()

    def add_buttons(self):
        self.main.addButtons(
            ["Save details", "Exit"], self.button_action, self.row, 0, 2
        )

    def add_header(self):
        self.main.addLabel("header", "Please fill in the form!", self.row, 0, 2)
        self.row += 1

    def add_entry_question(self, identifier, question, value):
        # Add the question fields and add a change listener.
        self.main.addLabel(identifier, question, row=self.row, column=0)
        self.main.addEntry(identifier, row=self.row, column=1)
        self.add_listener(self.main.getEntryWidget(identifier))

        # Set the entry's value and save the retrieval method.
        self.main.setEntry(identifier, value)
        self.questions[identifier] = (self.main.getEntry, self.main.setEntry,
                                      self.main.showEntry, self.main.hideEntry)
        self.row += 1

    def add_numeric_entry_question(self, identifier, question, value):
        # Add the question fields and add a change listener.
        self.main.addLabel(identifier, question, row=self.row, column=0)
        self.main.addNumericEntry(identifier, row=self.row, column=1)
        self.add_listener(self.main.getEntryWidget(identifier))

        # Unsupported by AppJar; do not return NumericEntry value as float.
        entry = self.main._gui__verifyItem(self.main.n_entries, identifier)
        entry.isNumeric = False

        # Set the entry's value and save the retrieval method.
        self.main.setEntry(identifier, value)
        self.questions[identifier] = (self.main.getEntry, self.main.setEntry,
                                      self.main.showEntry, self.main.hideEntry)
        self.row += 1

    def add_datepicker_question(self, identifier, question, value):
        pass

    def add_spinbox_question(self, identifier, question, value):
        # Add the question fields and add a change listener.
        self.main.addLabel(identifier, question, row=self.row, column=0)
        self.main.addSpinBoxRange(identifier, 0, 100000, row=self.row, column=1)
        self.add_listener(self.main.getSpinBoxWidget(identifier))

        # Set the entry's value and save the retrieval method.
        self.main.setSpinBox(identifier, value)
        self.questions[identifier] = (self.main.getSpinBox, self.main.setSpinBox,
                                      self.main.showSpinBox, self.main.hideSpinBox)

        self.row += 1

    def add_checkbox_question(self, identifier, question, value):
        # Add the question fields; change the checkbox text to 'Yes'.
        self.main.addLabel(identifier, question, row=self.row, column=0)
        self.main.addCheckBox(identifier, row=self.row, column=1)
        self.main.getCheckBoxWidget(identifier).config(text="Yes")
        self.add_listener(self.main.getCheckBoxWidget(identifier))

        # Set the entry's value and save the retrieval method.
        self.main.setCheckBox(identifier, ticked=value)
        self.questions[identifier] = (self.main.getCheckBox, self.main.setCheckBox,
                                      self.main.showCheckBox, self.main.hideCheckBox)
        self.row += 1

    def add_radiobutton_question(self, identifier, question, value):
        pass

    def add_computed_question(self, identifier, question, value):
        computed_id = "@computed_" + identifier
        self.main.addLabel(computed_id, question, row=self.row, column=0)
        self.main.addLabel(identifier, row=self.row, column=1)
        self.add_listener(self.main.getLabelWidget(identifier))

        # Set the entry's value and save the retrieval method.
        if value == Undefined:
            value = "@undefined"
        self.main.setLabel(identifier, value)
        self.questions[identifier] = (self.main.getLabel, self.main.setLabel,
                                      self.main.showLabel, self.main.hideLabel)
        self.row += 1

    def add_listener(self, tkinter_obj):
        tkinter_obj.bind("<FocusOut>", self.force_redraw)
        # tkinter_obj.bind("<Key>", self.force_redraw)
        tkinter_obj.bind("<ButtonRelease-1>", self.force_redraw)

    def get_question_values(self):
        question_values = OrderedDict()
        for question in self.questions:
            get_data_func, set_data_func, show, hide = self.questions[question]
            question_value = get_data_func(question)
            question_values[question] = question_value
        return question_values

    def force_redraw(self, _):
        assert self.redraw_function is not None, "Force redraw function not initialized!"
        # Request all form values, adjust the environment.
        question_values = self.get_question_values()
        self.redraw_function(question_values)

        print "Oh, I'm so busy redrawing stuff!"

    def button_action(self, button_pressed):
        if button_pressed == "Save details":
            self.save_data()
        else:
            self.main.stop()

    def save_data(self):
        json_dict = OrderedDict()
        for identifier, widget_functions in self.questions.iteritems():
            get_value = widget_functions[0]
            value = get_value(identifier)
            json_dict[identifier] = value

        # TODO: Add flag where to save.
        with open("./form_output.txt", "w+") as form_output:
            json.dump(json_dict, form_output, indent=4)
