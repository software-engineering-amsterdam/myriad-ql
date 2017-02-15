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
            quotedString.addParseAction(removeQuotes) +
            assignment_expr
        )

    if_stmt = \
        Group(
            if_lit + \
            l_paren + \
            arithmetic_statement + \
            r_paren + \
            l_curly + \
            OneOrMore(field_expr) + \
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
