// Define a grammar called QLS
grammar QLS;

// This removes the warning that is thrown by antlr making classes CLSCompliant by default
@parser::header {#pragma warning disable 3021}
@lexer::header {#pragma warning disable 3021}

/**
 *	Lexer rules
 */
Whitespace: [ \t\r\n\u000C]+ -> skip;
MultiLineComment: '/*' .*? '*/' -> skip;
SingleLineComment: '//' ~('\r' | '\n')* -> skip;
HexDigit: ('0'..'9'|'a'..'f'|'A'..'F');

Property: 'width' | 'font' | 'fontsize' | 'color' ;
Type: 'money' | 'boolean' | 'string' | 'int';
Widget: 'spinbox' | 'slider' | 'text' | 'radio' | 'checkbox' | 'dropdown' ;
Identifier: ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

ColorLiteral: '#' HexDigit HexDigit HexDigit HexDigit HexDigit HexDigit;
StringLiteral: '"' (~'"')* '"';
NumberLiteral: ('0'..'9')+;

/**
 * Parser rules
 */
stylesheet: 'stylesheet' Identifier '{' page* '}';
page: 'page' Identifier '{' (section | defaultStyle)* '}';
section: 'section' StringLiteral '{' (question | defaultStyle | section)* '}';
widget: 'widget' Widget;
question: 'question' Identifier ('{' widget '}')?;
defaultStyle: 'default' Type '{' setting* widget '}';
setting: Property ':' (StringLiteral | NumberLiteral | ColorLiteral);