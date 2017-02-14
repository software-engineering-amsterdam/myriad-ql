grammar QL;

@parser::header
{
    import ast.atom.*;
    import ast.*;
    import ast.type.*;
    import ast.expression.*;
}

form returns [Form result] 
		: 'form' ID block { $result = new Form($ID.text, $block.result); };

block returns [Block result]
		@init {
			$result = new Block();
		}
		: '{' (question { $result.addQuestion($question.result); } 
			| statement { $result.addStatement($statement.result); })*  
		  '}'
		; 

// TODO decide on maximum characters on one line
question returns [Question result]
		: ID ':' STRING type
		{ $result = new Question($ID.text, $STRING.text, $type.result); }
		| ID ':' STRING type computed_question
		{ $result = new ComputedQuestion($ID.text, $STRING.text, $type.result, Integer.parseInt($computed_question.text));}
		;

type returns [Type result]
	: 'boolean' { $result = new BooleanType(); }
	| 'date' 	{ $result = new DateType(); }
	| 'decimal' { $result = new DecimalType(); } 
	| 'integer' { $result = new IntegerType(); }
	| 'money'   { $result = new MoneyType(); }
	| 'string'  { $result = new StringType(); }
	;

computed_question: '(' type '-' type | type '+' type ')' ;

statement returns [Statement result]
 : IF parenthesisExpr block (ELSE IF parenthesisExpr block)* (ELSE block)? { $result = new Statement($parenthesisExpr.result, $block.result);}
 | WHILE parenthesisExpr block { $result = new Statement($parenthesisExpr.result, $block.result);}
 ;

parenthesisExpr returns [Expression result]
 : '(' expr ')' { $result = $expr.result; };

expr returns [Expression result]
 :  lhs = atom binOp rhs = atom { $result = $binOp.result.setElements($lhs.result, $rhs.result); }
 | unaryOp atom {  $result = $unaryOp.result.setElements($atom.result); }
 | atom { $result = $atom.result; }
 ;

binOp returns [BinaryExpression result]
 : '==' { $result = new EqExpression(); }
 | '!=' { $result = new NEqExpression(); }
 | '<=' { $result = new LEqExpression(); }
 | '>=' { $result = new GEqExpression(); }
 | '>'  { $result = new GExpression(); }
 | '<'  { $result = new LExpression(); }
 | '+'  { $result = new AddExpression(); }
 | '-'  { $result = new SubExpression(); }
 | '/'  { $result = new DivExpression(); }
 | '*'  { $result = new MulExpression(); }
 | '&&' { $result = new AndExpression(); }
 | '||' { $result = new OrExpression(); }
 ;

// TODO plus and minus
unaryOp returns [UnaryExpression result]
  : '!' { $result = new NotExpression(); }
  | '+' { $result = new PlusExpression(); }
  | '-' { $result = new MinusExpression(); }
  ;

atom returns [Atom result]
:
// :  DECIMAL { System.out.println($DECIMAL.text);
//                      	  $result = new DecimalAtom(Float.valueOf($DECIMAL.text)); }
//  | MONEY { System.out.println($MONEY.text);
//           	  $result = new MoneyAtom(Float.valueOf($MONEY.text)); }
  | INT
 	{ System.out.println($INT.text); 
 	  $result = new IntegerAtom(Integer.parseInt($INT.text)); }
 | STRING { System.out.println($STRING.text);
    $result = new StringAtom($STRING.text);
            }
 | BOOL { System.out.println($BOOL.text);
           $result = new BoolAtom(Boolean.valueOf($BOOL.text)); }
// | DDMMYY { System.out.println($DDMMYY.text);
//            $result = new DateAtom($DDMMYY.text); }
 | ID { System.out.println($ID.text);
                 $result = new StringAtom($ID.text); }
 ;

// TODO look up conventions tokens/names capital letters
BOOL: 'true' | 'false';
IF : 'if';
ELSE : 'else';
WHILE : 'while';

ID:  ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

INT: ('0'..'9')+;

TWO_DIGIT: ('0'..'9')('0'..'9');

DECIMAL : INT '.' INT | '.' INT;
MONEY : INT '.' TWO_DIGIT;

DDMMYY : TWO_DIGIT '.' TWO_DIGIT '.' TWO_DIGIT TWO_DIGIT; // TODO check valid date

STRING: '"' .*? '"';

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

// http://stackoverflow.com/questions/14778570/antlr-4-channel-hidden-and-options
COMMENT 
    :   ( '//' ~[\r\n]* '\r'? '\n'
        | '/*' .*? '*/'
        ) -> skip
    ;