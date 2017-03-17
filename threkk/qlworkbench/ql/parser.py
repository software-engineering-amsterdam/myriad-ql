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

    declaration -> STR ID COLON type

    assgination -> STR ID COLON type ASSIGN expression

    condition -> IF LPAREN cond RPARENT LBRACK statements RBRACK

    type -> BOOLEAN | DECIMAL | STRING

    cond -> cond AND comparison
          | cond OR comparison
          | comparison

    comparison -> comparison LT expression
                | comparison LET expression
                | comparison GT expression
                | comparison GET expression
                | comparison NEQ expression
                | comparison EQ expression
                | expression

    expression -> expression PLUS term
               | expression MINUS term
               | term

    term -> term MULT factor
          | term DIV factor
          | factor

    factor -> ID
            | LPAREN cond RPAREN


The output of the parser is an AST tree. The AST tree has been processed to
simplify the raw output of the grammar and some reduntant or innecesary nodes
and tokens haven been merged. The resultant AST tree is sucession of nested
tuples which each one represents one node of the three. The first element of
the tuple is the type of the tuple, and the upcoming elements are properties,
which can be also other tuples or arrays of tuples.
"""
from ply import yacc
from .ast.expression import AndExpression
from .ast.expression import OrExpression
from .ast.expression import LTExpression
from .ast.expression import LETExpression
from .ast.expression import GTExpression
from .ast.expression import GETExpression
from .ast.expression import NEQExpression
from .ast.expression import EQExpression
from .ast.expression import PlusExpression
from .ast.expression import MinusExpression
from .ast.expression import MultExpression
from .ast.expression import DivExpression
from .ast.expression import NotExpression
from .ast.expression import TrueExpression
from .ast.expression import FalseExpression
from .ast.expression import StringExpression
from .ast.expression import DecimalExpression
from .ast.expression import IdExpression
from .ast.node import BooleanAssignation
from .ast.node import DecimalAssignation
from .ast.node import StringAssignation
from .ast.node import BooleanDeclaration
from .ast.node import DecimalDeclaration
from .ast.node import StringDeclaration
from .ast.root import QLAST


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
        p[0] = QLAST(p[2])
        for statement in p[4]:
            p[0].register_node(statement)

    def p_statements(self, p):
        """
        statements : assignation statements
                   | declaration statements
                   | condition statements
                   | empty statements
                   | assignation
                   | declaration
                   | condition
                   | empty

        """
        # This looks ugly, but it is necessary to fix the different length
        # rules. This is a limitation of the patter matching of PLY, which
        # leaves much of this at developer's will.
        if (len(p) == 3):
            if type(p[1]) is list:
                p[0] = p[1] + p[2]
            else:
                p[0] = [p[1]] + p[2]
        else:
            if type(p[1]) is list:
                p[0] = p[1]
            else:
                p[0] = [p[1]]

    def p_declaration(self, p):
        """
        declaration : STR ID COLON BOOLEAN
                    | STR ID COLON DECIMAL
                    | STR ID COLON STRING
        """
        if p[4] == 'string':
            p[0] = StringDeclaration(p[1], p[2])
        elif p[4] == 'boolean':
            p[0] = BooleanDeclaration(p[1], p[2])
        elif p[4] == 'decimal':
            p[0] = DecimalDeclaration(p[1], p[2])
        else:
            # something something error
            pass

    def p_assignation(self, p):
        """
        assignation : STR ID COLON BOOLEAN ASSIGN expression
                    | STR ID COLON DECIMAL ASSIGN expression
                    | STR ID COLON STRING ASSIGN expression
        """
        if p[4] == 'string':
            p[0] = StringAssignation(p[1], p[2], p[6])
        elif p[4] == 'boolean':
            p[0] = BooleanAssignation(p[1], p[2], p[6])
        elif p[4] == 'decimal':
            p[0] = DecimalAssignation(p[1], p[2], p[6])
        else:
            # something something error
            pass

    def p_condition(self, p):
        """
        condition : IF LPAREN cond RPAREN LBRACK statements RBRACK
                  | IF LPAREN cond RPAREN LBRACK statements RBRACK ELSE LBRACK statements RBRACK 
        """  # noqa
        # Add the condition to the statements and keep iterating.
        for statement in p[6]:
            statement.add_condition(p[3])

        # Add the negative of the condition to those if p[10]:
        if len(p) == 12:
            for statement in p[10]:
                statement.add_condition(NotExpression(p[3]))

            p[0] = p[6] + p[10]
        else:
            p[0] = p[6]

    def p_cond(self, p):
        """
        cond : cond AND comparison
             | cond OR comparison
             | comparison
        """
        if len(p) == 4:
            if p[2] == '&&':
                p[0] = AndExpression(p[1], p[3])
            elif p[2] == '||':
                p[0] = OrExpression(p[1], p[3])
            else:
                print(p[1])
        else:
            p[0] = p[1]

    def p_comparison(self, p):
        """
        comparison : comparison LT expression
                   | comparison LET expression
                   | comparison GT expression
                   | comparison GET expression
                   | comparison NEQ expression
                   | comparison EQ expression
                   | expression
        """
        if len(p) == 4:
            if p[2] == '<':
                p[0] = LTExpression(p[1], p[3])
            elif p[2] == '<=':
                p[0] = LETExpression(p[1], p[3])
            elif p[2] == '>':
                p[0] = GTExpression(p[1], p[3])
            elif [2] == '>=':
                p[0] = GETExpression(p[1], p[3])
            elif p[2] == '!=':
                p[0] = NEQExpression(p[1], p[3])
            elif p[2] == '==':
                p[0] = EQExpression(p[1], p[3])
            else:
                print(p[1], p[2], p[3])
        else:
            p[0] = p[1]

    def p_expression(self, p):
        """
        expression : expression PLUS term
                   | expression MINUS term
                   | term
        """
        if len(p) == 4:
            if p[2] == '+':
                p[0] = PlusExpression(p[1], p[3])
            elif p[2] == '-':
                p[0] = MinusExpression(p[1], p[3])
            else:
                print(p[1])
        else:
            p[0] = p[1]

    def p_term(self, p):
        """
        term : term MULT factor
             | term DIV factor
             | factor
        """
        if len(p) == 4:
            if p[2] == '*':
                p[0] = MultExpression(p[1], p[3])
            elif p[2] == '/':
                p[0] = DivExpression(p[1], p[3])
            else:
                print(p[1])
        else:
            p[0] = p[1]

    def p_factor(self, p):
        """
        factor : LPAREN cond RPAREN
               | NOT factor
               | TRUE
               | FALSE
               | DECIMAL
               | STR
               | ID
        """
        if len(p) == 4:
            p[0] = p[2]
        elif len(p) == 3:
            p[0] = NotExpression(p[2])
        elif p[1] == 'true':
            p[0] = TrueExpression()
        elif p[1] == 'false':
            p[0] = FalseExpression()
        elif type(p[1]) == float:
            p[0] = DecimalExpression(p[1])
        elif p[1][0] == '"' and p[1][-1] == '"':
            p[0] = StringExpression(p[1])
        else:
            p[0] = IdExpression(p[1])

    def p_empty(self, p):
        'empty :'
        p[0] = []

    def p_error(self, p):
        print(p)
