grammar QL;

form
    : FORM LBRACE questions RBRACE EOF
    ;

questions
    : question questions
    | question
    ;

question
    : identifier COLON label type_specifier
    ;


label
    : Str
    ;

identifier
    : Ident
    ;

type_specifier
    : BOOL
    | STRING
    | INTEGER
    | DATE
    | DECIMAL
    | CURRENCY
    ;

boolean
    : AND
    | OR
    | BANG
    ;

comparisons
    : GT
    | LT
    | LE
    | GE
    | NOT_EQUAL
    | EQUAL_EQUAL
    ;

//Operators
PLUS : '+';
MINUS : '-';
PRODUCT : '*';
DIVIDE : '/';

// Tokens

BOOL : 'boolean' ;
STRING : 'string' ;
INTEGER : 'integer' ;
DATE : 'date' ;
DECIMAL : 'decimal' ;
CURRENCY : 'currency' ;
AND : '&&' ;
OR : '||' ;
BANG : '!' ;
LT : '<' ;
GT : '>' ;
LE : '<=' ;
GE : '>=' ;
NOT_EQUAL : '!=' ;
EQUAL_EQUAL : '==' ;

COLON
    : ':'
    ;

LBRACE
    : '{'
    ;

RBRACE
    : '}'
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

COMMENT
    : '/*' .*? '*/'  -> channel(HIDDEN)
    ;
