# -*- coding: utf-8 -*-
"""
This module defines the grammar of the QL language. Given the tokens described
in qlworkbench.ql.parser, the grammar formaly expressed is the following:
::

    S -> FORM ID LBRACK statements RBRACK

    statements -> statement*

    statement -> declaration
               | assignation
               | condition

    declaration -> LABEL ID COLON type

    assgination -> LABEL ID COLON type ASSIGN expresion

    condition -> IF LPAREN cond RPARENT LBRACK statements RBRACK

    type -> BOOLEAN | DATE | DECIMAL | INTEGER | MONEY | STRING

    cond -> cond AND comparison
          | cond OR comparison
          | comparison

    comparison -> comparison LT ID
                | comparison LET ID
                | comparison GT ID
                | comparison GET ID
                | comparison NEQ ID
                | comparison EQ ID
                | ID

    expresion -> expresion PLUS term
               | expresion MINUS term
               | term

    term -> term MULT factor
          | term DIV factor
          | factor

    factor -> ID
            | LPAREN expresion RPAREN

The output of the parser is an AST tree. The AST tree has been processed to
simplify the raw output of the grammar and some reduntant or innecesary nodes
and tokens haven been merged. The resultant AST tree is sucession of nested
tuples which each one represents one node of the three. The first element of
the tuple is the type of the tuple, and the upcoming elements are properties,
which can be also other tuples or arrays of tuples.
"""
# TODO: Add more information about the different types of nodes.
from lexer import QLLexer
from ply import yacc


class QLParser(object):
    """Encapsulates the logic of the PLY parser."""
    def __init__(self, tokens):
        self.tokens = tokens
        self.start = 'start'
        self.parser = yacc.yacc(module=self)

    def parse(self, input):
        self.parser.parse(input, debug=True)

    def p_start(self, p):
        """
        start : FORM ID LBRACK statements RBRACK
        """
        p[0] = (p[2], [] + p[4])

    def p_statements(self, p):
        """
        statements : statement statements
                   | statement
                   | empty
        """
        if (len(p) == 3):
            p[0] = [p[1]] + p[2]
        else:
            p[0] = [p[1]]

    def p_statement(self, p):
        """
        statement : declaration
                  | assignation
                  | condition
        """
        p[0] = p[1]

    def p_declaration(self, p):
        """
        declaration : LABEL ID COLON type
        """
        p[0] = ('declaration', p[1], p[2], p[4])

    def p_assignation(self, p):
        """
        assignation : LABEL ID COLON type ASSIGN expresion
        """
        p[0] = ('assignation', p[1], p[2], p[4], p[6])

    def p_condition(self, p):
        """
        condition : IF LPAREN cond RPAREN LBRACK statements RBRACK
        """
        p[0] = ('condition', p[3], p[6])

    def p_type(self, p):
        """
        type : BOOLEAN
             | DATE
             | DECIMAL
             | INTEGER
             | MONEY
             | STRING
        """
        p[0] = p[1]

    def p_cond(self, p):
        """
        cond : cond AND comparison
             | cond OR comparison
             | comparison
        """
        if len(p) == 4:
            p[0] = (p[2], p[1], p[3])
        else:
            p[0] = p[1]

    def p_comparison(self, p):
        """
        comparison : comparison LT ID
                   | comparison LET ID
                   | comparison GT ID
                   | comparison GET ID
                   | comparison NEQ ID
                   | comparison EQ ID
                   | ID
        """
        if len(p) == 4:
            p[0] = (p[2], p[3], p[1])
        else:
            p[0] = ('id', p[1])

    def p_expression(self, p):
        """
        expresion : expresion PLUS term
                  | expresion MINUS term
                  | term
        """
        if len(p) == 4:
            p[0] = (p[2], p[1], p[3])
        else:
            p[0] = p[1]

    def p_term(self, p):
        """
        term : term MULT factor
             | term DIV factor
             | factor
        """
        if len(p) == 4:
            p[0] = (p[2], p[1], p[2])
        else:
            p[0] = p[1]

    def p_factor(self, p):
        """
        factor : LPAREN expresion RPAREN
               | ID
        """
        if len(p) == 4:
            p[0] = p[2]
        else:
            p[0] = p[1]

    def p_empty(self, p):
        'empty :'
        # p[0] = 'empty'
        p[0] = []

    def p_error(self, p):
        print('Error?')


if __name__ == '__main__':
    data = """form taxOfficeExample {
                "Did you sell a house in 2010?"
                    hasSoldHouse: boolean
                "Did you buy a house in 2010?"
                    hasBoughtHouse: boolean
                "Did you enter a loan?"
                    hasMaintLoan: boolean

                if (hasSoldHouse) {
                    "What was the selling price?"
                        sellingPrice: money
                    "Private debts for the sold house:"
                        privateDebt: money
                    "Value residue:"
                        valueResidue: money =
                            (sellingPrice - privateDebt)
                }

            }"""
    lex = QLLexer()
    par = QLParser(lex.tokens)
    result = par.parser.parse(data, lexer=lex.lexer)
    print(result)
