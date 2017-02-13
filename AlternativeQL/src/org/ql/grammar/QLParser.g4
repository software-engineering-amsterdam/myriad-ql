grammar QLParser;

import QLLexer;

form
    :   'form' id=identifier '{' declaration* '}'
    ;

declaration
    :   type id=identifier ':' STRING_LITERAL defaultValue?   #questionDeclaration
    |   statement                                             #statementDeclaration
    ;

statement
    :   'if' '(' expression ')' '{' declaration* '}' ('else' statement)?    #ifStatement
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
