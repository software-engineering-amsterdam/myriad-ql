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
LeftParenthesis		: '(';
RightParenthesis	: ')';
LeftBracket			: '{';
RightBracket		: '}';
TypeDeclarator		: ':';
IfStatement			: 'if';
ElseStatement		: 'else';
FormStatement		: 'form';
AssignmentOperator	: '=';

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
	| ('!' expression) #NOT
	| ('-' expression) #MINUS
	| expression op=( '/' | '*' ) expression #MulDiv
	| expression op=( '+' | '-' ) expression #AddSub		
	| expression op=('<' | '<=' | '>' | '>=' ) expression #Comparison
	| expression op=('!=' | '==' ) expression #Equality
	| expression ('&&' ) expression #And
	| expression ('||' ) expression #Or	
;