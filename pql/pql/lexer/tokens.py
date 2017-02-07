#!/usr/bin/python


def get_tokens():
    return __token_definitions

"""Internal variable, should be exposed through method tokens()"""
__token_definitions = [
    # The skip-overs
    (r'[ \n\t]+',           None),
    (r'#[^\n]*',            None),

    # The reserved characters
    (r':',                  "COLON"),
    # (r'\'',                 "SINGLEQUOTE"),
    # (r'\"',                 "DOUBLEQUOTE"),

    # Operators
    (r'\+',                 "PLUS"),
    (r'\-',                 "MINUS"),

    # Assignments
    (r'=',                  "ASSIGN"),

    # Brackets
    (r'\(',                  "LPAREN"),
    (r'\)',                  "RPAREN"),
    (r'\{',                  "LCURLY"),
    (r'\}',                  "RCURLY"),

    # Types
    (r'boolean',            "BOOLEAN"),
    (r'money',              "MONEY"),

    # Reserved words
    (r'form',               "FORM"),
    # (r'hasSoldHouse',       "HASSOLDHOUSE"),
    # (r'hasBoughtHouse',     "HASBOUGHTHOUSE"),
    # (r'hasMaintLoan',       "HASMAINTLOAN"),
    # (r'sellingPrice',       "SELLINGPRICE"),
    # (r'privateDebt',        "PRIVATEDEBT"),
    # (r'valueResidue',       "VALUERESIDUE"),
    (r'if',                 "IF"),

    # String literal
    # (r'\"(\\.|[^"])*\"',    "DOUBLE_STRING_LITERAL"), # Or a field?
    (r'\"(\\.|[^"])*\"',    "FIELD"),  # Or a double string literal?

    # Identifier
    (r'([a-zA-Z])+',        "IDENTIFIER"),

    # Unidentifiable
    (r'.*',                 "UNINDENTIFIED"),
]
