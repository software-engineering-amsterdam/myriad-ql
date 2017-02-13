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
NumberLiteral: ('0'..'9')+;
MoneyLiteral: NumberLiteral '.' NumberLiteral;

// Allowed: a-zA-Z_$ as first char followed by a-zA-Z0-9_$ -> unspecified if correct
Type: 'boolean' | 'int' | 'string' | 'money' ;
Identifier: ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

/**
 * Parser rules
 */
form: 'form' Identifier '{' statement* '}';
statement: question | computedQuestion | conditionalBlock;
computedQuestion: question '(' expression ')';
question: Identifier ':' StringLiteral Type;
conditionalBlock: 'if' '(' condition ')'  '{' thenBlock '}' ('else'  '{' elseBlock '}')?;
condition: expression; 
thenBlock: statement*;
elseBlock: statement*;

/* Precedence based on C(++) precedence: http://en.cppreference.com/w/cpp/language/operator_precedence */
expression: 
	StringLiteral #String
	| BooleanLiteral #Bool
	| NumberLiteral #Number
	| Identifier #ID	
	| MoneyLiteral #Money
	| op='(' expression ')' #Parens
	| op=('!' | '-' | '+') expression #Unary
	| expression op=('/' | '*') expression #MulDiv
	| expression op=('+' | '-') expression #AddSub		
	| expression op=('<' | '<=' | '>' | '>=') expression #Comparison
	| expression op=('!=' | '==') expression #Equality
	| expression op='&&' expression #And
	| expression op='||' expression #Or	
;