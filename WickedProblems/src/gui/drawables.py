from ast.ast import *
from .labeledcb import LabeledCheckbutton
from tkinter import *

class DrawableBoolean(Boolean):
    def __init__(self, identifier):
        Boolean.__init__(self, identifier)

    def draw(self, _root, _variable, _text, _row, _refresh):
        labeledcb = LabeledCheckbutton(_root)
        labeledcb.label.configure(text=_text)
        labeledcb.checkbutton.configure(variable=_variable,
            command=_refresh)
        labeledcb.grid(row=_row, columnspan=2, sticky=W)
        return labeledcb
