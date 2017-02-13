grammar QL;

@header {package sc.ql.antlr;}

form
	: 'form' question+ 'endform' EOF
	;

questions
	: question
	| questions question
	;

question
	: STR NAME TYPE
	;

primary
	: INT
 	| STR
 	| BOOL
 	;

expression
	: primary calcExpr primary
	| primary relExpr primary
	| primary boolExpr primary
	;

calcExpr
    : '+'
    | '-'
    | '*'
    | '/'
    ;

relExpr
    : '<'
    | '<='
    | '>'
    | '>='
    | '=='
    | '!='
    ;

boolExpr
    : '&&'
    | '||'
    ;


// Tokens
BOOL
	: ('true'|'false')
	;
	
TYPE
	: 'boolean'
	| 'text'
	| 'number'
	;

INT
	: ('0'..'9')+
	;

NAME
	: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')+
	;

STR
	: '"' .*? '"'
	;

WS
	: [ \t\n\r]+ -> skip
    ;

COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;