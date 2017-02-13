grammar QL;

@parser::header
{
package org.uva.hatt.taxform.grammars;
}

@lexer::header
{
package org.uva.hatt.taxform.grammars;
}

form : 'form' formId '{' questions* '}';

formId : Ident;

questions: question
         | 'if (' Ident') {' questions* '}';

question: Str Ident ':' valueType;

valueType: 'boolean'
         | 'integer'
         | 'string'
         | 'money';

// Tokens
WS  :	(' ' | '\t' | '\n' | '\r') -> channel(HIDDEN)
    ;

COMMENT
     : ('/*' .* '*/') -> channel(HIDDEN)
    ;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Str: '"' .* '"';