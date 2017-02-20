grammar QL;

@parser::header
{
package org.uva.hatt.taxform.grammars;
}

@lexer::header
{
package org.uva.hatt.taxform.grammars;
}

form        : 'form' Identifier '{' formBody* '}';

formBody    : question
            | 'if ('expression') {' question* '}';

question    : StringLiteral Identifier ':' valueType;

valueType   : 'boolean'
            | 'integer'
            | 'string'
            | 'money';

expression  : (StringLiteral | BooleanLiteral | IntegerLiteral | Identifier)
            | (Unary expression)
            | expression (operator) expression
            ;

//operators in order of precedence from highest to lowest
operator    : Unary
            | Multiplicative
            | Additive
            | Relational
            | Equality
            | ConditionalAND
            | ConditionalOR
            ;

// Tokens
WS            :	(' ' | '\t' | '\n' | '\r')-> channel(HIDDEN);
Comment         : ('/*' .* '*/') -> channel(HIDDEN);
Identifier      : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
StringLiteral   : '"' (~'"')* '"';
BooleanLiteral  : 'true' | 'false';
IntegerLiteral  : ('0'..'9')*;

//operators in order of precedence from highest to lowest
Unary           : '!';
Multiplicative  : ' * ' | ' / ' | ' % ';
Additive        : ' + ' | ' - ';
Relational      : ' < ' | ' > ' | ' <= ' | ' >= ';
Equality        : ' == ' | ' != ';
ConditionalAND  : ' && ';
ConditionalOR   : ' || ';