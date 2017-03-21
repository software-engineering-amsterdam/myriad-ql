grammar QL;

form: 'form'  ID  '{' (formItem)*  '}';

formItem: question
        | computed
        | condition
        ;

question: STRING_LITERAL ID':' type;

computed: STRING_LITERAL ID':' type '=' '('expression')';

type: BOOL #booleanType | STRING #stringType | INT #intType | DATE #dateType | DEC  #decimalType| MONEY #moneyType;

condition: 'if' '('boolExpression')' '{' (formItem)* '}' (elseblock)?;

elseblock: 'else' '{'(formItem)*'}';

expression: boolExpression # booleanExpression | numExpression # numberExpression;

boolExpression: boolExpression op=('&&' | '||' | '==' | '!=') boolExpression        #logicalBooleanExpression
        | numExpression op=('<' | '>' | '<=' | '>=' | '!=' | '==') numExpression    #logicalIntegerExpression
        | '!'boolExpression                                                         #negatedBooleanExpression
        | ID                                                                        #booleanIdentifierExpression
        | ('true' | 'false')                                                        #booleanValueExpression
        ;

numExpression: numExpression op=('*' | '/') numExpression   #multiExpression
       | numExpression op=('+' | '-') numExpression         #addExpression
       | '('numExpression')'                                #nestedExpression
       | NUMBER                                             #numberValueExpression
       | ID                                                 #identifierExpression
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

ID: [a-zA-Z][a-zA-Z0-9_]*;

//Skipping and hiding
WHITESPACE: (' ' | '\n' | '\r' | '\t' | '\u000C')+ -> skip;
ONE_LINE_COMMENT: ('//' .*? ('\n'|'\r')) -> skip;
MULTI_LINE_COMMENT: ('/*' .*? '*/') -> skip;


