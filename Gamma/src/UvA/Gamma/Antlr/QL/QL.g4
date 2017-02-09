grammar QL;

form: 'form'  ID  BRACKET_OPEN (formItem)*  BRACKET_CLOSE;

formItem: input # in
        | condition  #cond
        ;

input: ID':' QUESTION type;

type: BOOL | STRING | INT | DATE | DEC | MONEY | '('intExpr')';

condition: 'if' '('boolExpr')' BRACKET_OPEN formItem BRACKET_CLOSE;

boolExpr: boolExpr op=('&&' | '||') boolExpr                          # andor
        | intExpr op=('<' | '>' | '<=' | '>=' | '!=' | '==') intExpr  # comparison
        | ID                                                          # boolId
        | ('true' | 'false')                                          # bool
        ;

intExpr: intExpr op=('*' | '/') intExpr # div
    | intExpr op=('+' | '-') intExpr    # add
    | NUMBER                            # int
    | ID                                # intId
    ;

//datatypes
BOOL:   'boolean';
STRING: 'string';
INT:    'integer';
DATE:   'date';
DEC:    'decimal';
MONEY:  'money';

//Operands
ADD: '+';
SUB: '-';

NUMBER: ('0'..'9')+;

QUESTION: '"'(~'"')+'"' ;
ID: [a-zA-Z]+;

//symbols
BRACKET_OPEN: '{';
BRACKET_CLOSE: '}';

//Skipping and hiding
WHITESPACE: (' ' | '\n' | '\r' | '\t' | '\u000C')+ -> skip;



