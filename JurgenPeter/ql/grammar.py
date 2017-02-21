from pyparsing import *
from ql.ast import *


ParserElement.enablePackrat()

identifier = Word(alphas)
identifier.addCondition(lambda tokens: tokens[0] not in "form if else true "
                        "false boolean string integer decimal".split())

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

plus_op = Literal("+").setParseAction(lambda _: PlusOp)
min_op = Literal("-").setParseAction(lambda _: MinOp)
not_op = Literal("!").setParseAction(lambda _: NotOp)

mul_op = Literal("*").setParseAction(lambda _: MulOp)
div_op = Literal("/").setParseAction(lambda _: DivOp)
add_op = Literal("+").setParseAction(lambda _: AddOp)
sub_op = Literal("-").setParseAction(lambda _: SubOp)

lt_op = Literal("<").setParseAction(lambda _: LtOp)
le_op = Literal("<=").setParseAction(lambda _: LeOp)
gt_op = Literal(">").setParseAction(lambda _: GtOp)
ge_op = Literal(">=").setParseAction(lambda _: GeOp)

eq_op = Literal("==").setParseAction(lambda _: EqOp)
ne_op = Literal("!=").setParseAction(lambda _: NeOp)

and_op = Literal("&&").setParseAction(lambda _: AndOp)
or_op = Literal("||").setParseAction(lambda _: OrOp)


def unop_action(tokens):
    tokens = tokens[0]
    nodetype = tokens[0]
    right = tokens[1]
    return nodetype(right)


# TODO: Nice comments or better code
def binop_action(tokens):
    tokens = tokens[0]
    while len(tokens) >= 3:
        left = tokens.pop(0)
        nodetype = tokens.pop(0)
        right = tokens.pop(0)
        tokens.insert(0, nodetype(left, right))
    return tokens[0]

expression = infixNotation(
    literal ^ variable,
    [(plus_op ^ min_op ^ not_op, 1, opAssoc.RIGHT, unop_action),
     (mul_op ^ div_op, 2, opAssoc.LEFT, binop_action),
     (add_op ^ sub_op, 2, opAssoc.LEFT, binop_action),
     (lt_op ^ le_op ^ gt_op ^ ge_op, 2, opAssoc.LEFT, binop_action),
     (eq_op ^ ne_op, 2, opAssoc.LEFT, binop_action),
     (and_op, 2, opAssoc.LEFT, binop_action),
     (or_op, 2, opAssoc.LEFT, binop_action)])

block = Forward()

datatype = oneOf("boolean string integer decimal")
datatype.setParseAction(lambda tokens: Datatype[tokens[0]])

question = identifier + Suppress(":") + QuotedString("\"") + datatype
question.setParseAction(lambda tokens: Question(*tokens))

computed_question = identifier + Suppress(":") + QuotedString("\"") +\
    datatype + Suppress("=") + expression
computed_question.setParseAction(lambda tokens: ComputedQuestion(*tokens))

if_conditional = Suppress("if") + expression + block
if_conditional.setParseAction(lambda tokens: IfConditional(*tokens))

ifelse_conditional = Suppress("if") + expression + block + Suppress("else") +\
    block
ifelse_conditional.setParseAction(lambda tokens: IfElseConditional(*tokens))

conditional = ifelse_conditional ^ if_conditional

statement = computed_question ^ question ^ conditional

block <<= Suppress("{") + ZeroOrMore(statement) + Suppress("}")
block.addParseAction(lambda tokens: [tokens.asList()])

form = Suppress("form") + identifier + block
form.setParseAction(lambda tokens: Form(*tokens))


def parse_file(filename):
    return form.parseFile(filename)[0]


def parse_string(string):
    return form.parseString(string)[0]
