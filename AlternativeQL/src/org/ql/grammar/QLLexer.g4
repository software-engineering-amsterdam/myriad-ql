lexer grammar QLLexer;

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
