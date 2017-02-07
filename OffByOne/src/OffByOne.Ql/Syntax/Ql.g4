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
	| Date ('>'|'<'|'=='|'<='|'>=') Date
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

NumericLiteral : Money | Decimal | SignedInt;

SignedInt : '-'? Int+ ;
Money : SignedInt '.' Digit Digit ;
Decimal : SignedInt '.' [0-9]+ ;
Date : '\'' Digit Digit '-' Digit Digit '-' Digit Digit Digit Digit '\'' ;

fragment
Int: Digit | ([1-9] Digit*) ;

fragment
Digit: [0-9] ;

StringLiteral : '"' (Escaped | . )*? '"' ;

fragment
Escaped : '\\' [btnr"\\] ;

Identifier : [a-z][a-zA-Z]* ;

BlockComment : '/*' .*? '*/' -> skip;
LineComment : '//' .*? '\n'  -> skip;
WS : [ \t\r\n]+ -> skip ;
