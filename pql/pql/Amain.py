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
    form = Suppress("form")
    lcurly = Suppress("{")
    rcurly = Suppress("}")
    lparen = Suppress("(")
    rparen = Suppress(")")
    colon = Suppress(":")
    assign = Suppress("=")

    # Expressions
    arithmeticExpr = \
        identifier + \
        Optional(
            oneOf(["+", "-"]) + \
            identifier
        )

    assignmentExpr = \
        identifier.setResultsName("lhs") + \
        colon + \
        identifier.setResultsName("rhs") + \
        Optional(
            assign + \
            lparen + \
            arithmeticExpr.setResultsName("aexpr") + \
            rparen
        )

    fieldExpr = \
        quotedString.setResultsName("field") + \
        assignmentExpr.setResultsName("assexpr")

    # Statements
    if_stmt = \
        Suppress("if") + \
        lparen + \
        arithmeticExpr.setResultsName("aexpr") + \
        rparen + \
        lcurly + \
        OneOrMore(fieldExpr) + \
        rcurly

    # Program
    program = \
        form + \
        identifier.setResultsName("fi") + \
        lcurly + \
        Group(
            Optional(
                Group(
                    fieldExpr | if_stmt
                ) + \
                ZeroOrMore(Group(fieldExpr | if_stmt))
            )
        ) + \
        rcurly

    tokens = program.parseString(ql_str)

    print(tokens)
