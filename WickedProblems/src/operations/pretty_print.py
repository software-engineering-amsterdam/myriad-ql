class PrettyPrint(QlAlg):
    state = []

    def __init__(self, tabs):
        self.tabs = tabs

    def Literal(self, value):
        class _anon():
            to_string = lambda self, tabs: "Literal({})".format(str(value))

        return _anon()

    def Form(self, name, block):
        class _anon():
            to_string = lambda self, tabs: "Form({}, {} \n)".format(
                str(name), block.to_string(tabs + 1))
        return _anon()

    def Block(self, statements):
        class _anon():
            to_string = lambda self, tabs: "Block(\n{})".format(
                (("\n").join([k.to_string(tabs + 1) for _, k in enumerate(statements)])))

        return _anon()

    def Variable(self, name, datatype):
        class _anon():
            to_string = lambda self, tabs: "Variable(name={}, datatype={})".format(
                str(name), datatype.to_string(tabs))
        return _anon()

    def RefVariable(self, name):
        class _anon():
            to_string = lambda self, tabs: "RefVariable(name={})".format(
                str(name))
        return _anon()

    def Question(self, variable, label):
        class _anon():
            to_string = lambda self, tabs: add_tabs(
                tabs) + "Question(variable={}, label='{}')".format(variable.to_string(tabs + 1), label.to_string(tabs))
        return _anon()

    def ifThen(self, expression, block):
        class _anon():
            to_string = lambda self, tabs:  add_tabs(tabs) + "ifThen(expression={}, block={}".format(
                expression.to_string(tabs + 1), block.to_string(tabs + 1)) + "\n" + add_tabs(tabs) + ")"
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        class _anon():
            to_string = lambda self, tabs: add_tabs(tabs) + "ComputedQuestion(variable={}, label='{}',".format(variable.to_string(
                tabs + 1), label.to_string(tabs)) + "\n" + add_tabs(tabs) + "expressions={})".format(expression.to_string(tabs + 1))
        return _anon()

    def Boolean(self, value=False):
        class _anon():
            to_string = lambda self, tabs: "Boolean({})".format(str(value))
        return _anon()

    def Money(self, value=False):
        class _anon():
            to_string = lambda self, tabs: "Money({})".format(str(value))
        return _anon()

    def Substraction(self, lhs, rhs):
        class _anon():
            to_string = lambda self, tabs: "Substraction({}, {})".format(
                lhs.to_string(tabs + 1), rhs.to_string(tabs + 1))
        return _anon()

    def Integer(self, value):
        class _anon():
            to_string = lambda self, tabs: "Integer({})".format(str(value))
        return _anon()

    def String(self, value):
        class _anon():
            to_string = lambda self, tabs: "String({})".format(str(value))
        return _anon()
