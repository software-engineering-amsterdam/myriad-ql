grammar Ql;

form : 'form' Identifier '{' (question|if)+ '}' ;
question : StringLiteral + Identifier + ':' + Type ;

if : 'if' booleanExpression '{' question+ '}' else? ;
else : 'else' (if | '{' question+ '}') ;

booleanExpression
	: 'true'
	| 'false'
	| Identifier
	| 'not' booleanExpression
	| booleanExpression ('and'|'or') booleanExpression
	| numericExpression ('>'|'<'|'=='|'<='|'>=') numericExpression
	;

numericExpression
	: NumericLiteral
	| Identifier
	| numericExpression ('*'|'/') numericExpression
	| numericExpression ('+'|'-') numericExpression
	;

Type 
	: 'boolean'
	| 'string'
	| 'integer'
	| 'date'
	| 'decimal'
	| 'money'
	;
NumericLiteral: '-'? (Int+ | (Int+ '.' Int+)) ;

fragment
Int: [0-9] | ([1-9][0-9]*) ;

StringLiteral : '"' (Escaped | . )*? '"';

fragment
Escaped : '\\' [btnr"\\] ;

Identifier : [a-z][a-zA-Z]* ;

BlockComment : '/*' .*? '*/' -> skip;
LineComment : '//' .*? '\n'  -> skip;
WS : [ \t\r\n]+ -> skip ;
