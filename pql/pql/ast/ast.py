# coding=utf-8
from collections import defaultdict

from pyparsing import lineno, col


class Node(object):
    def __init__(self, var_type, location=defaultdict(int)):
        self.var_type = var_type
        loc = location.get("loc", 0)
        src = location.get("src", '')
        self.location = {"line_no": lineno(loc, src), "col_no": col(loc, src)}

    def __str__(self, level=0):
        ret = "\t" * level + repr(self.var_type) + "\n"
        return ret

    def __repr__(self):
        return self.var_type


class Form(Node):
    def __init__(self, form_identifier, form_statement_list):
        super(Form, self).__init__('form')
        self.name = form_identifier
        self.statements = form_statement_list

    def apply(self, visitor):
        return visitor.form(self)

    def __str__(self, level=0):
        ret = "\t" * level + repr(self.var_type) + "\n"
        for child in self.children:
            ret += child.__str__()
        return ret


class Field(Node):
    def __init__(self, location, title, identifier, data_type, expression=None):
        super(Field, self).__init__('field', location)
        self.name = identifier
        self.title = title
        self.data_type = data_type
        self.expression = expression

    def apply(self, visitor):
        return visitor.field(self)


class If(Node):
    def __init__(self, boolean_statement):
        super(If, self).__init__('if')
        self.condition = boolean_statement[0]
        self.statements = boolean_statement[1]

    def apply(self, visitor):
        return visitor.conditional_if(self)


class IfElse(Node):
    def __init__(self, boolean_statement):
        super(IfElse, self).__init__('if_else')
        self.condition = boolean_statement[0]
        self.statements = boolean_statement[1]
        self.else_statement_list = boolean_statement.else_statement[0]

    def apply(self, visitor):
        return visitor.conditional_if_else(self)


class BinaryOperation(Node):
    def __init__(self, var_type, left, right):
        super(BinaryOperation, self).__init__(var_type)
        self.lhs, self.rhs = left, right


class Multiplication(BinaryOperation):
    def __init__(self, lhs, rhs):
        super(Multiplication, self).__init__('multiplication', lhs, rhs)

    def apply(self, visitor):
        return visitor.multiplication(self)


class Addition(BinaryOperation):
    def __init__(self, lhs, rhs):
        super(Addition, self).__init__('addition', lhs, rhs)

    def apply(self, visitor):
        return visitor.addition(self)


class Subtraction(BinaryOperation):
    def __init__(self, lhs, rhs):
        super(Subtraction, self).__init__('subtraction', lhs, rhs)

    def apply(self, visitor):
        return visitor.subtraction(self)


class Division(BinaryOperation):
    def __init__(self, lhs, rhs):
        super(Division, self).__init__('division', lhs, rhs)

    def apply(self, visitor):
        return visitor.division(self)


class UnaryOperation(Node):
    def __init__(self, var_type, right):
        super(UnaryOperation, self).__init__(var_type)
        self.rhs = right


class Positive(UnaryOperation):
    def __init__(self, rhs):
        super(Positive, self).__init__('positive', rhs)

    def apply(self, visitor):
        return visitor.positive(self)


class Negative(UnaryOperation):
    def __init__(self, rhs):
        super(Negative, self).__init__('negative', rhs)

    def apply(self, visitor):
        return visitor.negative(self)


class Negation(UnaryOperation):
    def __init__(self, rhs):
        super(Negation, self).__init__('negation', rhs)

    def apply(self, visitor):
        return visitor.negation(self)


class And(BinaryOperation):
    eval_function = all

    def __init__(self, left, right):
        super(And, self).__init__('and', left, right)

    def apply(self, visitor):
        return visitor.and_(self)


class Or(BinaryOperation):
    eval_function = any

    def __init__(self, left, right):
        super(Or, self).__init__('or', left, right)

    def apply(self, visitor):
        return visitor.or_(self)


class Equality(BinaryOperation):
    def __init__(self, left, right):
        super(Equality, self).__init__('equality', left, right)

    def apply(self, visitor):
        return visitor.equality(self)


class GreaterExclusive(BinaryOperation):
    def __init__(self, left, right):
        super(GreaterExclusive, self).__init__('greater_exclusive', left, right)

    def apply(self, visitor):
        return visitor.greater_exclusive(self)


class GreaterInclusive(BinaryOperation):
    def __init__(self, left, right):
        super(GreaterInclusive, self).__init__('greater_inclusive', left, right)

    def apply(self, visitor):
        return visitor.greater_inclusive(self)


class LowerInclusive(BinaryOperation):
    def __init__(self, left, right):
        super(LowerInclusive, self).__init__('lower_inclusive', left, right)

    def apply(self, visitor):
        return visitor.lower_inclusive(self)


class LowerExclusive(BinaryOperation):
    def __init__(self, left, right):
        super(LowerExclusive, self).__init__('lower_exclusive', left, right)

    def apply(self, visitor):
        return visitor.lower_exclusive(self)


class Inequality(BinaryOperation):
    def __init__(self, left, right):
        super(Inequality, self).__init__('inequality', left, right)

    def apply(self, visitor):
        return visitor.inequality(self)


class Value(Node):
    def __init__(self, value, data_type):
        super(Value, self).__init__('value')
        self.value = value
        self.data_type = data_type

    def apply(self, visitor):
        return visitor.value(self)


class Identifier(Node):
    def __init__(self, location, name):
        super(Identifier, self).__init__('identifier', location)
        self.name = name

    def apply(self, visitor):
        return visitor.identifier(self)


class Integer(object):
    def __init__(self, data):
        self.data_type = data

    def apply(self, visitor):
        return visitor.integer(self)

    def default_value(self):
        return int(0)


class Boolean(object):
    def __init__(self, data):
        self.data_type = data

    def apply(self, visitor):
        return visitor.boolean(self)

    def default_value(self):
        return False


class Money(object):
    def __init__(self, data):
        self.data_type = data

    def apply(self, visitor):
        return visitor.money(self)

    def default_value(self):
        return float(0.00)
