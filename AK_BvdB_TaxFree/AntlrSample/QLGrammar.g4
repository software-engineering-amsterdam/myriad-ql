grammar QLGrammar;

@header {
package org.uva.taxfree.gen;
}

form : 'form {' statement* '}';
statement : question
          | ifStatement
          ;
question : QUESTION '->' VARNAME ':' VARTYPE;
ifStatement : 'if (' expression ')' '{' question* '}';

expression : VARNAME
           | expression '==' expression
           ;

// The whitespace layout
WS : [ \t\r\n]+ -> skip ;
// Types
QUESTION : '"'~[?]+'?"';
VARTYPE : [a-z]+;
//VARTYPE : ('Bool' | 'String');
VARNAME : [a-zA-Z]+[ a-zA-Z0-9]*;