grammar QlParser;

form : 'form' Identifier '{' stat+ '}';
question : literal Identifier ':' Type ('(' expression ')')? ;
stat
	: question
	| ifStat
	;

ifStat : 'if' '(' expression ')' '{' stat+ '}' elseStat? ;

elseStat : 'else' '{' elseStats=stat+ '}';

expression
    : literal                       # Literal
    | Identifier                    # Identifier
    | '(' expression ')'            # Brackets

    | op=('!'|'-') expression       # Unary

    | expression op=('*'|'/'<assoc=left>) expression         # Multiplication
    | expression op=('+'|'-'<assoc=left>) expression         # Addition

    | expression op=('>'|'>='|'<'|'<='|'=='|'!=') expression # Compare

    | expression op='&&' expression # Conjunction
    | expression op='||' expression # Disjunction
    ;

literal
    : DateLiteral    # DateLiteral
    | BooleanLiteral # BooleanLiteral
    | MoneyLiteral   # MoneyLiteral
    | DecimalLiteral # DecimalLiteral
    | IntLiteral     # IntegerLiteral
    | StringLiteral  # StringLiteral
    ;
