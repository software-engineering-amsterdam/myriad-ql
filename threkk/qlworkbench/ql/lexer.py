# -*- coding: utf-8 -*-
"""
This module defines the parser of the QL language. It uses the PLY library to
take a string and return instead a stream of tokens. PLY is often used as a
single script but in this case we use a class for better encapsulation and code
organisation.
"""
import logging
import ply.lex as lex
from ply.lex import TOKEN


class QLLexer(object):
    """The QLLexer parser shaped as a class"""

    # Reserved words. The documentation
    # (http://www.dabeaz.com/ply/ply.html#ply_nn6) advises to store them
    # separately in order to substitute common string for reserved words.
    reserved = {
        'boolean': 'BOOLEAN',
        'date': 'DATE',
        'decimal': 'DECIMAL',
        'form': 'FORM',
        'if': 'IF',
        'integer': 'INTEGER',
        'money': 'MONEY',
        'string': 'STRING'
    }

    # List of tokens used in the lexer.
    tokens = (
        'LBRACK',    # {
        'RBRACK',    # }
        'LPAREN',    # (
        'RPAREN',    # )
        'COLON',     # :
        'AND',       # &&
        'OR',        # ||
        'NOT',       # !
        'LT',        # <
        'GT',        # >
        'LET',       # <=
        'GET',       # >=
        'EQ',        # ==
        'NEQ',       # !=
        'PLUS',      # +
        'MINUS',     # -
        'MULT',      # *
        'DIV',       # /
        'ASSIGN',    # =
        'ID',        # anyString
        'QUESTION',  # "A question?"
        'COMMENT',   # // This is a comment
        'WHITESPACE'
    ) + tuple(reserved.values())  # Added also the reserved words.

    # Reggular expressions that define the tokens.
    t_LBRACK = r'\{'
    t_RBRACK = r'\}'
    t_LPAREN = r'\('
    t_RPAREN = r'\)'
    t_COLON = r'\:'
    t_AND = r'&&'
    t_OR = r'\|\|'
    t_LT = r'<'
    t_GT = r'>'
    t_LET = r'<='
    t_GET = r'>='
    t_EQ = r'=='
    t_NEQ = r'!='
    t_PLUS = r'\+'
    t_MINUS = r'-'
    t_MULT = r'\*'
    t_DIV = r'/'
    t_ASSIGN = r'='
    t_QUESTION = r'\".+\"'

    # Regular expressions for tokens that require special interactions.
    ID = r'[a-zA-Z_][a-zA-Z_0-9]*'
    COMMENT = r'\/\/.*'
    WHITESPACE = r'\s+'

    @TOKEN(ID)
    def t_ID(self, t):
        """
        Identifies the ID tokens. It also identifies all the reserved words by
        looking them up in a dictionary and substituting the ID type for the
        reserved word.
        """
        t.type = self.reserved.get(t.value, 'ID')  # Check for reserved words
        return t

    @TOKEN(COMMENT)
    def t_COMMENT(self, t):
        """Comments are ignored"""
        pass

    @TOKEN(WHITESPACE)
    def t_WHITESPACE(self, t):
        """White spaces are ignored"""
        pass

    def t_error(self, t):
        """
        Error handler. It logs when a character is not recognised by any token
        of the lexer.
        """
        # Following the recommendations in the Hitchhiker's guide to Python
        # (http://docs.python-guide.org/en/latest/writing/logging/)
        self.log.error('Character not parsed: {value}'.format(
            value=t.value[0]))
        t.lexer.skip(1)

    def __init__(self, **kwargs):
        """Initialises the lexer. It complies with PLY requirements."""
        logging.basicConfig(
            format='%(asctime)s %(levelname)-s %(message)s',
            datefmt='%m/%d/%Y %I:%M:%S %p')
        self.log = logging.getLogger()
        self.lexer = lex.lex(module=self, debug=0, **kwargs)

    def getLexer(self):
        """Returns the initialised lexer"""
        return self.lexer


if __name__ == '__main__':
    data = """form Box1HouseOwning {
        hasSoldHouse: "Did you sell a house in 2010?" boolean
        hasBoughtHouse: "Did you by a house in 2010?" boolean
        sellingPrice: "Price the house was sold for:" money
        // This is a comment.
        hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?"
            boolean
        if (hasSoldHouse) {
            privateDebt: "Private debts for the sold house:" money
            valueResidue: "Value residue:" money(sellingPrice - privateDebt)
        }
    }"""
    lexer = QLLexer()
    lexer.getLexer().input(data)
    while True:
            tok = lexer.getLexer().token()
            if not tok:
                break
            print(tok)
