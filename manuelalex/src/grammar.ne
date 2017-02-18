# Match a CSS color
# http://www.w3.org/TR/css3-color/#colorunits

@builtin "whitespace.ne" # `_` means arbitrary amount of whitespace
@builtin "number.ne"     # `int`, `decimal`, and `percentage` number primitives
@builtin "string.ne"     # `string`, `char`, and `escape`
@{% let FormPostProcessor = require('./processors/FormPostProcessor.js'); %}


form         -> "form " formName openBrace newLine statement:* newLine closedBrace                           {% FormPostProcessor.form %}
formName     -> letters

statement    -> question
              | if_statement
              | answer                                                                                      {% FormPostProcessor.statement %}

question     -> "question " prime sentence prime newLine propertyName ":" space propertyType newLine        {% FormPostProcessor.question %}
if_statement -> "if " parOpen propertyName parClose space openBrace newLine statement:*  newLine closedBrace  {% FormPostProcessor.ifStatement %}
answer       -> "answer " prime sentence prime newLine allocation                                           {% FormPostProcessor.answer %}
allocation   -> propertyName ": " propertyType space assignOp space expression newLine
#expression   -> "(" propertyName space operator space propertyName ")"
#operator     -> "-" | "+" | "/" | "*"
expression  -> term | expression (min_op|plus_op) term                                         {% FormPostProcessor.expression %}
term        -> factor | term (divide_op | multiply_op) factor
factor      -> digits | propertyName | "(" expression ")"
digits      -> [0-9]:+

min_op      -> "-"
plus_op     -> "+"
divide_op   -> "/"
multiply_op -> "*"


assignOp     -> "="


propertyName -> letters
propertyType -> "boolean"
              | "string"
              | "integer"
              | "date"
              | "decimal"
              | "money"

newLine      -> "\n"                                                                                        {% FormPostProcessor.toNull %}
sentence     -> [\w|\s|?|:]:+                                                                               {% FormPostProcessor.toString %}
letters      -> [a-zA-Z]:+                                                                                  {% FormPostProcessor.toString %}
prime        -> "`"
openBrace    -> "{"
closedBrace  -> "}"
parOpen      -> "("
parClose     -> ")"
space        -> _


