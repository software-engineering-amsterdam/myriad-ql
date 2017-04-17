# -*- coding: utf-8 -*-
"""
This module defines the grammar of the QL language. Given the tokens described
in `qlworkbench.ql.parser`.

The output of the parser is an AST tree. The AST tree has been processed to
simplify the raw output of the grammar and some reduntant or innecesary nodes
and tokens haven been merged. The resultant AST tree is sucession of nodes
which each one represents one statement of the file and one field of the form.

The statement can be declarations of questions or assginations of values. They
have three possible types: boolean, string and decimal. These statement can be
conditional.
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
from .ast.node import Assignation
from .ast.node import Declaration
from .ast.node import Root
from .ast.type import Boolean
from .ast.type import Decimal
from .ast.type import String


class QLParser(object):
    """Encapsulates the logic of the PLY parser."""
    def __init__(self, tokens, checker):
        self.ast = Root()
        self.checker = checker
        self.tokens = tokens
        self.start = 'start'
        self.parser = yacc.yacc(module=self, debug=0, write_tables=0)

    def parse(self, input, lexer):
        self.parser.parse(input, lexer)
        return self.ast

    def p_start(self, p):
        """
        start : FORM ID LBRACK statements RBRACK
        """
        self.ast.set_text(p[2])
        for statement in p[4]:
            self.ast.add_node(statement)

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
        declaration : STR ID COLON type
        """
        p[0] = Declaration(p[1], p[2], p[4])

    def p_assignation(self, p):
        """
        assignation : STR ID COLON type ASSIGN expression
        """
        p[0] = Assignation(p[1], p[2], p[4], p[6])

    def p_type(self, p):
        """
        type : BOOLEAN
             | DECIMAL
             | STRING
        """
        if p[1] == 'boolean':
            p[0] = Boolean()
        elif p[1] == 'decimal':
            p[0] = Decimal()
        elif p[1] == 'string':
            p[0] = String()
        else:
            msg = "Syntax error at token {} ({})".format(p[1].value,
                                                         p[1].type)
            self.checker.register_error(p.lineno(2), msg)

    def p_condition(self, p):
        """
        condition : IF LPAREN cond RPAREN LBRACK statements RBRACK
                  | IF LPAREN cond RPAREN LBRACK statements RBRACK ELSE LBRACK statements RBRACK
        """  # noqa
        # Add the condition to the statements and keep iterating.
        for statement in p[6]:
            statement.add_condition(p[3])

        # Add the negative of the condition to those in the else clause:
        if len(p) == 12:
            for statement in p[10]:
                statement.add_condition(NotExpression(p[3]))

            # We add the if-statements and the else-statements to the list of
            # statements to analyse.
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
                msg = "Syntax error at token {} ({})".format(p[2].value,
                                                             p[2].type)
                self.checker.register_error(p.lineno(2), msg)
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
                msg = "Syntax error at token {} ({})".format(p[2].value,
                                                             p[2].type)
                self.checker.register_error(p.lineno(2), msg)
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
                msg = "Syntax error at token {} ({})".format(p[2].value,
                                                             p[2].type)
                self.checker.register_error(p.lineno(2), msg)
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
                msg = "Syntax error at token {} ({})".format(p[2].value,
                                                             p[2].type)
                self.checker.register_error(p.lineno(2), msg)

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
        if p:
            msg = "Syntax error at token {} ({})".format(p.value, p.type)
            self.checker.register_error(p.lineno, msg)
            # Just discard the token and tell the parser it's okay. It will be
            # stopped later by the typechecker.
            self.parser.errok()
        else:
            msg = "Syntax error at EOF"
            self.checker.register_error('EOF', msg)
