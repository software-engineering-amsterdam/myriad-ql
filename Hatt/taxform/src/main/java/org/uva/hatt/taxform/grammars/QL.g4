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
                | conditional;

question        : StringLiteral Identifier ':' valueType computedValue?;
conditional     : ifBlock elseBlock?;

ifBlock         : 'if (' expression ') {' items* '}';
elseBlock       : 'else {' items* '}';

computedValue   : '=' expression;

valueType       : 'boolean'                                                 # boolean
                | 'integer'                                                 # integer
                | 'string'                                                  # string
                | 'money'                                                   # money
                ;

expression      : StringLiteral
                | BooleanLiteral
                | IntegerLiteral
                | Identifier
                | '(' expression ')'
                | Unary expression
                | expression operator expression
                ;

//operators in order of precedence from highest to lowest
operator        : Unary
                | Multiplicative
                | Additive
                | Relational
                | Equality
                | ConditionalAND
                | ConditionalOR
                ;

// Tokens
WS              : (' ' | '\t' | '\n' | '\r')-> channel(HIDDEN);
Comment         : ('/*' .* '*/') -> channel(HIDDEN);
Identifier      : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
StringLiteral   : '"' (~'"')* '"';
BooleanLiteral  : 'true' | 'false';
IntegerLiteral  : ('0'..'9')+;

//operators in order of precedence from highest to lowest
Unary           : '!';
Multiplicative  : ' * ' | ' / ' | ' % ';
Additive        : ' + ' | ' - ';
Relational      : ' < ' | ' > ' | ' <= ' | ' >= ';
Equality        : ' == ' | ' != ';
ConditionalAND  : ' && ';
ConditionalOR   : ' || ';