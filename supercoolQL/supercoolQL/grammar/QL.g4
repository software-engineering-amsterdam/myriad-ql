grammar QL;

// parser

// https://github.com/antlr/antlr4/blob/master/doc/parser-rules.md  --parser rule reference

formDeclaration
    :   'form' id=ID OPEN_BRACKET statement* CLOSE_BRACKET
    ;

statement
    :   text=STRING ID ':' type calculatedValue? ';'    #question
    |   'if' '(' expression ')'
                '{' statement+ '}'                      #ifStatement
    |   'if' '(' expression ')'
                '{' ifCase+=statement '}'
                'else' '{' elseCase+=statement '}'      #ifElseStatement
    ;

expression
    :   STRING                                          #stringLiteral
    |   NUMBER                                          #integerLiteral
    |   ID                                              #parameter
    |   op=(TRUE|FALSE)                                 #booleanLiteral
    |   '(' expression ')'                              #parameterGroup
    |   '!' expression                                                       #negation
    |   left=expression op=('/'|'*') right=expression                        #mulDiv
    |   left=expression op=('-'|'+') right=expression                        #addSub
    |   left=expression op=('>'|'<'|'=='|'!='|'<='|'>=') right=expression    #comparation
    |   left=expression '&&' right=expression           #logicalAnd
    |   left=expression '||' right=expression           #logicalOr
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

ID:   [a-zA-Z_]+[a-zA-Z0-9_]* ;

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
    : '//' ~[\r\n]* -> channel(HIDDEN)
    ;

TRUE: 'TRUE' ;
FALSE: 'FALSE' ;

OPEN_BRACKET :  '{' ;
CLOSE_BRACKET : '}' ;
OPEN_PARENTH :  '(' ;
CLOSE_PARENTH : ')' ;
