from appJar import gui
from ql.visitors.questionfinder import QuestionFinder


class FormApp:
    LABELPREFIX = "label_"
    ENTRYPREFIX = "entry_"

    def __init__(self, ast, symboltable):
        self.environment = {name: None for name in symboltable}
        self.app = gui(ast.name)
        questions = QuestionFinder().visit(ast)

        for question in questions:
            self.app.addLabel(self.create_label_id(question.name), question.label)
            self.app.addEntry(self.create_entry_id(question.name))

        self.app.go()

    def create_label_id(self, name):
        return self.LABELPREFIX + name

    def create_entry_id(self, name):
        return self.ENTRYPREFIX + name

