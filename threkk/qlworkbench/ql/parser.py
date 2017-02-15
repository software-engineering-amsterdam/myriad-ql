# -*- coding: utf-8 -*-
"""
This module defines the grammar of the QL language. Given the tokens described
in qlworkbench.ql.parser, the grammar formaly expressed is the following:

S -> FROM ID LBRACK statements RBRACK

statements -> statement*

statement -> declaration
           | assignation
           | condition

declaration -> QUESTION ID COLON type

assgination -> QUESTION ID COLON type ASSIGN expr

condition -> IF LPAREN cond RPARENT LBRACK statements RBRACK

type -> BOOLEAN | DATE | DECIMAL | INTEGER | MONEY | STRING

condition -> condition AND comparison
           | condition OR comparison
           | comparison

comparison -> comparison LT ID
            | comparison LET ID
            | comparison GT ID
            | comparison GET ID
            | comparison NEQ ID
            | comparison EQ ID
            | ID

expr -> expr PLUS term
      | expr MINUS term
      | term

term -> term MULT factor
      | term DIV factor
      | factor

factor -> ID
        | LPAREN expr RPAREN
"""
