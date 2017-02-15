grammar QL;

form: 'form'  ID  '{' (formItem)*  '}';

formItem: question
        | computed
        | condition
        ;

question: STRING_LITERAL ID':' type;

computed: STRING_LITERAL ID':' type '=' '('expression')';

type: BOOL | STRING | INT | DATE | DEC | MONEY;

condition: 'if' '('boolExpr')' '{' (formItem)* '}';

expression: boolExpr
          | numExpr
          ;

boolExpr: boolExpr op=('&&' | '||' | '==' | '!=') boolExpr            # andor
        | numExpr op=('<' | '>' | '<=' | '>=' | '!=' | '==') numExpr  # comparison
        | ID                                                          # boolId
        | ('true' | 'false')                                          # bool
        ;

numExpr: numExpr op=('*' | '/') numExpr    # div
       | numExpr op=('+' | '-') numExpr    # add
       | NUMBER                            # num
       | ID                                # numId
       ;

//datatypes
BOOL:   'boolean';
STRING: 'string';
INT:    'integer';
DATE:   'date';
DEC:    'decimal';
MONEY:  'money';

NUMBER: ('0'..'9')+('.'('0'..'9')+)?;

STRING_LITERAL: '"'(~'"')+'"' ;
ID: [a-zA-Z][a-zA-Z0-9]+;

//Skipping and hiding
WHITESPACE: (' ' | '\n' | '\r' | '\t' | '\u000C')+ -> skip;



