from tkinter import Tk,Frame,Label,Entry,Radiobutton,Spinbox
'''

Playground:

Window

Frame

Section

FormElements
- RadioButton
- Checkbox
- InputBox
-



FormElements
- EnumerateController
    - Selectbox
    - Checkbox
- BooleanController
    - RadioButton "yes" : " no"
    - Checkbox "on" : "of"
- InputController
    - InputBox
- NumericController
    - Slider
    - InputBox

'''

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
        label = Label(master=self.parent, cnf={'text': self.label})
        label.pack()
        entry = Entry(self.parent, textvariable=self.variable)
        entry.pack()

class ScaleElement(FormElement):
    def __init__(self, parent):
        self.parent = parent

    def pack(self):
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
        label = Label(master=self.parent, cnf={'text': self.label,})
        label.pack()
        w = Spinbox(self.parent, from_=0, to=10, textvariable=self.variable)
        w.pack()

class IntegerController(FormElement):
    parent = None
    label = "IntegerController"

    def __init__(self, parent,variable):
        self.parent = parent
        self.variable = variable
        self.element = NumberElement(parent, self.label, self.variable)

    def render(self):
        self.element.label = self.label
        self.element.pack()

class CheckboxElement(FormElement):
    def __init__(self, parent):
        self.parent = parent

    def pack(self):
        label = Label(master=self.parent, cnf={'text': "Boolean Box"})
        label.pack()
        c = Checkbutton(self.parent, text="Checkbox element")
        c.pack()

class RadioElement(FormElement):
    def __init__(self, parent, label, variable):
        self.label = label
        self.parent = parent
        self.variable = variable
        self.variable.set(0)
        self.options = ["No","Yes"]

    def value(self):
        return self.variable.get()

    def pack(self):
        label = Label(master=self.parent, cnf={'text': self.label})
        label.pack()
        for index, option in enumerate(self.options):
            Radiobutton(self.parent, variable=self.variable, text=option,
                               value=index).pack()

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
        self.element.label = self.label
        self.element.pack()

class ReadOnlyController(FormElement):
    parent = None
    label = "ReadOnlyController"

    def __init__(self, parent, variable):
        self.parent = parent
        self.variable = variable
        self.element = InputElement(parent, self.label, self.variable)

    def render(self):
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
    def __init__(self):
        root = Tk()
        self.window = Window(root)
        self.root = root
        self.root.minsize(width=670, height=670)
        self.elements = []

    def add_element(self, element):
        element.parent = self.window
        self.elements.append(element)

    def setup_elements(self):
        [element.render() for element in self.elements]

    def render(self):
        self.setup_elements()
        self.window.pack()
        self.root.mainloop()
