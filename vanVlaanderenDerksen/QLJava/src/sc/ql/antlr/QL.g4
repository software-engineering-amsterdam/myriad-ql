grammar QL;

@header {package sc.ql.antlr;}

form
	: 'form' formElement+ 'endform' EOF
	;

formElement																			
	: label=STRING_LITERAL id=ID_LITERAL type ('=' expression)? 	#calculatedQuestion
	| label=STRING_LITERAL id=ID_LITERAL type						#question
	| 'if' '('expression')' ('else' formElement+)? 'endif' 			#ifStatement
	;

expression
	: '(' expression ')' 						#parenthesis
	| '!' expression							#notExpression
	| left=expression '*' right=expression		#multiply
	| left=expression '/' right=expression		#divide
	| left=expression '+' right=expression		#add
	| left=expression '-' right=expression		#substract
	| left=expression '<' right=expression 		#lessThen
	| left=expression '<=' right=expression 	#lessThenEqual
	| left=expression '>' right=expression 		#greaterThen
	| left=expression '>=' right=expression 	#greaterThenEqual
	| left=expression '==' right=expression 	#equals
	| left=expression '!=' right=expression 	#equalsNot
	| left=expression '&&' right=expression		#logicalAnd
	| left=expression '||' right=expression		#logicalOr
	| atom=BOOLEAN_LITERAL						#booleanLiteral
	| atom=INTEGER_LITERAL						#integerLiteral
	| atom=ID_LITERAL							#idLiteral
	| atom=STRING_LITERAL						#stringLiteral
	;

type
	: 'BOOLEAN'		#booleanType
	| 'INTEGER' 	#integerType
	| 'MONEY'		#moneyType
	| 'STRING'		#stringType
	;

BOOLEAN_LITERAL
	: ('true'|'false')
	;

INTEGER_LITERAL
	: ('0'..'9')+
	;

ID_LITERAL
	: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
	;

STRING_LITERAL
	: '"' ('\\"'|.)*? '"'
	;

WS
	: [ \t\n\r]+ -> skip
    ;

COMMENT
    : '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    : '//' .*?[\r\n] -> skip
    ;