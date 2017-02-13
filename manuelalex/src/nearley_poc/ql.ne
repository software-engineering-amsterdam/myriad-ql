# Match a CSS color
# http://www.w3.org/TR/css3-color/#colorunits

@builtin "whitespace.ne" # `_` means arbitrary amount of whitespace
@builtin "number.ne"     # `int`, `decimal`, and `percentage` number primitives
@builtin "string.ne"     # `string`, `char`, and `escape`
@{% let toString = (data) => data.join().split(",").join("");%}
@{% let Question = require('./Question.js');  let question = (data, location, reject) => { return data;return new Question({name: data[0], propertyName: data[3][0], type: data[6][0]}) }; %}


form         -> "form " formName openBrace newLine  statements  newLine closedBrace
formName     -> letters
statements   -> statement:*
statement    -> question | if_statement | answer
question     -> "question " prime sentence prime newLine propertyName ":" space propertyType newLine
if_statement -> "if " parOpen propertyName parClose space openBrace newLine statements newLine closedBrace
answer       -> "answer " prime sentence prime newLine allocation
allocation   -> propertyName ": " propertyType space assignOp space expression newLine
expression   -> "(" propertyName space operator space propertyName ")"
operator     -> "+" | "-" | "*" | "/"
assignOp     -> "="


propertyName -> letters
propertyType -> "boolean" | "string" | "integer" | "date" | "decimal" | "money"
newLine      -> "\n" {% ()=> null %}
sentence     -> [\w|\s|?|:]:+ {% toString %}
letters      -> [a-zA-Z]:+ {% toString %}
prime        -> "`"
openBrace    -> "{"
closedBrace  -> "}"
parOpen      -> "("
parClose     -> ")"
space        -> _

