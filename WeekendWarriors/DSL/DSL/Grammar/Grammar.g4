// Define a grammar called Grammar
grammar Grammar;

/**
	Major flaw: Lexer is not doing shit in this file.

	Example QL file
	
	Every word/thing is followed by "| ..." where ... is replaced by a comment explaining
	what I think should be considered when parsing this.

	form										| 0..* 
	taxOfficeExample							| Identifier: Only 1, Allowed: a-zA-Z0-9_$
	{											| BracketLeft token
	  
												| This is a question
		"Did you sell a house in 2010?"			| StringLiteral value, ?escape and unicode?
	    hasSoldHouse							| Identifier: Only 1, Allowed: a-zA-Z0-9_$
		:										| TypeDeclaration token
		boolean									| TypeDefinition (boolean, int, string)
	  
		"Did you buy a house in 2010?" 
		hasBoughtHouse: boolean
		"Did you enter a loan?" 
		hasMaintLoan: boolean
	
		if										| If token
		(										| Left parenthesis token
		hasSoldHouse							| expression
		)										| Right parenthesis token
		{										| Left bracket token 
	    "What was the selling price?"
	      sellingPrice: money
	    "Private debts for the sold house:"
	      privateDebt: money
	    "Value residue:"
	      valueResidue: money 
		  =										| Assignment operator token
	        (sellingPrice - privateDebt)		| expression
	  }											| BracketRight token
	}											| BracketRight token
 */

// A QL file contains 0 or multiple forms
unitthing: Unit;
Unit
	:
		( Form )*
	;

Form
	: 
		FormLiteral Identifier Statement
	;

// Definition for a question with a variable bound to it

// Definition for a conditional question block
ConditionalQuestions
	:
		IfStatement LeftParenthesis Expression RightParenthesis Statement
	;

// Definition for a statement which contains questions or conditionals
Statement
	:
		LeftBracket (QuestionWithVariable | ConditionalQuestions)* RightBracket
	;

// Definition for a question with variable
QuestionWithVariable
	:
		StringLiteral Identifier Declarator TypeDefinition (AssignmentOperator Expression | /* nothing */)
	;

/*	Definition for an expression
	An expression is any combination of literals/variables/operators that resolves to a value
 */
Expression
	:
		Statement //\todo: this should not be constant
	;

// Literals
Constant: StringLiteral | NumberLiteral /* Currently no need*/ | BooleanLiteral /* Currently no need */;

fragment StringLiteral: '\"' ( ~('\"'|'\\') | EscapeSequence)* '\"';
fragment NumberLiteral: '1'..'9' ('0'..'9')*;
fragment BooleanLiteral: 'true' | 'false';

EscapeSequence
	:	
		'\\' 'b'
		| '\\' 't'
		| '\\' 'n'
		| '\\' 'f'
		| '\\' 'r'
		| '\\' '\"'
		| '\\' '\''
		| '\\' '\\'
		| '\\' '0'..'3' OctalDigit OctalDigit
		| '\\' OctalDigit OctalDigit
		| '\\' OctalDigit
		| UnicodeChar
	;

fragment UnicodeChar: '\\' 'u' HexDigit HexDigit HexDigit HexDigit;
fragment HexDigit: '0'..'9'|'a'..'f'|'A'..'F';
fragment OctalDigit: '0'..'7';

// Tokens
fragment LeftParenthesis: '(';
fragment RightParenthesis: ')';
fragment LeftBracket: '{';
fragment RightBracket: '}';
fragment Declarator: ':';
fragment IfStatement: 'if';
fragment FormLiteral: 'form';
fragment AssignmentOperator: '=';

// Conditional literals
fragment Identifier: ('a'..'z'|'A'..'Z'|'_'|'$') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'$')*;
fragment TypeDefinition: 'boolean' | 'int' | 'string';
