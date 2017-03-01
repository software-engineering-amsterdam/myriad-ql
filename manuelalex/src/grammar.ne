
@builtin "whitespace.ne" # `_` means arbitrary amount of whitespace
@builtin "number.ne"     # `int`, `decimal`, and `percentage` number primitives
@builtin "string.ne"     # `string`, `char`, and `escape`

form                    -> "form" _ formName _ "{" _ statement:* "}"                            {% FormPostProcessor.form %}
formName                -> word

statement               -> question                                                                           {% FormPostProcessor.statement %}
                        | answer                                                                              {% FormPostProcessor.statement %}
                        | if_statement                                                                        {% FormPostProcessor.statement %}
                        | ifelse_statement                                                                    {% FormPostProcessor.statement %}

question                -> "question" _ "'" sentence "'" _ propertyName ":" _ propertyType _                  {% FormPostProcessor.question %}

ifelse_statement        -> if_statement else_clause                                                           {% FormPostProcessor.ifElseStatement %}
if_statement            -> "if" _ conditional if_body                                                         {% FormPostProcessor.ifStatement %}
if_body                 -> _ "{" _ statement:* "}" _
conditional             -> "(" bool_expression ")"
else_clause             -> "else" _ "{" _ statement:* "}" _

answer                  -> "answer" _ "'" sentence "'" _ allocation _                                         {% FormPostProcessor.answer %}
allocation              -> propertyName ":" _ propertyType _ "=" _ (arithmetic_expression|bool_expression)           {% FormPostProcessor.allocation %}

arithmetic_expression   -> term | arithmetic_expression ("-"|"+") term                                             {% FormPostProcessor.expression %}
term                    -> factor | term ("/" | "*") factor
factor                  -> digits | propertyName | "(" arithmetic_expression ")"
digits                  -> [0-9]:+                                                                             {% (data)=> Number(data[0]) %}


bool_expression         -> and_test | bool_expression _ ("||" | "|") _ and_test                                {% FormPostProcessor.booleanExpression %}
and_test                -> not_test | and_test _ ("&&" | "&") _ not_test                                         {% FormPostProcessor.and_test %}
not_test                -> comparison | "!" not_test | propertyName                                              {% FormPostProcessor.not_test %}
comparison              -> propertyName _ comp_operator _ propertyName                                         {% FormPostProcessor.comparison %}
comp_operator           -> "<" | ">" | ">=" | "<=" | "!=" | "=="

propertyName            -> [A-Za-z0-9]:+                                                                      {% function(d) { return d[0].join("") } %}
propertyType            -> "boolean"                                                                          {% FormPostProcessor.boolean %}
                         | "string"                                                                           {% FormPostProcessor.string %}
                         | "integer"                                                                          {% FormPostProcessor.number %}
                         | "date"                                                                             {% FormPostProcessor.date %}
                         | "decimal"                                                                          {% FormPostProcessor.number %}
                         | "money"                                                                            {% FormPostProcessor.money %}

sentence                -> [ A-Za-z0-9!@#$%^&*()_+\-\=}{\[\]":;?/>.<,i]:+                                     {% function(d) { return d[0].join("") } %}
word                    -> [A-Za-z0-9]:+                                                                     {% function(d) { return d[0].join("") } %}





