import sys
import os
import io
# At a stable state of lexing and parsing this import needs to be changed to a more concrete version which only imports
# the needed methods from the pyparsing package
from pyparsing import *


# NOTES
# # # #
# The prototypical pyparsing program has the following structure:
#
# • Import names from pyparsing module
# • Define grammar using pyparsing classes and helper methods
# • Use the grammar to parse the input text
# • Process the results from parsing the input text
# # # #

def parse(input_string):
    identifier = Word(alphas, alphanums + '_')
    number = Word(nums + ".")
    operand = number | identifier
    form = Literal("form")
    if_lit = Literal("if")
    lcurly = Suppress("{")
    rcurly = Suppress("}")
    lparen = Suppress("(")
    rparen = Suppress(")")
    colon = Suppress(":")
    assign = Suppress("=")
    data_types = oneOf(["boolean", "money"])
    signop = oneOf(["+", "-"])
    multop = oneOf(["*", "/"])
    arith_prec = operatorPrecedence(
        operand,
        [(signop, 1, opAssoc.RIGHT),
         (multop, 2, opAssoc.LEFT),
         (signop, 2, opAssoc.LEFT),]
    )

    # Expressions
    arithmetic_expr = \
        Group(
            arith_prec
        )

    arithmetic_statement = \
        OneOrMore(arithmetic_expr | (lparen + arithmetic_expr + rparen))

    assignment_expr = \
        identifier.setResultsName("identifier") + \
        colon + \
        data_types + \
        Optional(
            assign +
            arithmetic_statement
        )

    field_expr = \
        Group(
            quotedString.setResultsName("question_literal") +
            assignment_expr.setResultsName("assignment_expression")
        )

    # Statements
    if_stmt = \
        Group(
            Group(
                if_lit +
                lparen +
                arithmetic_statement.setResultsName("arithmetic_expression") +
                rparen
            ) +
            lcurly +
            OneOrMore(field_expr) +
            rcurly
        )

    # Program
    program = \
        Group(
            form +
            identifier.setResultsName("form_identifier")
        ) + \
        lcurly + \
        Group(
            ZeroOrMore(field_expr | if_stmt)
        ) + \
        rcurly

    tokens = program.parseString(input_string)
    print(tokens)
    return tokens


if __name__ == '__main__':
    try:
        ql_file = io.open(sys.argv[1], 'r')
    except IndexError:
        print(" ".join(["Usage: python pql.py", str(os.path.join("path", "to", "your", "file"))]))
        sys.exit(1)
    except FileNotFoundError:
        print("The given file could not be found.")
        sys.exit(2)

    ql_str = ql_file.read()
    ql_file.close()
    parse(ql_str)
