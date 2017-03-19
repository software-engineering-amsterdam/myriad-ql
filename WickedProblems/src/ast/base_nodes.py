class Node(object):
    def __init__(self, identifier):
        pass

    def __str__(self):
        return self.__class__.__name__ +'(\n\t'+str(vars(self))+'\n)'

    __repr__ = __str__

class Root(Node):
    def __init__(self, identifier, children):
        pass

class Form(Node):
    def __init__(self, name, block):
        self.name = name
        self.block = block

    def alg(self, _alg):
        return _alg.Form(self.name, self.block.alg(_alg))

class Statement(Node):
    pass

class Expression(Node):
    pass

class Block(Node):
    def __init__(self, statements):
        self.statements = statements

    def alg(self, _alg):
        return _alg.Block([x.alg(_alg) for x in self.statements])

class Question(Statement):
    def __init__(self, variable, label):
        self.variable = variable
        self.label = label

    def alg(self, _alg):
        return _alg.Question(self.variable.alg(_alg), self.label.alg(_alg))

class ComputedQuestion(Statement):
    def __init__(self, variable, label, expression):
        self.variable = variable
        self.label = label
        self.expression = expression

    def alg(self, _alg):
        return _alg.ComputedQuestion(self.variable.alg(_alg), self.label.alg(_alg), self.expression.alg(_alg))

class ifThen(Statement):
    def __init__(self, condition, block):
        self.condition = condition
        self.block = block

    def alg(self, _alg):
        return _alg.ifThen(self.condition.alg(_alg), self.block.alg(_alg))
