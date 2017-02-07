grammar QL;

form
    :   T_FORM T_STRING T_OPEN_BRACKET (declaration)* T_CLOSE_BRACKET
    ;

declaration
    :   question T_SEMICOLON
    |   statement
    ;

question
    :   type T_STRING T_COLON T_TEXT default_value?
    |   type T_ASSIGN expression T_COLON T_TEXT default_value?
    ;

statement
    :   T_IF T_OPEN_PARENT expression T_CLOSE_PARENT T_OPEN_BRACKET declaration* T_CLOSE_BRACKET (T_ELSE statement)?
    ;

default_value
    :   T_ASSIGN expression
    ;

expression
    :
    |   T_OPEN_PARENT expression T_CLOSE_PARENT
    |   '!' expression
    |   expression ('*'|'+'|'-'|'/') expression
    |   expression ('>'|'<'|'=='|'!='|'<='|'>=') expression
    |   expression ('&&'|'||') expression
    |   literal
    ;

literal
    :
    |   T_BOOLEAN
    |   T_INTEGER
    |   T_MONEY
    |   T_FLOAT
    |   T_STRING
    ;

type
    :   'boolean'
    |   'float'
    |   'money'
    |   'string'
    |   'integer'
    |   'date'
    ;

T_ASSIGN : '=';
T_IF : 'if';
T_ELSE : 'else';
T_TEXT :   '"' (T_ESC | ~ ["\\])* '"';
T_COLON :   ':';
T_FORM :   'form';
T_STRING : ([a-zA-Z0-9_])+;
T_MONEY : [0-9].([0-9])?;
T_FLOAT : [0-9].([0-9])?;
T_INTEGER : [0-9];
T_BOOLEAN : 'true' | 'false';
T_OPEN_BRACKET :   '{';
T_CLOSE_BRACKET: '}';
T_OPEN_PARENT : '(';
T_CLOSE_PARENT : ')';
LINE_COMMENT : '//' ~[\r\n]* -> channel(HIDDEN);
T_SEMICOLON : ';';

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

fragment T_ESC
   : '\\' (["\\/bfnrt] | T_UNICODE)
   ;
fragment T_UNICODE
   : 'u' T_HEX T_HEX T_HEX T_HEX
   ;
fragment T_HEX
   : [0-9a-fA-F]
   ;