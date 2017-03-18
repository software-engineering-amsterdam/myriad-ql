grammar QLS;

stylesheet
    :   'stylesheet' id=identifier page*
    ;

page
    :   'page' id=identifier '{' pageItem* '}'
    ;

pageItem
    :   section         #pageSection
    |   defaultWidget   #pageDefaultWidget
    ;

section
    :   'section' name=STRING_LITERAL '{' sectionItem* '}'
    |   'section' name=STRING_LITERAL sectionItem
    ;

sectionItem
    :   question        #sectionQuestion
    |   section         #sectionNested
    |   defaultWidget   #sectionDefaultWidget
    ;

question
    :   'question' id=identifier            #questionNoWidget
    |   'question' id=identifier widget     #questionWidget
    ;

widget
    : 'widget' widgetType
    ;

widgetType
    : 'spinbox'                                                     #spinboxWidget
    | 'slider'                                                      #sliderWidget
    | 'text'                                                        #textWidget
    | 'checkbox'                                                    #checkboxWidget
    | 'radio' '(' yes=STRING_LITERAL ',' no=STRING_LITERAL ')'      #radioWidget
    | 'dropdown' '(' yes=STRING_LITERAL ',' no=STRING_LITERAL ')'   #dropdownWidget
    ;

defaultWidget
    : 'default' type widget                     #defaultNoStyle
    | 'default' type '{' styleRule+ widget '}'  #defaultWithStyle
    ;

styleRule // TODO: Color - Hexadecimal
    : 'width' ':'  INTEGER_LITERAL      #widthRule
    | 'font' ':' STRING_LITERAL         #fontRule
    | 'fontsize' ':' INTEGER_LITERAL    #fontSizeRule
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