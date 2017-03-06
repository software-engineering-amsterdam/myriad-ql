// Define a grammar called QLS
grammar QLS;

// This removes the warning that is thrown by antlr making classes CLSCompliant by default
//@parser::header {#pragma warning disable 3021}
//@lexer::header {#pragma warning disable 3021}

/**
 Proposed grammar
 -> Use brackets everywhere, fk spaces/tabs
 -> Radio does not take arguments
 -> CSS for widgets is ; seperated (e.g. width: 100; font-size: 14;)

stylesheet Identifier
 - Special: is root element, max 1 per input
 - Parent: None
 - Children: Only page elements can be children

page Identifier
 - Parent: stylesheet
 - Children: Only sections can be children
 - Nope, also: default

section Identifier
 - Parent: page or section
 - Children: question(s), default(s), section(s)

question Identifier
 - Parent: section
 - Children: Optionally a widget

widget Type 
 - Parent: question
 - Children: none

default Type
 - Parent: section
 - Children: any cssItem and 1 widget at the end

cssItem
 - Special: uses format   property: value;
 - Parent: default
 - Children: none

 */
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