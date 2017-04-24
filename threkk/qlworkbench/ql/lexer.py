# -*- coding: utf-8 -*-
"""
This module defines the parser of the QL language. It uses the PLY library to
take a string and return instead a stream of tokens. PLY is often used as a
single script but in this case we use a class for better encapsulation and code
organisation.
"""
from ply import lex
from ply.lex import TOKEN


class QLLexer(object):
    """The QLLexer parser shaped as a class"""

    # Reserved words. The documentation
    # (http://www.dabeaz.com/ply/ply.html#ply_nn6) advises to store them
    # separately in order to substitute common string for reserved words.
    reserved = {
        'boolean': 'BOOLEAN',
        'decimal': 'DECIMAL',
        'else': 'ELSE',
        'false': 'FALSE',
        'form': 'FORM',
        'if': 'IF',
        'string': 'STRING',
        'true': 'TRUE'
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
        'STR'        # "A question?"
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
    t_NOT = r'!'
    t_STR = r'\".+\"'

    # Regular expressions for tokens that require special interactions.
    ID = r'[a-zA-Z_][a-zA-Z_0-9]*'
    COMMENT = r'\/\/.*'
    NEW_LINE = r'\n+'
    WHITESPACE = r'\s+'
    DECIMAL = r'\d+(.\d+)?'

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

    @TOKEN(NEW_LINE)
    def t_newline(self, t):
        """Tracks the line number for error reporting purposes."""
        t.lexer.lineno += len(t.value)

    @TOKEN(DECIMAL)
    def t_DECIMAL(self, t):
        """We make sure the value returned is an float"""
        t.value = float(t.value)
        return t

    def t_error(self, t):
        """
        Error handler. It logs when a character is not recognised by any token
        of the lexer.
        """
        msg = 'Illegal character {}'.format(t.value[0])
        self.checker.register_error(t.lineno, msg)
        t.lexer.skip(1)

    def __init__(self, checker):
        """Initialises the lexer. It complies with PLY requirements."""
        self.checker = checker
        self.lexer = lex.lex(module=self, debug=0)
