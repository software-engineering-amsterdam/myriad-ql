grammar QlsGrammar;

stylesheet
    : 'stylesheet' Identifier (page)*
    ;

page
    : 'page' Identifier '{' (section|defaultBlock)* '}'
    ;

section
    : 'section' StringLiteral '{' (question|section|defaultBlock)* '}'
    ;

question	
	: 'question' Identifier widget?
	;

widget
	: 'widget' widgetType 
	;

defaultBlock
	: 'default' type widget
	| 'default' type '{' (styleRule)* widget '}'
	;

styleRule
	: 'height' ':' IntegerLiteral     # heightStyleRule
    | 'width' ':' IntegerLiteral      # widthStyleRule
    | 'fontsize' ':' IntegerLiteral   # fontSizeStyleRule
    | 'color' ':' HexColorLiteral     # colorStyleRule
    | 'fontstyle' ':' StringLiteral   # fontStyleStyleRule
    | 'font' ':' StringLiteral        # fontNameStyleRule
	;

widgetType
    : 'spinbox'                 # spinboxWidgetType
    | 'radio' optionsList       # radioWidgetType
    | 'dropdown' optionsList    # dropdownWidgetType
    | 'checkbox'                # checkboxWidgetType
    ;

optionsList
	: '(' option ')'
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


// Syntax

WhiteSpace : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

Comment
    :   '/*' .*? '*/' -> channel(HIDDEN);
    
LineComment 
    :   '//' ~[\r\n]* -> channel(HIDDEN);

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