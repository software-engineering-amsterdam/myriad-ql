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
question: StringLiteral Identifier TypeDeclarator Type (AssignmentOperator expression | /* nothing */);
conditionalBlock: IfStatement LeftParenthesis expression RightParenthesis statement;
expression: TypeDeclarator TypeDeclarator TypeDeclarator TypeDeclarator;
