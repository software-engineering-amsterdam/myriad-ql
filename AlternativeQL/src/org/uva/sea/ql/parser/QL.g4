grammar QL;

form
    :   T_FORM T_NAME T_OPEN_BRACKET (declaration T_SEMICOLON)* T_CLOSE_BRACKET
    ;

declaration
    :   question
    |   statement
    ;

question
    :   type T_NAME T_COLON T_STRING default_value?
    ;

statement
    :   T_IF T_OPEN_PARENT expression T_CLOSE_PARENT T_OPEN_BRACKET declaration* T_CLOSE_BRACKET (T_ELSE statement)?
    ;

default_value
    :   T_ASSIGN expression
    ;

expression
    :
    ;

type
    :   'boolean'
    |   'float'
    |   'money'
    |   'string'
    |   'integer'
    ;


T_ASSIGN : '=';
T_IF : 'if';
T_ELSE : 'else';
T_STRING :   '"' (T_ESC | ~ ["\\])* '"';
T_COLON :   ':';
T_FORM :   'form';
T_NAME : ([a-zA-Z0-9_])+;
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