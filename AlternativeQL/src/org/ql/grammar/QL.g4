grammar QL;

form
    :   'form' id=identifier '{' statement* '}'
    ;

statement
    :   type id=identifier ':' text=questionText defaultValue? ';'      #question
    |   'if' '(' expression ')' '{'
                (thenStatements+=statement)* '}'                        #ifThen
    |   'if' '(' expression ')' '{'
            (thenStatements+=statement)* '}'
        ('else' '{'
            (elseStatements+=statement)* '}')?                          #ifElseThen
    ;

questionText
    : STRING_LITERAL
    ;

defaultValue
    :   '=' value=expression
    ;

expression
    :   BOOLEAN_LITERAL                                        #booleanLiteral
    |   STRING_LITERAL                                         #stringLiteral
    |   DECIMAL_LITERAL                                        #decimalLiteral
    |   INTEGER_LITERAL                                        #integerLiteral
    |   ID                                                     #parameter
    |   '(' expression ')'                                     #group
    |   '!' expression                                         #negation
    |   expression '++'                                        #increment
    |   expression '--'                                        #decrement
    |   <assoc=left> left=expression '/' right=expression      #division
    |   <assoc=left> left=expression '*' right=expression      #product
    |   left=expression '-' right=expression                   #subtraction
    |   left=expression '+' right=expression                   #addition
    |   left=expression '>' right=expression                   #greaterThan
    |   left=expression '<' right=expression                   #lowerThan
    |   left=expression '==' right=expression                  #equals
    |   left=expression '!=' right=expression                  #notEqual
    |   left=expression '<=' right=expression                  #lowerThanOrEqual
    |   left=expression '>=' right=expression                  #greaterThanOrEqual
    |   left=expression '&&' right=expression                  #logicalAnd
    |   left=expression '||' right=expression                  #logicalOr
    ;

identifier
    :   ID
    ;

type
    :   'boolean'   #typeBoolean
    |   'float'     #typeFloat
    |   'integer'   #typeInteger
    |   'string'    #typeString
    |   'money'     #typeMoney
    |   'date'      #typeDate
    ;

LINE_COMMENT : '//' ~[\r\n]* -> channel(HIDDEN);

// literal
STRING_LITERAL : '"' (ESCAPE_QUOTE | ~ ["\\])* '"';
BOOLEAN_LITERAL : 'true' | 'false';
DECIMAL_LITERAL : [0-9]+ '.' [0-9]+;
INTEGER_LITERAL : [0-9]+;

// names
ID : [a-zA-Z][a-zA-Z0-9_]+;

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

fragment ESCAPE_QUOTE
   : '\\' (["\\/bfnrt] | UNICODE)
   ;
fragment UNICODE
   : 'u' HEX HEX HEX HEX
   ;
fragment HEX
   : [0-9a-fA-F]
   ;