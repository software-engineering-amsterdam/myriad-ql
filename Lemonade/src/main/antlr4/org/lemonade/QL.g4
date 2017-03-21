grammar QL;

form
    : 'form' identifier '{' body+ '}' EOF
    ;

body
    : question
    | conditional
    ;

question
    : identifier ':' label type_specifier '=' expr
    | identifier ':' label type_specifier
    ;

conditional
    : 'if' LPAREN expr RPAREN '{' body+ '}' 'else' '{' body+ '}'
    | 'if' LPAREN expr RPAREN '{' body+ '}'
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
    | date                          #dateAtom
    | STR                           #stringAtom
    | IDENT                         #identifierAtom
    | INT                           #integerAtom
    | DECIMAL                       #decimalAtom
    ;

date
    : DOUBLEDIGIT '/' DOUBLEDIGIT '/' QUADDIGIT
    ;

label
    : STR
    ;

identifier
    : IDENT                         #identifierValue
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

DOUBLEDIGIT
    : DIGIT DIGIT
    ;

QUADDIGIT
    : DIGIT DIGIT DIGIT DIGIT
    ;
INT
    : DIGIT+
    ;

DECIMAL
    : DIGIT+ '.' DIGIT+
    ;

IDENT
    : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

DIGIT
    : [0-9]
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