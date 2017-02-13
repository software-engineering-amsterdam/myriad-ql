from pyparsing import *
from form_ast import *


class Grammar:
    datatype = oneOf("boolean string integer decimal money")
    datatype.setParseAction(lambda tokens: Datatype[tokens[0]])

    identifier = Word(alphas)
    identifier.addCondition(
        lambda tokens: tokens[0] not in """true false form if else boolean
                                        string integer decimal money""".split()
    )
    identifier.setParseAction(lambda tokens: Iden(tokens[0]))

    true = Literal("true").setParseAction(lambda _: Const(True, Datatype.boolean))
    false = Literal("false").setParseAction(lambda _: Const(False, Datatype.boolean))

    mul_op = oneOf("* /").setParseAction(lambda tokens: Operator[tokens[0]])
    add_op = oneOf("+ -").setParseAction(lambda tokens: Operator[tokens[0]])
    sign_op = oneOf("+ -").setParseAction(lambda tokens: Operator[tokens[0]])
    neg_op = Literal("!").setParseAction(lambda tokens: Operator[tokens[0]])
    dis_op = Literal("||").setParseAction(lambda tokens: Operator[tokens[0]])
    con_op = Literal("&&").setParseAction(lambda tokens: Operator[tokens[0]])
    eq_op = oneOf("== !=").setParseAction(lambda tokens: Operator[tokens[0]])
    compr_op = oneOf("< > <= >=").setParseAction(lambda tokens: Operator[tokens[0]]) # FIXME == != in compr_expr

    semicolon = Suppress(":")
    bracket_open = Suppress("{")
    bracket_close = Suppress("}")
    assignment = Suppress("=")

    integer = pyparsing_common.integer.addParseAction(lambda i: Const(i, Datatype.integer))
    decimal = pyparsing_common.real.addParseAction(lambda d: Const(d, Datatype.decimal))

    num_atom = identifier ^ integer ^ decimal
    num_expr = infixNotation(num_atom, [(sign_op, 1, opAssoc.RIGHT, lambda tokens: UnOp(*tokens[0])),
                                              (mul_op, 2, opAssoc.LEFT, lambda tokens: BinOp(*tokens[0])),
                                              (add_op, 2, opAssoc.LEFT, lambda tokens: BinOp(*tokens[0]))])

    compr_expr = num_expr + compr_op + num_expr
    compr_expr.setParseAction(lambda tokens: BinOp(*tokens))

    bool_atom = identifier ^ true ^ false ^ compr_expr # FIXME compr_expr break eq_op in bool_expr
    bool_expr = infixNotation(bool_atom, [(neg_op, 1, opAssoc.RIGHT, lambda tokens: UnOp(*tokens[0])),
                                                (eq_op, 2, opAssoc.LEFT, lambda tokens: BinOp(*tokens[0])),
                                                (con_op, 2, opAssoc.LEFT, lambda tokens: BinOp(*tokens[0])),
                                                (dis_op, 2, opAssoc.LEFT, lambda tokens: BinOp(*tokens[0]))])

    expression = bool_expr ^ num_expr

    block = Forward()

    question = identifier + semicolon + QuotedString("\"") + datatype +\
        Optional(assignment + expression)
    question.setParseAction(lambda tokens: Quest(*tokens))

    conditional = Suppress("if") + bool_expr + bracket_open + block +\
        bracket_close + Optional(Suppress("else") + bracket_open +
                                 block + bracket_close)
    conditional.setParseAction(lambda tokens: Cond(*tokens))

    statement = question ^ conditional
    block <<= Group(ZeroOrMore(statement))

    form = Suppress("form") + identifier + bracket_open + block +\
        bracket_close
    form.setParseAction(lambda tokens: Form(*tokens))


f = Grammar.form.parseFile("testForm.txt")[0]
print(f)

"""
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
            return Cond(Parser.parse_expression(tokens[1]),
                        [Parser.parse_statement(t) for t in tokens[2]])
        if len(tokens) == 4 and tokens[0] == "if":
            return Cond(Parser.parse_expression(tokens[1]),
                        [Parser.parse_statement(t) for t in tokens[2]],
                        [Parser.parse_statement(t) for t in tokens[3]])
        return None

    @staticmethod
    def parse_question(tokens):
        if len(tokens) == 3:
            return Quest(tokens[0], tokens[1], Datatype[tokens[2]])
        elif len(tokens) == 4:
            return Quest(tokens[0], tokens[1], Datatype[tokens[2]],
                         Parser.parse_expression(tokens[3]))
        return None

    @staticmethod
    def parse_expression(tokens):
        if type(tokens) == int:
            return Const(tokens, Datatype.integer)
        if type(tokens) == float:
            return Const(tokens, Datatype.decimal)
        if type(tokens) == bool:
            return Const(tokens, Datatype.boolean)
        if type(tokens) == str:
            if hasattr(Datatype, tokens):
                return Datatype[tokens]
            if hasattr(Operator, tokens):
                return Operator[tokens]
            if len(tokens) >= 2 and tokens[0] == tokens[-1] == "\"":
                return Const(tokens[1:-1], Datatype.string)
            return Iden(tokens)
        else:
            # When the argument is a list of (grouped) tokens, these are
            # recursively parsed into expressions first. That way, we can build
            # the parse tree node for the current expression immediately
            # afterwards.
            tokens = [Parser.parse_expression(t) for t in tokens]
            if len(tokens) == 2:
                return UnOp(*tokens[0:2])
            while len(tokens) >= 3:
                tokens = [BinOp(*tokens[0:3])] + tokens[3:]
            return tokens[0]
"""
