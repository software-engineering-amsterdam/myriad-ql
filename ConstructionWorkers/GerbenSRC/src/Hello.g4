grammar QL;

/**
 * =====================
 * GENERAL DEFINITIONS
 * =====================
 */

// complete form - topmost node
form    : 'form' ID '{' statement* '}';

// statement - can be a question or an if statement
statement   : questionDeclaration
            | ifStatement
            ;

// an if statement
// supported form: if(expr){...}
ifStatement : 'if' '(' expression ')' '{' (statement)* '}';

// Question types
// two supported versions:
// 1. Question expecting user's answer.
// 2. Question (field) value of which is computed from other variables / values.
questionDeclaration : type ID '(' STRING ')' ';'                             # simpleQuestion
                    | type ID '(' STRING ')' '=' expression ';'              # computedQuestion
                    ;

// all alowed variable types.
type    : 'bool'        # boolType
        | 'int'         # intType
        | 'string'      # stringType
        ;
/**
 * =====================
 * Expressions
 * =====================
 */

// this defines what an expression looks like. (logical and numerical)
expression  : '(' expression ')'                                                    # parenthesisExpression
            | op=('+'|'-'|'!') expression                                           # unaryExpression
            | expression op=('*' | '/') expression                                  # mulDivExpression
            | expression op=('+' | '-') expression                                  # addSubExpression
            | expression op=('>' | '>=' | '<' | '<=' | '==' | '!=') expression      # comparisonExpression
            | expression '&&' expression                                            # logicalAndExpression
            | expression '||' expression                                            # logicalOrExpression
            | BOOL                                                                  # boolExpression
            | INT                                                                   # intExpression
            | STRING                                                                # stringExpression
            | ID                                                                    # identifierExpression
            ;

/**
 * =====================
 * LEXER RULES - literals
 * =====================
 */

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
ID  :   [a-zA-Z]+;
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