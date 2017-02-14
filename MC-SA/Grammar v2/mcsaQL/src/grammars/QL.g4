grammar Exp;

start : 'form' ID '{' statementContent '}';

statementContent
    : categorise*
    ;

categorise
    : STRING caseNewInput
    | NUMBER
    | WHITESPACE
    | COMMENT
    | 'if' OPEN_PARENTH ifCase CLOSE_PARENTH OPEN_BRACKET statementContent CLOSE_BRACKET
    ;

ifCase
    : ID
    | NUMBER
    | ifCase '>' ifCase
    | ifCase '<' ifCase
    | ifCase '<=' ifCase
    | ifCase '>=' ifCase
    | ifCase '==' ifCase
    | ifCase 'AND' ifCase
    | ifCase 'OR' ifCase
    ;

caseNewInput
    : ID ':' type
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