grammar QLSGrammar;

// Pages
// Sections
// Widget types
// Styles for question type (string, boolean, int)
// Overide widgets and styles per question
// Widget types: Slider, spinbox (numbers), text (numbers + string), yesno-radio, checkbox, yes-no dropdown (booleans)

// For us: defaults supported on page basis, not inside sections

@header {
package org.uva.taxfree.ql.gen;
}

stylesheet : 'stylesheet ' formId=VARIABLE_LITERAL '{' page* '}';

page : 'page' pageName=VARIABLE_LITERAL '{' pageStatement* '}';

pageStatement : 'section' sectionName=STRING_LITERAL '{' sectionStatement* '}'  #section
              | 'default' varType '{' styleOption+ '}'                          #defaultStyle
              ;

sectionStatement : 'question' questionId=VARIABLE_LITERAL                       #question
//                 | 'question' questionId=VARIABLE_LITERAL 'widget' widgetType   #questionWithWidget
                 | 'question' questionId=VARIABLE_LITERAL '{' styleOption+ '}'  #questionWithStyle
                 ;

styleOption : 'widget' ':' widgetType               #widgetStyle
            | 'font' ':' STRING_LITERAL             #fontStyle
            | 'fontsize' ':' INTEGER_LITERAL        #fontsizeStyle
            | 'color' ':' COLOR_LITERAL             #colorStyle
            | 'background-color' ':' COLOR_LITERAL  #backgroundColorStyle
//            | 'width' ':' INTEGER_LITERAL           #widthStyle // Not supported yet
            ;

widgetType : 'slider'                                                               #sliderWidget
           | 'spinbox'                                                              #spinboxWidget
           | 'text'                                                                 #textWidget
           | 'radio' '(' textTrue=STRING_LITERAL ',' textFalse=STRING_LITERAL ')'   #radioWidget
           | 'checkbox'                                                             #checkboxWidget
           | 'dropdown' '(' textTrue=STRING_LITERAL ',' textFalse=STRING_LITERAL ')'#dropdownWidget
           ;

varType : 'boolean' # booleanType
        | 'string'  # stringType
        | 'integer' # integerType
        ;

// The whitespace layout
WS : [ \t\r\n]+ -> skip;
// Comment layout
COMMENT : [/][/]~[\n]* -> skip;
// Types
BOOLEAN_LITERAL : ('true' | 'false');
INTEGER_LITERAL : [0-9]+;
COLOR_LITERAL : '#' COLOR_DIGIT COLOR_DIGIT COLOR_DIGIT COLOR_DIGIT COLOR_DIGIT COLOR_DIGIT; // Antlr doesn't support {6} or somehting equal to that
COLOR_DIGIT : [0-9a-fA-F];
STRING_LITERAL : '"'~["]+'"';
VARIABLE_LITERAL : [a-zA-Z]+;