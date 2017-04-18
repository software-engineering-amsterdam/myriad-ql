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
                | left=expression op='/'  right=expression                  # division
                | left=expression op='*'  right=expression                  # multiplication
                | left=expression op='-'  right=expression                  # subtraction
                | left=expression op='+'  right=expression                  # addition
                | left=expression op='<'  right=expression                  # lessThan
                | left=expression op='<=' right=expression                  # lessThanOrEqual
                | left=expression op='>'  right=expression                  # greaterThan
                | left=expression op='>=' right=expression                  # greaterThanOrEqual
                | left=expression op='==' right=expression                  # equal
                | left=expression op='!=' right=expression                  # notEqual
                | left=expression op='&&' right=expression                  # logicalAnd
                | left=expression op='||' right=expression                  # logicalOr
                ;

// Tokens
WS              : (' ' | '\t' | '\n' | '\r')-> channel(HIDDEN);
Comment         : ('/*' .* '*/') -> channel(HIDDEN);
BooleanLiteral  : ('true' | 'false');
Identifier      : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
StringLiteral   : '"' (~'"')* '"';
IntegerLiteral  : ('0'..'9')+;