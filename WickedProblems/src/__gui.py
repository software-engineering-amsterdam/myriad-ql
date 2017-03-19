import Tkinter as tk
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

    def __init__(self, parent, elements=[]):
        self.elements = elements
        self.parent = parent

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

    def __init__(self, parent, label):
        self.parent = parent
        self.label = label

    def pack(self):
        label = tk.Label(master=self.parent, cnf={'text': self.label})
        label.pack()
        entry = tk.Entry(self.parent)
        entry.pack()


class ScaleElement(FormElement):

    def __init__(self, parent):
        self.parent = parent

    def pack(self):
        label = tk.Label(master=self.parent, cnf={'text': "Scale Box"})
        label.pack()
        w = tk.Scale(self.parent, from_=0, to=10, orient=tk.HORIZONTAL)
        w.pack()


class NumberElement(FormElement):

    def __init__(self, parent):
        self.parent = parent

    def pack(self):
        label = tk.Label(master=self.parent, cnf={'text': "Number Box"})
        label.pack()
        w = tk.Spinbox(self.parent, from_=0, to=10)
        w.pack()


class CheckboxElement(FormElement):

    def __init__(self, parent):
        self.parent = parent

    def pack(self):
        label = tk.Label(master=self.parent, cnf={'text': "Boolean Box"})
        label.pack()
        c = tk.Checkbutton(self.parent, text="Checkbox element")
        c.pack()



class RadioElement(FormElement):

    def __init__(self, parent, label):
        self.label = label
        self.parent = parent
        self.variable = tk.IntVar()
        self.variable.set(0)
        self.options = ["Yes", "No"]

    def value(self):
        return self.variable.get()

    def pack(self):
        label = tk.Label(master=self.parent, cnf={'text': self.label})
        label.pack()
        for index, option in enumerate(self.options):
            tk.Radiobutton(self.parent, variable=self.variable, text=option,
                               value=index).pack()



class ButtonElement(FormElement):

    def __init__(self, parent):
        self.parent = parent

    def pack(self):
        b = tk.Button(self.parent, text="OK")
        b.pack()


class TextController(FormElement):
    parent = None
    label = "TextController"

    def __init__(self, parent):
        self.parent = parent
        self.element = InputElement(parent, self.label)

    def render(self):
        self.element.label = self.label
        self.element.pack()


class BooleanController(FormElement):
    parent = None
    label = "TextController"

    def __init__(self, parent):
        self.parent = parent
        self.element = RadioElement(parent, self.label)

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
        group = tk.Frame(self.parent)
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


class Window(tk.Frame):

    def __init__(self, parent=None):
        tk.Frame.__init__(self, parent)

# class Config(object):
    

class Application(object):

    def __init__(self):
        root = tk.Tk()
        self.window = Window(root)
        self.root = root
        self.root.minsize(width=670, height=670)
        listb = tk.Listbox(self.window)
        listb2 = tk.Listbox(self.window)

        self.elements = []

        form = FormController(self.window)

        # widgets

        page1 = PageController(None)
        # page1.add_element(ButtonController(None))
        # page1.add_element(BooleanController(None))
        # page1.add_element(TextController(None))
        # page1.add_element(ButtonController(None))
        
        page1.add_element(QuestionController(
            None, "Have you sold a house?", BooleanController(None)))
        page1.add_element(QuestionController(
            None, "Have you bought a house?", BooleanController(None)))
        page1.add_element(QuestionController(
            None, "Have you sold a house?", BooleanController(None)))
        page1.add_element(QuestionController(
            None, "Have you bought a house?", BooleanController(None)))


        form.add_element(page1)
        form.render()
        self.setup_elements()

    def setup_elements(self):
        [element.render() for element in self.elements]

    def render(self):

        self.window.pack()
        self.root.mainloop()


