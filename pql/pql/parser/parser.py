from pyparsing import *

input = "form taxOfficeExample {}"

TYPE_VAR = Word(alphas, alphanums)
WORD_FORM = Keyword("form").setParseAction(lambda string, loc, tokens: tokens[0])


# Combine form type with its name declaration
form = WORD_FORM + TYPE_VAR

# Allow for multiple blocks
together = OneOrMore(form)

greeting = together.parseString(input)
print(greeting)
