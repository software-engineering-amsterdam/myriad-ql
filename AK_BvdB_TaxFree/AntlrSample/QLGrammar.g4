grammar QLGrammar;

form : 'form {' statement* '}';
statement : ifStatement
          | question
          ;
question : QUESTION '->' VARNAME ':' VARTYPE;
ifStatement : 'if (' VARNAME ')' '{' question* '}';

// The whitespace layout
WS : [ \t\r\n]+ -> skip ;
// Types
QUESTION : '"'~[?]+'?"';
VARTYPE : [a-z]+;
VARNAME : [a-zA-Z]+[ a-zA-Z0-9]*;