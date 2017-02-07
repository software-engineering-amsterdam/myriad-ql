grammar QL;

form
    :   FORM ID OPEN_BRACKET (declaration)* CLOSE_BRACKET
    ;

declaration
    :   question SEMICOLON
    |   statement
    ;

question
    :   type ID COLON STRING defaulvalue?
    |   type ASSIGN expression COLON STRING defaulvalue?
    ;

statement
    :   IF OPEN_PARENT expression CLOSE_PARENT OPEN_BRACKET declaration* CLOSE_BRACKET (ELSE statement)?
    ;

defaulvalue
    :   ASSIGN expression
    ;

expression
    :   boolean_literal
    |   string_literal
    |   float_literal
    |   integer_literal
    |   parameter
    |   OPEN_PARENT expression CLOSE_PARENT
    |   <assoc=right> '!' expression
    |   <assoc=left> expression '/' expression
    |   <assoc=left> expression '*' expression
    |   expression '-' expression
    |   expression '+' expression
    |   expression '>' expression
    |   expression '<' expression
    |   expression '==' expression
    |   expression '!=' expression
    |   expression '<=' expression
    |   expression '>=' expression
    |   expression '<=' expression
    |   expression '>=' expression
    |   expression ('&&'|'||') expression
    ;

parameter
    :   ID
    ;

boolean_literal
    :   BOOLEAN
    ;

string_literal
    :   STRING
    ;

float_literal
    :   FLOAT
    ;

integer_literal
    :   INTEGER
    ;

type
    :   'boolean'
    |   'float'
    |   'money'
    |   'ID'
    |   'integer'
    |   'date'
    ;

ASSIGN : '=';
IF : 'if';
ELSE : 'else';
COLON : ':';
FORM : 'form';
BOOLEAN : 'true' | 'false';
OPEN_BRACKET :   '{';
CLOSE_BRACKET: '}';
OPEN_PARENT : '(';
CLOSE_PARENT : ')';
SEMICOLON : ';';

LINE_COMMENT : '//' ~[\r\n]* -> channel(HIDDEN);
STRING : '"' (ESC | ~ ["\\])* '"';
FLOAT : [0-9]+ '.' [0-9]+;
ID : [a-zA-Z][a-zA-Z0-9_]+;
INTEGER : [0-9]+;

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

fragment ESC
   : '\\' (["\\/bfnrt] | UNICODE)
   ;
fragment UNICODE
   : 'u' HEX HEX HEX HEX
   ;
fragment HEX
   : [0-9a-fA-F]
   ;