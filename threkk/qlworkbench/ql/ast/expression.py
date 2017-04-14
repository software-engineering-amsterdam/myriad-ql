# -*- coding: utf-8 -*-
"""
This module holds the definition of the expressions that can be found in the
AST tree. The expressions are not nodes by themselves but they are part of the
nodes. The express the conditions of the conditionals or the evaluation of the
values in the assignation nodes. They are not visually represented in the UI
but hold most of the logic behind it.
"""
from .type import Boolean
from .type import Decimal
from .type import String


class Expression(object):
    """
    Abstract parent class. It defines the shared fields and functions that
    all the nodes have. This is very important as during the recurision, the
    visitor will rely on them.
    """
    def __init__(self, type):
        self.type = type

    def get_value(self, context):
        """
        Retrieves the value of the expression node depending on the context.
        Usually this value is recursive and depends on the value of the
        children of the expression node.
        """
        pass

    def depends_on(self):
        """
        Gets the list of the variables that the expression depends on. This
        means that if any children or children of them of the expression
        contains the identifier of a variable, this one will be returned by
        this function as a list. In case it does not has any dependency, this
        list will be empty.
        """
        pass

    def get_children(self):
        """
        Returns a list of the children of the node. In case it is a LeafNode,
        the list will be empty.
        """
        return []

    def get_type(self, context):
        """
        Returns the type of the expression. In most of the cases it depends on
        the operation but in case it is an identifier it depends on the context
        it is analysed as we do not have the declaration of the identifier in
        the expression.
        """
        return self.type


# BINARY EXPRESSIONS
class BinaryExpression(Expression):
    """Parent abstract class for binary expressions."""
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
    """Logic AND expression. It takes two Boolean and returns a Boolean."""
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '&&', lnode, rnode)

    def get_value(self, context):
        return self.lnode.get_value(context) and self.rnode.get_value(context)


class OrExpression(BinaryExpression):
    """Logic OR expression. It takes two Boolean and returns a Boolean."""
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '||', lnode, rnode)

    def get_value(self, context):
        return self.lnode.get_value(context) or self.rnode.get_value(context)


class LTExpression(BinaryExpression):
    """
    Mathematical LESS THAN expression. It takes two Decimal and returns a
    Boolean.
    """
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '<', lnode, rnode)

    def get_value(self, context):
        return self.lnode.get_value(context) < self.rnode.get_value(context)


class LETExpression(BinaryExpression):
    """
    Mathematical LESS OR EQUAL THAN expression. It takes two Decimal and
    returns a Boolean.
    """
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '<=', lnode, rnode)

    def get_value(self, context):
        return self.lnode.get_value(context) <= self.rnode.get_value(context)


class GTExpression(BinaryExpression):
    """
    Mathematical GREATER THAN expression. It takes two Decimal and returns a
    Boolean.
    """
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '>', lnode, rnode)

    def get_value(self, context):
        return self.lnode.get_value(context) > self.rnode.get_value(context)


class GETExpression(BinaryExpression):
    """
    Mathematical GREATER OR EQUAL THAN expression. It takes two Decimal and
    returns a Boolean.
    """
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '>=', lnode, rnode)

    def get_value(self, context):
        return self.lnode.get_value(context) >= self.rnode.get_value(context)


class NEQExpression(BinaryExpression):
    """
    Logic NOT EQUAL expression. It takes two variables of the same any type and
    returns a Boolean.
    """
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '!=', lnode, rnode)

    def get_value(self, context):
        return self.lnode.get_value(context) != self.rnode.get_value(context)


class EQExpression(BinaryExpression):
    """
    Logic EQUAL expression. It takes two variables of the same any type and
    returns a Boolean.
    """
    def __init__(self, lnode, rnode):
        super().__init__(Boolean(), '==', lnode, rnode)

    def get_value(self, context):
        return self.lnode.get_value(context) == self.rnode.get_value(context)


class PlusExpression(BinaryExpression):
    """
    Mathematical PLUS expression. It takes two Decimal and returns a Decimal.
    """
    def __init__(self, lnode, rnode):
        super().__init__(Decimal(), '+', lnode, rnode)

    def get_value(self, context):
        return self.lnode.get_value(context) + self.rnode.get_value(context)


class MinusExpression(BinaryExpression):
    """
    Mathematical MINUS expression. It takes two Decimal and returns a Decimal.
    """
    def __init__(self, lnode, rnode):
        super().__init__(Decimal(), '-', lnode, rnode)

    def get_value(self, context):
        return self.lnode.get_value(context) - self.rnode.get_value(context)


class MultExpression(BinaryExpression):
    """
    Mathematical MULTIPLY BY expression. It takes two Decimal and returns a
    Decimal.
    """
    def __init__(self, lnode, rnode):
        super().__init__(Decimal(), '*', lnode, rnode)

    def get_value(self, context):
        return self.lnode.get_value(context) * self.rnode.get_value(context)


class DivExpression(BinaryExpression):
    """
    Mathematical DIVIDE BY expression. It takes two Decimal and returns a
    Decimal.
    """
    def __init__(self, lnode, rnode):
        super().__init__(Decimal(), '/', lnode, rnode)

    def get_value(self, context):
        if self.rnode.get_value(context) == 0.0:
            return 0.0
        else:
            return (self.lnode.get_value(context) /
                    self.rnode.get_value(context))


# UNARY EXPRESSIONS
class UnaryExpression(Expression):
    """Abstract parent class for unary expressions."""
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
    """
    Logical NOT operation. It takes a Boolean and returns a Boolean.
    """
    def __init__(self, node):
        super().__init__(Boolean(), '!', node)

    def get_value(self, context):
        return not self.node.get_value(context)


# LEAF EXPRESSIONS
class LeafExpression(Expression):
    """Abstract parent class for leaf expressions."""
    def __init__(self, type):
        super().__init__(type)

    def depends_on(self):
        return []

    def get_children(self):
        return []


class TrueExpression(LeafExpression):
    """Leaf expression which holds the Boolean value True."""
    def __init__(self):
        super().__init__(Boolean())

    def get_value(self, context):
        return True

    def __str__(self):
        return 'true'


class FalseExpression(LeafExpression):
    """Leaf expression which holds the Boolean value False."""
    def __init__(self):
        super().__init__(Boolean())

    def get_value(self, context):
        return False

    def __str__(self):
        return 'false'


class DecimalExpression(LeafExpression):
    """Leaf expression which holds a Decimal value."""
    def __init__(self, decimal):
        super().__init__(Decimal())
        self.decimal = decimal

    def get_value(self, context):
        return float(self.decimal)

    def __str__(self):
        return self.decimal.__str__()


class StringExpression(LeafExpression):
    """Leaf expression which holds a String value."""
    def __init__(self, string):
        super().__init__(String())
        self.string = string

    def get_value(self, context):
        return self.string[1:-1]

    def __str__(self):
        return self.string


class IdExpression(LeafExpression):
    """
    ID expressions are a leaf expression which are quite different from the
    others, although it holds many of the same properties and principles. The
    most important one is that its type and value depends strictly on the
    context given. Without a valid context, the IdExpressions will fail.
    """
    def __init__(self, id):
        super().__init__(None)
        self.id = id

    def get_value(self, context):
        return context.get_value(self.id)

    def depends_on(self):
        return [self.id]

    def get_type(self, context):
        return context.get_type(self.id)

    def __str__(self):
        return '{}'.format(self.id)
