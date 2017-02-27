parser grammar QlParser;
options { tokenVocab=QlLexer; }

form : FORM Identifier LBRACE stat+ RBRACE ;
question : literal Identifier COLON Type (LPAREN expression RPAREN)? ;
stat
	: question
	| ifStat
	;

ifStat : IF LPAREN expression RPAREN LBRACE stat+ RBRACE elseStat? ;

elseStat : ELSE LBRACE elseStats=stat+ RBRACE;

expression
    : literal                       # ExpressionLiteral
    | Identifier                    # ExpressionIdentifier
    | LPAREN expression RPAREN      # ExpressionBracket

    | OP_NOT expression             # ExpressionNot
    | OP_SUB expression             # ExpressionNegate

    | expression OP_MUL expression  # ExpressionMultiply
    | expression OP_DIV<assoc=left> expression  # ExpressionDivide
    | expression OP_SUB<assoc=left> expression  # ExpressionSubtract
    | expression OP_ADD expression  # ExpressionAdd

    | expression OP_GTE expression  # ExpressionGreaterThanOrEqual
    | expression OP_LTE expression  # ExpressionLesserThanOrEqual
    | expression OP_GT expression   # ExpressionGreaterThan
    | expression OP_LT expression   # ExpressionLesserThan
    | expression OP_NEQ expression  # ExpressionNotEqual
    | expression OP_EQ expression   # ExpressionEqual

    | expression OP_AND expression  # ExpressionAnd
    | expression OP_OR expression   # ExpressionOr
	;

literal
	: DateLiteral	 # DateLiteral
	| BooleanLiteral # BooleanLiteral
	| MoneyLiteral	 # MoneyLiteral
	| DecimalLiteral # DecimalLiteral
	| IntLiteral	 # IntegerLiteral
	| StringLiteral  # StringLiteral
	;
