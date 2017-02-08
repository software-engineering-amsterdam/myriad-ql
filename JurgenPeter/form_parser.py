from pyparsing import *
from form_ast import *


class Grammar:
    dataType = oneOf("boolean string integer decimal money")

    identifier = Word(alphas).addCondition(
        lambda tokens: tokens[0] not in ["true", "false", "form", "if", "else"]
    )

    true = Literal("true").setParseAction(lambda _: True)
    false = Literal("false").setParseAction(lambda _: False)

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

    compr_expr = Group(num_expr + compr_op + num_expr)

    bool_atom = identifier ^ true ^ false ^ compr_expr
    bool_expr = Group(infixNotation(bool_atom, [(neg_op, 1, opAssoc.RIGHT),
                                                (eq_op, 2, opAssoc.LEFT),
                                                (con_op, 2, opAssoc.LEFT),
                                                (dis_op, 2, opAssoc.LEFT)]))

    expression = bool_expr ^ num_expr

    block = Forward()

    question = identifier + semicolon + QuotedString("\"") + dataType +\
        Optional(assignment + expression)

    conditional = Literal("if") + bool_expr + bracket_open + Group(block) +\
        bracket_close + Optional(Suppress("else") + bracket_open +
                                 Group(block) + bracket_close)

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
        if len(tokens) == 4 and tokens[0] == "if":
            return Conditional(Parser.parse_expression(tokens[1]),
                               [Parser.parse_statement(t) for t in tokens[2]],
                               [Parser.parse_statement(t) for t in tokens[3]])
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
        if type(tokens) == bool:
            return Constant(tokens, Datatype.boolean)
        if type(tokens) == str:
            if hasattr(Datatype, tokens):
                return Datatype[tokens]
            if hasattr(Operator, tokens):
                return Operator[tokens]
            if len(tokens) >= 2 and tokens[0] == tokens[-1] == "\"":
                return Constant(tokens[1:-1], Datatype.string)
            return Identifier(tokens)
        else:
            # When the argument is a list of parsed groups and tokens, the
            # inner groups are resursively parsed into expressions first.
            tokens = [Parser.parse_expression(t) for t in tokens]
            if len(tokens) == 2:
                return UnaryOperator(*tokens[0:2])
            while len(tokens) >= 3:
                tokens = [BinaryOperator(*tokens[0:3])] + tokens[3:]
            return tokens[0]
