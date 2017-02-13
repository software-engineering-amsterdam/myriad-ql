grammar QLParser;

import QLLexer;

form
    :   FORM ID OPEN_BRACKET (declaration)* CLOSE_BRACKET
    ;

declaration
    :   question SEMICOLON
    |   statement
    ;

question
    :   type ID COLON STRING_LITERAL defaulvalue?
    |   type ASSIGN expression COLON STRING_LITERAL defaulvalue?
    ;

statement
    :   IF OPEN_PARENT expression CLOSE_PARENT OPEN_BRACKET declaration* CLOSE_BRACKET (ELSE statement)?
    ;

defaulvalue
    :   ASSIGN expression
    ;

expression
    :   literal
    |   parameter
    |   OPEN_PARENT expression CLOSE_PARENT
    |   <assoc=right> BANG expression
    |   <assoc=left> expression DIV expression
    |   <assoc=left> expression MUL expression
    |   expression SUB expression
    |   expression ADD expression
    |   expression GT expression
    |   expression LT expression
    |   expression EQUAL expression
    |   expression NOTEQUAL expression
    |   expression LE expression
    |   expression GE expression
    |   expression AND expression
    |   expression OR expression
    ;

parameter
    :   ID
    ;

literal
    :   BOOLEAN_LITERAL
    |   STRING_LITERAL
    |   FLOAT_LITERAL
    |   INTEGER_LITERAL
    ;

type
    :   BOOLEAN
    |   FLOAT
    |   INTEGER
    |   STRING
    |   MONEY
    |   DATE
    ;
