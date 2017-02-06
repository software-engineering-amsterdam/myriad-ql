grammar QL;
options {backtrack=true; memoize=true;}
 
form
    : question* EOF
    ;

question
    : FORM LBRACE TYPE Ident RBRACE
    ;

// Tokens
WS  : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN)
    ;

TYPE
    : 'boolean'
    ;

LBRACE
    : '{'
    ;

RBRACE
    : '}'
    ;

COMMENT
    : '/*' .*? '*/'  -> channel(HIDDEN)
    ;

FORM
    : 'form'
    ;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int
    : ('0'..'9')+
    ;

Str
    : '"' .*? '"'
    ;