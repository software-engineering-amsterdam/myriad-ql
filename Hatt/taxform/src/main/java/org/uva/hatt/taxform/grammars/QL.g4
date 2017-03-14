grammar QL;

@parser::header
{
package org.uva.hatt.taxform.grammars;
}

@lexer::header
{
package org.uva.hatt.taxform.grammars;
}

form            : 'form' Identifier '{' items* '}';

items           : question
                | computedQuestion
                | ifThen
                | ifThenElse;

question        : StringLiteral Identifier ':' valueType;
computedQuestion: StringLiteral Identifier ':' valueType '=' expression;

ifThen          : ifBlock;
ifThenElse      : ifBlock elseBlock;

ifBlock         : 'if (' expression ') {' items* '}';
elseBlock       : 'else {' items* '}';

valueType       : 'boolean'                                                 # boolean
                | 'integer'                                                 # integer
                | 'string'                                                  # string
                | 'money'                                                   # money
                ;

expression      : BooleanLiteral                                            # booleanLiteral
                | StringLiteral                                             # stringLiteral
                | IntegerLiteral                                            # integerLiteral
                | Identifier                                                # identifier
                | '(' expression ')'                                        # groupedExpression
                | left=expression op='/'  right=expression                  # computationExpression
                | left=expression op='*'  right=expression                  # computationExpression
                | left=expression op='-'  right=expression                  # computationExpression
                | left=expression op='+'  right=expression                  # computationExpression
                | left=expression op='<'  right=expression                  # computationExpression
                | left=expression op='<=' right=expression                  # computationExpression
                | left=expression op='>'  right=expression                  # computationExpression
                | left=expression op='>=' right=expression                  # computationExpression
                | left=expression op='==' right=expression                  # booleanExpression
                | left=expression op='!=' right=expression                  # booleanExpression
                | left=expression op='&&' right=expression                  # booleanExpression
                | left=expression op='||' right=expression                  # booleanExpression
                ;

// Tokens
WS              : (' ' | '\t' | '\n' | '\r')-> channel(HIDDEN);
Comment         : ('/*' .* '*/') -> channel(HIDDEN);
BooleanLiteral  : ('true' | 'false');
Identifier      : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
StringLiteral   : '"' (~'"')* '"';
IntegerLiteral  : ('0'..'9')+;