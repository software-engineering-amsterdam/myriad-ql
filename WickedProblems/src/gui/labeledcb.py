from tkinter import Frame, Checkbutton, Label, W

class LabeledCheckbutton(Frame):
    def __init__(self, root, row = 0):
        Frame.__init__(self, root)
        self.checkbutton = Checkbutton(self, width=25)
        self.label = Label(self, width=25,anchor=W)
        self.label.grid(row=row, column=0)
        self.checkbutton.grid(row=row, column=1)
