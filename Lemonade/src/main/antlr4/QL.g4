grammar QL;

assign : STRING COLON STRING PERIOD ;

COLON : ':' ;
PERIOD : '.' ;

STRING : ('a'..'z') ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;