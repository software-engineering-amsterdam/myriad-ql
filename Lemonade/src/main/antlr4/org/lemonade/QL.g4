grammar QL;

form
    : FORMKEY identifier '{' statements '}' EOF
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
    : IFKEY '(' expr ')' '{' questions '}'
    ;

expr
    : 'true' | 'false'
    | INTEGER
    | unaryoperator expr
    | expr binaryoperator expr
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

FORMKEY
    : 'form'
    ;

IFKEY
    : 'if'
    ;

INTEGER
    : ('0'..'9')+
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