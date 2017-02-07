grammar QL;

form
    : FORMKEY identifier LBRACE formbod RBRACE EOF
    ;

formbod
    : questions
    | questions conditional
    ;
    
questions
    : question questions
    | question
    ;


question
    : identifier COLON label type_specifier
    ;

expr
    : BOOLEAN
    | INT
    | unaryoperator expr
    | expr binaryoperator expr
    ;

conditional
    : CONDKEY LPAREN expr RPAREN LBRACE questions RBRACE
    ;

label
    : STR
    ;

identifier
    : IDENT
    ;

type_specifier
    : BOOL
    | STRING
    | INTEGER
    | DATE
    | DECIMAL
    | CURRENCY
    ;

unaryoperator
    : BANG
    ;

binaryoperator
    : AND
    | OR
    | GT
    | LT
    | LE
    | GE
    | NOT_EQUAL
    | EQUAL_EQUAL
    | PLUS
    | MINUS
    | PRODUCT
    | DIVIDE
    ;

//Operators
PLUS
    : '+'
    ;
MINUS
    : '-'
    ;
PRODUCT
    : '*'
    ;
DIVIDE
    : '/'
    ;
AND
    : '&&'
    ;
OR
    : '||'
    ;
BANG
    : '!'
    ;
LT
    : '<'
    ;
GT
    : '>'
    ;
LE
    : '<='
    ;
GE
    : '>='
    ;
NOT_EQUAL
    : '!='
    ;
EQUAL_EQUAL
    : '=='
    ;

// Other Tokens

BOOLEAN
    : 'True' | 'False'
    ;

BOOL
    : 'boolean'
    ;
STRING
    : 'string'
    ;
INTEGER
    : 'integer'
    ;
DATE
    : 'date'
    ;
DECIMAL
    : 'decimal'
    ;
CURRENCY
    : 'currency'
    ;

COLON
    : ':'
    ;

LBRACE
    : '{'
    ;

RBRACE
    : '}'
    ;

LPAREN
    : '('
    ;

RPAREN
    : ')'
    ;

CONDKEY
    : 'if'
    ;

FORMKEY
    : 'form'
    ;

IDENT
    : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT
    : ('0'..'9')+
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