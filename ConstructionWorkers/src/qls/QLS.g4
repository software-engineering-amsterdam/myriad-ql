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
    : 'width:' INTEGER         # widthStyle
    | 'font:' STRING          # fontStyle
    | 'fontsize:' INTEGER      # fontsizeStyle
    | 'color:' HEX            # colorStyle
    ;

type
    : 'boolean'         # booleanType
    | 'integer'         # integerType
    | 'string'          # stringType
    | 'money'           # moneyType
    ;

STRING
    :  '"' (ESC | ~('\\'|'"'))* '"';

Identifier
    :   [a-zA-Z]+;

INTEGER
    : [0-9]+;

HEX
    : '#' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT;

WS
    : [\t\r\n\f]+ -> channel(HIDDEN);

fragment ESC
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE
    ;

fragment HEXDIGIT
    : [0-9a-fA-F];

fragment UNICODE
    : 'u' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT;
