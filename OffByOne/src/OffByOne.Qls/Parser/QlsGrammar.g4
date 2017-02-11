grammar QlsGrammar;

stylesheet
    : Stylesheet Identifier OpenBracket styleSheetBody* CloseBracket
    ;

styleSheetBody
    : Page Identifier OpenBracket pageBody CloseBracket
    | Default type styleStatement
    ;

pageBody
    : Section Identifier OpenBracket pageBody CloseBracket
    | Question Identifier OpenBracket styleStatement* CloseBracket
    | defaultTypeStatement
    ;

defaultTypeStatement
    : Default type OpenBracket styleStatement* CloseBracket
    ;

styleStatement
	: Widget widgetType
	| Identifier Colon literal // Missing hex?
	;



// Types

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
	: StringLiteral ',' option
	| StringLiteral
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

Identifier : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;