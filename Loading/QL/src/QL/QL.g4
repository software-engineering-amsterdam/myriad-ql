grammar QL;

@parser::header
{
    import QL.ast.atom.*;
    import QL.ast.*;
    import QL.ast.type.*;
    import QL.ast.expression.*;
}

form returns [Form result] 
 : 'form' ID block { $result = new Form($ID.text, $block.result, $ctx.start.getLine()); };

block returns [Block result]
 : '{' blockItems { $result = new Block($blockItems.result, $blockItems.start.getLine()); } '}'
 ;

blockItems returns [List<BlockItem> result]
@ init {
	$result = new ArrayList<BlockItem>();
}
 : ( ID ':' str type      { $result.add(new Question($ID.text, $str.result, $type.result, $ctx.start.getLine())); }
 |   ID ':' str type expr { $result.add(new ComputedQuestion($ID.text, $str.result, $type.result, $expr.result, $ctx.start.getLine())); }
 |   'if' '(' expr ')' ifblock = block 'else' elseblock = block { $result.add(new IfElseStatement($expr.result, $ifblock.result, $elseblock.result, $ctx.start.getLine())); }
 |   'if' '(' expr ')' block { $result.add(new Statement($expr.result, $block.result, $ctx.start.getLine())); }
   )*
 ;

type returns [Type result]
 : 'boolean' { $result = new BooleanType($ctx.start.getLine()); }
 | 'integer' { $result = new IntegerType($ctx.start.getLine()); }
 | 'string'  { $result = new StringType($ctx.start.getLine()); }
 ;

expr returns [Expression result]
 : '(' expr ')' { $result = $expr.result; }
 | lhs = expr '/'  rhs = expr { $result = new DivExpr($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '*'  rhs = expr { $result = new MulExpr($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '+'  rhs = expr { $result = new AddExpr($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '-'  rhs = expr { $result = new SubExpr($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '==' rhs = expr { $result = new EqExpr($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '!=' rhs = expr { $result = new NEqExpr($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '<=' rhs = expr { $result = new LEqExpr($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '>=' rhs = expr { $result = new GEqExpr($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '>'  rhs = expr { $result = new GExpr($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '<'  rhs = expr { $result = new LExpr($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '&&' rhs = expr { $result = new AndExpr($lhs.result, $rhs.result, $lhs.start.getLine()); }
 | lhs = expr '||' rhs = expr { $result = new OrExpr($lhs.result, $rhs.result, $lhs.start.getLine()); } 
 | '!' expr { $result = new NotExpr($expr.result, $expr.start.getLine()); }
 | '+' expr { $result = new PlusExpr($expr.result, $expr.start.getLine()); }
 | '-' expr { $result = new MinusExpr($expr.result, $expr.start.getLine()); } 
 | ID  { $result = new IdExpr($ID.text, $ctx.start.getLine()); }
 | atom { $result = $atom.result; }
 ;

atom returns [Atom result]
 : INT    { $result = new IntegerAtom(Integer.parseInt($INT.text), $ctx.start.getLine()); }
 | str { $result = new StringAtom($str.result, $ctx.start.getLine()); }
 | BOOL   { $result = new BoolAtom(Boolean.valueOf($BOOL.text), $ctx.start.getLine()); }
 ;

str returns [String result]
 : STRING { $result = $STRING.text.substring(1, $STRING.text.length()-1); }
 ;

BOOL: 'true' | 'false';

ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT : ('0'..'9')+;

DECIMAL : INT '.' INT | '.' INT;

STRING : '"' .*? '"';

WS : [ \t\r\n]+ -> skip;

COMMENT : ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/') -> skip;