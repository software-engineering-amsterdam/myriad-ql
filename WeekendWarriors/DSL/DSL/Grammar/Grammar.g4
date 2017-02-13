// Define a grammar called Grammar
grammar Grammar;

/**
 *	Lexer rules
 */
Whitespace: [ \t\r\n\u000C]+ -> skip;
MultiLineComment: '/*' .*? '*/' -> skip;
SingleLineComment: '//' ~('\r' | '\n')* -> skip;

// Literal values in the code
StringLiteral: '"' (~'"')* '"';
BooleanLiteral: 'true' | 'false';
NumberLiteral: '1'..'9' ('0'..'9')*;

// Tokens
LeftParenthesis				: '(';
RightParenthesis			: ')';
LeftBracket					: '{';
RightBracket				: '}';
TypeDeclarator				: ':';
IfStatement					: 'if';
ElseStatement				: 'else';
FormStatement				: 'form';
AssignmentOperator			: '=';
NotOperator					: '!';
MinusOperator				: '-';
DivisionOperator			: '/';
MultiplyOperator			: '*';
AdditionOperator			: '+';
SubtractionOperator			: '-';
LessThanOperator			: '<';
LessThanOrEqualsOperator	: '<=';
GreaterThanOperator			: '>';
GreaterThanOrEqualsOperator	: '>=';
NotEqualOperator			: '!=';
EqualsOperator				: '==';
AndOperator					: '&&';
OrOperator					: '||';

// Allowed: a-zA-Z_$ as first char followed by a-zA-Z0-9_$ -> unspecified if correct
Type: 'boolean' | 'int' | 'string';
Identifier: ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

/**
 * Parser rules
 */
form: FormStatement Identifier statement;
statement: LeftBracket (question | conditionalBlock)* RightBracket;
question: StringLiteral Identifier TypeDeclarator Type (AssignmentOperator LeftParenthesis expression RightParenthesis | /* nothing */);
conditionalBlock: IfStatement LeftParenthesis expression RightParenthesis statement (ElseStatement statement | /* nothing */);

/* Precedence based on C(++) precedence: http://en.cppreference.com/w/cpp/language/operator_precedence */
expression: 
	StringLiteral #String
	| BooleanLiteral #Bool
	| NumberLiteral #Number
	| Identifier #ID	
	| LeftParenthesis expression RightParenthesis #Parens
	| NotOperator expression #NOT
	| SubtractionOperator expression #MINUS
	| expression op=(DivisionOperator | MultiplyOperator) expression #MulDiv
	| expression op=(AdditionOperator | SubtractionOperator) expression #AddSub		
	| expression op=(LessThanOperator | LessThanOrEqualsOperator | GreaterThanOperator | GreaterThanOrEqualsOperator) expression #Comparison
	| expression op=(NotEqualOperator | EqualsOperator) expression #Equality
	| expression AndOperator expression #And
	| expression OrOperator expression #Or	
;