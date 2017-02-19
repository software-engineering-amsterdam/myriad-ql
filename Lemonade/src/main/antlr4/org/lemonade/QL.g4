grammar QL;

form
    : 'form' identifier '{' block+ '}' EOF
    ;

block
    : question
    | conditional
    ;

question
    : identifier ':' label type_specifier
    ;

conditional
    : 'if' '(' expr ')' '{' block+ '}'
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

IDENT
    : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT
    : ('0'..'9')+
    ;

DECIMAL
    : ('0'..'9')+ '.' ('0'..'9')+
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