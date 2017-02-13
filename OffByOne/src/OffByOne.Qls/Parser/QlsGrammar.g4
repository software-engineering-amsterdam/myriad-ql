grammar QlsGrammar;

stylesheet
    : STYLESHEET Identifier (page)+
    ;

page
    : PAGE Identifier OPEN_BRACKET (section|defaultBlock)+ CLOSE_BRACKET
    ;

section
    : SECTION StringLiteral OPEN_BRACKET (question|section|defaultBlock)+ CLOSE_BRACKET
    ;

question	
	: QUESTION Identifier widget?
	;

widget
	: WIDGET widgetType 
	;

defaultBlock
	: DEFAULT type widget
	| DEFAULT type OPEN_BRACKET (styleRule)+ widget CLOSE_BRACKET
	;

styleRule
	: Identifier COLON literal
	;

widgetType
    : 'checkbox'
    | 'spinbox'
    | 'radio' optionsList
    | 'dropdown' optionsList
    | 'checkbox' optionsList
    ;

optionsList
	: OPEN_PARENTHESIS option CLOSE_PARENTHESIS
	;

option
	: literal ',' option
	| literal
	;

type
	: 'boolean'
	| 'string'
	| 'integer'
	| 'float'
	| 'money'
    | 'date'
	;

literal
	: BooleanLieral
	| IntegerLiteral
	| StringLiteral
    | HexColorLiteral
	;

// Lexer tokens. Move to separate file?

// Keywords
STYLESHEET : 'stylesheet';
PAGE : 'page';
QUESTION : 'question';
SECTION: 'section';
WIDGET: 'widget';
DEFAULT: 'default';


// Syntax
OPEN_BRACKET : '{';
CLOSE_BRACKET : '}';
OPEN_PARENTHESIS : '(';
CLOSE_PARENTHESIS : ')';
SEMICOLON : ';';
COLON: ':';

WhiteSpace : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

Comment
    :   '/*' .*? '*/' -> channel(HIDDEN);
    
LineComment 
    :   '//' ~[\r\n]* -> channel(HIDDEN);

Boolean : 'bool';
Integer : 'int';
String : 'str';

BooleanLieral :  'true' | 'false';
IntegerLiteral  :   ('0'..'9')+;
StringLiteral  :   '"' .*? '"';
HexColorLiteral : '#' ('0'..'9' | 'a'..'f')+;

Identifier : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;