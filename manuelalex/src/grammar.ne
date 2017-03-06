
@builtin "whitespace.ne" # `_` means arbitrary amount of whitespace
@builtin "number.ne"     # `int`, `decimal`, and `percentage` number primitives
@builtin "string.ne"     # `string`, `char`, and `escape`

form                    -> "form" _ formName _ "{" _ statement:* "}"                                          {% FormPostProcessor.form %}
formName                -> word

statement               -> question                                                                           {% FormPostProcessor.statement %}
                        | answer                                                                              {% FormPostProcessor.statement %}
                        | if_statement                                                                        {% FormPostProcessor.statement %}
                        | ifelse_statement                                                                    {% FormPostProcessor.statement %}

question                -> "question" _ "'" sentence "'" _ propertyName ":" _ propertyType _                  {% FormPostProcessor.question %}

ifelse_statement        -> if_statement else_clause                                                           {% FormPostProcessor.ifElseStatement %}
if_statement            -> "if" _ "(" expression ")" if_body                                                  {% FormPostProcessor.ifStatement %}
if_body                 -> _ "{" _ statement:* "}" _
else_clause             -> "else" _ "{" _ statement:* "}" _

answer                  -> "answer" _ "'" sentence "'" _ allocation _                                         {% FormPostProcessor.answer %}
allocation              -> propertyName ":" _ propertyType _ "=" _ (expression)                               {% FormPostProcessor.allocation %}


expression                  -> and_expression | expression _ "||" _ expression                                 {% FormPostProcessor.expression %}
and_expression              -> not_expression | and_expression _ "&&" _ and_expression
not_expression              -> comparison | "!" not_expression
comparison                  -> plus_minus_expression | comparison _ ("<" | ">" | ">=" | "<=" | "!=" | "==") _ comparison
plus_minus_expression       -> multiply_divide_expression | plus_minus_expression ("-"|"+") plus_minus_expression  {% FormPostProcessor.plusMinExpression %}
multiply_divide_expression  -> factor | multiply_divide_expression ("/" | "*") multiply_divide_expression
factor                      -> digits | propertyName | "(" expression ")"
digits                      -> [0-9]:+                                                                                                           {% (data)=> Number(data[0]) %}

propertyName            -> [A-Za-z0-9]:+                                                                      {% function(d) { return d[0].join("") } %}
propertyType            -> "boolean"                                                                          {% FormPostProcessor.boolean %}
                         | "string"                                                                           {% FormPostProcessor.string %}
                         | "integer"                                                                          {% FormPostProcessor.number %}
                         | "date"                                                                             {% FormPostProcessor.date %}
                         | "decimal"                                                                          {% FormPostProcessor.number %}
                         | "money"                                                                            {% FormPostProcessor.money %}

sentence                -> [ A-Za-z0-9!@#$%^&*()_+\-\=}{\[\]":;?/>.<,i]:+                                     {% function(d) { return d[0].join("") } %}
word                    -> [A-Za-z0-9]:+                                                                     {% function(d) { return d[0].join("") } %}





