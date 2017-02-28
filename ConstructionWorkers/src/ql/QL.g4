grammar QL;

form
    : 'form' IDENTIFIER '{' statement* '}';

statement
    : type IDENTIFIER '(' STRING ')' ';'                    # simpleQuestion
    | type IDENTIFIER '(' STRING ')' ':' expression ';'     # computedQuestion
    | 'if' '(' expression ')' '{' statement* '}'            # ifStatement
    ;

type
    : 'boolean'     # boolType
    | 'string'      # stringType
    | 'integer'     # intType
    | 'money'       # moneyType
    ;

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

BOOL
    : 'true'
    | 'false'
    ;

STRING
    : '"' (ESC | ~["\\])* '"';

INT
    : [0-9]+;

MONEY
    : [0-9]+ '.' [0-9][0-9];

IDENTIFIER
    : [a-zA-Z]+;

LINE_COMMENT
    : '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN);

MULTI_LINE_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN);

WS
    : [ \r\t\u000C\n]+ -> channel(HIDDEN);

fragment ESC : '\\' (["\\/bfnrt] | UNICODE);
fragment UNICODE : 'u' HEX HEX HEX HEX;
fragment HEX : [0-9a-fA-F];