grammar Calculator;

//parser rules
expr: expr op=('*' | '/') expr # div
    | expr op=('+' | '-') expr # add
    | NUMBER                   # int
    ;

//Lexer rules

ADD : '+' ;
SUB : '-' ;
MUL : '*' ;
DIV : '/' ;

NUMBER : (DIGIT)+ ;

WHITESPACE: (' ' | '\n' | '\r' | '\t' | '\u000C')+ -> skip ;

DIGIT : '0'..'9';
