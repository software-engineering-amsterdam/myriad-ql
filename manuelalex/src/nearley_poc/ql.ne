# Match a CSS color
# http://www.w3.org/TR/css3-color/#colorunits

@builtin "whitespace.ne" # `_` means arbitrary amount of whitespace
@builtin "number.ne"     # `int`, `decimal`, and `percentage` number primitives
@builtin "string.ne"     # `string`, `char`, and `escape`
@{% let PostProcessor = require('./PostProcessor.js'); %}


form         -> "form " formName "{" newLine  statements  newLine "}"
formName     -> letters
statements   -> statement:*
statement    -> question | if_statement | answer
question     -> sentence "?" newLine propertyName ":" _ propertyType newLine {% PostProcessor.question %}
if_statement -> "if (" propertyName ") {\n" statements "\n}"
answer       -> sentence ":\n" allocation {% function(data){console.log(data)} %}
allocation   -> "valueResidue: money = " expression "\n"
expression   -> "(" propertyName _ operator _ propertyName ")"
operator     -> "+" | "-" | "*" | "/"

propertyName -> letters
propertyType -> "boolean" | "money"
newLine      -> "\n" {% PostProcessor.toNull %}
sentence     -> [\w|\s]:+ {% PostProcessor.toString %}
letters      -> [a-zA-Z]:+ {% PostProcessor.toString %}



