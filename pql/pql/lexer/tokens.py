def get_tokens():
    return __token_definitions

RESERVED    = 'RESERVED'
INT         = 'INT'
FIELD       = 'FIELD'
ID          = 'ID'
# COLON       = "COLON"
# PLUS        = "PLUS"
# MINUS       = "MINUS"
# ASSIGN      = "ASSIGN"
# LPAREN      = "LPAREN"
# RPAREN      = "RPAREN"
# LCURLY      = "LCURLY"
# RCURLY      = "RCULRY"
# BOOLEAN     = "BOOLEAN"
# MONEY       = "MONEY"
# FORM        = "FORM"
# IF          = "IF"
# FIELD       = 'FIELD'
# IDENTIFIER  = 'IDENTIFIER'

"""Internal variable, should be exposed through method tokens()"""
# __token_definitions = [
#     # The skip-overs
#     (r'[ \n\t]+',           None),
#     (r'#[^\n]*',            None),
#
#     # The reserved characters
#     (r':',                  COLON),
#
#     # Operators
#     (r'\+',                 PLUS),
#     (r'\-',                 MINUS),
#
#     # Assignments
#     (r'=',                  ASSIGN),
#
#     # Brackets
#     (r'\(',                 LPAREN),
#     (r'\)',                 RPAREN),
#     (r'\{',                 LCURLY),
#     (r'\}',                 RCURLY),
#
#     # Types
#     (r'boolean',            BOOLEAN),
#     (r'money',              MONEY),
#
#     # Reserved words
#     (r'form',               FORM),
#     (r'if',                 IF),
#
#     # String literal
#     (r'\"(\\.|[^"])*\"',    FIELD),
#
#     # Identifier
#     (r'([a-zA-Z])+',        IDENTIFIER),
#
#     # Unidentifiable. Use only for debugging the lexer
#     # (r'.*',                 "UNINDENTIFIED"),
# ]

__token_definitions = [
    # The skip-overs
    (r'[ \n\t]+',           None),
    (r'#[^\n]*',            None),

    # The reserved characters
    (r':',                  RESERVED),

    # Operators
    (r'\+',                 RESERVED),
    (r'\-',                 RESERVED),

    # Assignments
    (r'=',                  RESERVED),

    # Brackets
    (r'\(',                 RESERVED),
    (r'\)',                 RESERVED),
    (r'\{',                 RESERVED),
    (r'\}',                 RESERVED),

    # Types
    (r'boolean',            RESERVED),
    (r'money',              RESERVED),

    # Reserved words
    (r'form',               RESERVED),
    (r'if',                 RESERVED),

    # String literal
    (r'\"(\\.|[^"])*\"',    FIELD),

    # Identifier
    (r'([a-zA-Z])+',        ID),

    # Unidentifiable. Use only for debugging the lexer
    # (r'.*',                 "UNINDENTIFIED"),
]
