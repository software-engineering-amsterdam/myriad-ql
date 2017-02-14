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
    : unaryoperator expr
    | expr binaryoperator expr
    | BOOLEAN
    | INTEGER
    ;

label
    : STRING
    ;

identifier
    : IDENTIFIER
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


INTEGER
    : ('0'..'9')+
    ;

BOOLEAN
    : 'true' | 'false'
    ;

IDENTIFIER
    : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

STRING
    : '"' .*? '"'
    ;

COMMENT
    : '/*' .*? '*/'  -> channel(HIDDEN)
    ;

WHITESPACE
    : [ \t\r\n]+ -> channel(HIDDEN)
    ;