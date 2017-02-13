from pyparsing import *
from form_ast import *


class Grammar:
    identifier = Word(alphas)
    identifier.addCondition(lambda tokens:
        tokens[0] not in """true false form if else boolean string integer
                            decimal money""".split())
    identifier.setParseAction(lambda tokens: Iden(tokens[0]))

    datatype = oneOf("boolean string integer decimal money")
    datatype.setParseAction(lambda tokens: Datatype[tokens[0]])

    mul_op = oneOf("* /")
    add_op = oneOf("+ -")
    sign_op = oneOf("+ -")
    neg_op = Literal("!")
    dis_op = Literal("||")
    con_op = Literal("&&")
    eq_op = oneOf("== !=")
    rel_op = oneOf("< > <= >=") # FIXME == != in compr_expr

    for op in [mul_op, add_op, sign_op, neg_op, dis_op, con_op, eq_op, rel_op]:
        op.setParseAction(lambda tokens: Operator[tokens[0]])

    semicolon = Suppress(":")
    bracket_open = Suppress("{")
    bracket_close = Suppress("}")
    assignment = Suppress("=")

    integer = pyparsing_common.integer
    integer.addParseAction(lambda tokens: Const(tokens[0], Datatype.integer))
    decimal = pyparsing_common.real
    decimal.addParseAction(lambda tokens: Const(tokens[0], Datatype.decimal))
    true = Literal("true")
    true.setParseAction(lambda _: Const(True, Datatype.boolean))
    false = Literal("false")
    false.setParseAction(lambda _: Const(False, Datatype.boolean))
    string = QuotedString("\"")
    string.setParseAction(lambda tokens: Const(tokens[0], Datatype.string))

    num_atom = identifier ^ integer ^ decimal
    num_expr = infixNotation(num_atom, [(sign_op, 1, opAssoc.RIGHT,
                                         lambda tokens: UnOp(*tokens[0])),
                                        (mul_op, 2, opAssoc.LEFT,
                                         lambda tokens: BinOp(*tokens[0])),
                                        (add_op, 2, opAssoc.LEFT,
                                         lambda tokens: BinOp(*tokens[0]))])

    rel_expr = num_expr + rel_op + num_expr
    rel_expr.setParseAction(lambda tokens: BinOp(*tokens))

    bool_atom = identifier ^ true ^ false ^ rel_expr
    bool_expr = infixNotation(bool_atom, [(neg_op, 1, opAssoc.RIGHT,
                                           lambda tokens: UnOp(*tokens[0])),
                                          (eq_op, 2, opAssoc.LEFT,
                                           lambda tokens: BinOp(*tokens[0])),
                                          (con_op, 2, opAssoc.LEFT,
                                           lambda tokens: BinOp(*tokens[0])),
                                          (dis_op, 2, opAssoc.LEFT,
                                           lambda tokens: BinOp(*tokens[0]))])

    expression = bool_expr ^ num_expr

    block = Forward()

    question = identifier + semicolon + string + datatype +\
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
