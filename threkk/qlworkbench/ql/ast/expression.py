from .type import Boolean
from .type import Decimal
from .type import String


class Expression(object):
    def __init__(self, type):
        self.type = type

    def read(self, context):
        pass

    def depends_on(self):
        pass

    def get_children(self):
        return []

    def get_type(self, context):
        return self.type


# BINARY EXPRESSIONS
class BinaryExpression(Expression):
    def __init__(self, type, operation, lnode, rnode):
        super().__init__(type)
        self.operation = operation
        self.lnode = lnode
        self.rnode = rnode

    def depends_on(self):
        return self.lnode.depends_on() + self.rnode.depends_on()

    def get_children(self):
        return [self.lnode, self.rnode]

    def __str__(self):
        return '{} {} {}'.format(self.lnode, self.operation,
                                 self.rnode)


class AndExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '&&', lnode, rnode)

    def read(self, context):
        return self.lnode.read(context) and self.rnode.read(context)


class OrExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '||', lnode, rnode)

    def read(self, context):
        return self.lnode.read(context) or self.rnode.read(context)


class LTExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '<', lnode, rnode)

    def read(self, context):
        return self.lnode.read(context) < self.rnode.read(context)


class LETExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '<=', lnode, rnode)

    def read(self, context):
        return self.lnode.read(context) <= self.rnode.read(context)


class GTExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '>', lnode, rnode)

    def read(self, context):
        return self.lnode.read(context) > self.rnode.read(context)


class GETExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '>=', lnode, rnode)

    def read(self, context):
        return self.lnode.read(context) >= self.rnode.read(context)


class NEQExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '!=', lnode, rnode)

    def read(self, context):
        return self.lnode.read(context) != self.rnode.read(context)


class EQExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '==', lnode, rnode)

    def read(self, context):
        return self.lnode.read(context) == self.rnode.read(context)


class PlusExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Decimal(), '+', lnode, rnode)

    def read(self, context):
        return self.lnode.read(context) + self.rnode.read(context)


class MinusExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Decimal(), '-', lnode, rnode)

    def read(self, context):
        return self.lnode.read(context) - self.rnode.read(context)


class MultExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Decimal(), '*', lnode, rnode)

    def read(self, context):
        return self.lnode.read(context) * self.rnode.read(context)


class DivExpression(BinaryExpression):
    def __init__(self, lnode, rnode):
        super().__init__(Decimal(), '/', lnode, rnode)

    def read(self, context):
        if self.rnode.read(context) == 0.0:
            return 0.0
        else:
            return self.lnode.read(context) / self.rnode.read(context)


# UNARY EXPRESSIONS
class UnaryExpression(Expression):
    def __init__(self, type, operation, node):
        super().__init__(type)
        self.node = node
        self.operation = operation

    def __str__(self):
        return '{}{}'.format(self.operation, self.node)

    def depends_on(self):
        return self.node.depends_on()

    def get_children(self):
        return [self.node]


class NotExpression(UnaryExpression):
    def __init__(self, node):
        super().__init__(Boolean(), '!', node)

    def read(self, context):
        return not self.node.read(context)


# LEAF EXPRESSIONS
class LeafExpression(Expression):
    def __init__(self, type):
        super().__init__(type)

    def depends_on(self):
        return []

    def get_children(self):
        return []


class TrueExpression(LeafExpression):
    def __init__(self):
        super().__init__(Boolean())

    def read(self, context):
        return True

    def __str__(self):
        return 'true'


class FalseExpression(LeafExpression):
    def __init__(self):
        super().__init__(Boolean())

    def read(self, context):
        return False

    def __str__(self):
        return 'false'


class DecimalExpression(LeafExpression):
    def __init__(self, decimal):
        super().__init__(Decimal())
        self.decimal = decimal

    def read(self, context):
        return float(self.decimal)

    def __str__(self):
        return self.decimal.__str__()


class StringExpression(LeafExpression):
    def __init__(self, string):
        super().__init__(String())
        self.string = string

    def read(self, context):
        return self.string[1:-1]

    def __str__(self):
        return self.string


# A leaf expression which is quite unique.
class IdExpression(LeafExpression):
    def __init__(self, id):
        super().__init__(None)
        self.id = id

    def read(self, context):
        return context.get_value(self.id)

    def depends_on(self):
        return [self.id]

    def get_type(self, context):
        return context.get_type(self.id)

    def __str__(self):
        return '{}'.format(self.id)
