grammar Hello;
r : 'hello' ID ;
ID : [a-z]+ ;
WS : [ \t\n]+ -> skip ;