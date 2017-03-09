from pyparsing import *
from ql.ast import *
from ql.datatypes import *


ParserElement.enablePackrat()

identifier = Word(alphas)
identifier.addCondition(lambda tokens: tokens[0] not in "form if else true "
                        "false boolean string integer decimal".split())

variable = identifier.copy()
variable.addParseAction(lambda tokens: Variable(tokens[0]))

integer = pyparsing_common.integer
integer.addParseAction(lambda tokens: IntegerConstant(tokens[0]))

decimal = pyparsing_common.real
decimal.addParseAction(lambda tokens: DecimalConstant(tokens[0]))

true = Literal("true")
true.setParseAction(lambda _: BooleanConstant(True))
false = Literal("false")
false.setParseAction(lambda _: BooleanConstant(False))
boolean = true ^ false

string = QuotedString("\"")
string.setParseAction(lambda tokens: StringConstant(tokens[0]))

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
    """ ParseAction to create a UnOp node from parsed tokens. The node
        constructor comes from the respective operator ParseAction. """
    tokens = tokens[0]
    nodetype = tokens[0]
    right = tokens[1]
    return nodetype(right)


def binop_action(tokens):
    """ ParseAction to create a BinOp node from parsed tokens. The node
        constructor comes from the respective operator ParseAction. Since
        pyparsing cannot group left associative operators automatically, we do
        this here ourselves. """
    tokens = tokens[0]
    while len(tokens) >= 3:
        left = tokens.pop(0)
        nodetype = tokens.pop(0)
        right = tokens.pop(0)
        tokens.insert(0, nodetype(left, right))

    assert len(tokens) == 1
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

integer_datatype = Literal("integer").setParseAction(lambda _: IntegerDatatype())
decimal_datatype = Literal("decimal").setParseAction(lambda _: DecimalDatatype())
boolean_datatype = Literal("boolean").setParseAction(lambda _: BooleanDatatype())
string_datatype = Literal("string").setParseAction(lambda _: StringDatatype())
datatype = integer_datatype ^ decimal_datatype ^ boolean_datatype ^ string_datatype

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

conditional = if_conditional ^ ifelse_conditional

statement = question ^ computed_question ^ conditional

block <<= Suppress("{") + ZeroOrMore(statement) + Suppress("}")
block.addParseAction(lambda tokens: [tokens.asList()])

form = Suppress("form") + identifier + block
form.setParseAction(lambda tokens: Form(*tokens))


def parse_file(filename):
    return form.parseFile(filename)[0]


def parse_string(string):
    return form.parseString(string)[0]
