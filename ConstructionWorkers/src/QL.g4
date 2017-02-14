grammar QL;

// Complete form (i.e., the topmost node).
form
    : 'form' IDENTIFIER '{' statement* '}';

// Statement. Can be a simple question, a computed question, or an if-statement.
statement
    : type IDENTIFIER '(' STRING ')' ';'                    # simpleQuestion
    | type IDENTIFIER '(' STRING ')' ':' expression ';'     # computedQuestion
    | 'if' '(' expression ')' '{' statement* '}'            # ifStatement
    ;

// All alowed types.
type
    : 'boolean'     # boolType
    | 'string'      # stringType
    | 'int'         # intType
    | 'money'       # moneyType
    ;

// Expressions (logical and numerical).
expression
    : '(' expression ')'                                                # parenthesesExpression
    | op=('+'|'-'|'!') expression                                       # unaryExpression
    | expression op=('*' | '/') expression                              # multDivExpression
    | expression op=('+' | '-') expression                              # addSubExpression
    | expression op=('>' | '>=' | '<' | '<=' | '==' | '!=') expression  # comparisonExpression
    | expression '&&' expression                                        # andExpression
    | expression '||' expression                                        # orExpression
    | BOOL                                                              # boolExpression
    | STRING                                                            # stringExpression
    | INT                                                               # intExpression
    | MONEY                                                             # moneyExpression
    | IDENTIFIER                                                        # identifierExpression
    ;

// Boolean definition.
BOOL
    : 'true'
    | 'false'
    ;

// String definition (i.e., a question).
STRING
    : '"' (ESC | ~["\\])* '"';

// MyInteger definition.
INT
    : [0-9]+;

// Money defintion.
MONEY
    : [0-9]+ '.' [0-9][0-9];

// Identifier definition.
IDENTIFIER
    : [a-zA-Z]+;

// Single-line comment definition (matches anything after '//' until newline).
LINE_COMMENT
    : '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN);

// Multi-line comment definition (matches anything between '/*' and '*/').
MULTI_LINE_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN);

// Whitespace defintion.
WS
    : [ \r\t\u000C\n]+ -> channel(HIDDEN);

// Fragments.
fragment ESC : '\\' (["\\/bfnrt] | UNICODE);
fragment UNICODE : 'u' HEX HEX HEX HEX;
fragment HEX : [0-9a-fA-F];