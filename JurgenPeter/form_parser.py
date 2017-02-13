from pyparsing import *
from form_ast import *


def create_binop(wrapped_tokens):
    tokens = wrapped_tokens[0]
    while len(tokens) >= 3:
        tokens = [BinOp(*tokens[0:3])] + tokens[3:]
    return tokens[0]


class Grammar:

    semicolon = Suppress(":")
    braces_open = Suppress("{")
    braces_close = Suppress("}")
    assignment = Suppress("=")

    identifier = Word(alphas)
    identifier.addCondition(
        lambda tokens: tokens[0] not in
        "form if else true false boolean string integer decimal money".split())

    variable = identifier.copy()
    variable.addParseAction(lambda tokens: Variable(tokens[0]))

    integer = pyparsing_common.integer
    integer.addParseAction(lambda tokens: Constant(tokens[0], Datatype.integer))

    decimal = pyparsing_common.real
    decimal.addParseAction(lambda tokens: Constant(tokens[0], Datatype.decimal))

    true = Literal("true")
    true.setParseAction(lambda _: Constant(True, Datatype.boolean))
    false = Literal("false")
    false.setParseAction(lambda _: Constant(False, Datatype.boolean))
    boolean = true ^ false

    string = QuotedString("\"")
    string.setParseAction(lambda tokens: Constant(tokens[0], Datatype.string))

    literal = integer ^ decimal ^ boolean ^ string

    neg_op = Literal("!")
    sign_op = oneOf("+ -")
    mul_op = oneOf("* /")
    add_op = oneOf("+ -")
    rel_op = oneOf("< > <= >=")
    eq_op = oneOf("== !=")
    and_op = Literal("&&")
    or_op = Literal("||")

    for op in [neg_op, sign_op, mul_op, add_op, rel_op, eq_op, and_op, or_op]:
        op.setParseAction(lambda tokens: Operator[tokens[0]])

    expression = infixNotation(
        literal ^ variable,
        [(neg_op, 1, opAssoc.RIGHT, lambda tokens: UnOp(*tokens[0])),
         (sign_op, 1, opAssoc.RIGHT, lambda tokens: UnOp(*tokens[0])),
         (mul_op, 2, opAssoc.LEFT, create_binop),
         (add_op, 2, opAssoc.LEFT, create_binop),
         (rel_op, 2, opAssoc.LEFT, create_binop),
         (eq_op, 2, opAssoc.LEFT, create_binop),
         (and_op, 2, opAssoc.LEFT, create_binop),
         (or_op, 2, opAssoc.LEFT, create_binop)])

    datatype = oneOf("boolean string integer decimal money")
    datatype.setParseAction(lambda tokens: Datatype[tokens[0]])

    block = Forward()

    question = identifier + semicolon + QuotedString("\"") + datatype +\
        Optional(assignment + expression)
    question.setParseAction(lambda tokens: Question(*tokens))

    conditional = Suppress("if") + expression + braces_open + block +\
        braces_close + Optional(Suppress("else") + braces_open + block +
                                braces_close)
    conditional.setParseAction(lambda tokens: Conditional(*tokens))

    statement = question ^ conditional

    block <<= Group(ZeroOrMore(statement))
    block.addParseAction(lambda tokens: tokens.asList())

    form = Suppress("form") + identifier + braces_open + block +\
        braces_close
    form.setParseAction(lambda tokens: Form(*tokens))
