grammar QLGrammar;

@header {
package org.uva.taxfree.gen;
}

form : 'form ' formId=VARIABLE_LITERAL '{' statement* '}';
statement : question
          | calculation
          | ifStatement
          | ifElseStatement
          ;

question : QUESTION '->' VARIABLE_LITERAL ':' varType;
calculation : DESCRIPTION '->' VARIABLE_LITERAL ':' varType '=' expression;
ifStatement : 'if (' expression ')' '{' statement* '}';
ifElseStatement : ifStatement 'else' '{' statement* '}';

expression : BOOLEAN_LITERAL                                #booleanLiteral
           | INTEGER_LITERAL                                #integerLiteral
           | STRING_LITERAL                                 #stringLiteral
           | VARIABLE_LITERAL                               #varNameLiteral
           | '(' expression ')'                             #parenthesizedExpression
           | left=expression operator='/' right=expression  #calculationExpression
           | left=expression operator='*' right=expression  #calculationExpression
           | left=expression operator='-' right=expression  #calculationExpression
           | left=expression operator='+' right=expression  #calculationExpression
           | left=expression operator='<' right=expression  #calculationExpression
           | left=expression operator='>' right=expression  #calculationExpression
           | left=expression operator='>=' right=expression #calculationExpression
           | left=expression operator='<=' right=expression #calculationExpression
           | left=expression operator='||' right=expression #booleanExpression
           | left=expression operator='&&' right=expression #booleanExpression
           | left=expression operator='!=' right=expression #uniformExpression
           | left=expression operator='==' right=expression #uniformExpression
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
QUESTION : '"'~[?]+'?"';
DESCRIPTION : '"'~[:]+':"';
BOOLEAN_LITERAL : ('true' | 'false');
INTEGER_LITERAL : [0-9]+;
STRING_LITERAL : '"'~["]+'"';
//FORMID : [a-zA-Z]+;
VARIABLE_LITERAL : [a-zA-Z]+;