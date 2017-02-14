grammar QLParser;

import QLLexer;

form
    :   'form' id=identifier '{' statement* '}'
    ;

statement
    :   type id=identifier ':' text=questionText defaultValue? ';'                   #question
    |   'if' '(' expression ')' '{' statement* '}' ('else' '{' statement* '}')?      #if
    ;

questionText
    : STRING_LITERAL
    ;

defaultValue
    :   '=' value=expression
    ;

expression
    :   BOOLEAN_LITERAL                             #booleanLiteral
    |   STRING_LITERAL                              #stringLiteral
    |   FLOAT_LITERAL                               #floatLiteral
    |   INTEGER_LITERAL                             #integerLiteral
    |   ID                                          #parameter
    |   '(' expression ')'                          #grouped
    |   '!' expression                              #negation
    |   '++' expression                             #decrement
    |   '--' expression                             #increment
    |   <assoc=left> expression '/' expression      #division
    |   <assoc=left> expression '*' expression      #product
    |   expression '-' expression                   #subtraction
    |   expression '+' expression                   #addition
    |   expression '>' expression                   #greaterThan
    |   expression '<' expression                   #lowerThan
    |   expression '==' expression                  #equals
    |   expression '!=' expression                  #notEqual
    |   expression '<=' expression                  #lowerThanOrEqual
    |   expression '>=' expression                  #greaterThanOrEqual
    |   expression '&&' expression                  #logicalAnd
    |   expression '||' expression                  #logicalOr
    ;

identifier
    :   ID
    ;

type
    :   'boolean'   #typeBoolean
    |   'float'     #typeFloat
    |   'integer'   #typeInteger
    |   'string'    #typeString
    |   'money'     #typeMoney
    |   'date'      #typeDate
    ;
