grammar Ql;

form : 'form' Identifier '{' stat+ '}' ;
question : StringLiteral Identifier ':' + Type ;
stat
	: question
	| if
	;

if : 'if' booleanExpression '{' stat+ '}' else? ;
else : 'else' (if | '{' stat+ '}') ;

booleanExpression
	: BooleanLiteral
	| Identifier
	| '(' booleanExpression ')'
	| 'not' booleanExpression
	| booleanExpression ('and'|'or') booleanExpression
	| numericExpression ('>'|'<'|'=='|'<='|'>=') numericExpression
	| dateExpression ('>'|'<'|'=='|'<='|'>=') dateExpression
	;

numericExpression
	: NumericLiteral
	| Identifier
	| '(' numericExpression ')'
	| numericExpression ('*'|'/') numericExpression
	| numericExpression ('+'|'-') numericExpression
	;

dateExpression
	: DateLiteral
	| Identifier
	;

Type 
	: 'boolean'
	| 'string'
	| 'integer'
	| 'date'
	| 'decimal'
	| 'money'
	;

BooleanLiteral
	: 'true'
	| 'false'
	;

NumericLiteral 
	: Money 
	| Decimal 
	| SignedInt
	;

DateLiteral : '\'' Digit Digit '-' Digit Digit '-' Digit Digit Digit Digit '\'' ;

SignedInt : '-'? Int+ ;
Money : SignedInt '.' Digit Digit ;
Decimal : SignedInt '.' [0-9]+ ;

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
