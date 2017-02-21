grammar QL;

@header {package sc.ql.antlr;}

form
	: 'form' form_element+ 'endform' EOF
	;

form_element																			
	: STR ID TYPE ('=' expression) ? 														#Question
	| 'if' conditional_block ('else if' conditional_block)* ('else' form_element+)? 'endif' #if_statement
	;

conditional_block
	: '(' expression ')' form_element+
	;
	
expression
	: '(' expression ')' 												#parenExpr
	| '!' expression													#boolExpr
	| left=expression op=('*'|'/') right=expression						#opExpr
	| left=expression op=('+'|'-') right=expression						#opExpr
	| left=expression op=('<'|'<='|'>'|'>='|'=='|'!=') right=expression #relExpr
	| left=expression op=('&&'|'||') right=expression					#boolExpr
	| atom=BOOL															#boolAtom
	| atom=INT															#intAtom
	| atom=ID															#idAtom
	| atom=STR															#strAtom
	;

BOOL
	: ('true'|'false')
	;
	
TYPE
	: 'BOOLEAN'
	| 'DATE'
	| 'FLOAT'
	| 'INTEGER'
	| 'MONEY'
	| 'STRING'
	;

INT
	: ('0'..'9')+
	;

ID
	: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
	;

STR
	: '"' ('\\"'|.)*? '"'
	;

WS
	: [ \t\n\r]+ -> skip
    ;

COMMENT
    : '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    : '//' .*?[\r\n] -> skip
    ;