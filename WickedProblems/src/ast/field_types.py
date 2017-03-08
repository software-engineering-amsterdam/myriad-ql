from .base_nodes import Node
from gui.labeledcb import LabeledCheckbutton
from tkinter import W, IntVar

class FieldType(Node):
    def __init__(self):
        Node.__init__(self, "field_type")

    # def __str__(self):
        # return "{}".format(self._value)

    # def __call__(self, value):
    #     self._value = value

    def eval(self):
        return self._value

class Boolean(FieldType):
    def __init__(self, identifier):
        FieldType.__init__(self)
        self._identifier = identifier
        self._value = None

    def __or__(self, other):
        return (self.eval() | other.eval())

    def __and__(self, other):
        return (self.eval() & other.eval())

    def __invert__(self):
        if(self._value == True):
            return False
        return True

    def draw(self, _interface, _parent):
        # TODO: SOMETHING IS BROKEN HERE!!
        if(self._value == None):
            self._value = IntVar()
        labeledcb = LabeledCheckbutton(_interface._root)
        labeledcb.label.configure(text=str(_parent._text))
        labeledcb.checkbutton.configure(variable=self._value,
                                        onvalue=True,
                                        offvalue=False,
                                        command=_interface.refresh
                                        )
        labeledcb.grid(row=_interface.get_new_row(), columnspan=2, sticky=W)
        # print(self._value.get())

class String(FieldType):
    def __init__(self, identifier, value = [""]):
        FieldType.__init__(self)
        self._value = str(value[0])

    def __call__(self, identifier, value):
        self._identifier = identifier
        self._value = value

    def __str__(self):
        return self._value

class Integer(FieldType):
    def __init__(self, identifier, value = [0]):
        FieldType.__init__(self)
        self._value = int(value[0])

    def __add__(self, other):
        if(other.__class__ == Integer):
            return (self.eval() + other.eval())
        return NotImplemented

    def __sub__(self, other):
        if(other.__class__ == Integer):
            return (self.eval() - other.eval())
        return NotImplemented

    def __mul__(self, other):
        if(other.__class__ == Integer):
            return (self.eval() * other.eval())
        return NotImplemented

    def __truediv__(self, other):
        if(other.__class__ == Integer):
            return (self.eval() / other.eval())
        return NotImplemented

    def __lt__(self, other):
        if(other.__class__ == Integer):
            return self.eval() < other.eval()
        return NotImplemented

    def __gt__(self, other):
        if(other.__class__ == Integer):
            return self.eval() > other.eval()
        return NotImplemented

    def __ge__(self, other):
        if(other.__class__ == Integer):
            return self.eval() >= other.eval()
        return NotImplemented

    def ___le__(self, other):
        if(other.__class__ == Integer):
            return self.eval() <= other.eval()
        return NotImplemented

    def __ne__(self, other):
        if(other.__class__ == Integer):
            return self.eval() != other.eval()
        return NotImplemented

    def __eq__(self, other):
        if(other.__class__ == Integer):
            return self.eval() == other.eval()
        return NotImplemented

class Date(FieldType):
    def __init__(self, identifier, value):
        FieldType.__init__(self)
        # TODO
        self._value = value[0]

class Decimal(FieldType):
    def __init__(self, identifier, value = [float(0)]):
        FieldType.__init__(self)
        self._value = float(value[0])

# TODO
class Money(FieldType):
    def __init__(self, identifier, value = [0]):
        FieldType.__init__(self)
        self._value = float(value[0])

# TODO
class Currency(FieldType):
    def __init__(self, identifier, value = [0]):
        FieldType.__init__(self)
        self._value = float(value[0])

class Undefined(FieldType):
    def __init__(self):
        FieldType.__init__(self)
        self._value = False

    def __sub__(self, other):
        return None
