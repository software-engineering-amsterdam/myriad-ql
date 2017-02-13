grammar QLParser;

import QLLexer;

form
    :   'form' id=identifier '{' statement* '}'
    ;

statement
    :   type id=identifier ':' questionMsg=STRING_LITERAL defaultValue? ';'  #question
    |   'if' '(' expression ')' '{' statement* '}' ('else' statement)?       #if
    ;

defaultValue
    :   '=' value=expression
    ;

expression
    :   literal
    |   parameter
    |   '(' expression ')'
    |   '!' expression
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
    |   expression '&&' expression
    |   expression '||' expression
    ;

parameter
    :   ID
    ;

identifier
    :   ID
    ;

literal
    :   BOOLEAN_LITERAL     #booleanLiteral
    |   STRING_LITERAL      #stringLiteral
    |   FLOAT_LITERAL       #floatLiteral
    |   INTEGER_LITERAL     #integerLiteral
    ;

type
    :   'boolean'   #typeBoolean
    |   'float'     #typeFloat
    |   'integer'   #typeInteger
    |   'string'    #typeString
    |   'money'     #typeMoney
    |   'date'      #typeDate
    ;
