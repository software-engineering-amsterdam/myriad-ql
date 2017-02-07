grammar QL;

@header {package sc.ql.antlr;}

parse
	: unExpr EOF
	;

primary
	: Int
 	| Str
 	| Bool
 	;

unExpr
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
WS
	:	(' ' | '\t' | '\n' | '\r') -> channel(HIDDEN)
    ;

COMMENT
    :	'/*' .* '*/' -> channel(HIDDEN)
    ;

Ident
	:	('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
	;

Int
	: 	('0'..'9')+
	;

Str
	: '"' .* '"'
	;

Bool
	: ('true'|'false'|'TRUE'|'FALSE')
	;
