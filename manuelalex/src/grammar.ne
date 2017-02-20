# Match a CSS color
# http://www.w3.org/TR/css3-color/#colorunits

@builtin "whitespace.ne" # `_` means arbitrary amount of whitespace
@builtin "number.ne"     # `int`, `decimal`, and `percentage` number primitives
@builtin "string.ne"     # `string`, `char`, and `escape`
@{% let FormPostProcessor = require('./processors/FormPostProcessor.js');
    FormPostProcessor = new FormPostProcessor();
 %}


form                -> "form" _ formName _ openBrace _ statement:* closedBrace                            {% FormPostProcessor.form.bind(FormPostProcessor) %}
formName            -> word

statement           -> question
                    | if_statement
                    | answer
                    | ifelse_statement                                                                    {% FormPostProcessor.statement.bind(FormPostProcessor) %}

question            -> "question" _ prime sentence prime _ propertyName ":" _ propertyType _              {% FormPostProcessor.question %}

ifelse_statement   -> if_statement "else" _ openBrace _ statement:* closedBrace _                        {% FormPostProcessor.ifElseStatement %}
if_statement       -> "if" _ parOpen propertyName parClose _ openBrace _ statement:* closedBrace _         {% FormPostProcessor.ifStatement %}
answer             -> "answer" _ prime sentence prime _ allocation _                                       {% FormPostProcessor.answer %}
allocation         -> propertyName ":" _ propertyType _ assignOp _ expression                              {% FormPostProcessor.allocation %}
#expression        -> "(" propertyName _ operator _ propertyName ")"
#operator          -> "-" | "+" | "/" | "*"
expression         -> term | expression (min_op|plus_op) term                                             {% FormPostProcessor.expression %}
term               -> factor | term (divide_op | multiply_op) factor
factor             -> digits | propertyName | parOpen expression parClose
digits             -> [0-9]:+                                                                             {% (d)=> Number(d[0]) %}

min_op             -> "-"                                                                                 {% FormPostProcessor.minOp %}
plus_op            -> "+"                                                                                 {% FormPostProcessor.plusOP %}
divide_op          -> "/"                                                                                 {% FormPostProcessor.divideOp %}
multiply_op        -> "*"                                                                                 {% FormPostProcessor.multiplyOp %}

assignOp     -> "="


propertyName -> [A-Za-z0-9]:+                                                                      {% FormPostProcessor.toString %}
propertyType -> "boolean"                                                                          {% ()=> Boolean %}
              | "string"                                                                           {% ()=> String %}
              | "integer"                                                                          {% ()=> Number %}
              | "date"                                                                             {% ()=> Date %}
              | "decimal"                                                                          {% ()=> Number %}
              | "money"                                                                            {% FormPostProcessor.money %}

sentence -> [ A-Za-z0-9!@#$%^&*()_+\-\=}{\[\]":;?/>.<,i]:+                                         {% function(d) { return d[0].join("") } %}


word         -> [A-Za-z0-9]:+                                                                      {% FormPostProcessor.toString %}
prime        -> "'"
openBrace    -> "{"
closedBrace  -> "}"
parOpen      -> "("
parClose     -> ")"


