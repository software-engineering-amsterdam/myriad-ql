
@builtin "whitespace.ne" # `_` means arbitrary amount of whitespace
@builtin "number.ne"     # `int`, `decimal`, and `percentage` number primitives
@builtin "string.ne"     # `string`, `char`, and `escape`

form -> "form" _ formName _ "{" _ statement:* "}"    {% ASTBuilder.form %}
formName -> word

statement -> question
         | answer
         | if_statement
         | ifelse_statement

question -> "question" _ "'" sentence "'" _ propertyName ":" _ propertyType _    {% ASTBuilder.question %}

ifelse_statement -> if_statement else_clause     {% ASTBuilder.ifElseStatement %}
if_statement -> "if" _ "(" expression ")" body   {% ASTBuilder.ifStatement %}
else_clause -> "else" body
body -> _ "{" _ statement:* "}" _

answer -> "answer" _ "'" sentence "'" _ allocation _   {% ASTBuilder.answer %}
allocation -> propertyName ":" _ propertyType _ "=" _ (expression) {% ASTBuilder.allocation %}

expression -> and_expression | expression _ "||" _ expression   {% ASTBuilder.expression %}
and_expression -> not_expression | and_expression _ "&&" _ and_expression   {% ASTBuilder.expression %}
not_expression              -> comparison | "!" not_expression  {% ASTBuilder.prefixExpression %}
comparison                  -> plus_minus_expression | comparison _ ("<" | ">" | ">=" | "<=" | "!=" | "==") _ comparison    {% ASTBuilder.deepExpression %}
plus_minus_expression       -> multiply_divide_expression | plus_minus_expression _ ("-" | "+") _ plus_minus_expression     {% ASTBuilder.deepExpression %}
multiply_divide_expression  -> factor | multiply_divide_expression _ ("/" | "*") _ multiply_divide_expression   {% ASTBuilder.deepExpression %}
factor  -> digits
       | propertyName
       | "(" expression ")"    {% (data)=> data[1] %}

digits -> [0-9]:+   {% ASTBuilder.digits %}

propertyName -> [A-Za-z0-9]:+    {% ASTBuilder.property %}
propertyType -> "boolean"        {% ASTBuilder.boolean %}
            | "string"           {% ASTBuilder.string %}
            | "integer"          {% ASTBuilder.number %}
            | "date"             {% ASTBuilder.date %}
            | "decimal"          {% ASTBuilder.number %}
            | "money"            {% ASTBuilder.money %}

sentence -> [ A-Za-z0-9!@#$%^&*()_+\-\=}{\[\]":;?/>.<,i]:+   {% function(d) { return d[0].join("") } %}
word -> [A-Za-z0-9]:+    {% function(d) { return d[0].join("") } %}
