grammar QL;

@parser::header
{
    import ast.atom.*;
    import ast.*;
    import ast.type.*;
    import ast.expression.*;
}

form returns [Form result] 
 : 'form' ID block { $result = new Form($ID.text, $block.result, $ctx.start.getLine()); };

// TODO combine question and statement
block returns [Block result]
 : '{' blockItems { $result = new Block($blockItems.result, $blockItems.start.getLine()); } '}'
 ;

// TODO decide on maximum characters on one line
blockItems returns [List<BlockItem> result]
@ init {
	$result = new ArrayList<BlockItem>();
}
 : ( ID ':' STRING type      { $result.add(new Question($ID.text, $STRING.text, $type.result, $ctx.start.getLine())); }
 |   ID ':' STRING type expr { $result.add(new ComputedQuestion($ID.text, $STRING.text, $type.result, $expr.result, $ctx.start.getLine())); }
 |   'if' '(' expr ')' block ('else' block)? { $result.add(new Statement($expr.result, $block.result, $ctx.start.getLine())); } // TODO add else
   )*
 ;

type returns [Type result]
 : 'boolean' { $result = new BooleanType($ctx.start.getLine()); }
 | 'integer' { $result = new IntegerType($ctx.start.getLine()); }
 | 'string'  { $result = new StringType($ctx.start.getLine()); }
 ;

// TODO implement precedence
expr returns [Expression result]
 : '(' expr ')' { $result = $expr.result; }
 | lhs = expr '/'  rhs = expr { $result = new DivExpression($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '*'  rhs = expr { $result = new MulExpression($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '+'  rhs = expr { $result = new AddExpression($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '-'  rhs = expr { $result = new SubExpression($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '==' rhs = expr { $result = new EqExpression($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '!=' rhs = expr { $result = new NEqExpression($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '<=' rhs = expr { $result = new LEqExpression($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '>=' rhs = expr { $result = new GEqExpression($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '>'  rhs = expr { $result = new GExpression($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '<'  rhs = expr { $result = new LExpression($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '&&' rhs = expr { $result = new AndExpression($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '||' rhs = expr { $result = new OrExpression($lhs.result, $rhs.result, $lhs.start.getLine()); } 
 | '!' expr { $result = new NotExpression($expr.result, $expr.start.getLine()); }
 | '+' expr { $result = new PlusExpression($expr.result, $expr.start.getLine()); }
 | '-' expr { $result = new MinusExpression($expr.result, $expr.start.getLine()); } 
 | ID  { $result = new IdExpression($ID.text, $ctx.start.getLine()); }
 | atom { $result = $atom.result; }
 ;

atom returns [Atom result]
 : INT    { $result = new IntegerAtom(Integer.parseInt($INT.text), $ctx.start.getLine()); }
 | STRING { $result = new StringAtom($STRING.text, $ctx.start.getLine()); }
 | BOOL   { $result = new BoolAtom(Boolean.valueOf($BOOL.text), $ctx.start.getLine()); }
 ;

BOOL: 'true' | 'false';

ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT : ('0'..'9')+;

DECIMAL : INT '.' INT | '.' INT;

STRING : '"' .*? '"';

WS : [ \t\r\n]+ -> skip;

COMMENT : ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/') -> skip;