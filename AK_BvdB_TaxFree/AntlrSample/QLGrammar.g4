grammar QLGrammar;

@header {
package org.uva.taxfree.gen;
}

form : 'form ' formId=VARIABLE_LITERAL '{' statement* '}';
statement : LABEL_QUESTION '->' VARIABLE_LITERAL ':' varType #question
          | LABEL_DESCRIPTION '->' VARIABLE_LITERAL ':' varType '=' expression #calculation
          | 'if (' expression ')' '{' statement* '}' #ifStatement
          | 'if (' expression ')' '{' thenBlock=statement* '}' 'else' '{' elseBlock=statement* '}' #ifElseStatement
          ;

expression : BOOLEAN_LITERAL                                #booleanLiteral
           | INTEGER_LITERAL                                #integerLiteral
           | STRING_LITERAL                                 #stringLiteral
           | VARIABLE_LITERAL                               #varNameLiteral
           | '(' expression ')'                             #parenthesizedExpression
           | left=expression op=(OPERATOR_PLUS|OPERATOR_MINUS) right=expression  #calculationExpression
           | left=expression operator='/' right=expression  #calculationExpression
           | left=expression operator='*' right=expression  #calculationExpression
           | left=expression operator='<' right=expression  #calculationExpression
           | left=expression operator='>' right=expression  #calculationExpression
           | left=expression operator='>=' right=expression #calculationExpression
           | left=expression operator='<=' right=expression #calculationExpression
           | left=expression operator='==' right=expression #uniformExpression
           | left=expression operator='!=' right=expression #uniformExpression
           | left=expression operator='&&' right=expression #booleanExpression
           | left=expression operator='||' right=expression #booleanExpression

//           | left=expression operator='-' right=expression  #calculationExpression
//           | left=expression operator='+' right=expression  #calculationExpression
//           | left=expression operator='/' right=expression  #calculationExpression
//           | left=expression operator='*' right=expression  #calculationExpression
//           | left=expression operator='<' right=expression  #calculationExpression
//           | left=expression operator='>' right=expression  #calculationExpression
//           | left=expression operator='>=' right=expression #calculationExpression
//           | left=expression operator='<=' right=expression #calculationExpression
//           | left=expression operator='==' right=expression #uniformExpression
//           | left=expression operator='!=' right=expression #uniformExpression
//           | left=expression operator='&&' right=expression #booleanExpression
//           | left=expression operator='||' right=expression #booleanExpression
           ;

varType : 'boolean' # booleanType
        | 'string'  # stringType
        | 'integer' # integerType
        ;

// The whitespace layout
WS : [ \t\r\n]+ -> skip;
// Comment layout
COMMENT : [/][/]~[\n]* -> skip;
// Types
LABEL_QUESTION : '"'~[?]+'?"';
LABEL_DESCRIPTION : '"'~[:]+':"';
BOOLEAN_LITERAL : ('true' | 'false');
INTEGER_LITERAL : [0-9]+;
STRING_LITERAL : '"'~["]+'"';
// Operators
OPERATOR_MINUS : '-';
OPERATOR_PLUS : '+';
// Variables
VARIABLE_LITERAL : [a-zA-Z]+;