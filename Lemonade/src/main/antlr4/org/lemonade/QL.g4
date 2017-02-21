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
    : 'if' '(' expr ')' '{' body+ '}'
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
    : unaryoperator expr            #unaryExpression
    | expr binaryoperator expr      #binaryExpression
    | literal                       #literalExpression
    ;

literal
    : BOOLEAN                       #booleanLiteral
    | STR                           #stringLiteral
    | IDENT                         #identifierLiteral
    | INT                           #integerLiteral
    | DECIMAL                       #decimalLiteral
    ;

label
    : STR
    ;

identifier
    : IDENT
    ;

unaryoperator
    : '-'                           #negateUnary
    | '!'                           #bangUnary
    ;

binaryoperator
    : '*'                           #productBinary
    | '/'                           #divideBinary
    | '+'                           #plusBinary
    | '-'                           #minusBinary
    | '<'                           #lessThanBinary
    | '<='                          #lessThanEqualBinary
    | '>'                           #greaterThanBinary
    | '>='                          #greaterThanEqualBinary
    | '=='                          #equalBinary
    | '!='                          #notEqualBinary
    | '&&'                          #andBinary
    | '||'                          #orBinary
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

COMMENT
    : '/*' .*? '*/'  -> channel(HIDDEN)
    ;

WHITESPACE
    : [ \t\r\n]+ -> channel(HIDDEN)
    ;