from pyparsing import *
from form_ast import *

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
    conditional = Literal("if") + bool_expr + bracket_open + Group(block) + bracket_close
    conditional.setParseAction(lambda tokens: ["_if"] + tokens[1:])
    statement = Group(question ^ conditional)
    block <<= ZeroOrMore(statement)

    form = Literal("form") + identifier + bracket_open + Group(block) + bracket_close
    form.setParseAction(lambda tokens: ["_form"] + tokens[1:])


class Parser:

    @staticmethod
    def parse_file(filename):
        # TODO catch errors: print warning line and return None
        tokens = Grammar.form.parseFile(filename)
        return Parser.parse_form(tokens)

    @staticmethod
    def parse_form(tokens):
        if len(tokens) == 3 and tokens[0] == "_form":
            return Form(tokens[1], [Parser.parse_statement(t) for t in tokens[2]])
        return None

    @staticmethod
    def parse_statement(tokens):
        conditional = Parser.parse_conditional(tokens)
        if conditional is not None:
            return conditional
        return Parser.parse_question(tokens)

    @staticmethod
    def parse_conditional(tokens):
        if len(tokens) == 3 and tokens[0] == "_if":
            return Conditional(Parser.parse_expression(tokens[1]), [Parser.parse_statement(t) for t in tokens[2]])
        return None

    @staticmethod
    def parse_question(tokens):
        if len(tokens) == 3:
            return Question(tokens[0], tokens[1], Datatype[tokens[2]])
        elif len(tokens) == 4:
            return Question(tokens[0], tokens[1], Datatype[tokens[2]], Parser.parse_expression(tokens[3]))
        return None

    @staticmethod
    def parse_expression(tokens):
        if type(tokens) == str:
            if hasattr(Datatype, tokens):
                return Datatype[tokens]
            if tokens == "true":
                return Constant(True, Datatype.boolean)
            if tokens == "false":
                return Constant(False, Datatype.boolean)
            if tokens[0] == "\"" and tokens[-1] == "\"" and len(tokens) >= 2:
                return Constant(tokens[1:-1], Datatype.string)
            if tokens.isdigit():
                return Constant(int(tokens), Datatype.integer)
            if tokens.isdecimal():
                return Constant(float(tokens), Datatype.decimal)
            # TODO: check date
            return Identifier(tokens)
        else:
            tokens = [Parser.parse_expression(t) for t in tokens]
            if len(tokens) == 2:
                return UnaryOperator(*tokens[0:2])
            while len(tokens) >= 3:
                tokens = [BinaryOperator(*tokens[0:3])] + tokens[3:]
            return tokens[0]


if __name__ == "__main__":
    # TODO remove testcode for unittests
    filepath = "testForm.txt"
    # result = Grammar.form.parseFile(filepath)
    print(Parser.parse_file(filepath))
