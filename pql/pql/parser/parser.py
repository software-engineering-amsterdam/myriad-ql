from pyparsing import *


class PQLParser(object):
    def __init__(self):
        self.TYPE_VAR = Word(alphas, alphanums)
        self.WORD_FORM = Keyword("form").setParseAction(lambda string, loc, tokens: tokens[0])
        self.LIT_FORM = self.WORD_FORM + self.TYPE_VAR
        self.TYPE_FORM = OneOrMore(self.LIT_FORM)

    def start_parse_form_block(self, input_string):
        print(self.TYPE_FORM.parseString(input_string))
