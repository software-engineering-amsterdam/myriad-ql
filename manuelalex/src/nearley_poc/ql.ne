# Match a CSS color
# http://www.w3.org/TR/css3-color/#colorunits

@builtin "whitespace.ne" # `_` means arbitrary amount of whitespace
@builtin "number.ne"     # `int`, `decimal`, and `percentage` number primitives
@builtin "string.ne"     # `string`, `char`, and `escape`
@{% let toString = (data) => data.join().split(",").join("");%}


form         -> "form " formName "{" newLine  statements  newLine "}" {% function(data, location, reject){ console.log(data); return data} %}
formName     -> letters
statements   -> statement:*
statement    -> question | if_statement | answer
question     -> sentence "?" newLine propertyName ":" _ propertyType newLine
if_statement -> "if (" propertyName ") {\n" statements "\n}"
answer       -> sentence ":\n" allocation
allocation   -> "valueResidue: money = " expression "\n"
expression   -> "(" propertyName _ operator _ propertyName ")"
operator     -> "+" | "-" | "*" | "/"

propertyName -> letters
propertyType -> "boolean" | "money"
newLine      -> "\n"
sentence     -> [\w|\s]:+ {% toString %}
letters      -> [a-zA-Z]:+ {% toString %}



