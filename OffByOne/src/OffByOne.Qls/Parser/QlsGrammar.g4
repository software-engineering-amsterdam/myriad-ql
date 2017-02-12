grammar QlsGrammar;

stylesheet
    : Stylesheet Identifier (page)+
    ;

page
    : Page Identifier OpenBracket (section|defaultBlock)+ CloseBracket
    ;

section
    : Section StringLiteral OpenBracket (question|section|defaultBlock)+ CloseBracket
    ;

question	
	: Question Identifier widget?
	;

widget
	: Widget widgetType 
	;

defaultBlock
	: Default type widget
	| Default type OpenBracket (styleRule)+ widget CloseBracket
	;

styleRule
	: Identifier Colon literal
	;

widgetType
    : 'checkbox'
    | 'spinbox'
    | 'radio' optionsList
    | 'dropdown' optionsList
    | 'checkbox' optionsList
    ;

optionsList
	: OpenParenthesis option Closeparenthesis
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

Stylesheet : 'stylesheet';
Page : 'page';
Question : 'question';
Section: 'section';
Widget: 'widget';
Default: 'default';


OpenBracket : '{';
CloseBracket : '}';
OpenParenthesis : '(';
Closeparenthesis : ')';
Semicolon : ';';
Colon: ':';

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