from pyparsing import *
from form_ast import *


# TODO please improve nesting/grouping
# TODO move to better location
def createbinop(tokens):
    if len(tokens[0]) >= 3:
        return createbinop([[BinOp(*tokens[0][0:3])] + tokens[0][3:]])
    return tokens[0][0]


class Grammar:
    identifier = Word(alphas).addCondition(
        lambda tokens: tokens[0] not in """true false form if else boolean
                                        string integer decimal money""".split())
    identifier.addParseAction(lambda tokens: Variable(tokens[0]))

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
    braces_open = Suppress("{")
    braces_close = Suppress("}")
    assignment = Suppress("=")

    integer = pyparsing_common.integer
    integer.addParseAction(lambda tokens: Constant(tokens[0], Datatype.integer))
    decimal = pyparsing_common.real
    decimal.addParseAction(lambda tokens: Constant(tokens[0], Datatype.decimal))
    true = Literal("true")
    true.setParseAction(lambda _: Constant(True, Datatype.boolean))
    false = Literal("false")
    false.setParseAction(lambda _: Constant(False, Datatype.boolean))
    string = QuotedString("\"")
    string.setParseAction(lambda tokens: Constant(tokens[0], Datatype.string))

    num_atom = identifier ^ integer ^ decimal
    num_expr = infixNotation(num_atom, [(sign_op, 1, opAssoc.RIGHT,
                                         lambda tokens: UnOp(*tokens[0])),
                                        (mul_op, 2, opAssoc.LEFT,
                                         createbinop),
                                        (add_op, 2, opAssoc.LEFT,
                                         createbinop)])

    rel_expr = num_expr + rel_op + num_expr
    rel_expr.setParseAction(lambda tokens: BinOp(*tokens))

    bool_atom = identifier ^ true ^ false ^ rel_expr
    bool_expr = infixNotation(bool_atom, [(neg_op, 1, opAssoc.RIGHT,
                                           lambda tokens: UnOp(*tokens[0])),
                                          (eq_op, 2, opAssoc.LEFT,
                                           createbinop),
                                          (con_op, 2, opAssoc.LEFT,
                                           createbinop),
                                          (dis_op, 2, opAssoc.LEFT,
                                           createbinop)])

    expression = bool_expr ^ num_expr

    block = Forward()

    question = identifier + semicolon + QuotedString("\"") + datatype +\
        Optional(assignment + expression)
    question.setParseAction(lambda tokens: Question(*tokens))

    conditional = Suppress("if") + bool_expr + braces_open + block +\
        braces_close + Optional(Suppress("else") + braces_open +
                                 block + braces_close)
    conditional.setParseAction(lambda tokens: Condition(*tokens))

    statement = question ^ conditional

    block <<= Group(ZeroOrMore(statement))
    block.addParseAction(lambda tokens: tokens.asList())

    formname = Word(alphas).addCondition(
        lambda tokens: tokens[0] not in """true false form if else boolean
                                        string integer decimal money""".split())

    form = Suppress("form") + formname + braces_open + block +\
        braces_close
    form.setParseAction(lambda tokens: Form(*tokens))
