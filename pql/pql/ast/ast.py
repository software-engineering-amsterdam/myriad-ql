# coding=utf-8
import collections


# class RootNode(object):
#     def __init__(self, parser_output):
#         self.root = Node("root_node")
#         self.root.add_child(Form(parser_output))
#
#     def __str__(self):
#         return self.root.__str__()


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
        return '<%s>', self.var_type


class Form(Node):
    def __init__(self, parsed_output):
        super(Form, self).__init__('form')
        self.name = parsed_output[0].form_identifier
        self.children = parsed_output[0].form_statement_list

    def __str__(self, level=0):
        ret = "\t" * level + repr(self.var_type) + "\n"
        for child in self.children:
            ret += child.__str__()
        return ret


class Field(Node):
    def __init__(self, parsed_output):
        super(Field, self).__init__('field')
        self.name = parsed_output.field_expression[0].identifier
        self.title = parsed_output.field_expression[0].title


class Assignment(Node):
    def __init__(self, parsed_output):
        super(Assignment, self).__init__('assignment')
        self.identifier = parsed_output.assignment_expr.identifier
        self.title = parsed_output.assignment_expr.title
        self.add_child(parsed_output.assignment_expr)


class Arithmetic(Node):
    def __init__(self, parsed_output):
        super(Arithmetic, self).__init__('arithmetic_statement')
        self.identifier = parsed_output.arithmetic_statement.identifier
        self.title = parsed_output.arithmetic_statement.title
        self.add_child(parsed_output.arithmetic_statement)


class Expression(Node):
    def __init__(self, parsed_output):
        super(Expression, self).__init__('arithmetic_expression')
        self.identifier = parsed_output.arthmentic_expression.identifier
        self.title = parsed_output.arthmentic_expression.title
        self.add_child(parsed_output.arthmentic_expression)


class Operand(Node):
    def __init__(self, parsed_output):
        super(Operand, self).__init__('arithmetic_operand')
        self.identifier = parsed_output.arthmentic_expression.identifier
        self.title = parsed_output.arthmentic_expression.title
        self.add_child(parsed_output.arthmentic_expression)


# class BoolOperand(Node):
#     def __init__(self, t, parsed_output):
#         super(BoolOperand).__init__('bool_operand')
#         self.label = t[0]
#         self.value = eval(t[0])
#
#     def __bool__(self):
#         return self.value
#
#     def __str__(self):
#         return self.label
#     __repr__ = __str__
#     __nonzero__ = __bool__
#
#
# class BoolBinOp(object):
#     def __init__(self,t):
#         self.args = t[0][0::2]
#
#     def __str__(self):
#         sep = " %s " % self.identifier
#         return "(" + sep.join(map(str,self.args)) + ")"
#
#     def __bool__(self):
#         return self.evalop(bool(a) for a in self.args)
#     __nonzero__ = __bool__
#     __repr__ = __str__
#
#
# class BoolAnd(BoolBinOp):
#     reprsymbol = '&'
#     evalop = all
#
# class BoolOr(BoolBinOp):
#     reprsymbol = '|'
#     evalop = any
#
#
# class BoolNot(Node):
#     def __init__(self, t, var_type):
#         super(BoolNot).__init__(var_type)
#         self.arg = t[0][1]
#
#     def __bool__(self):
#         v = bool(self.arg)
#         return not v

