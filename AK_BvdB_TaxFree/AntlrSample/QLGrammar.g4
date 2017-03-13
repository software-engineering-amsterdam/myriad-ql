grammar QLGrammar;

@header {
package org.uva.taxfree.gen;
}

form : 'form ' formId=VARIABLE_LITERAL '{' statement* '}';
statement : LABEL '->' VARIABLE_LITERAL ':' varType #question
          | LABEL '->' VARIABLE_LITERAL ':' varType '=' expression #calculation
          | 'if (' expression ')' '{' thenBlock=statement* '}' #ifStatement
          | 'if (' expression ')' '{' (thenBlocks+=statement)* '}' 'else' '{' (elseBlocks+=statement)* '}' #ifElseStatement
          ;

expression : BOOLEAN_LITERAL                                #booleanLiteral
           | INTEGER_LITERAL                                #integerLiteral
           | STRING_LITERAL                                 #stringLiteral
           | VARIABLE_LITERAL                               #varNameLiteral
           | '(' expression ')'                             #parenthesizedExpression
           | left=expression op=(OP_MULTIPLY|OP_DIVIDE) right=expression                                    #binaryExpression
           | left=expression op=(OP_PLUS|OP_MINUS) right=expression                                         #binaryExpression
           | left=expression op=(OP_SMALLER|OP_SMALLEROREQUAL|OP_BIGGER|OP_BIGGEROREQUAL) right=expression  #binaryExpression
           | left=expression op=(OP_EQUALS|OP_NOTEQUALS) right=expression                                   #binaryExpression
           | left=expression op=OP_LOGICAL_AND right=expression                                             #binaryExpression
           | left=expression op=OP_LOGICAL_OR right=expression                                              #binaryExpression
           ;

varType : 'boolean' # booleanType
        | 'string'  # stringType
        | 'integer' # integerType
        ;

// The whitespace layout
WS : [ \t\r\n]+ -> skip;
// Comment layout
COMMENT : [/][/]~[\n]* -> skip;
// Operators
OP_MULTIPLY : '*';
OP_DIVIDE : '/';
OP_PLUS : '+';
OP_MINUS : '-';
OP_SMALLEROREQUAL : '<=';
OP_BIGGEROREQUAL : '>=';
OP_SMALLER : '<';
OP_BIGGER : '>';
OP_EQUALS : '==';
OP_NOTEQUALS : '!=';
OP_LOGICAL_AND : '&&';
OP_LOGICAL_OR : '||';
// Types
LABEL : '"'(~'"')+'"' ;
BOOLEAN_LITERAL : ('true' | 'false');
INTEGER_LITERAL : [0-9]+;
STRING_LITERAL : '"'~["]+'"';
VARIABLE_LITERAL : [a-zA-Z]+;