# At a stable state of lexing and parsing this import needs to be changed to a more concrete version which only imports
# the needed methods from the pyparsing package
from pyparsing import *


def parse(input_string):
    identifier = Word(alphas, alphanums + '_')
    number = Word(nums + ".")

    arith_operand = number | identifier
    bool_operand = Literal("true") | Literal("false") | identifier

    # Reserved keywords
    form = Suppress("form")
    if_lit = Suppress("if")

    # Reserved symbols
    l_curly = Suppress("{")
    r_curly = Suppress("}")
    l_paren = Suppress("(")
    r_paren = Suppress(")")
    colon = Suppress(":")
    data_types = oneOf(["boolean", "money", "string", "integer"])

    # Reserved operators
    mult_op = oneOf(["*", "/"])
    additive_op = oneOf(["+", "-"])
    rat_op = oneOf(["<", "<=", ">", ">="])
    eqal_op = oneOf(["==", "!="])
    con_and_op = Literal("&&")
    con_or_op = Literal("||")
    assign_op = Suppress("=")

    # Arithmetic precedence
    arith_prec = infixNotation(
        arith_operand,
        [
            (mult_op, 2, opAssoc.LEFT),
            (additive_op, 2, opAssoc.LEFT),
            (rat_op, 2, opAssoc.LEFT),
            (eqal_op, 2, opAssoc.LEFT),
            (con_and_op, 2, opAssoc.LEFT),
            (con_or_op, 2, opAssoc.LEFT),
        ]
    )

    # Expressions
    arithmetic_expr = \
        arith_prec

    arithmetic_statement = \
        OneOrMore(arithmetic_expr | (l_paren + arithmetic_expr + r_paren))

    assignment_expr = \
        identifier.setResultsName("identifier") + \
        colon + \
        data_types.setResultsName("data_type") + \
        Optional(
            assign_op +
            arithmetic_statement.setResultsName("arithmetic_statement")
        )

    field_expr = \
        Group(
            QuotedString('"', unquoteResults=True) +
            assignment_expr
        )

    if_stmt = Forward()
    if_stmt << \
        Group(
            if_lit +
            l_paren +
            arithmetic_statement +
            r_paren +
            l_curly +
            (OneOrMore(field_expr) | ZeroOrMore(if_stmt)) +
            r_curly
        )

    # Program
    program = \
        form + \
        identifier.setResultsName("form_identifier") + \
        l_curly + \
        Group(
            ZeroOrMore(field_expr.setResultsName("field_expression*") | if_stmt.setResultsName("if_statement*"))
        ).setResultsName("form_statement_list") + \
        r_curly

    tokens = program.parseString(input_string)
    return tokens
