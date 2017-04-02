grammar qls;

@parser::header
{
    import qls.ast.*;
    import qls.ast.widget.*;
    import QL.ast.type.*;
    import QL.ui.Style;
}


stylesheet returns [Stylesheet result] 
 : 'stylesheet' ID pages { $result = new Stylesheet($ID.text, $pages.result, $ctx.start.getLine()); };
 
 pages returns [List<Page> result]
 @ init {
	$result = new ArrayList<Page>();
}
 : ( 'page' ID '{' defaultWidgets sections  '}' { $result.add(new Page($ID.text, $sections.result, $defaultWidgets.result, $ctx.start.getLine())); })+
 | ( 'page' ID '{' sections '}' { $result.add(new Page($ID.text, $sections.result, new ArrayList<DefaultWidget>(), $ctx.start.getLine())); })+
 ;
 
 sections returns [List<Section> result]
 @ init {
	$result = new ArrayList<Section>();
}
 : ('section' STRING '{'? defaultWidgets questions  '}'? { $result.add(new Section($STRING.text, $questions.result, $defaultWidgets.result, $ctx.start.getLine())); }
 |  'section' STRING '{'? questions '}'? { $result.add(new Section($STRING.text, $questions.result, new ArrayList<DefaultWidget>(), $ctx.start.getLine())); })+
 ;

questions returns [List<Question> result]
@ init {
	$result = new ArrayList<Question>();
}
 : ('question' ID widget { $result.add(new QuestionWithWidget($ID.text, $widget.result, $ctx.start.getLine())); }
 |  'question' ID { $result.add(new Question($ID.text, $ctx.start.getLine())); }
   )+
 ;


widget returns [Widget result]
 : 'widget' 'checkbox' { $result = new Checkbox($ctx.start.getLine()); }
 | 'widget' 'radio(' param1 = STRING ',' param2 = STRING ')' { $result = new Radio($param1.text, $param2.text, $ctx.start.getLine()); }
 | 'widget' 'spinbox' { $result = new Spinbox($ctx.start.getLine()); }
 | 'widget' 'slider' { $result = new Slider($ctx.start.getLine()); }
 | 'widget' 'numberfield' { $result = new NumberField($ctx.start.getLine()); }
 | 'widget' 'textfield' { $result = new TextField($ctx.start.getLine()); }
 | 'widget' 'dropdown(' param1 = STRING ',' param2 = STRING ')' { $result = new DropDown($param1.text, $param2.text, $ctx.start.getLine()); }
 ;
  
defaultWidgets returns [List<DefaultWidget> result]
@ init {
	$result = new ArrayList<DefaultWidget>();
}
 : ('default' type widget { $result.add(new DefaultWidget($type.result, $widget.result, $ctx.start.getLine())); }
 | 'default' type 
   'width:' width = INT
   'font:' font = STRING
   'fontsize:' fontSize = INT
   'color:' color =  STRING
   widget { $result.add(new DefaultStyle(new Style(Integer.parseInt($width.text), $font.text, 
     Integer.parseInt($fontSize.text), $color.text, $type.result), $widget.result, $ctx.start.getLine())); }
   )+
 ;
 
 type returns [Type result]
 : 'boolean' { $result = new BooleanType($ctx.start.getLine()); }
 | 'integer' { $result = new IntegerType($ctx.start.getLine()); }
 | 'string'  { $result = new StringType($ctx.start.getLine()); }
 ;
 
 
// TODO defaultType has many commandline arguments  
//defaultType returns [List<DefaultType> result]
//@ init {
//	$result = new ArrayList<DefaultType>();
//}
//: 'default' ID 
//  'width:' width = INT
//  'font:' font = STRING
//  'fontsize:' fontSize = INT
//  'color:' color =  STRING
//  widget { $result.add(new DefaultType($ID.text, Integer.parseInt($width.text), $font.text, 
//  	Integer.parseInt($fontSize.text), $color.text, $widget.result, $ctx.start.getLine())); };
//  	
  

// TODO you can specify the default width / font multiple times 
//defOptions returns [List<Option> result]
//: 'width:' INT { $result.add(new Width(Integer.parseInt($INT.text)); }
//  'font:' STRING { $result.add(new Font($STRING.text); }
//  'fontsize:' STRING { $result.add(new FontSize($STRING.text)); }
//  'color:' INT { $result.add(new Color(Integer.parseInt($INT.text))); };

//
//defaultWidget returns [DefaultWidget result]
//@ init {
//	$result = new ArrayList<DefaultWidget>();
//}
//: 'default' type widget { $result.add(new DefaultWidget(type, widget)); };

// TODO copied from QL.g4 : can we insert it?

ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT : ('0'..'9')+;

STRING : '"' .*? '"';

WS : [ \t\r\n]+ -> skip;

COMMENT : ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/') -> skip;
