grammar QL;

// parser

// https://github.com/antlr/antlr4/blob/master/doc/parser-rules.md  --parser rule reference

formDeclaration
    :   'form' id=ID OPEN_BRACKET statement+ CLOSE_BRACKET
    ;

statement
    :   text=STRING ID ':' type calculatedValue? ';'                            #question
    |   'if' OPEN_PARENTH expression CLOSE_PARENTH
                OPEN_BRACKET statement+ CLOSE_BRACKET                           #ifStatement
    |   'if' OPEN_PARENTH expression CLOSE_PARENTH
                OPEN_BRACKET ifCase+=statement CLOSE_BRACKET
                    'else' OPEN_BRACKET elseCase+=statement CLOSE_BRACKET       #ifElseStatement
    ;

expression
    :   STRING                                  #stringLiteral
    |   NUMBER                                  #integerLiteral
    |   ID                                      #parameter
    |   '(' expression+ ')'                     #parameterGroup
    |   '!' expression                          #negation
    |   left=expression '/' right=expression    #division
    |   left=expression '*' right=expression    #multiplication
    |   left=expression '-' right=expression    #subtraction
    |   left=expression '+' right=expression    #addition
    |   left=expression '>' right=expression    #greaterThan
    |   left=expression '<' right=expression    #lessThan
    |   left=expression '==' right=expression   #equal
    |   left=expression '!=' right=expression   #notEqual
    |   left=expression '<=' right=expression   #lessThanEqualTo
    |   left=expression '>=' right=expression   #greaterThanEqualTo
    |   left=expression 'AND' right=expression  #logicalAnd
    |   left=expression 'OR' right=expression   #logicalOr
    ;

calculatedValue
    :   '=' value=expression
    ;

type
    : 'boolean' #booleanType
    | 'integer' #integerType
    | 'string'  #stringType
    ;

//lexer

ID:   [a-zA-Z0-9$_]+ ;

STRING: '"' .*? '"';

NUMBER
    :    ('0'..'9')+ ('.' ('0'..'9')+)?
    ;

WHITESPACE
    :   (' ' | '\t' | '\r'| '\n') -> channel(HIDDEN)
    ;

MULTI_LINE_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

SINGLE_LINE_COMMENT
    : '//' .*? -> channel(HIDDEN)
    ;

OPEN_BRACKET :  '{' ;
CLOSE_BRACKET : '}' ;
OPEN_PARENTH :  '(' ;
CLOSE_PARENTH : ')' ;
