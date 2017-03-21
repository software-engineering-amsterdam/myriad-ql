grammar QLS;

stylesheet
    :   'stylesheet' id=identifier page*
    ;

page
    :   'page' id=identifier '{' section* defaultWidget* '}'
    ;

section
    :   'section' name=STRING_LITERAL '{' question* section* defaultWidget* '}'
    |   'section' name=STRING_LITERAL (question | section | defaultWidget)
    ;

question
    :   'question' id=identifier            #questionNoWidget
    |   'question' id=identifier widget     #questionWidget
    ;

widget
    : 'widget' widgetType
    ;

widgetType
    : 'spinbox'                                                     #spinbox
    | 'slider'                                                      #slider
    | 'text'                                                        #text
    | 'checkbox'                                                    #checkbox
    | 'radio' '(' yes=STRING_LITERAL ',' no=STRING_LITERAL ')'      #radio
    | 'dropdown' '(' yes=STRING_LITERAL ',' no=STRING_LITERAL ')'   #dropdown
    ;

defaultWidget
    : 'default' type widget                     #defaultNoStyle
    | 'default' type '{' styleRule+ widget '}'  #defaultWithStyle
    ;

styleRule
    : 'width' ':' INTEGER_LITERAL       #widthRule
    | 'font' ':' STRING_LITERAL         #fontRule
    | 'fontsize' ':' INTEGER_LITERAL    #fontSizeRule
    | 'color' ':' COLOR_LITERAL         #colorRule
    ;

// TODO: duplicates with QL grammar: extract in separate file?

type
    :   'boolean'   #typeBoolean
    |   'float'     #typeFloat
    |   'integer'   #typeInteger
    |   'string'    #typeString
    |   'money'     #typeMoney
    ;

identifier
    :   ID
    ;

LINE_COMMENT : '//' ~[\r\n]* -> channel(HIDDEN);

// literal
STRING_LITERAL : '"' (ESCAPE_QUOTE | ~ ["\\])* '"';
INTEGER_LITERAL : [0-9]+;
COLOR_LITERAL : '#' HEX HEX HEX HEX HEX HEX ;

// names
ID : [a-zA-Z][a-zA-Z0-9_]+;

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

fragment ESCAPE_QUOTE
   : '\\' (["\\/bfnrt] | UNICODE)
   ;
fragment UNICODE
   : 'u' HEX HEX HEX HEX
   ;
fragment HEX
   : [0-9a-fA-F]
   ;