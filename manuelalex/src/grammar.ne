# Match a CSS color
# http://www.w3.org/TR/css3-color/#colorunits

@builtin "whitespace.ne" # `_` means arbitrary amount of whitespace
@builtin "number.ne"     # `int`, `decimal`, and `percentage` number primitives
@builtin "string.ne"     # `string`, `char`, and `escape`
@{% let FormPostProcessor = require('./processors/FormPostProcessor.js');
    FormPostProcessor = new FormPostProcessor();
 %}


form         -> "form " formName _ openBrace _ newLine:* _ statement:* _ newLine:* _ closedBrace                         {% FormPostProcessor.form %}
formName     -> word

statement    -> question
              | if_statement
              | answer                                                                                        {% FormPostProcessor.statement %}

question     -> "question " prime sentence prime _ newLine:* propertyName ":" _ propertyType newLine:*           {% FormPostProcessor.question %}


if_statement -> "if " parOpen propertyName parClose space openBrace newLine:* statement:*  newLine closedBrace  {% FormPostProcessor.ifStatement %}
answer       -> "answer " prime sentence prime _ newLine:* allocation                                             {% FormPostProcessor.answer %}
allocation   -> propertyName ": " _ propertyType space assignOp space expression newLine:*
#expression   -> "(" propertyName space operator space propertyName ")"
#operator     -> "-" | "+" | "/" | "*"
expression  -> term | expression (min_op|plus_op) term                                                        {% FormPostProcessor.expression %}
term        -> factor | term (divide_op | multiply_op) factor
factor      -> digits | propertyName | "(" expression ")"                                                       {% FormPostProcessor.factor %}
digits      -> [0-9]:+

min_op      -> "-"
plus_op     -> "+"
divide_op   -> "/"
multiply_op -> "*"

assignOp     -> "="


propertyName -> word
propertyType -> "boolean"
              | "string"
              | "integer"
              | "date"
              | "decimal"
              | "money"

newLine      -> "\n"                                                                                        {% FormPostProcessor.toNull %}
sentence     -> [\w|\s|?|:]:+                                                                               {% FormPostProcessor.toString %}
word         -> [A-Za-z0-9-]:+                                                                                  {% FormPostProcessor.toString %}
prime        -> "'"
openBrace    -> "{"
closedBrace  -> "}"
parOpen      -> "("
parClose     -> ")"
space        -> _


