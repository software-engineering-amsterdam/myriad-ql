# coding=utf-8

from pyparsing import lineno

from pql.typechecker.types import DataTypes


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

    def __str__(self):
        return "(Line number: {})".format(self.line_number)

    def __repr__(self):
        return str(self)


class Form(Node):
    def __init__(self, form_identifier, form_statement_list):
        super(Form, self).__init__('form')
        self.name = form_identifier
        self.statements = form_statement_list

    def apply(self, visitor, args=None):
        return visitor.form(self, args)


class Field(Node):
    def __init__(self, position, source, title, name, data_type, var_type='field'):
        super(Field, self).__init__(var_type, position, source)
        self.title = title
        self.name = name
        self.data_type = data_type

    def apply(self, visitor, args=None):
        return visitor.field(self, args)


class Assignment(Field):
    def __init__(self, position, source, title, name, data_type, expression):
        super(Assignment, self).__init__(position, source, title, name, data_type, 'assignment')
        self.expression = expression

    def apply(self, visitor, args=None):
        return visitor.assignment(self, args)


class If(Node):
    def __init__(self, boolean_statement):
        super(If, self).__init__('if')
        self.condition = boolean_statement[0]
        self.statements = boolean_statement[1]

    def apply(self, visitor, args=None):
        return visitor.conditional_if(self, args)


class IfElse(Node):
    def __init__(self, boolean_statement):
        super(IfElse, self).__init__('if_else')
        self.condition = boolean_statement[0]
        self.statements = boolean_statement[1]
        self.else_statement_list = boolean_statement.else_statement[0]

    def apply(self, visitor, args=None):
        return visitor.conditional_if_else(self, args)


class BinaryOperation(Node):
    def __init__(self, var_type, left, right, position, source):
        super(BinaryOperation, self).__init__(var_type, position, source)
        self.lhs, self.rhs = left, right


class Multiplication(BinaryOperation):
    def __init__(self, position, source, lhs, rhs):
        super(Multiplication, self).__init__('multiplication', lhs, rhs, position, source)

    def apply(self, visitor, args=None):
        return visitor.multiplication(self, args)


class Addition(BinaryOperation):
    def __init__(self, position, source, lhs, rhs):
        super(Addition, self).__init__('addition', lhs, rhs, position, source)

    def apply(self, visitor, args=None):
        return visitor.addition(self, args)


class Subtraction(BinaryOperation):
    def __init__(self, position, source, lhs, rhs):
        super(Subtraction, self).__init__('subtraction', lhs, rhs, position, source)

    def apply(self, visitor, args=None):
        return visitor.subtraction(self, args)


class Division(BinaryOperation):
    def __init__(self, position, source, lhs, rhs):
        super(Division, self).__init__('division', lhs, rhs, position, source)

    def apply(self, visitor, args=None):
        return visitor.division(self, args)


class UnaryOperation(Node):
    def __init__(self, var_type, operand, position, source):
        super(UnaryOperation, self).__init__(var_type, position, source)
        self.operand = operand


class Positive(UnaryOperation):
    def __init__(self, position, source, operand):
        super(Positive, self).__init__('positive', operand, position, source)

    def apply(self, visitor, args=None):
        return visitor.positive(self, args)


class Negative(UnaryOperation):
    def __init__(self, position, source, operand):
        super(Negative, self).__init__('negative', operand, position, source)

    def apply(self, visitor, args=None):
        return visitor.negative(self, args)


class Negation(UnaryOperation):
    def __init__(self, position, source, operand):
        super(Negation, self).__init__('negation', operand, position, source)

    def apply(self, visitor, args=None):
        return visitor.negation(self, args)


class And(BinaryOperation):
    eval_function = all

    def __init__(self, position, source, left, right):
        super(And, self).__init__('and', left, right, position, source)

    def apply(self, visitor, args=None):
        return visitor.and_(self, args)


class Or(BinaryOperation):
    eval_function = any

    def __init__(self, position, source, left, right):
        super(Or, self).__init__('or', left, right, position, source)

    def apply(self, visitor, args=None):
        return visitor.or_(self, args)


class Equality(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(Equality, self).__init__('equality', left, right, position, source)

    def apply(self, visitor, args=None):
        return visitor.equality(self, args)


class GreaterExclusive(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(GreaterExclusive, self).__init__('greater_exclusive', left, right, position, source)

    def apply(self, visitor, args=None):
        return visitor.greater_exclusive(self, args)


class GreaterInclusive(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(GreaterInclusive, self).__init__('greater_inclusive', left, right, position, source)

    def apply(self, visitor, args=None):
        return visitor.greater_inclusive(self, args)


class LowerInclusive(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(LowerInclusive, self).__init__('lower_inclusive', left, right, position, source)

    def apply(self, visitor, args=None):
        return visitor.lower_inclusive(self, args)


class LowerExclusive(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(LowerExclusive, self).__init__('lower_exclusive', left, right, position, source)

    def apply(self, visitor, args=None):
        return visitor.lower_exclusive(self, args)


class Inequality(BinaryOperation):
    def __init__(self, position, source, left, right):
        super(Inequality, self).__init__('inequality', left, right, position, source)

    def apply(self, visitor, args=None):
        return visitor.inequality(self, args)


class Value(Node):
    def __init__(self, var_type, position, source, value, data_type):
        super(Value, self).__init__(var_type, position, source)
        self.value = value
        self.data_type = data_type


class Integer(Value):
    def __init__(self,  position=0, source='', value=0):
        super(Integer, self).__init__("integer", position, source, value, DataTypes.integer)

    def apply(self, visitor, args=None):
        return visitor.integer(self, args)


class Boolean(Value):
    def __init__(self, position=0, source='', value=False):
        super(Boolean, self).__init__("boolean", position, source, value, DataTypes.boolean)

    def apply(self, visitor, args=None):
        return visitor.boolean(self, args)


class Money(Value):
    def __init__(self,  position=0, source='', value=0.00):
        super(Money, self).__init__("money", position, source, value, DataTypes.money)

    def apply(self, visitor, args=None):
        return visitor.money(self, args)


class String(Value):
    def __init__(self,  position=0, source='', value=''):
        super(String, self).__init__("string", position, source, value, DataTypes.string)

    def apply(self, visitor, args=None):
        return visitor.string(self, args)


class Identifier(Node):
    def __init__(self, position, source, name):
        super(Identifier, self).__init__('identifier', position, source)
        self.name = name

    def apply(self, visitor, args=None):
        return visitor.identifier(self, args)
