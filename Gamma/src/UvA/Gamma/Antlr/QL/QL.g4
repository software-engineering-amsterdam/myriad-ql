grammar QL;

@members{
    String intID = "";
}

form: 'form'  ID  '{' (formItem)*  '}';

formItem: question
        | computed
        | condition
        ;

question: STRING_LITERAL ID':' type;

computed: STRING_LITERAL ID':' expression;

type: BOOL | STRING | INT | DATE | DEC | MONEY;

condition: 'if' '('boolExpr')' '{' (formItem)* '}' (elseblock)?;

elseblock: 'else' '{'(formItem)*'}';

expression: BOOL '=' '('boolExpr')'       # booleanExpression
          | (DEC|INT) '=' '('numExpr')'   # numberExpression
          | MONEY '=' '('numExpr')'       # moneyExpression
          ;



boolExpr: boolExpr op=('&&' | '||' | '==' | '!=') boolExpr
        | numExpr op=('<' | '>' | '<=' | '>=' | '!=' | '==') numExpr
        | '('numExpr')'
        | ID
        | ('true' | 'false')
        ;

numExpr: numExpr op=('*' | '/') numExpr
       | numExpr op=('+' | '-') numExpr
       | '('numExpr')'
       | NUMBER
       | ID
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
ID: [a-zA-Z][a-zA-Z0-9]*;

//Skipping and hiding
WHITESPACE: (' ' | '\n' | '\r' | '\t' | '\u000C')+ -> skip;



