import ql
import ast.nodes as nodes
import ui.elements
from Tkinter import *


class QlAlgGUI(ql.QlAlg):
    ''' TODO  Proper implementation of the Render functions '''
    """ Return form elements """

    def literal(self, value):
        literal = type("Literal", (nodes.Literal,), {
            "render": lambda self: str(self.value)})
        return literal(value)

    def boolean_type(self):
        _node = type("BooleanType", (nodes.BooleanType,), {
            "render": lambda self, question, pane:  Checkbutton(pane, text=question.label, variable=question.variable).pack()})
        return _node()

    def string_type(self):
        def render(self, question, pane):
            Label(pane, text=question.label).pack()
            Entry(pane).pack()

        _node = type("StringType", (nodes.StringType,), {
            "render": render})
        return _node()

    def integer_type(self):
        def render(self, question, pane):
            Label(pane, text=question.label).pack()
            Entry(pane).pack()
        _node = type("IntegerType", (nodes.IntegerType,), {
            "render":render})
        return _node()

    def money_type(self):
        def render(self, question, pane):
            Label(pane, text=question.label).pack()
            Entry(pane).pack()
        _node = type("MoneyType", (nodes.MoneyType,), {
            "render": render})
        return _node()

    def date_type(self):
        _node = type("DateType", (nodes.DateType,), {
            "render": lambda self: self.value})
        return _node()

    def block(self, statements):
        def render(self, pane):
            self.widgets = [x.render(pane) for _, x in enumerate(statements)]
        block = type("Block", (nodes.Block,), {"render": render})
        return block(statements)

    def form(self, name, block):
        def render(self, pane):
            self.block.render(pane)

        form = type("Form", (nodes.Form,), {"render": render})
        return form(name, block)

    def stringPrimitive(self, value):
        string_primitive = type("StringPrimitive", (nodes.StringPrimitive,), {
            "render": lambda self: None})
        return string_primitive(value)

    def variable(self, name, datatype):
        def render(self, question, pane):
            widget = self.datatype.render(question, pane)
        
        variable = type("Variable", (nodes.Variable,), {
            "render": render})
        return variable(name, datatype)

    def label(self, label):
        _node = type("Label", (nodes.Label,), {
            "render": lambda self, pane:  Label(pane, text=self.label)})
        return _node(label)

    def question(self, variable, label):
        def render(self, pane):
            self.variable.render(self, pane)

            # CheckBoxQuestion should be based on the data_type
        question = type("Question", (ui.elements.Question,), {
                        "render": render})
        return question(variable, label)

    def type(self, data_type):
        _typeClass = type("Type", (nodes.Type,), {
            "eval": lambda self: 0})
        return _typeClass(data_type)
