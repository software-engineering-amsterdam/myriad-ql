from pyparsing import *


class Grammar:
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

    # num_expr = Forward()
    num_atom = Word(nums) ^ identifier
    num_expr = Group(infixNotation(num_atom, [(sign_op, 1, opAssoc.RIGHT),
                                              (mul_op, 2, opAssoc.LEFT),
                                              (add_op, 2, opAssoc.LEFT)]))

    compr_expr = num_expr + compr_op + num_expr

    bool_atom = identifier ^ "true" ^ "false" ^ Group(compr_expr)
    bool_expr = Group(infixNotation(bool_atom, [(neg_op, 1, opAssoc.RIGHT),
                                                (eq_op, 2, opAssoc.LEFT),
                                                (con_op, 2, opAssoc.LEFT),
                                                (dis_op, 2, opAssoc.LEFT)]))

    expression = bool_expr ^ num_expr

    block = Forward()
    question = identifier + semicolon + QuotedString("\"") + datatype + Optional(assignment + expression)
    conditional = Literal("if") + bool_expr + bracket_open + block + bracket_close
    conditional.setParseAction(lambda tokens: ["_if"] + tokens[1:])
    statement = Group(question ^ conditional)
    block <<= ZeroOrMore(statement)

    form = Literal("form") + identifier + bracket_open + Group(block) + bracket_close
    form.setParseAction(lambda tokens: ["_form"] + tokens[1:])


class Parser:

    @staticmethod
    def parse_file(filename):
        # TODO catch errors: print warning line and return None
        tokens = Grammar.form.parseFilename(filename)
        return Parser.parse_form(tokens)

    @staticmethod
    def parse_form(tokens):
        return None

    @staticmethod
    def parse_statement(tokens):
        return None

    @staticmethod
    def parse_conditional(tokens):
        return None

    @staticmethod
    def parse_question(tokens):
        return None

    @staticmethod
    def parse_expression(tokens):
        return None


if __name__ == "__main__":
    # TODO remove testcode for unittests
    result = Grammar.form.parseFile("JurgenPeter/testForm.txt")
    print(result)
