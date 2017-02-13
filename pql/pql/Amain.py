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

    # Tokens
    identifier = Word(alphas, alphanums + '_')
    number = Word(nums + ".")
    form = Literal("form")
    if_lit = Literal("if")
    lcurly = Suppress("{")
    rcurly = Suppress("}")
    lparen = Suppress("(")
    rparen = Suppress(")")
    colon = Suppress(":")
    assign = Suppress("=")
    data_types = oneOf(["boolean", "money"])

    # Expressions
    arithmeticExpr = \
        Group(
            identifier +
            Optional(
                oneOf(["+", "-"]) +
                identifier
            )
        )

    arithmeticStmt = \
        OneOrMore(arithmeticExpr | (lparen + arithmeticExpr + rparen))

    assignmentExpr = \
        identifier.setResultsName("identifier") + \
        colon + \
        data_types + \
        Optional(
            assign +
            arithmeticStmt
        )

    fieldExpr = \
        Group(
            quotedString.setResultsName("question_literal") +
            assignmentExpr.setResultsName("assignment_expression")
        )

    # Statements
    if_stmt = \
        Group(
            Group(
                if_lit +
                lparen +
                arithmeticStmt.setResultsName("arithmetic_expression") +
                rparen
            ) +
            lcurly +
            OneOrMore(fieldExpr) +
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
            ZeroOrMore(fieldExpr | if_stmt)
        ) + \
        rcurly

    tokens = program.parseString(ql_str)

    print(tokens)
