class Variable(object):
    def __init__(self, variable, type):
        self.type = type
        self.name = variable

    def read(self, context):
        return context.get_value(self.name)

    def __str__(self):
        return '({}, {})'.format(self.name, self.type)


class Node(object):
    def __init__(self, variable, type):
        self.variable = Variable(variable, type)
        self.conditions = []

    def read(self, context):
        return self.variable.read(context)

    def register(self, ql):
        ql.register_node(self)

    def add_condition(self, expression):
        self.conditions.append(expression)


# DECLARATIONS
class Declaration(Node):
    def __init__(self, text, variable, type):
        super().__init__(variable, type)
        self.text = text

    def build_ui(self, ui):
        ui.add_question(self.text, self.variable, self.conditions)

    def __str__(self):
        return '(declaration, {}, {})"'.format(self.text, self.variable)


# ASSIGNATIONS
class Assignation(Node):
    def __init__(self, text, variable, type, expression):
        super().__init__(variable, type)
        self.text = text[1:-1]
        self.expression = expression

    def build_ui(self, ui):
        ui.add_assignation(self.text, self.variable, self.expression,
                           self.conditions)

    def __str__(self):
        return '(assignation, {}, {}, {})'.format(self.text, self.variable,
                                                  self.expression)
