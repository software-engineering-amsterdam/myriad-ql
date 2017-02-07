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
    :   type ID COLON STRING defaulvalue?
    |   type ASSIGN expression COLON STRING defaulvalue?
    ;

statement
    :   IF OPEN_PARENT expression CLOSE_PARENT OPEN_BRACKET declaration* CLOSE_BRACKET (ELSE statement)?
    ;

defaulvalue
    :   ASSIGN expression
    ;

expression
    :   boolean_literal
    |   string_literal
    |   float_literal
    |   integer_literal
    |   parameter
    |   OPEN_PARENT expression CLOSE_PARENT
    |   <assoc=right> '!' expression
    |   <assoc=left> expression '/' expression
    |   <assoc=left> expression '*' expression
    |   expression '-' expression
    |   expression '+' expression
    |   expression '>' expression
    |   expression '<' expression
    |   expression '==' expression
    |   expression '!=' expression
    |   expression '<=' expression
    |   expression '>=' expression
    |   expression '<=' expression
    |   expression '>=' expression
    |   expression ('&&'|'||') expression
    ;

parameter
    :   ID
    ;

boolean_literal
    :   BOOLEAN
    ;

string_literal
    :   STRING
    ;

float_literal
    :   FLOAT
    ;

integer_literal
    :   INTEGER
    ;

type
    :   'boolean'
    |   'float'
    |   'money'
    |   'ID'
    |   'integer'
    |   'date'
    ;
