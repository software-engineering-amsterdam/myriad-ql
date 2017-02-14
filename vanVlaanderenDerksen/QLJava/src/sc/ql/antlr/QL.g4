grammar QL;

@header {package sc.ql.antlr;}

form
	: 'form' form_element+ 'endform' EOF
	;

form_element
	: STR ID TYPE 																			#Question
	| STR ID TYPE '=' expression 															#calcQuestion
	| 'if' conditional_block ('else if' conditional_block)* ('else' form_element+)? 'endif' #if_statement
	;

conditional_block
	: '(' expression ')' form_element+
	;

expression
	: '(' expression ')' 												#parenExpr
	| '!' expression													#notExpr
	| left=expression op=('*'|'/') right=expression						#opExpr
	| left=expression op=('+'|'-') right=expression						#opExpr
	| left=expression op=('<'|'<='|'>'|'>='|'=='|'!=') right=expression #relExpr
	| left=expression op=('&&'|'||') right=expression					#boolExpr
	| atom=BOOL															#atomExpr
	| atom=INT															#atomExpr
	| atom=ID															#atomExpr
	| atom=STR															#atomExpr
	;

BOOL
	: ('true'|'false')
	;
	
TYPE
	: 'boolean'
	| 'date'
	| 'float'
	| 'integer'
	| 'money'
	| 'text'
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