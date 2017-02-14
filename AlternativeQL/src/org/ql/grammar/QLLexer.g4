lexer grammar QLLexer;

LINE_COMMENT : '//' ~[\r\n]* -> channel(HIDDEN);

// literal
STRING_LITERAL : '"' (ESCAPE_QUOTE | ~ ["\\])* '"';
BOOLEAN_LITERAL : 'true' | 'false';
DECIMAL_LITERAL : [0-9]+ '.' [0-9]+;
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
