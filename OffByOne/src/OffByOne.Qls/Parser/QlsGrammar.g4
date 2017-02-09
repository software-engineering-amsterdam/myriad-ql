grammar QlsGrammar;

stylesheet
    : Stylesheet Identifier OpenBracket page CloseBracket
    ;

page
    : Page Identifier OpenBracket section CloseBracket
    ;

section
    : Section Identifier declaration
    ;

declaration
    : Widget widgetType
    | section
    ;

widgetType
    :   'checkbox'
    |   'spinbox'
    ;

// Lexer tokens. Move to separate file?

Stylesheet : 'stylesheet';
Page : 'page';
Section: 'section';
Widget: 'widget';


OpenBracket : '{';
CloseBracket : '}';
OpenParenthesis : '(';
Closeparenthesis : ')';
Semicolon : ';';

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