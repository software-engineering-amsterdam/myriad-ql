grammar QL;

form
    : 'form' identifier '{' statements '}' EOF
    ;

statements
    : statements statement
    | statement
    ;

statement
    : questions
    | conditionals
    ;

questions
    : question questions
    | question
    ;

question
    : identifier ':' label type_specifier
    ;

conditionals
    : conditional conditionals
    | conditional
    ;

conditional
    : 'if' '(' expr ')' '{' questions '}'
    ;

expr
    : BOOLEAN
    | INT
    | unaryoperator expr
    | expr binaryoperator expr
    ;

label
    : STR
    ;

identifier
    : IDENT
    ;

type_specifier
    : 'boolean'
    | 'string'
    | 'integer'
    | 'date'
    | 'decimal'
    | 'currency'
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

WS
    : [ \t\r\n]+ -> channel(HIDDEN)
    ;