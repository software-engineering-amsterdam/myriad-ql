// Define a grammar called Grammar
grammar Grammar;

/**
 *	Lexer rules
 */
Whitespace: [ \t\r\n\u000C]+ -> channel(HIDDEN);
Comment: '/*' .*? '*/' -> skip;
SingleComment: '//' ~('\r' | '\n')* -> skip;

// Literal values in the code
StringLiteral: '"' (~'"')+ '"';
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
Identifier: ('a'..'z'|'A'..'Z'|'_'|'$') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'$')*;

/**
 * Parser rules
 */
unit: (form)*;
form: FormStatement Identifier statement;
statement: LeftBracket (question | conditionalBlock)* RightBracket;
question: StringLiteral Identifier TypeDeclarator Type (AssignmentOperator LeftParenthesis expression RightParenthesis | /* nothing */);
conditionalBlock: IfStatement LeftParenthesis expression RightParenthesis statement (ElseStatement statement | /* nothing */);
expression: 
	booleanExpression | comparisonExpression | arithmeticExpression |
	parenthesisExpression | StringLiteral | BooleanLiteral | NumberLiteral | Identifier;

// Boolean operators
booleanExpression: andExpression | orExpression | notExpression;
andExpression: expression '&&' expression;
orExpression: expression '||' expression;
notExpression: '!' expression;

// Comparison operators
comparisonExpression: lessThanExpression | lessThanExpression | lessThanOrEqualExpression 
 | greaterThanExpression | greaterThanOrEqualExpression | notEqualToExpression | equalToExpression;
lessThanExpression: expression '<' expression;
lessThanOrEqualExpression: expression '<=' expression;
greaterThanExpression: expression '>' expression;
greaterThanOrEqualExpression: expression '>=' expression;
notEqualToExpression: expression '!=' expression;
equalToExpression: expression '==' expression;

// Basic arithmetic
arithmeticExpression: additionExpression | subtractionExpression | multiplicationExpression | divisionExpression;
divisionExpression: expression '/' expression;
multiplicationExpression: expression '*' expression;
additionExpression: expression '+' expression;
subtractionExpression: expression '-' expression;

// Parenthesis
parenthesisExpression: '(' expression ')';