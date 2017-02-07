from pyparsing import *
from form_ast import *


class Grammar:
    dataType = oneOf("boolean string integer date decimal money")
    identifier = Word(alphas)
    identifier.addCondition(
        lambda tokens: tokens[0] not in ["true", "false", "form", "if", "else"]
    )
    bool_val = oneOf("true false")

    mul_op = oneOf("* /")
    add_op = oneOf("+ -")
    sign_op = oneOf("+ -")
    compr_op = oneOf("< > <= >= == !=")
    neg_op = "!"
    dis_op = "||"
    con_op = "&&"
    eq_op = oneOf("== !=")

    semicolon = Suppress(":")
    bracket_open = Suppress("{")
    bracket_close = Suppress("}")
    assignment = Suppress("=")

    num_atom = identifier ^ pyparsing_common.integer ^ pyparsing_common.real
    num_expr = Group(infixNotation(num_atom, [(sign_op, 1, opAssoc.RIGHT),
                                              (mul_op, 2, opAssoc.LEFT),
                                              (add_op, 2, opAssoc.LEFT)]))

    compr_expr = num_expr + compr_op + num_expr

    bool_atom = identifier ^ bool_val ^ Group(compr_expr)
    bool_expr = Group(infixNotation(bool_atom, [(neg_op, 1, opAssoc.RIGHT),
                                                (eq_op, 2, opAssoc.LEFT),
                                                (con_op, 2, opAssoc.LEFT),
                                                (dis_op, 2, opAssoc.LEFT)]))

    expression = bool_expr ^ num_expr

    block = Forward()

    question = identifier + semicolon + QuotedString("\"") + dataType +\
        Optional(assignment + expression)

    conditional = Literal("if") + bool_expr + bracket_open + Group(block) +\
        bracket_close + Optional(Literal("else") + bracket_open + Group(block) +
                                 bracket_close)

    statement = Group(question ^ conditional)
    block <<= ZeroOrMore(statement)

    form = Literal("form") + identifier + bracket_open + Group(block) +\
        bracket_close


class Parser:

    @staticmethod
    def parse_file(filename):
        try:
            tokens = Grammar.form.parseFile(filename)
            return Parser.parse_form(tokens)
        except ParseException:
            return None

    @staticmethod
    def parse_form(tokens):
        if len(tokens) == 3 and tokens[0] == "form":
            return Form(tokens[1], [Parser.parse_statement(t) for t in
                                    tokens[2]])
        return None

    @staticmethod
    def parse_statement(tokens):
        conditional = Parser.parse_conditional(tokens)
        if conditional is not None:
            return conditional
        return Parser.parse_question(tokens)

    @staticmethod
    def parse_conditional(tokens):
        if len(tokens) == 3 and tokens[0] == "if":
            return Conditional(Parser.parse_expression(tokens[1]),
                               [Parser.parse_statement(t) for t in tokens[2]])
        if len(tokens) == 5 and tokens[0] == "if" and tokens[3] == "else":
            return Conditional(Parser.parse_expression(tokens[1]),
                               [Parser.parse_statement(t) for t in tokens[2]],
                               [Parser.parse_statement(t) for t in tokens[4]])
        return None

    @staticmethod
    def parse_question(tokens):
        if len(tokens) == 3:
            return Question(tokens[0], tokens[1], Datatype[tokens[2]])
        elif len(tokens) == 4:
            return Question(tokens[0], tokens[1], Datatype[tokens[2]],
                            Parser.parse_expression(tokens[3]))
        return None

    @staticmethod
    def parse_expression(tokens):
        if type(tokens) == int:
            return Constant(tokens, Datatype.integer)
        if type(tokens) == float:
            return Constant(tokens, Datatype.decimal)
        if type(tokens) == str:
            if hasattr(Datatype, tokens):
                return Datatype[tokens]
            if tokens == "true":
                return Constant(True, Datatype.boolean)
            if tokens == "false":
                return Constant(False, Datatype.boolean)
            if tokens[0] == "\"" and tokens[-1] == "\"" and len(tokens) >= 2:
                return Constant(tokens[1:-1], Datatype.string)
            # TODO: check date
            return Identifier(tokens)
        else:
            tokens = [Parser.parse_expression(t) for t in tokens]
            if len(tokens) == 2:
                return UnaryOperator(*tokens[0:2])
            while len(tokens) >= 3:
                tokens = [BinaryOperator(*tokens[0:3])] + tokens[3:]
            return tokens[0]
