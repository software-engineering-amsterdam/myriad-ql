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


#TODO: If you check debug there is an unneccessary extra level caused by Expression/Arithmetic, might need fixing in parser
class Expression(Node):
    def __init__(self, arithmetic):
        super(Expression, self).__init__('arithmetic_expression')
        self.add_child(arithmetic)


class Arithmetic(Node):
    def __init__(self, parsed_output):
        super(Arithmetic, self).__init__('arithmetic_statement')
        self.add_child(parsed_output)


class Conditional(Node):
    def __init__(self, parsed_output):
        super(Conditional, self).__init__('conditional')
        a = parsed_output[0]
        self.else_ = None


class BinaryOperation(Node):
    def __init__(self, var_type, parsed_tokens):
        super(BinaryOperation, self).__init__(var_type)
        self.arguments = parsed_tokens[0]

#TODO: Cases where there is one long * statement,  a * b * c can't be parsed atm
class Multiplication(BinaryOperation):
    def __init__(self, parsed_tokens):
        super(Multiplication, self).__init__('multiplication', parsed_tokens)


class Addition(BinaryOperation):
    def __init__(self, parsed_tokens):
        super(Addition, self).__init__('addition', parsed_tokens)


class Substraction(BinaryOperation):
    def __init__(self, parsed_tokens):
        super(Substraction, self).__init__('substraction', parsed_tokens)


class Division(BinaryOperation):
    def __init__(self, parsed_tokens):
        super(Division, self).__init__('division', parsed_tokens)

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

