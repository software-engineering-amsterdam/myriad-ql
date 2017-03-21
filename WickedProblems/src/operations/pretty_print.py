from .ql import QlAlg


class PrettyPrint(QlAlg):
    state = []

    def __init__(self, tabs):
        self.tabs = tabs

    def Literal(self, value):
        return lambda tabs: "Literal({})".format(str(value))

    def Form(self, name, block):
        tabs = self.tabs
        return lambda: "Form({}, {} \n)".format(
            str(name), block(tabs+1))

    def Block(self, statements):
        return lambda tabs: "Block(\n{})".format(
            (("\n").join([k(tabs + 1) for _, k in enumerate(statements)])))

    def Variable(self, name, datatype):
        return lambda tabs: "Variable(name={}, datatype={})".format(
            str(name), datatype(tabs))

    def RefVariable(self, name):
        return lambda tabs: "RefVariable(name={})".format(
            str(name))

    def Question(self, variable, label):
        return lambda tabs: add_tabs(
            tabs) + "Question(variable={}, label='{}')".format(variable(tabs + 1), label(tabs))

    def ifThen(self, expression, block):
        return lambda tabs:  add_tabs(tabs) + "ifThen(expression={}, block={}".format(
            expression(tabs + 1), block(tabs + 1)) + "\n" + add_tabs(tabs) + ")"

    def ComputedQuestion(self, variable, label, expression):
        return lambda tabs: add_tabs(tabs) + "ComputedQuestion(variable={}, label='{}',".format(variable(
            tabs + 1), label(tabs)) + "\n" + add_tabs(tabs) + "expressions={})".format(expression(tabs + 1))

    def Boolean(self, value=False):
        return lambda tabs: "Boolean({})".format(str(value))


    def Money(self, value=False):
        return  lambda tabs: "Money({})".format(str(value))


    def Substraction(self, lhs, rhs):
        return lambda tabs: "Substraction({}, {})".format(
                lhs(tabs + 1), rhs(tabs + 1))

    def Integer(self, value):
       return  lambda tabs: "Integer({})".format(str(value))

    def String(self, value):
        return lambda tabs: "String({})".format(str(value))

    def StringLiteral(self, value):
        return lambda tabs: "StringLiteral({})".format(str(value))

    def Multiplication(self, lhs, rhs):
       return lambda tabs: "Multiplication({}, {})".format(
                lhs(tabs + 1), rhs(tabs + 1))

    def Addition(self, lhs, rhs):
       return lambda tabs: "Addition({}, {})".format(
                lhs(tabs + 1), rhs(tabs + 1))

def add_tabs(tabs=0):
    return '\t' * tabs
