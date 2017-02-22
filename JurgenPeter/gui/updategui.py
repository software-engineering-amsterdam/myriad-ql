from gui.formapp import *

class UpdateGUI:

    def __init__(self, app):
        self.app = app
        self.stack = []

    def push(self, element):
        self.stack.append(element)

    def pop(self):
        self.stack.pop()

    def read(self):
        return self.stack[-1]

    def visit(self, node):
        node.accept(self)

    def visit_form(self, node):
        for statement in node.body:
            statement.accept(self)

    def visit_if_conditional(self, node):
        # TODO: evaluate condition?
        # self.push(node.condition) # TODO: Stack is not needed after all right?
        if not node.condition:
            return
        for statement in node.ifbody:
            statement.accept(self)
        # self.pop()

    def visit_ifelse_conditional(self, node):
        # self.push(node.condition)
        if node.condition:
            for statement in node.ifbody:
                statement.accept(self)
        else:
            for statement in node.elsebody:
                statement.accept(self)
        # self.pop()

    def visit_question(self, node):
        labeltype = 1
        # TODO: some way to get correct widget type (see comment at the end)
        questiontype = 2

        self.app.showWidget(FormApp.create_label_id(node.name), labeltype)
        self.app.showWidget(FormApp.create_entry_id(node.name), questiontype)


    def visit_computed_question(self, node):
        # Not sure what to do with computed question
        # Just calculate the outcome and nothing more right?
        pass

# appJar widgets:
# WINDOW = 0
# LABEL = 1
# ENTRY = 2
# BUTTON = 3
# CHECKBOX = 4
# SCALE = 5
# RADIOBUTTON = 6
# LISTBOX = 7
# MESSAGE = 8
# SPIN = 9
# OPTION = 10
# TEXTAREA = 11
# LINK = 12
# METER = 13
# IMAGE = 14
# PIECHART = 15
# PROPERTIES = 16
# GRID = 17
