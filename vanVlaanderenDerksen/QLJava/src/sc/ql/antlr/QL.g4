grammar QL;

@header {package sc.ql.antlr;}

form
	: 'form' form_element+ 'endform' EOF
	;

form_element
	: STR ID TYPE ('=' expression)?
	| 'if' conditional_block ('else if' conditional_block)* ('else' form_element+)? 'endif'
	;

conditional_block
	: '(' expression ')' form_element+
	;

expression
	: '(' expression ')' 
	| '!' expression
	| expression op=('*'|'/') expression
	| expression op=('+'|'-') expression
	| expression op=('<'|'<='|'>'|'>='|'=='|'!=') expression
	| expression op=('&&'|'||') expression
	| BOOL
	| INT
	| ID
	| STR
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