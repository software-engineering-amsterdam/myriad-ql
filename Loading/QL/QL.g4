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

// TODO combine question and statement
block returns [Block result]
 : '{' blockItems { $result = new Block($blockItems.result); } '}'
 ;

// TODO decide on maximum characters on one line
blockItems returns [List<BlockItem> result]
@ init {
	List<BlockItem> result = new ArrayList<BlockItem>();
}
 : ( ID ':' STRING type      { $result.add(new Question($ID.text, $STRING.text, $type.result, $type.start.getLine()); }
 |   ID ':' STRING type expr { $result = new ComputedQuestion($ID.text, $STRING.text, $type.result, $expr.result);}
 |   'if' '(' expr ')' block ('else' block)? { $result.add(new Statement($expr.result, $block.result)); } // TODO add else
   )*
 ;

type returns [Type result]
 : 'boolean' { $result = new BooleanType(); }
 | 'date' 	 { $result = new DateType(); }
 | 'decimal' { $result = new DecimalType(); }
 | 'integer' { $result = new IntegerType(); }
 | 'money'   { $result = new MoneyType(); }
 | 'string'  { $result = new StringType(); }
 ;

// TODO implement precedence
expr returns [Expression result]
 : '(' expr ')' { $result = $expr.result; }
 | lhs = expr '/'  rhs = expr { $result = new DivExpression($lhs.result, $rhs.result); }
 | lhs = expr '*'  rhs = expr { $result = new MulExpression($lhs.result, $rhs.result); }
 | lhs = expr '+'  rhs = expr { $result = new AddExpression($lhs.result, $rhs.result); }
 | lhs = expr '-'  rhs = expr { $result = new SubExpression($lhs.result, $rhs.result); }
 | lhs = expr '==' rhs = expr { $result = new EqExpression($lhs.result, $rhs.result); }
 | lhs = expr '!=' rhs = expr { $result = new NEqExpression($lhs.result, $rhs.result); }
 | lhs = expr '<=' rhs = expr { $result = new LEqExpression($lhs.result, $rhs.result); }
 | lhs = expr '>=' rhs = expr { $result = new GEqExpression($lhs.result, $rhs.result); }
 | lhs = expr '>'  rhs = expr { $result = new GExpression($lhs.result, $rhs.result); }
 | lhs = expr '<'  rhs = expr { $result = new LExpression($lhs.result, $rhs.result); }
 | lhs = expr '&&' rhs = expr { $result = new AndExpression($lhs.result, $rhs.result); }
 | lhs = expr '||' rhs = expr { $result = new OrExpression($lhs.result, $rhs.result); } 
 | '!' expr { $result = new NotExpression($expr.result); }
 | '+' expr { $result = new PlusExpression($expr.result); }
 | '-' expr { $result = new MinusExpression($expr.result); } 
 | atom { $result = $atom.result; }
 | ID  { $result = new IdExpression($ID.text); }
 ;

atom returns [Atom result]
 : INT    { $result = new IntegerAtom(Integer.parseInt($INT.text)); }
 | STRING { $result = new StringAtom($STRING.text); }
 | BOOL   { $result = new BoolAtom(Boolean.valueOf($BOOL.text)); }
 ;

BOOL: 'true' | 'false';

ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT : ('0'..'9')+;

DECIMAL : INT '.' INT | '.' INT;

STRING : '"' .*? '"';

WS : [ \t\r\n]+ -> skip;

COMMENT : ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/') -> skip;