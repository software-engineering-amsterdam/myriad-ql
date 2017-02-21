grammar QL;

formDeclaration : 'form' ID OPEN_BRACKET statement+ CLOSE_BRACKET;

statement
    : question
    | ifStatement
    ;

ifStatement
    : 'if' ifCase OPEN_BRACKET statement+ CLOSE_BRACKET
    ;

question
    : STRING questionParameters
    ;

ifCase
    : ifCaseArgs
    | OPEN_PARENTH ifCaseArgs TOKEN ifCaseArgs CLOSE_PARENTH
    | OPEN_PARENTH ifCaseArgs TOKEN ifCaseArgs CLOSE_PARENTH
    | OPEN_PARENTH ifCaseArgs TOKEN ifCaseArgs CLOSE_PARENTH
    | OPEN_PARENTH ifCaseArgs TOKEN ifCaseArgs CLOSE_PARENTH
    | OPEN_PARENTH ifCaseArgs TOKEN ifCaseArgs CLOSE_PARENTH
    | OPEN_PARENTH ifCase TOKEN ifCase CLOSE_PARENTH
    | OPEN_PARENTH ifCase TOKEN ifCase CLOSE_PARENTH
    ;

ifCaseArgs
    : ID
    | NUMBER
    ;

questionParameters
    : ID':' type
    ;

type
    : 'boolean'
    | 'integer'
    | 'double'
    | 'float'
    | 'string'
    | 'money' ('=' mathaction)*
    ;

mathaction
    :   mathaction op = ( '*' | '/' ) mathaction  // MulDiv
    |   mathaction op = ( '+' | '-' ) mathaction  // AddSub
    |   NUMBER                              // int
    |   ID                                  // id
    |   '(' mathaction ')'                  // parens
    ;

//lexer

TOKEN
    : '>'
    | '<'
    | '>='
    | '<='
    | '=='
    | 'AND'
    | 'OR'
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
