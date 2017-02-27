
@builtin "whitespace.ne" # `_` means arbitrary amount of whitespace
@builtin "number.ne"     # `int`, `decimal`, and `percentage` number primitives
@builtin "string.ne"     # `string`, `char`, and `escape`

form                    -> "form" _ formName _ openBrace _ statement:* closedBrace                            {% FormPostProcessor.form %}
formName                -> word

statement               -> question                                                                           {% FormPostProcessor.statement %}
                        | answer                                                                              {% FormPostProcessor.statement %}
                        | if_statement                                                                        {% FormPostProcessor.statement %}
                        | ifelse_statement                                                                    {% FormPostProcessor.statement %}
                        | ifelseifelse_statement                                                              {% FormPostProcessor.statement %}

question                -> "question" _ prime sentence prime _ propertyName ":" _ propertyType _              {% FormPostProcessor.question %}

ifelseifelse_statement  -> if_statement "else if" _ conditional if_body else_clause                           {% FormPostProcessor.ifElseIfElseStatement %}
ifelse_statement        -> if_statement else_clause                                                           {% FormPostProcessor.ifElseStatement %}
if_statement            -> "if" _ conditional if_body                                                         {% FormPostProcessor.ifStatement %}
if_body                 -> _ openBrace _ statement:* closedBrace _
conditional             -> parOpen bool_expression parClose
else_clause             -> "else" _ openBrace _ statement:* closedBrace _

answer                  -> "answer" _ prime sentence prime _ allocation _                                      {% FormPostProcessor.answer %}
allocation              -> propertyName ":" _ propertyType _ assignOp _ (arithmetic_expression|bool_expression)           {% FormPostProcessor.allocation %}

arithmetic_expression   -> term | arithmetic_expression (min_op|plus_op) term                                             {% FormPostProcessor.expression %}
term                    -> factor | term (divide_op | multiply_op) factor
factor                  -> digits | propertyName | parOpen arithmetic_expression parClose
digits                  -> [0-9]:+                                                                             {% (data)=> Number(data[0]) %}

min_op                  -> "-"                                                                                 {% FormPostProcessor.minOp %}
plus_op                 -> "+"                                                                                 {% FormPostProcessor.plusOP %}
divide_op               -> "/"                                                                                 {% FormPostProcessor.divideOp %}
multiply_op             -> "*"                                                                                 {% FormPostProcessor.multiplyOp %}

assignOp                -> "="


bool_expression         -> and_test | bool_expression _ ("||" | "|") _ and_test                                {% FormPostProcessor.booleanExpression %}
and_test                -> not_test | and_test _ ("&&" | "&") _ not_test                                         {% FormPostProcessor.and_test %}
not_test                -> comparison | "!" not_test | propertyName                                              {% FormPostProcessor.not_test %}
comparison              -> propertyName _ comp_operator _ propertyName                                         {% FormPostProcessor.comparison %}
comp_operator           -> "<" | ">" | ">=" | "<=" | "!=" | "=="



propertyName            -> [A-Za-z0-9]:+                                                                      {% function(d) { return d[0].join("") } %}
propertyType            -> "boolean"                                                                          {% ()=> new Boolean() %}
                         | "string"                                                                           {% ()=> new String() %}
                         | "integer"                                                                          {% ()=> new Number() %}
                         | "date"                                                                             {% ()=> new Date() %}
                         | "decimal"                                                                          {% ()=> new Number() %}
                         | "money"                                                                            {% FormPostProcessor.money %}

sentence                -> [ A-Za-z0-9!@#$%^&*()_+\-\=}{\[\]":;?/>.<,i]:+                                     {% function(d) { return d[0].join("") } %}


word                    -> [A-Za-z0-9]:+                                                                     {% function(d) { return d[0].join("") } %}
prime                   -> "'"
openBrace               -> "{"
closedBrace             -> "}"
parOpen                 -> "("
parClose                -> ")"


