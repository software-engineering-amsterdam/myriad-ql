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
    :   BOOLEAN_LITERAL                                        #booleanLiteral
    |   STRING_LITERAL                                         #stringLiteral
    |   DECIMAL_LITERAL                                        #decimalLiteral
    |   INTEGER_LITERAL                                        #integerLiteral
    |   ID                                                     #parameter
    |   '(' expression ')'                                     #group
    |   '!' expression                                         #negation
    |   '++' expression                                        #decrement
    |   '--' expression                                        #increment
    |   <assoc=left> left=expression '/' right=expression      #division
    |   <assoc=left> left=expression '*' right=expression      #product
    |   left=expression '-' right=expression                   #subtraction
    |   left=expression '+' right=expression                   #addition
    |   left=expression '>' right=expression                   #greaterThan
    |   left=expression '<' right=expression                   #lowerThan
    |   left=expression '==' right=expression                  #equals
    |   left=expression '!=' right=expression                  #notEqual
    |   left=expression '<=' right=expression                  #lowerThanOrEqual
    |   left=expression '>=' right=expression                  #greaterThanOrEqual
    |   left=expression '&&' right=expression                  #logicalAnd
    |   left=expression '||' right=expression                  #logicalOr
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
