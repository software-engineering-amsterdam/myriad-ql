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
    arith_operand = number | identifier
    bool_operand = Literal("true") | Literal("false") | identifier
    form = Literal("form")
    if_lit = Literal("if")
    l_curly = Suppress("{")
    r_curly = Suppress("}")
    l_paren = Suppress("(")
    r_paren = Suppress(")")
    colon = Suppress(":")
    assign = Suppress("=")
    data_types = oneOf(["boolean", "money", "string"])
    signop = oneOf(["+", "-"])
    multop = oneOf(["*", "/"])

    arith_prec = operatorPrecedence(
        arith_operand,
        [(multop, 2, opAssoc.LEFT),
         (signop, 2, opAssoc.LEFT),]
    )

    bool_prec = infixNotation(
        bool_operand,
        [("!", 1, opAssoc.RIGHT),
         ("&", 2, opAssoc.LEFT),
         ("|", 2, opAssoc.LEFT)]
    )

    # Expressions
    arithmetic_expr = \
        Group(
            arith_prec
        )

    arithmetic_statement = \
        OneOrMore(arithmetic_expr | (l_paren + arithmetic_expr + r_paren))
    #
    # boolean_statement = \
    #     OneOrMore(boo)

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
            quotedString.setResultsName("question_literal").addParseAction(removeQuotes) +
            assignment_expr.setResultsName("assignment_expression")
        )

    # Statements
    if_stmt = \
        Group(
            Group(
                if_lit +
                l_paren +
                arithmetic_statement +
                r_paren
            ) +
            l_curly +
            OneOrMore(field_expr) +
            r_curly
        )

    # Program
    program = \
        Group(
            form +
            identifier.setResultsName("form_identifier")
        ) + \
        l_curly + \
        Group(
            ZeroOrMore(field_expr | if_stmt)
        ) + \
        r_curly

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
