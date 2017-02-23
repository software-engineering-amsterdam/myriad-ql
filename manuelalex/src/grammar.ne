# Match a CSS color
# http://www.w3.org/TR/css3-color/#colorunits

@builtin "whitespace.ne" # `_` means arbitrary amount of whitespace
@builtin "number.ne"     # `int`, `decimal`, and `percentage` number primitives
@builtin "string.ne"     # `string`, `char`, and `escape`
@{% let FormPostProcessor = require('./processors/FormPostProcessor.js');
    FormPostProcessor = new FormPostProcessor();
 %}


form                    -> "form" _ formName _ openBrace _ statement:* closedBrace                            {% FormPostProcessor.form.bind(FormPostProcessor) %}
formName                -> word

statement               -> question                                                                           {% FormPostProcessor.statement.bind(FormPostProcessor) %}
                        | answer                                                                              {% FormPostProcessor.statement.bind(FormPostProcessor) %}
                        | if_statement                                                                        {% FormPostProcessor.statement.bind(FormPostProcessor) %}
                        | ifelse_statement                                                                    {% FormPostProcessor.statement.bind(FormPostProcessor) %}
                        | ifelseifelse_statement                                                              {% FormPostProcessor.statement.bind(FormPostProcessor) %}

question                -> "question" _ prime sentence prime _ propertyName ":" _ propertyType _              {% FormPostProcessor.question %}

ifelseifelse_statement  -> if_statement "else if" _ conditional if_body else_clause                           {% FormPostProcessor.ifElseIfElseStatement %}
ifelse_statement        -> if_statement else_clause                                                           {% FormPostProcessor.ifElseStatement %}
if_statement            -> "if" _ conditional if_body                                                         {% FormPostProcessor.ifStatement %}
if_body                 -> _ openBrace _ statement:* closedBrace _
conditional             -> parOpen or_test parClose
else_clause             -> "else" _ openBrace _ statement:* closedBrace _

answer                  -> "answer" _ prime sentence prime _ allocation _                                      {% FormPostProcessor.answer %}
allocation              -> propertyName ":" _ propertyType _ assignOp _ expression                             {% FormPostProcessor.allocation %}

expression              -> term | expression (min_op|plus_op) term                                             {% FormPostProcessor.expression %}
term                    -> factor | term (divide_op | multiply_op) factor
factor                  -> digits | propertyName | parOpen expression parClose
digits                  -> [0-9]:+                                                                             {% (data)=> Number(data[0]) %}

min_op                  -> "-"                                                                                 {% FormPostProcessor.minOp %}
plus_op                 -> "+"                                                                                 {% FormPostProcessor.plusOP %}
divide_op               -> "/"                                                                                 {% FormPostProcessor.divideOp %}
multiply_op             -> "*"                                                                                 {% FormPostProcessor.multiplyOp %}

assignOp                -> "="


or_test                 -> and_test | or_test _ ("||" | "|") _ and_test
and_test                -> not_test | and_test _ ("&&" | "&") _ not_test
not_test                -> comparison | "!" not_test | propertyName
comparison              -> propertyName _ comp_operator _ propertyName
comp_operator           -> "<" | ">" | ">=" | "<=" | "!=" | "=="



propertyName            -> [A-Za-z0-9]:+                                                                      {% FormPostProcessor.toString %}
propertyType            -> "boolean"                                                                          {% ()=> Boolean %}
                         | "string"                                                                           {% ()=> String %}
                         | "integer"                                                                          {% ()=> Number %}
                         | "date"                                                                             {% ()=> Date %}
                         | "decimal"                                                                          {% ()=> Number %}
                         | "money"                                                                            {% FormPostProcessor.money %}

sentence                -> [ A-Za-z0-9!@#$%^&*()_+\-\=}{\[\]":;?/>.<,i]:+                                     {% function(d) { return d[0].join("") } %}


word                    -> [A-Za-z0-9]:+                                                                      {% FormPostProcessor.toString %}
prime                   -> "'"
openBrace               -> "{"
closedBrace             -> "}"
parOpen                 -> "("
parClose                -> ")"


