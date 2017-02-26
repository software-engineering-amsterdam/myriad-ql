# coding=utf-8
class Node(object):
    def __init__(self, var_type):
        self.var_type = var_type
        self.children = []

    def add_child(self, node):
        self.children.append(node)

    def __str__(self, level=0):
        ret = "\t" * level + repr(self.var_type) + "\n"
        for child in self.children:
            ret += child.__str__()
        return ret

    def __repr__(self):
        return self.var_type


class Form(Node):
    def __init__(self, form_identifier, form_statement_list):
        super(Form, self).__init__('form')
        self.name = form_identifier
        self.children = form_statement_list

    def apply(self, visitor):
        return visitor.form(self)

    def __str__(self, level=0):
        ret = "\t" * level + repr(self.var_type) + "\n"
        for child in self.children:
            ret += child.__str__()
        return ret


class Field(Node):
    def __init__(self, title, identifier, data_type, arithmetic_statement=None):
        super(Field, self).__init__('field')
        self.name = identifier
        self.title = title
        self.data_type = data_type
        if arithmetic_statement:
            self.add_child(arithmetic_statement)

    def apply(self, visitor):
        return visitor.field(self)


class Expression(Node):
    def __init__(self, arithmetic):
        super(Expression, self).__init__('arithmetic_expression')
        self.add_child(arithmetic)

    def apply(self, visitor):
        return visitor.expression(self)


class Arithmetic(Node):
    def __init__(self, parsed_output):
        super(Arithmetic, self).__init__('arithmetic_statement')
        self.add_child(parsed_output)

    def apply(self, visitor):
        return visitor.arithmetic(self)


class Conditional(Node):
    def __init__(self,  boolean_statement):
        super(Conditional, self).__init__('conditional')
        self.condition = boolean_statement[0]
        self.statements = boolean_statement[1]
        self.else_statement_list = None
        if boolean_statement.else_statement is not None and len(boolean_statement.else_statement) > 0:
            self.else_statement_list = boolean_statement.else_statement[0]

    def apply(self, visitor):
        return visitor.conditional(self)


class BinaryOperation(Node):
    def __init__(self, var_type, left, right):
        super(BinaryOperation, self).__init__(var_type)
        self.lhs, self.rhs = left, right


class Multiplication(BinaryOperation):
    def __init__(self, lhs, rhs):
        super(Multiplication, self).__init__('multiplication', lhs, rhs)


class Addition(BinaryOperation):
    def __init__(self, lhs, rhs):
        super(Addition, self).__init__('addition', lhs, rhs)


class Subtraction(BinaryOperation):
    def __init__(self, lhs, rhs):
        super(Subtraction, self).__init__('substraction', lhs, rhs)

    def apply(self, visitor):
        return visitor.subtraction(self)


class Division(BinaryOperation):
    def __init__(self, lhs, rhs):
        super(Division, self).__init__('division', lhs, rhs)

    def apply(self, visitor):
        return visitor.division(self)


class BoolOperand(Node):
    def __init__(self, parsed_output):
        super(BoolOperand, self).__init__('bool_operand')
        self.label = parsed_output[0]


class UnaryOperation(Node):
    def __init__(self, var_type, right):
        super(UnaryOperation, self).__init__(var_type)
        self.right = right


class Positive(UnaryOperation):
    def __init__(self, right):
        super(Positive, self).__init__('positive', right)


class Negative(UnaryOperation):
    def __init__(self, right):
        super(Negative, self).__init__('negative', right)


class Negation(UnaryOperation):
    def __init__(self, right):
        super(Negation, self).__init__('negation', right)


class And(BinaryOperation):
    eval_function = all

    def __init__(self,  left, right):
        super(And, self).__init__('and', left, right)

    def apply(self, visitor):
        return visitor._and(self)


class Or(BinaryOperation):
    eval_function = any

    def __init__(self,  left, right):
        super(Or, self).__init__('or', left, right)

    def apply(self, visitor):
        return visitor._or(self)

class Equality(BinaryOperation):
    def __init__(self,  left, right):
        super(Equality, self).__init__('equality', left, right)


class GreaterExclusive(BinaryOperation):
    def __init__(self,  left, right):
        super(GreaterExclusive, self).__init__('greater_exclusive', left, right)


class GreaterInclusive(BinaryOperation):
    def __init__(self,  left, right):
        super(GreaterInclusive, self).__init__('greater_inclusive', left, right)


class LowerInlusive(BinaryOperation):
    def __init__(self,  left, right):
        super(LowerInlusive, self).__init__('lower_inclusive', left, right)


class LowerExclusive(BinaryOperation):
    def __init__(self,  left, right):
        super(LowerExclusive, self).__init__('lower_exclusive', left, right)


class Inequality(BinaryOperation):
    def __init__(self,  left, right):
        super(Inequality, self).__init__('inequality', left, right)


class Condition(Node):
    def __init__(self, parsed_output):
        super(Condition, self).__init__('condition')
        self.add_child(parsed_output)


class Value:
    def __init__(self, value):
        self.value = value
