grammar QLGrammar;

@header {
package org.uva.taxfree.gen;
}

form : 'form ' formId=STRING_LITERAL '{' statement* '}';
statement : question
          | calculation
          | ifStatement
          | ifElseStatement
          ;

question : QUESTION '->' STRING_LITERAL ':' varType;
calculation : DESCRIPTION '->' STRING_LITERAL ':' varType '=' expression;
ifStatement : 'if (' expression ')' '{' question* '}';
ifElseStatement : ifStatement 'else' '{' question* '}';

expression : BOOLEAN_LITERAL                                #booleanLiteral
           | INTEGER_LITERAL                                #integerLiteral
           | STRING_LITERAL                                 #stringLiteral
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
WS : [ \t\r\n]+ -> skip ;
// Types
QUESTION : '"'~[?]+'?"';
DESCRIPTION : '"'~[:]+':"';
BOOLEAN_LITERAL : ('true' | 'false');
INTEGER_LITERAL : [0-9]+;
STRING_LITERAL : [a-zA-Z]+;