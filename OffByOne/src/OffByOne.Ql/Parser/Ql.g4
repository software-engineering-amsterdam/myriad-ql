grammar Ql;

form : 'form' Identifier '{' stat+ '}';
question : label=StringLiteral Identifier ':' Type ('(' expression ')')? ;
stat
	: question
	| ifStat
	;

ifStat : 'if' '(' expression ')' '{' stat+ '}' elseStat? ;

elseStat : 'else' '{' elseStats=stat+ '}';

expression
    : literal                 # LiteralExpression
    | Identifier              # Identifier
    | '(' expression ')'      # Brackets

    | op=('!'|'-') expression # Unary

    | lhs=expression op=('*'|'/'<assoc=left>) rhs=expression         # Multiplication
    | lhs=expression op=('+'|'-'<assoc=left>) rhs=expression         # Addition

    | lhs=expression op=('>'|'>='|'<'|'<='|'=='|'!=') rhs=expression # Compare

    | lhs=expression op='&&' rhs=expression # And
    | lhs=expression op='||' rhs=expression # Or
    ;

literal
    : DateLiteral    # DateLiteral
    | BooleanLiteral # BooleanLiteral
    | MoneyLiteral   # MoneyLiteral
    | DecimalLiteral # DecimalLiteral
    | IntLiteral     # IntegerLiteral
    | StringLiteral  # StringLiteral
    ;

Type 
	: 'boolean'
	| 'date'
	| 'decimal'
	| 'integer'
	| 'money'
	| 'string'
	;

BooleanLiteral
	: 'true'
	| 'false'
	;

DateLiteral : '\'' Digit Digit '-' Digit Digit '-' Digit Digit Digit Digit '\'' ;

MoneyLiteral : IntLiteral '.' Digit Digit ;
DecimalLiteral : IntLiteral '.' [0-9]+ ;

IntLiteral: Digit | ([1-9] Digit*) ;

fragment
Digit: [0-9] ;

StringLiteral : '"' (Escaped | . )*? '"' ;

fragment
Escaped : '\\' [btnr"\\] ;

Identifier : [a-z][a-zA-Z]* ;

BlockComment : '/*' .*? '*/' -> skip;
LineComment : '//' .*? '\n'  -> skip;
WS : [ \t\r\n]+ -> skip ;
