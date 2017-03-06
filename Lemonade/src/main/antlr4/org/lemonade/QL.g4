grammar QL;

form
    : 'form' identifier '{' body+ '}' EOF
    ;

body
    : question
    | conditional
    ;

question
    : identifier ':' label type_specifier
    ;

conditional
    : 'if' LPAREN expr RPAREN '{' body+ '}'
    ;

type_specifier
    : 'boolean'                     #booleanType
    | 'string'                      #stringType
    | 'integer'                     #integerType
    | 'date'                        #dateType
    | 'decimal'                     #decimalType
    | 'money'                       #moneyType
    ;

expr
 : LPAREN expr RPAREN                   #parenExpr
 | MINUS expr                           #unaryMinusExpr
 | BANG expr                            #unaryBangExpr
 | expr op=(PRODUCT | DIVIDE) expr      #productDivideExpr
 | expr op=(PLUS | MINUS) expr          #plusMinExpr
 | expr op=(LTEQ | GTEQ | LT | GT) expr #relationalExpr
 | expr op=(EQ | NEQ) expr              #equalityExpr
 | expr AND expr                        #andExpr
 | expr OR expr                         #orExpr
 | atom                                 #atomExpr
 ;

atom
    : BOOLEAN                       #booleanAtom
    | STR                           #stringAtom
    | IDENT                         #identifierAtom
    | INT                           #integerAtom
    | DECIMAL                       #decimalAtom
    ;

label
    : STR
    ;

identifier
    : IDENT
    ;

MINUS
    : '-'
    ;

BANG
    : '!'
    ;

PRODUCT
    : '*'
    ;

DIVIDE
    : '/'
    ;

PLUS
    : '+'
    ;

LTEQ
    : '<='
    ;

GTEQ
    : '>='
    ;

LT
    : '<'
    ;

GT
    : '>'
    ;

EQ
    : '=='
    ;

NEQ
    : '!='
    ;

AND
    : '&&'
    ;

OR
    : '||'
    ;

BOOLEAN
    : 'true' | 'false'
    ;

INT
    : ('0'..'9')+
    ;

DECIMAL
    : ('0'..'9')+ '.' ('0'..'9')+
    ;

IDENT
    : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

STR
    : '"' .*? '"'
    ;

LPAREN
    : '('
    ;

RPAREN
    : ')'
    ;
COMMENT
    : '/*' .*? '*/'  -> channel(HIDDEN)
    ;

WHITESPACE
    : [ \t\r\n]+ -> channel(HIDDEN)
    ;