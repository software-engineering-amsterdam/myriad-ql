grammar QlsGrammar;

stylesheet
    : Stylesheet Identifier styleSheetBody*
    ;

styleSheetBody
    : Page Identifier OpenBracket pageBody* CloseBracket #pageStyleBody
    | Default type styleStatement #defaultValueTypeRule
    ;

pageBody
    : Section StringLiteral OpenBracket pageBody CloseBracket
    | Section StringLiteral pageBody
    | Question Identifier styleStatement
    | Question Identifier
    | defaultTypeStatement
    ;

defaultTypeStatement
    : Default type OpenBracket styleStatement* CloseBracket
    | Default type styleStatement
    ;

styleStatement
	: Widget widgetType
	| Identifier Colon literal
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