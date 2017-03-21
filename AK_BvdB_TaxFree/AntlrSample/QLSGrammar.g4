grammar QLSGrammar;

@header {
package org.uva.taxfree.ql.gen;
}

stylesheet : 'stylesheet ' formId=VARIABLE_LITERAL '{' page* '}';

page : 'page' pageName=VARIABLE_LITERAL '{' layoutStatement* '}';

layoutStatement : 'section' sectionName=STRING_LITERAL '{' layoutStatement* '}' #section
                | 'question' questionId=VARIABLE_LITERAL 'widget' widgetType    #questionWithWidget
                | 'question' questionId=VARIABLE_LITERAL                        #question
                | 'default' varType 'widget' widgetType                         #defaultWidget
                ;
widgetType : 'checkbox'                                                         #checkboxWidget
           | 'spinbox'                                                          #spinboxWidget
           | 'radio(' trueText=STRING_LITERAL ',' falseText=STRING_LITERAL ')'  #radioWidget
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
STRING_LITERAL : '"'~["]+'"';
VARIABLE_LITERAL : [a-zA-Z]+;