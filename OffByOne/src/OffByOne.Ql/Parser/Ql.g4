grammar Ql;

form : 'form' Identifier '{' stat+ '}' ;
question : StringLiteral Identifier ':' Type ;
stat
	: question
	| ifStat
	;

ifStat  
	: 'if' '(' expression ')' '{' stat+ '}' elseStat
	| 'if' '(' expression ')' '{' stat+ '}'
	;

elseStat : 'else' '{' elseStats=stat+ '}';

expression
	: literal									# ExpressionLiteral
	| Identifier								# ExpressionIdentifier
	| '(' expression ')'						# ExpressionBracket
	| expression BinaryOperator expression		# ExpressionBinary
	| UnaryOperator expression					# ExpressionUnary
	;

BinaryOperator
	: '*' | '/'
	| '+' | '-'
	| '>'|'<'|'=='|'<='|'>='
	| 'and' | 'or'
	;

UnaryOperator : 'not';

literal
	: DateLiteral	 # DateLiteral
	| BooleanLiteral # BooleanLiteral
	| Money			 # MoneyLiteral
	| Decimal		 # DecimalLiteral
	| SignedInt		 # IntegerLiteral
	| StringLiteral  # StringLiteral
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
