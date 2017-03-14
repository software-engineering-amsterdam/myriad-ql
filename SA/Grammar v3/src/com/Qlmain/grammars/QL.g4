grammar QL;

formDeclaration : 'form' ID OPEN_BRACKET statement+ CLOSE_BRACKET;

statement
    : question
    | ifStatement
    ;

ifStatement
    : 'if' OPEN_PARENTH expression CLOSE_PARENTH OPEN_BRACKET statement+ CLOSE_BRACKET
    ;

question
    : STRING questionParameters
    ;

/*ifCase
    : expresion
    |  ifCase TOKEN ifCase
    ;

ifCaseArgs
    : ID
    | NUMBER
    ;*/

questionParameters
    : ID':' type
    ;

type
    : 'boolean'
    | 'integer'
    | 'string'
    | 'money' ('=' expression)?
    ;

expression
    :   expression op = ( '*' | '/' )                       expression    # MulDiv
    |   expression op = ( '+' | '-' )                       expression    # AddSub
    |   expression op = ( 'AND' | 'OR' )                    expression    # AndOr
    |   expression op = ( '<' | '>' | '>=' | '<=' | '==' )  expression    # GreaterSmallerEqqual
    |   BOOLEAN                                                           # bool
    |   NUMBER                                                            # number
    |   ID                                                                # id
    |   '(' expression ')'                                                # parens
    ;

//lexer

/*TOKEN
    : '>'
    | '<'
    | '>='
    | '<='
    | '=='
    | 'AND'
    | 'OR'
    ;*/

BOOLEAN
    : 'true'
    | 'false'
    ;

ID:   [a-zA-Z$_]+ ;

STRING: '"' .*? '"';

NUMBER
    :    ('0'..'9')+ ('.' ('0'..'9')+)?
    ;

WHITESPACE
    :   (' ' | '\t' | '\r'| '\n') -> channel(HIDDEN)
    ;

COMMENT : '/*' .*? '*/' -> channel(HIDDEN);

OPEN_BRACKET :  '{' ;
CLOSE_BRACKET : '}' ;
OPEN_PARENTH :  '(' ;
CLOSE_PARENTH : ')' ;
