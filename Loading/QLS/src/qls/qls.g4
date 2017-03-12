grammar qls;

@parser::header
{
    import qls.ast.*;
    import qls.ast.widget.*;
}


stylesheet returns [Stylesheet result] 
 : 'stylesheet' ID pages { $result = new Stylesheet($ID.text, $pages.result, $ctx.start.getLine()); };
 
 pages returns [List<Page> result]
 : 'page' ID '{' sections '}' { $result.add(new Page($ID.text, $sections.result, $ctx.start.getLine())); };
 
 sections returns [List<Section> result]
 : 'section' STRING '{' questions defaultStyle '}' { $result.add(new SectionWithDefault($STRING.text, $questions.result, $defaultStyle.result, $ctx.start.getLine())); }
   'section' STRING '{' questions '}' { $result.add(new Section($STRING.text, $questions.result, $ctx.start.getLine())); };

questions returns [List<Question> result]
: 'question' STRING widget { $result.add(new Question($STRING.text, $widget.result, $ctx.start.getLine())); };

widget returns [Widget result]
: 'widget' 'checkbox' { $result = new CheckBox($ctx.start.getLine()); }
  'widget' 'radio(' param1 = STRING ',' param2 = STRING ')' { $result = new RadioButton($param1.text, $param2.text, $ctx.start.getLine()); }
  'widget' 'spinbox' { $result = new SpinBox($ctx.start.getLine()); }
  ;
  
defaultStyle returns [DefaultStyle result]
: 'default' ID 
  'width:' width = INT
  'font:' font = STRING
  'fontsize:' fontSize = INT
  'color:' color =  STRING
  widget { $result = new DefaultStyle($ID.text, Integer.parseInt($width.text), $font.text, 
  	Integer.parseInt($fontSize.text), $color.text, $ctx.start.getLine()); };

// TODO you can specify the default width / font multiple times 
//defOptions returns [List<Option> result]
//: 'width:' INT { $result.add(new Width(Integer.parseInt($INT.text)); }
//  'font:' STRING { $result.add(new Font($STRING.text); }
//  'fontsize:' STRING { $result.add(new FontSize($STRING.text)); }
//  'color:' INT { $result.add(new Color(Integer.parseInt($INT.text))); };
 
ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT : ('0'..'9')+;

STRING : '"' .*? '"';

WS : [ \t\r\n]+ -> skip;

COMMENT : ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/') -> skip;
