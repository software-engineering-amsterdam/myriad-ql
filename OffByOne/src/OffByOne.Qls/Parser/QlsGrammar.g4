grammar QlsGrammar;

stylesheet
    : STYLESHEET Identifier (page)*
    ;

page
    : PAGE Identifier OPEN_BRACE (section|defaultBlock)* CLOSE_BRACE
    ;

section
    : SECTION StringLiteral OPEN_BRACE (question|section|defaultBlock)* CLOSE_BRACE
    ;

question	
	: QUESTION Identifier widget?
	;

widget
	: WIDGET widgetType 
	;

defaultBlock
	: DEFAULT type widget
	| DEFAULT type OPEN_BRACE (styleRule)* widget CLOSE_BRACE
	;

styleRule
	: 'height' COLON IntegerLiteral     # heightStyleRule
    | 'width' COLON IntegerLiteral      # widthStyleRule
    | 'fontsize' COLON IntegerLiteral   # fontSizeStyleRule
    | 'color' COLON HexColorLiteral     # colorStyleRule
    | 'fontstyle' COLON StringLiteral   # fontStyleStyleRule
    | 'font' COLON StringLiteral        # fontNameStyleRule
	;

widgetType
    : 'spinbox' # spinboxWidgetType
    | 'radio' optionsList # radioWidgetType
    | 'dropdown' optionsList # dropdownWidgetType
    | 'checkbox' # checkboxWidgetType
    ;

optionsList
	: OPEN_PARENTHESIS option CLOSE_PARENTHESIS
	;

option
	: StringLiteral ',' option
	| StringLiteral
	;

type
	: 'boolean'     # booleanType
	| 'string'      # stringType
	| 'integer'     # integerType
	| 'float'       # floatType
	| 'money'       # moneyType
    | 'date'        # dateType
	;

literal
	: IntegerLiteral    # integerLiteralType
	| StringLiteral     # stringLiteralType
    | HexColorLiteral   # hexLiteralType
    | DecimalLiteral    # decimalLiteralType
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
OPEN_BRACE : '{';
CLOSE_BRACE : '}';
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
DateLiteral : '\'' Digit Digit '-' Digit Digit '-' Digit Digit Digit Digit '\'' ;
MoneyLiteral : '-'? Int+ '.' Digit Digit ;
DecimalLiteral : '-'? Int+ '.' [0-9]+ ;

fragment Int: Digit | ([1-9] Digit*) ;
fragment Digit: [0-9] ;

Identifier : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;