// Define a grammar called QLS
grammar QLS;

// This removes the warning that is thrown by antlr making classes CLSCompliant by default
@parser::header {#pragma warning disable 3021}
@lexer::header {#pragma warning disable 3021}

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

LiteralValue: 'money' | 'boolean' | 'string' | 'int';
Type: 'spinbox' | 'slider' | 'text' | 'radio' | 'checkbox' | 'dropdown' ;
Identifier: ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
Property: 'width' | 'font' | 'fontsize' | 'color' ;
/**
 * Parser rules
 */
stylesheet: 'stylesheet' Identifier '{' page* '}';
page: 'page' Identifier '{' section* '}';
section: 'section' Identifier '{' (question | defaultStyle | section)* '}';
widgetQuestion: question '{' widget '}';
widget: 'widget' Type;
question: 'question' Identifier;
defaultStyle: 'default' LiteralValue '{' cssItem* widget '}';
cssItem: Identifier ':' Identifier;