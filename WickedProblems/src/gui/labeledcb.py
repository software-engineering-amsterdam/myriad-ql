from tkinter import Frame, Checkbutton, Label

class LabeledCheckbutton(Frame):
    def __init__(self, root):
        Frame.__init__(self, root)
        self.checkbutton = Checkbutton(self)
        self.label = Label(self)
        self.label.grid(row=0, column=0)
        self.checkbutton.grid(row=0, column=1)
