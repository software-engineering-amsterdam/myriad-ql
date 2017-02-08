from pyparsing import *

class WickedDSL:
    '''
        Grammar definitions
    '''
    colon = Suppress(':')
    equals = '='
    word = Word(alphas)
    field_type = oneOf('boolean money')
    form_type = oneOf('form')
    evaluation = 'if'
    condition_unquoted = QuotedString(quoteChar="(", endQuoteChar=")", escChar='\\')
    condition_quoted = QuotedString(quoteChar="(", endQuoteChar=")", escChar='\\', unquoteResults=False)

    codeblock_unquoted = QuotedString(quoteChar="{", endQuoteChar="}", escChar='\\')
    codeblock_quoted = QuotedString(quoteChar="{", endQuoteChar="}", escChar='\\', unquoteResults=False)

    field_name = word
    field_display = QuotedString('"')
    form_name = word

    question = field_display + field_name + colon + field_type
    evaluate = evaluation + condition_unquoted + codeblock_quoted
    statement = field_display + field_name + colon + field_type + equals + condition_quoted

    field = statement | question | evaluate

    form_outer = form_type + form_name + codeblock_unquoted
    form_inner = OneOrMore(field)

    def loadFile(ql_file):
        # open the file as a list of strings
        with open(ql_file, 'r') as ql_file_stream:
            __ql_content = ql_file_stream.readlines()
        # newlines and such
        __ql_content = [x.strip() for x in __ql_content]

        # squash the list into a string and return
        return ' '.join(__ql_content)
