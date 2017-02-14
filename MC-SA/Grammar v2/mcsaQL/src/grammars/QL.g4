grammar QL;

start : 'form' ID '{' content '}';

content
    : categorise*
    ;

categorise
    : STRING caseNewInput
    | NUMBER
    | WHITESPACE
    | COMMENT
    | 'if' '(' ID ')' '{' content '}'
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
    :   mathaction op=('*'|'/') mathaction  // MulDiv
    |   mathaction op=('+'|'-') mathaction  // AddSub
    |   NUMBER                              // int
    |   ID                                  // id
    |   '(' mathaction ')'                  // parens
    ;

/*boolean
    : 'True'
    | 'False'
    | 'true'
    | 'false'
    ;
*/
ID:   [a-zA-Z$_]+ ;

STRING: '"' .*? '"';

NUMBER
    :    ('0'..'9')+ ('.' ('0'..'9')+)?
    ;

WHITESPACE
    :   (' ' | '\t' | '\r'| '\n') -> channel(HIDDEN)
    ;

COMMENT : '/*' .*? '*/' -> channel(HIDDEN);