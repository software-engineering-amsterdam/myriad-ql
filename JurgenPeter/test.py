from pyparsing import *

identifier = Word(alphas)
datatype = oneOf("boolean string integer date decimal money")

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



num_expr = Forward()
num_atom = Word(nums) ^ identifier ^ "(" + num_expr + ")"
num_expr <<= operatorPrecedence(num_atom, [(sign_op, 1, opAssoc.RIGHT),
                                           (mul_op, 2, opAssoc.LEFT),
                                           (add_op, 2, opAssoc.LEFT)])

compr_expr = num_expr + compr_op + num_expr

bool_expr = Forward()
bool_atom = identifier ^ "true" ^ "false" ^ "(" + bool_expr + ")" ^ compr_expr ^ "(" + compr_expr + ")"
bool_expr <<= operatorPrecedence(bool_atom, [(neg_op, 1, opAssoc.RIGHT),
                                             (eq_op, 2, opAssoc.LEFT),
                                             (con_op, 2, opAssoc.LEFT),
                                             (dis_op, 2, opAssoc.LEFT)])

expression = bool_expr ^ num_expr

block = Forward()
question = identifier + semicolon + QuotedString("\"") + datatype + Optional(assignment + Group(expression))
conditional = Literal("if") + Group(bool_expr) + bracket_open + block + bracket_close
statement = Group(question ^ conditional)
block <<= ZeroOrMore(statement)

form = Literal("form") + identifier + bracket_open + block + bracket_close

print(form.parseFile("JurgenPeter/testForm.txt"))
