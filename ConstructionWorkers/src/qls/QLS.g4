grammar QLS;

stylesheet
    : 'stylesheet' Identifier '{' (defaultStyle | section)* '}' ;

defaultStyle
    : 'default' type widget                   # defaultWithoutStyleDeclaration
    | 'default' type '{' style+ widget '}'    # defaultWithStyleDeclaration
    ;

section
    : 'section' STRING '{' (question | section | defaultStyle)* '}'
    ;

question
    : 'question' Identifier widget                 # widgetQuestion
    | 'question' Identifier                        # normalQuestion
    ;

widget
    : 'widget checkbox'                                    # checkbox
    | 'widget spinbox'                                     # spinbox
    | 'widget slider'                                      # slider
    | 'widget textbox'                                     # textbox
    | 'widget radio' '(' yes=STRING ',' no=STRING ')'      # radio
    | 'widget dropdown' '(' yes=STRING ',' no=STRING ')'   # dropdown
    ;

style
    : 'width:' NUMBER         # widthStyle
    | 'font:' STRING          # fontStyle
    | 'fontsize:' NUMBER      # fontsizeStyle
    | 'color:' HEX            # colorStyle
    ;

type
    : 'boolean'         # boolType
    | 'integer'         # intType
    | 'string'          # stringType
    | 'money'           # moneyType
    ;

STRING
    :  '"' (ESC | ~["\\])* '"' ;
Identifier
    :   [a-zA-Z]+;
NUMBER
    : [0-9]+ ;
HEX
    : '#' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT ;
WS
    :   [ \r\t\u000C\n]+ -> channel(HIDDEN)
    ;

fragment ESC
    :   '\\' (["\\/bfnrt] | UNICODE) ;
fragment HEXDIGIT
    : [0-9a-fA-F] ;
fragment UNICODE
    : 'u' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT ;
