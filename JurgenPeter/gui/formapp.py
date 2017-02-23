from appJar import gui
from ql.visitors.questionfinder import QuestionFinder
from gui.updategui import *

class FormApp:
    LABELPREFIX = "label_"
    ENTRYPREFIX = "entry_"

    def __init__(self, ast, symboltable):
        self.ast = ast
        self.environment = {name: None for name in symboltable}
        self.app = gui(ast.name)
        self.app.bindKey("<KeyPress>", self.update_gui)
        questions = QuestionFinder().visit(ast)

        # TODO how to determine the widget type to be created
        for question in questions:
            self.app.addLabel(self.create_label_id(question.name),
                              question.label)
            self.app.addEntry(self.create_entry_id(question.name))

    def start(self):
        self.app.go()

    def stop(self):
        self.app.stop()

    def create_label_id(self, name):
        return self.LABELPREFIX + name

    def create_entry_id(self, name):
        return self.ENTRYPREFIX + name

    def update_gui(self, _):
        UpdateGUI(self, self.environment).visit(self.ast)

    def hide(self, name):
        print("hide {}".format(name))

    def show(self, name):
        print("show {}".format(name))
