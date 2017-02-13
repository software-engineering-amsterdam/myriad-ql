lexer grammar QLLexer;

ASSIGN : '=';
IF : 'if';
ELSE : 'else';
COLON : ':';
FORM : 'form';
OPEN_BRACKET :   '{';
CLOSE_BRACKET: '}';
OPEN_PARENT : '(';
CLOSE_PARENT : ')';
SEMICOLON : ';';
BANG : '!';
DIV : '/';
MUL : '*';
ADD : '+';
SUB : '-';
GT : '>';
LT : '<';
EQUAL : '==';
LE : '<=';
GE : '>=';
NOTEQUAL : '!=';
AND : '&&';
OR : '||';

// types
BOOLEAN : 'boolean' | 'bool';
FLOAT : 'float';
INTEGER : 'integer';
STRING : 'string';
MONEY : 'money';
DATE : 'date';

LINE_COMMENT : '//' ~[\r\n]* -> channel(HIDDEN);

// literals
STRING_LITERAL : '"' (ESC | ~ ["\\])* '"';
BOOLEAN_LITERAL : 'true' | 'false';
FLOAT_LITERAL : [0-9]+ '.' [0-9]+;
INTEGER_LITERAL : [0-9]+;

// names
ID : [a-zA-Z][a-zA-Z0-9_]+;

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
