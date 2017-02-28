grammar QL;

@parser::header
{
    import ast.atom.*;
    import ast.*;
    import ast.type.*;
    import ast.expression.*;
}

form returns [Form result] 
 : 'form' ID block { $result = new Form($ID.text, $block.result, $block.start.getLine()); };

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
 : ID ':' STRING type { $result = new Question($ID.text, $STRING.text, $type.result, $type.start.getLine()); }
 | ID ':' STRING type computed_question { $result = new ComputedQuestion($ID.text, $STRING.text, $type.result, $computed_question.result.evaluate(), $type.start.getLine());}
 ;

type returns [Type result]
 : 'boolean' { $result = new BooleanType(); }
 | 'date' 	{ $result = new DateType(); }
 | 'decimal' { $result = new DecimalType(); }
 | 'integer' { $result = new IntegerType(); }
 | 'money'   { $result = new MoneyType(); }
 | 'string'  { $result = new StringType(); }
 ;

computed_question returns [Expression result]
 : parenthesisExpr { $result = $parenthesisExpr.result; }
 ;

statement returns [Statement result]
 : IF parenthesisExpr  block (ELSE IF parenthesisExpr block)* (ELSE block)? { $result = new Statement($parenthesisExpr.result, $block.result);} // TODO else does not work
 ;

parenthesisExpr returns [Expression result]
 : '(' expr ')' { $result = $expr.result; }
 | ('(' atom ')' | atom ) { $result = $atom.result; }
 | ('(' ID ')' | ID ) { $result = new IdExpression($ID.text); }
 ;

//expr2 returns [Expression result]
// : lhs = parenthesisExpr op=('/' | '*') rhs = parenthesisExpr
//    {
//      if ($op.text.equals("/")) {
//        $result = new DivExpression($lhs.result, $rhs.result);
//      }
//      if ($op.text.equals("*")) {
//        $result = new MulExpression($lhs.result, $rhs.result);
//      }
//    }
//
// ;

expr returns [Expression result]
 : lhs = parenthesisExpr binOp rhs = parenthesisExpr { $result = $binOp.result.setElements($lhs.result, $rhs.result); }
 | unaryOp parenthesisExpr { $result = $unaryOp.result.setElements($parenthesisExpr.result); }
 // | unaryOp atom {  $result = $unaryOp.result.setElements($atom.result); }
 // | atom { $result = $atom.result; }
 ;

binOp returns [BinaryExpression result]
 : '/'  { $result = new DivExpression(); }
 | '*'  { $result = new MulExpression(); }
 | '+'  { $result = new AddExpression(); }
 | '-'  { $result = new SubExpression(); }
 | '==' { $result = new EqExpression(); }
 | '!=' { $result = new NEqExpression(); }
 | '<=' { $result = new LEqExpression(); }
 | '>=' { $result = new GEqExpression(); }
 | '>'  { $result = new GExpression(); }
 | '<'  { $result = new LExpression(); }
 | '&&' { $result = new AndExpression(); }
 | '||' { $result = new OrExpression(); }
 ;

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

 ;

// TODO look up conventions tokens/names capital letters
BOOL: 'true' | 'false';
IF : 'if';
ELSE : 'else';
WHILE : 'while';

ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT : ('0'..'9')+;

TWO_DIGIT : ('0'..'9')('0'..'9');

DECIMAL : INT '.' INT | '.' INT;
MONEY : INT '.' TWO_DIGIT;

DDMMYY : TWO_DIGIT '.' TWO_DIGIT '.' TWO_DIGIT TWO_DIGIT; // TODO check valid date

STRING : '"' .*? '"';

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

// http://stackoverflow.com/questions/14778570/antlr-4-channel-hidden-and-options
COMMENT : ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/') -> skip;