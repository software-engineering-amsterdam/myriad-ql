grammar QL;

form: 'form'  ID  '{' (formItem)*  '}';

formItem: input         #in
        | computed      #comp
        | condition     #cond
        ;

input: QUESTION ID':' type;

computed: QUESTION ID':' type '=' '('expression')';

type: BOOL | STRING | INT | DATE | DEC | MONEY;

condition: 'if' '('boolExpr')' '{' (formItem)* '}';

expression: boolExpr
          | intExpr
          ;

boolExpr: boolExpr op=('&&' | '||' | '==' | '!=') boolExpr            # andor
        | intExpr op=('<' | '>' | '<=' | '>=' | '!=' | '==') intExpr  # comparison
        | ID                                                          # boolId
        | ('true' | 'false')                                          # bool
        ;

intExpr: intExpr op=('*' | '/') intExpr    # div
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

NUMBER: ('0'..'9')+;

QUESTION: '"'(~'"')+'"' ;
ID: [a-zA-Z]+;

//Skipping and hiding
WHITESPACE: (' ' | '\n' | '\r' | '\t' | '\u000C')+ -> skip;



