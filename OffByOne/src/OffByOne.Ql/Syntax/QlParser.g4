parser grammar QlParser;
options { tokenVocab=QlLexer; }

form : FORM Identifier LBRACE stat+ RBRACE ;
question : StringLiteral Identifier COLON Type ; // TODO: default value / precomputed value
stat
	: question
	| ifStat
	;

ifStat : IF LPAREN expression RPAREN LBRACE stat+ RBRACE elseStat? ;

elseStat : ELSE LBRACE elseStats=stat+ RBRACE;

expression
    : LPAREN expression RPAREN      # ExpressionBracket

    | OP_NOT expression             # ExpressionNot
    | OP_SUB expression             # ExpressionNegate

    | expression OP_MUL expression  # ExpressionMultiply
    | expression OP_DIV expression  # ExpressionDivide
    | expression OP_SUB expression  # ExpressionSubtract
    | expression OP_ADD expression  # ExpressionAdd

    | expression OP_GTE expression  # ExpressionGreaterThanOrEqual
    | expression OP_LTE expression  # ExpressionLesserThanOrEqual
    | expression OP_GT expression   # ExpressionGreaterThan
    | expression OP_LT expression   # ExpressionLesserThan
    | expression OP_NEQ expression  # ExpressionNotEqual
    | expression OP_EQ expression   # ExpressionEqual

    | expression OP_AND expression  # ExpressionAnd
    | expression OP_OR expression   # ExpressionOr
    | literal                       # ExpressionLiteral
    | Identifier                    # ExpressionIdentifier
	;

literal
	: DateLiteral	 # DateLiteral
	| BooleanLiteral # BooleanLiteral
	| Money			 # MoneyLiteral
	| Decimal		 # DecimalLiteral
	| SignedInt		 # IntegerLiteral
	| StringLiteral  # StringLiteral
	;
