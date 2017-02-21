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
    def __init__(self,  boolean_statement):
        super(Conditional, self).__init__('conditional')
        block = boolean_statement[0]
        self.condition = block[0]
        self.statements = block[1]
        #TODO Do something with potential else block
        self.else_statement_list = None
        del block


class BinaryOperation(Node):
    def __init__(self, var_type, parsed_tokens):
        Node.__init__(self, var_type)
        self.operator = parsed_tokens[0][1]
        self.arguments = parsed_tokens[0][::2]


#TODO: Cases where there is one long * statement,  a * b * c can't be parsed atm
class Multiplication(BinaryOperation):
    def __init__(self, parsed_tokens):
        BinaryOperation.__init__(self, 'multiplication', parsed_tokens)


class Addition(BinaryOperation):
    def __init__(self, parsed_tokens):
        BinaryOperation.__init__(self, 'addition', parsed_tokens)


class Subtraction(BinaryOperation):
    def __init__(self, parsed_tokens):
        BinaryOperation.__init__(self, 'subtraction', parsed_tokens)


class Division(BinaryOperation):
    def __init__(self, parsed_tokens):
        BinaryOperation.__init__(self, 'division', parsed_tokens)


class AddSub(Addition, Subtraction):
    def __init__(self, parsed_tokens):
        print(parsed_tokens)
        Addition.__init__(self, parsed_tokens)
        Subtraction.__init__(self, parsed_tokens)


class MultDiv(Multiplication, Division):
    def __init__(self, parsed_tokens):
        Multiplication.__init__(self, parsed_tokens)
        Division.__init__(self, parsed_tokens)


class BoolOperand(Node):
    def __init__(self, parsed_output):
        super(BoolOperand, self).__init__('bool_operand')
        self.label = parsed_output[0]


class BoolBinOp(Node):
    symbol = None
    eval_function = None

    def __init__(self, parsed_output, var_type):
        super(BoolBinOp, self).__init__(var_type)
        arguments = parsed_output[0][0::2]
        for arg in arguments:
            self.add_child(arg)
        del arguments


class BoolAnd(BoolBinOp):
    symbol = '&&'
    eval_function = all

    def __init__(self, parsed_output):
        super(BoolAnd, self).__init__(parsed_output, 'boolean_and')


class BoolOr(BoolBinOp):
    symbol = '||'
    eval_function = any

    def __init__(self, parsed_output):
        super(BoolOr, self).__init__(parsed_output, 'boolean_or')


class Condition(Node):
    def __init__(self, parsed_output):
        super(Condition, self).__init__('condition')
        self.add_child(parsed_output)
