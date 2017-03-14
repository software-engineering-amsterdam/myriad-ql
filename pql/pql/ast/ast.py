# coding=utf-8
from collections import defaultdict

from pyparsing import lineno, col


class Node(object):
    def __init__(self, var_type, position=0, source=""):
        self.var_type = var_type
        self.location = Location(position, source)

    def __str__(self, level=0):
        return self.var_type

    def __repr__(self):
        return str(self)


class Location(object):
    def __init__(self, position, source):
        self.line_number = lineno(position, source)
        # self.column_number = col(position, source)

    def __str__(self):
        return "(Line number: {})".format(self.line_number)

    def __repr__(self):
        return str(self)


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
    def __init__(self, title, identifier, data_type, expression=None):
        super(Field, self).__init__('field')
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
    def __init__(self, var_type, left, right, position, source):
        super(BinaryOperation, self).__init__(var_type, position, source)
        self.lhs, self.rhs = left, right


class Multiplication(BinaryOperation):
    def __init__(self, position, source, lhs, rhs):
        super(Multiplication, self).__init__('multiplication', lhs, rhs, position, source)

    def apply(self, visitor):
        return visitor.multiplication(self)


class Addition(BinaryOperation):
    def __init__(self, position, source, lhs, rhs):
        super(Addition, self).__init__('addition', lhs, rhs, position, source)

    def apply(self, visitor):
        return visitor.addition(self)


class Subtraction(BinaryOperation):
    def __init__(self, position, source, lhs, rhs):
        super(Subtraction, self).__init__('subtraction', lhs, rhs, position, source)

    def apply(self, visitor):
        return visitor.subtraction(self)


class Division(BinaryOperation):
    def __init__(self, position, source, lhs, rhs):
        super(Division, self).__init__('division', lhs, rhs, position, source)

    def apply(self, visitor):
        return visitor.division(self)


class UnaryOperation(Node):
    def __init__(self, var_type, operand, position, source):
        super(UnaryOperation, self).__init__(var_type, position, source)
        self.operand = operand


class Positive(UnaryOperation):
    def __init__(self, position, source, operand):
        super(Positive, self).__init__('positive', operand, position, source)

    def apply(self, visitor):
        return visitor.positive(self)


class Negative(UnaryOperation):
    def __init__(self, position, source, operand):
        super(Negative, self).__init__('negative', operand, position, source)

    def apply(self, visitor):
        return visitor.negative(self)


class Negation(UnaryOperation):
    def __init__(self, position, source, operand):
        super(Negation, self).__init__('negation', operand, position, source)

    def apply(self, visitor):
        return visitor.negation(self)


class And(BinaryOperation):
    eval_function = all

    def __init__(self, position, source, left, right):
        super(And, self).__init__('and', left, right, position, source)

    def apply(self, visitor):
        return visitor.and_(self)


class Or(BinaryOperation):
    eval_function = any

    def __init__(self, position, source, left, right):
        super(Or, self).__init__('or', left, right, position, source)

    def apply(self, visitor):
        return visitor.or_(self)


class Equality(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(Equality, self).__init__('equality', left, right, position, source)

    def apply(self, visitor):
        return visitor.equality(self)


class GreaterExclusive(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(GreaterExclusive, self).__init__('greater_exclusive', left, right, position, source)

    def apply(self, visitor):
        return visitor.greater_exclusive(self)


class GreaterInclusive(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(GreaterInclusive, self).__init__('greater_inclusive', left, right, position, source)

    def apply(self, visitor):
        return visitor.greater_inclusive(self)


class LowerInclusive(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(LowerInclusive, self).__init__('lower_inclusive', left, right, position, source)

    def apply(self, visitor):
        return visitor.lower_inclusive(self)


class LowerExclusive(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(LowerExclusive, self).__init__('lower_exclusive', left, right, position, source)

    def apply(self, visitor):
        return visitor.lower_exclusive(self)


class Inequality(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(Inequality, self).__init__('inequality', left, right, position, source)

    def apply(self, visitor):
        return visitor.inequality(self)


class Value(Node):
    def __init__(self, position, source, value, data_type):
        super(Value, self).__init__('value', position, source)
        self.value = value
        self.data_type = data_type

    def apply(self, visitor):
        return visitor.value(self)


class Identifier(Node):
    def __init__(self, position, source, name):
        super(Identifier, self).__init__('identifier', position, source)
        self.name = name

    def apply(self, visitor):
        return visitor.identifier(self)


class Integer(Node):
    def __init__(self, position, source, data_type):
        super(Integer, self).__init__("integer", position, source)
        self.data_type = data_type

    def apply(self, visitor):
        return visitor.integer(self)

    def default_value(self):
        return int(0)


class Boolean(Node):
    def __init__(self, position, source, data_type):
        super(Boolean, self).__init__("boolean", position, source)
        self.data_type = data_type

    def apply(self, visitor):
        return visitor.boolean(self)

    def default_value(self):
        return False


class Money(Node):
    def __init__(self, position, source, data_type):
        super(Money, self).__init__("money", position, source)
        self.data_type = data_type

    def apply(self, visitor):
        return visitor.money(self)

    def default_value(self):
        return float(0.00)
