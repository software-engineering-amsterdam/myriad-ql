grammar QL;

// complete form - topmost node
form    : 'form' Identifier '{' section* '}';

// statement - can be a question or an if statement
section   : type Identifier '(' STRING ')' ';'                             # normalQuestion
          | type Identifier '(' STRING ')' ':' expression ';'              # expressionQuestion
          | 'if' '(' expression ')' '{' (section)* '}'                     # ifStatement
          ;

// all alowed variable types.
type    : 'boolean'     # boolType
        | 'int'         # intType
        | 'string'      # stringType
        ;

// this defines what an expression looks like. (logical and numerical)
expression  : '(' expression ')'                                                    # parenthesisExpression
            | op=('+'|'-'|'!') expression                                           # unaryExpression
            | expression op=('*' | '/') expression                                  # multDivExpression
            | expression op=('+' | '-') expression                                  # addSubExpression
            | expression op=('>' | '>=' | '<' | '<=' | '==' | '!=') expression      # equalityExpression
            | expression '&&' expression                                            # andExpression
            | expression '||' expression                                            # orExpression
            | BOOL                                                                  # boolExpression
            | INT                                                                   # intExpression
            | STRING                                                                # stringExpression
            | Identifier                                                            # identifierExpression
            ;

// boolean value definition
BOOL   : 'true'
       | 'false'
       ;

// string definition
STRING :  '"' (ESC | ~["\\])* '"' ;
// integer definition
// integer is a sequence of digits of a length that can vary
INT : DIGIT+ ;
// identifier definition
// user to identify variable names
Identifier  :   [a-zA-Z]+;
// comment matches anything between /* and */
COMMENT
    :   '/*' .*? '*/'    -> channel(HIDDEN)
    ;
// ignore whitespaces
WS  :   [ \r\t\u000C\n]+ -> channel(HIDDEN)
    ;
// line comment matches anything after // until newline
LINE_COMMENT
    : '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN)
    ;

// Fragments
fragment ESC :   '\\' (["\\/bfnrt] | UNICODE) ;
fragment UNICODE : 'u' HEX HEX HEX HEX ;
fragment HEX : [0-9a-fA-F] ;
fragment DIGIT   : [0-9] ; // match single digit