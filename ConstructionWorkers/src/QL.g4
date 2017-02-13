grammar QL;

// Complete form (i.e., the topmost node).
form
    : 'form' Identifier '{' section* '}';

// Statement. Can be a 'title', a question, or an if-statement.
section
    : type Identifier '(' STRING ')' ';'                    # question
    | type Identifier '(' STRING ')' ':' expression ';'     # expressionQuestion
    | 'if' '(' expression ')' '{' (section)* '}'            # ifStatement
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
    : '(' expression ')'                                                # parenthesisExpression
    | op=('+'|'-'|'!') expression                                       # unaryExpression
    | expression op=('*' | '/') expression                              # multDivExpression
    | expression op=('+' | '-') expression                              # addSubExpression
    | expression op=('>' | '>=' | '<' | '<=' | '==' | '!=') expression  # equalityExpression
    | expression '&&' expression                                        # andExpression
    | expression '||' expression                                        # orExpression
    | BOOL                                                              # boolExpression
    | STRING                                                            # stringExpression
    | INT                                                               # intExpression
    | MONEY                                                             # moneyExpression
    | Identifier                                                        # identifierExpression
    ;

// Boolean definition.
BOOL
    : 'true'
    | 'false'
    ;

// String definition.
STRING
    : '"' (ESC | ~["\\])* '"';

// Integer definition.
INT
    : [0-9]+;

// Money defintion.
MONEY
    : [0-9]+ '.' [0-9][0-9];

// Identifier definition.
Identifier
    : [a-zA-Z]+;

// Single-line comment definition (matches anything after '//' until newline).
LINE_COMMENT
    : '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN);

// Multi-line comment definition (matches anything between '/*' and '*/').
MULTI_LINE_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN);

// Ignore whitespace.
WS
    : [ \r\t\u000C\n]+ -> channel(HIDDEN);

// Fragments.
fragment ESC : '\\' (["\\/bfnrt] | UNICODE);
fragment UNICODE : 'u' HEX HEX HEX HEX;
fragment HEX : [0-9a-fA-F];