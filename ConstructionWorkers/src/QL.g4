grammar QL;

// Parser rules.

form
    :   'form' IDENTIFIER '{' statement* '}';

statement
    :   questionStatement
    |   ifStatement
    ;

questionStatement
    :   QUESTION IDENTIFIER ':' type
    |   QUESTION IDENTIFIER ':' type '=' expression
    ;

ifStatement
    :   'if' '(' expression ')' '{' statement* '}';

expression
    :   '(' expression ')'                                                  # parenthesisExpression
    |   op=('+'|'-'|'!') expression                                         # unaryExpression
    |   expression op=('*' | '/') expression                                # mulDivExpression
    |   expression op=('+' | '-') expression                                # addSubExpression
    |   expression op=('>' | '>=' | '<' | '<=' | '==' | '!=') expression    # comparisonExpression
    |   expression '&&' expression                                          # logicalAndExpression
    |   expression '||' expression                                          # logicalOrExpression
    |   BOOL                                                                # boolExpression
    |   INT                                                                 # intExpression
//    |   STRING                                                              # stringExpression
    |   IDENTIFIER                                                          # identifierExpression
    ;

type
    :   'boolean'
    |   'int'
    |   'string'
    ;

// Lexer rules.

BOOL
    :   'true'
    |   'false'
    ;

IDENTIFIER
    :   [a-zA-Z]+;

INT
    :   [0-9]+;

QUESTION
    :   '"'[ a-zA-Z0-9]+('?' | ':')+'"';

WS
    :   [ \r\t\u000C\n]+ -> skip;