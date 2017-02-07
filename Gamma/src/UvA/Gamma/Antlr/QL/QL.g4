grammar QL;

form: 'form'  ID  BRACKET_OPEN (formItem)*  BRACKET_CLOSE;

formItem: input # in
        | condition  #cond
        ;

input: INPUT_ID QUESTION type;

type: BOOL | STRING | INT | DATE | DEC | MONEY ;

condition: 'if' '('boolExpr')' BRACKET_OPEN formItem BRACKET_CLOSE;

expr: expr op=('*' | '/') expr # div
    | expr op=('+' | '-') expr # add
    | NUMBER                   # int
    | ID                       # id
    ;

boolExpr: boolExpr op=('&&' | '||') boolExpr                    # andor
        | expr op=('<' | '>' | '<=' | '>=' | '!=' | '==') expr  # comparison
        | ID                                                    # boolId
        | ('true' | 'false')                                    # bool
        ;

//datatypes
BOOL:   'boolean';
STRING: 'string';
INT:    'integer';
DATE:   'date';
DEC:    'decimal';
MONEY:  'money';

NUMBER: ('0'..'9')+;

QUESTION: '"'[a-z-A-Z?0-9 \n\r\t]+'"' ;
INPUT_ID: ID':';
ID: [a-zA-Z]+;

//symbols
BRACKET_OPEN: '{';
BRACKET_CLOSE: '}';

//Skipping and hiding
WHITESPACE: (' ' | '\n' | '\r' | '\t' | '\u000C')+ -> skip;



