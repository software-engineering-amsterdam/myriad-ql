grammar QL;

form
    : 'form' identifier '{' block+ '}' EOF
    ;

block
    : question+
    | conditional+
    ;

question
    : identifier ':' label type_specifier
    ;

conditional
    : 'if' '(' expr ')' '{' question+ '}'
    ;

type_specifier
    : 'boolean'
    | 'string'
    | 'integer'
    | 'date'
    | 'decimal'
    | 'currency'
    ;

expr
    : unaryoperator expr
    | expr binaryoperator expr
    | BOOLEAN
    | INT
    | IDENT
    ;

label
    : STR
    ;

identifier
    : IDENT
    ;

unaryoperator
    : '-'
    | '!'
    ;

binaryoperator
    : '*'
    | '/'
    | '+'
    | '-'
    | '<'
    | '<='
    | '>'
    | '>='
    | '=='
    | '!='
    | '&&'
    | '||'
    ;

IDENT
    : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT
    : ('0'..'9')+
    ;

BOOLEAN
    : 'true' | 'false'
    ;

STR
    : '"' .*? '"'
    ;

COMMENT
    : '/*' .*? '*/'  -> channel(HIDDEN)
    ;

WHITESPACE
    : [ \t\r\n]+ -> channel(HIDDEN)
    ;