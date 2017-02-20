lexer grammar QlLexer;

LBRACE : '{' ;
RBRACE : '}' ;
LPAREN : '(' ;
RPAREN : ')' ;
COLON : ':' ;

FORM : 'form' ;
IF : 'if' ;
ELSE : 'else' ;

OP_MUL : '*' ;
OP_DIV : '/' ;
OP_SUB : '-' ;
OP_ADD : '+' ;
OP_GTE : '>=';
OP_LTE : '<=';
OP_GT : '>' ;
OP_LT : '<' ;
OP_NEQ : '!=' ;
OP_EQ : '==' ;
OP_AND : 'and' ;
OP_OR : 'or' ;
OP_NOT : 'not' ;

Type 
	: 'boolean'
	| 'date'
	| 'decimal'
	| 'integer'
	| 'money'
	| 'string'
	;

BooleanLiteral
	: 'true'
	| 'false'
	;

DateLiteral : '\'' Digit Digit '-' Digit Digit '-' Digit Digit Digit Digit '\'' ;

MoneyLiteral : IntLiteral '.' Digit Digit ;
DecimalLiteral : IntLiteral '.' [0-9]+ ;

IntLiteral: Digit | ([1-9] Digit*) ;

fragment
Digit: [0-9] ;

StringLiteral : '"' (Escaped | . )*? '"' ;

fragment
Escaped : '\\' [btnr"\\] ;

Identifier : [a-z][a-zA-Z]* ;

BlockComment : '/*' .*? '*/' -> skip;
LineComment : '//' .*? '\n'  -> skip;
WS : [ \t\r\n]+ -> skip ;
