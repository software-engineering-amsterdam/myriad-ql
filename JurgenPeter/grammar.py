from pyparsing import *
from form_ast import *


# TODO improve
def create_unop(tokens):
    return tokens[1](tokens[0])


def create_binop(wrapped_tokens):
    tokens = wrapped_tokens[0]
    while len(tokens) >= 3:
        left = tokens.pop(0)
        right = tokens.pop(1)
        tokens[0] = tokens[0](left, right)
    return tokens[0]


class Grammar:
    identifier = Word(alphas)
    identifier.addCondition(
        lambda tokens: tokens[0] not in
        "form if else true false boolean string integer decimal money".split())

    variable = identifier.copy()
    variable.addParseAction(lambda tokens: Variable(tokens[0]))

    integer = pyparsing_common.integer
    integer.addParseAction(lambda tokens: Constant(tokens[0],
                                                   Datatype.integer))

    decimal = pyparsing_common.real
    decimal.addParseAction(lambda tokens: Constant(tokens[0],
                                                   Datatype.decimal))

    true = Literal("true")
    true.setParseAction(lambda _: Constant(True, Datatype.boolean))
    false = Literal("false")
    false.setParseAction(lambda _: Constant(False, Datatype.boolean))
    boolean = true ^ false

    string = QuotedString("\"")
    string.setParseAction(lambda tokens: Constant(tokens[0], Datatype.string))

    literal = integer ^ decimal ^ boolean ^ string

    """
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
    """

    plus_op = Literal("+").setParseAction(lambda _: PlusOp)
    min_op = Literal("-").setParseAction(lambda _: MinOp)
    not_op = Literal("!").setParseAction(lambda _: NotOp)

    mul_op = Literal("*").setParseAction(lambda _: MulOp)
    div_op = Literal("/").setParseAction(lambda _: DivOp)
    add_op = Literal("+").setParseAction(lambda _: AddOp)
    sub_op = Literal("-").setParseAction(lambda _: SubOp)

    lt_op = Literal("<").setParseAction(lambda _: LtbOp)
    le_op = Literal("<=").setParseAction(lambda _: LebOp)
    gt_op = Literal(">").setParseAction(lambda _: GtOp)
    ge_op = Literal(">=").setParseAction(lambda _: GeOp)

    eq_op = Literal("==").setParseAction(lambda _: EqOp)
    ne_op = Literal("!=").setParseAction(lambda _: NeOp)

    and_op = Literal("&&").setParseAction(lambda _: AndOp)
    or_op = Literal("||").setParseAction(lambda _: OrOp)

    expression = infixNotation(
        literal ^ variable,
        [(plus_op ^ min_op ^ not_op, 1, opAssoc.RIGHT, create_unop),
         (mul_op ^ div_op, 2, opAssoc.LEFT, create_binop),
         (add_op ^ sub_op, 2, opAssoc.LEFT, create_binop),
         (lt_op ^ le_op ^ gt_op ^ ge_op, 2, opAssoc.LEFT, create_binop),
         (eq_op ^ ne_op, 2, opAssoc.LEFT, create_binop),
         (and_op, 2, opAssoc.LEFT, create_binop),
         (or_op ^ sub_op, 2, opAssoc.LEFT, create_binop)])

    """
        [(neg_op, 1, opAssoc.RIGHT, lambda tokens: UnOp(*tokens[0])),
         (sign_op, 1, opAssoc.RIGHT, lambda tokens: UnOp(*tokens[0])),
         (mul_op, 2, opAssoc.LEFT, create_binop),
         (add_op, 2, opAssoc.LEFT, create_binop),
         (rel_op, 2, opAssoc.LEFT, create_binop),
         (eq_op, 2, opAssoc.LEFT, create_binop),
         (and_op, 2, opAssoc.LEFT, create_binop),
         (or_op, 2, opAssoc.LEFT, create_binop)])
    """

    datatype = oneOf("boolean string integer decimal money")
    datatype.setParseAction(lambda tokens: Datatype[tokens[0]])

    block = Forward()

    question = identifier + Suppress(":") + QuotedString("\"") + datatype +\
        Optional(Suppress("=") + expression)
    question.setParseAction(lambda tokens: Question(*tokens))

    conditional = Suppress("if") + expression + Suppress("{") + block +\
        Suppress("}") + Optional(Suppress("else") + Suppress("{") + block +
                                 Suppress("}"))
    conditional.setParseAction(lambda tokens: Conditional(*tokens))

    statement = question ^ conditional

    block <<= Group(ZeroOrMore(statement))
    block.addParseAction(lambda tokens: tokens.asList())

    form = Suppress("form") + identifier + Suppress("{") + block +\
        Suppress("}")
    form.setParseAction(lambda tokens: Form(*tokens))

f = Grammar.form.parseFile("testForm.txt")[0]
print(f)
