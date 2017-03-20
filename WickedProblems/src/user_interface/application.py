from tkinter import Tk, Frame, filedialog, Menu, messagebox
from user_interface.ui import Window
from operations.environment import Environment
from operations.variables import GetVariables
from operations.gui import BuildGui
from operations.register_questions import RegisterComputedQuestions
from operations.register_conditions import RegisterConditions
from parser.ql import QL
import json

class Application(object):

    def __init__(self, ast, name="My Program"):
        root = Tk()
        root.app = self
        root.title(name)
        self.window = Window(root)
        self.root = root
        self.root.minsize(width=670, height=670)
        self.environment = None 
        self.elements = []
        self.environment = Environment()
        ast.alg(GetVariables(self.environment))()
        ast.alg(RegisterComputedQuestions(self.environment))()
        ast.alg(RegisterConditions(self.environment))()
        form = ast.alg(BuildGui(self.root, self.environment))()
        self.add_element(form)

    def reload(self, event=None):
        for widget in self.root.winfo_children():
            widget.destroy()

        self.environment.update_computed_questions()
        self.setup_elements()

    def export_form(self):
        f = filedialog.asksaveasfile(mode='w', defaultextension=".json")
        if f is None:
            return
        f.write(json.dumps(self.environment.export(), indent=4))
        f.close()

    def show_error(self, message):
        messagebox.showerror("Error", message)

    def show_warning(self, message):
        messagebox.showwarning("Warning", message)

    def show_message(self, message):
        messagebox.showinfo("Message", message)

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
