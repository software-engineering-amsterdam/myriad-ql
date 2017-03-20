from tkinter import Tk, Frame, Label, Entry, Radiobutton, Spinbox, Button, filedialog, Menu
from operations.eval import Eval
from parser.ql import QL
import json

class FormElement(object):
    def __init__(self):
        pass

    def __str__(self):
        return self.__class__.__name__ + '(\n\t' + str(vars(self)) + '\n)'

    __repr__ = __str__


class FormController(object):
    elements = []

    def __init__(self, parent, elements=[]):
        self.parent = parent
        [self.add_element(element) for element in elements]

    def add_element(self, element):
        element.parent = self.parent
        self.elements.append(element)

    def render(self):
        [element.render() for element in self.elements]

    def __str__(self):
        return self.__class__.__name__ + '(\n\t' + str(vars(self)) + '\n)'

    __repr__ = __str__


class SectionElement(FormElement):
    pass

class InputElement(FormElement):
    label = "InputElement "

    def __init__(self, parent, label, variable):
        self.parent = parent
        self.label = label
        self.variable = variable

    def pack(self):
        key = self.parent.app.environment.get_var_key(self.variable)
        if(self.parent.app.environment.is_visible(key)):
            label = Label(master=self.parent, cnf={'text': self.label})
            label.pack()
            entry = Entry(self.parent, textvariable=self.variable)
            entry.pack()
            entry.bind('<FocusOut>', self.parent.app.reload)

class DisabledInputElement(FormElement):
    label = "DisabledInputElement "

    def __init__(self, parent, label, variable):
        self.parent = parent
        self.label = label
        self.variable = variable

    def pack(self):
        key = self.parent.app.environment.get_var_key(self.variable)
        if(self.parent.app.environment.is_visible(key)):
            label = Label(master=self.parent, cnf={'text': self.label})
            label.pack()
            entry = Entry(self.parent, textvariable=self.variable,
                          state='readonly')
            entry.pack()

class ScaleElement(FormElement):
    def __init__(self, parent):
        self.parent = parent

    def pack(self):
        key = self.parent.app.environment.get_var_key(self.variable)
        if(self.parent.app.environment.is_visible(key)):
            label = Label(master=self.parent, cnf={'text': "Scale Box"})
            label.pack()
            w = Scale(self.parent, from_=0, to=10, orient=HORIZONTAL)
            w.pack()

class NumberElement(FormElement):
    def __init__(self, parent, label, variable):
        self.parent = parent
        self.label = label
        self.variable = variable

    def pack(self):
        key = self.parent.app.environment.get_var_key(self.variable)
        if(self.parent.app.environment.is_visible(key)):
            label = Label(master=self.parent, cnf={'text': self.label, })
            label.pack()
            w = Spinbox(self.parent, from_=0, to=1000000,
                        textvariable=self.variable, command=self.parent.app.reload)
            w.bind('<FocusOut>', self.parent.app.reload)
            w.pack()

class IntegerController(FormElement):
    parent = None
    label = "IntegerController"

    def __init__(self, parent, variable):
        self.parent = parent
        self.variable = variable
        self.element = NumberElement(parent, self.label, self.variable)

    def render(self):
        key = self.parent.app.environment.get_var_key(self.variable)
        if self.parent.app.environment.is_visible(key) == 1:
            self.element.label = self.label
            self.element.pack()

class CheckboxElement(FormElement):
    def __init__(self, parent):
        self.parent = parent

    def pack(self):
        key = self.parent.app.environment.get_var_key(self.variable)
        if(self.parent.app.environment.is_visible(key)):
            label = Label(master=self.parent, cnf={'text': "Boolean Box"})
            label.pack()
            Checkbutton(self.parent, text="Checkbox element",
                        command=self.parent.app.reload).pack()

class RadioElement(FormElement):
    def __init__(self, parent, label, variable):
        self.label = label
        self.parent = parent
        self.variable = variable
        self.variable.set(0)
        self.options = ["No", "Yes"]

    def value(self):
        return self.variable.get()

    def pack(self):
        key = self.parent.app.environment.get_var_key(self.variable)
        if(self.parent.app.environment.is_visible(key)):
            label = Label(master=self.parent, cnf={'text': self.label})
            label.pack()
            for index, option in enumerate(self.options):
                Radiobutton(self.parent, variable=self.variable, text=option,
                            value=index, command=self.parent.app.reload).pack()

class ButtonElement(FormElement):
    def __init__(self, parent):
        self.parent = parent

    def pack(self):
        b = Button(self.parent, text="OK")
        b.pack()

class TextController(FormElement):
    parent = None
    label = "TextController"

    def __init__(self, parent, variable):
        self.parent = parent
        self.variable = variable
        self.element = InputElement(parent, self.label, self.variable)

    def render(self):
        key = self.parent.app.environment.get_var_key(self.variable)
        if self.parent.app.environment.is_visible(key) == 1:
            self.element.label = self.label
            self.element.pack()

class ReadOnlyController(FormElement):
    parent = None
    label = "ReadOnlyController"

    def __init__(self, parent, variable):
        self.parent = parent
        self.variable = variable.variable
        self.element = DisabledInputElement(parent, self.label, self.variable)

    def render(self):
        key = self.parent.app.environment.get_var_key(self.variable)
        if self.parent.app.environment.is_visible(key) == 1:
            self.element.label = self.label
            self.element.pack()

class BooleanController(FormElement):
    parent = None
    label = "BooleanController"

    def __init__(self, parent, variable):
        self.parent = parent
        self.variable = variable
        self.element = RadioElement(parent, self.label, self.variable)

    def render(self):
        self.element.label = self.label
        self.element.pack()

class ButtonController(FormElement):
    parent = None

    def __init__(self, parent=None):
        self.parent = parent
        self.element = ButtonElement(parent)

    def render(self):
        self.element.pack()

class PageElement(FormElement):
    def __init__(self, parent):
        self.parent = parent

    def pack(self):
        group = Frame(self.parent)
        group.pack(padx=10, pady=10)

class PageController(FormElement):
    def __init__(self, parent):
        self.parent = parent
        self.element = PageElement(parent)
        self.elements = []

    def add_element(self, element):
        element.parent = self.element
        self.elements.append(element)

    def render(self):
        [element.render() for element in self.elements]
        self.element.pack()

class QuestionController(FormElement):
    label = "This is a question"
    variable = None  # depends on type needed
    parent = None
    controller = None

    def __init__(self, parent, label, controller):
        self.controller = controller
        self.label = label
        controller.label = self.label

    def render(self):
        ''' Return Controller '''
        self.controller.render()

class ComputedQuestionController(FormElement):
    label = "This is a question"
    variable = None  # depends on type needed
    parent = None
    controller = None

    def __init__(self, parent, label, controller):
        self.controller = controller
        self.label = label
        controller.label = self.label

    def render(self):
        ''' Return Controller '''
        self.controller.render()

class Window(Frame):
    def __init__(self, parent=None):
        Frame.__init__(self, parent)

class Application(object):
    def __init__(self, name = "My Program"):
        root = Tk()
        root.app = self
        root.title(name)
        self.window = Window(root)
        self.root = root
        self.root.minsize(width=670, height=670)
        self.environment = None
        self.elements = []

    def reload(self, event=None):
        for widget in self.root.winfo_children():
            widget.destroy()

        self.environment.update_computed_questions()
        self.setup_elements()

    def export_form(self):
        f = filedialog.asksaveasfile(mode='w', defaultextension=".json")
        if f is None:
            return
        f.write(json.dumps(self.environment.export()))
        f.close()

    def add_element(self, element):
        element.parent = self.window
        self.elements.append(element)

    def construct_menu(self):
        self.menu = Menu(self.root)
        self.root.config(menu=self.menu)

        self.filemenu = Menu(self.menu)
        self.menu.add_cascade(label="File", menu=self.filemenu)
        self.filemenu.add_command(label="Save as...", command=self.export_form)
        self.filemenu.add_command(label="Reload", command=self.reload)
        self.filemenu.add_separator()
        self.filemenu.add_command(label="Exit", command=self.root.destroy)

        self.helpmenu = Menu(self.menu)
        self.menu.add_cascade(label="Help", menu=self.helpmenu)
        self.helpmenu.add_command(label="About...", command=self.reload)

    def setup_elements(self):
        self.construct_menu()
        [element.render() for element in self.elements]

    def render(self):
        self.setup_elements()
        self.window.pack()
        self.root.mainloop()
