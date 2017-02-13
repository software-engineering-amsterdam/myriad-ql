grammar QL;

@parser::header
{
    import ast.IntegerAtom;
    import ast.*;
}

root returns [Form result] 
		: 'form' ID block 
		{ $result = new Form($ID.text, $block.result); };

block returns [Block result]
		@init {
			$result = new Block();
		}
		: '{' (question { $result.addQuestion($question.result); } 
			| statement { $result.addStatement($statement.result); })*  
		  '}'
		; 
		
// TODO replace by other definition		
// question returns [Question result]
	// : STRING { $result = new Question($STRING.text); };

// TODO decide on maximum characters on one line
question returns [Question result]
		: ID ':' STRING type
		{ $result = new Question($ID.text, $STRING.text, $type.text); }
		| ID ':' STRING type computed_question
		{ $result = new Question($ID.text, $STRING.text, $type.text, Integer.parseInt($computed_question.text));}
		;

type: ( 'boolean' | 'date' | 'decimal' | 'integer' | 'money' | 'string' ) ;

computed_question: '(' type '-' type | type '+' type ')' ;

statement returns [Statement result]
 : IF parenthesisExpr block (ELSE IF parenthesisExpr block)* (ELSE block)? { $result = new Statement($parenthesisExpr.result, $block.result);}
 | WHILE parenthesisExpr block { $result = new Statement($parenthesisExpr.result, $block.result);}
 ;

parenthesisExpr returns [Expression result]
 : '(' expr ')' { $result = $expr.result; };

expr returns [Expression result]
 : lhs=atom '==' rhs=atom { $result = new Expression($lhs.result, $rhs.result); };
// | atom relOp atom
// | atom boolOp atom
// | atom arithOp atom
// | '!' atom
// | '+' atom
// | '-' atom
// | atom
// ;

relOp
 : '==' | '!=' | '<=' | '>=' | '>' | '<';

boolOp
 : '&&' | '||';

arithOp
 : '+' | '-' | '/' | '*';

atom returns [Atom result]
 : // DECIMAL
 // | MONEY
   INT 
 	{ System.out.println($INT.text); 
 	  $result = new IntegerAtom(Integer.parseInt($INT.text)); }
 | STRING { System.out.println($STRING.text);
    $result = new StringAtom($STRING.text);
            }
 // | BOOL
 // | DDMMYY
 // | ID
 ;

// TODO look up conventions tokens/names capital letters
BOOL: ('true' | 'false');
IF : 'if';
ELSE : 'else';
WHILE : 'while';

ID:  ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

INT: ('0'..'9')+;

TWO_DIGIT: ('0'..'9')('0'..'9');

DECIMAL : INT '.' INT | '.' INT;
MONEY : INT '.' TWO_DIGIT;

DDMMYY : TWO_DIGIT ('.' | '-' | '/') TWO_DIGIT ('.' | '-' | '/') TWO_DIGIT ; // TODO check valid date

STRING: ('"' .*? '"');

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

// http://stackoverflow.com/questions/14778570/antlr-4-channel-hidden-and-options
COMMENT 
    :   ( '//' ~[\r\n]* '\r'? '\n'
        | '/*' .*? '*/'
        ) -> skip
    ;