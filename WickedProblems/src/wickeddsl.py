from pyparsing import *

class WickedDSL:
    '''
    Grammar definitions
    '''
    colon = Suppress(':')
    equals = '='
    word = Word(alphas)
    field_type = oneOf('boolean money')
    page_type = oneOf('page')
    settings_type = oneOf('default')
    stylesheet_type = oneOf('stylesheet')
    stylesheet_section = 'section'
    stylesheet_content = oneOf('question')
    form_type = oneOf('form')
    widget = 'widget'
    widget_noarg = oneOf('spinbox checkbox')
    evaluation = 'if'
    field_display = QuotedString('"')

    condition_unquoted = QuotedString(quoteChar="(", endQuoteChar=")", escChar='\\')
    condition_quoted = QuotedString(quoteChar="(", endQuoteChar=")", escChar='\\', unquoteResults=False)
    codeblock_unquoted = QuotedString(quoteChar="{", endQuoteChar="}", escChar='\\')
    codeblock_quoted = QuotedString(quoteChar="{", endQuoteChar="}", escChar='\\', unquoteResults=False)
    radio = QuotedString(quoteChar="radio(", endQuoteChar=")", escChar='\\', unquoteResults=False)

    widget_arg = radio
    widget_type = widget_noarg | widget_arg
    default = settings_type + field_type + ((widget + widget_type) | codeblock_quoted)
    name = word
    identifier = word
    question = field_display + name + colon + field_type
    evaluate = evaluation + condition_unquoted + codeblock_quoted
    statement = field_display + name + colon + field_type + equals + condition_quoted
    question_widget = identifier + widget + widget_type
    question_nowidget = identifier
    widgetnowidget = question_widget | identifier
    questionnoquestion = stylesheet_content + widgetnowidget
    codeblocknocodeblock = codeblock_quoted | questionnoquestion
    section = field_display + codeblocknocodeblock
    page = page_type + name + codeblock_unquoted
    page_content = stylesheet_section + (section | (question_widget | question_nowidget))
    page_layout = default | page_content
    content_type = OneOrMore(page_layout)
    field = statement | question | evaluate
    form_outer = form_type + name + codeblock_unquoted
    form_inner = OneOrMore(field)
    stylesheet = stylesheet_type + name + OneOrMore(page)

    def load_file(ql_file):
        # open the file as a list of strings
        with open(ql_file, 'r') as ql_file_stream:
            __ql_content = ql_file_stream.readlines()
            # newlines and such
            __ql_content = [x.strip() for x in __ql_content]

            # squash the list into a string and return
            __ql_content =  ' '.join(__ql_content)

            # Do the initial format to make sure all blocks can be extracted
            if(__ql_content.count('\}') > 0):
                raise Exception("Illegal token \"}\"")

            return __ql_content

    def parse_structure(__ql_content, __ql_structure, __id, __parent):
        fields = __ql_content[0:len(__ql_content)]

        if(len(fields) > 0):
            # Store all fields (tuples of 3: name, variable, type)
            for x in range(0,int(len(fields)/3)):
                try:
                    if(fields[3*x+3] == "="):
                        fields[3*x+2:3*x+5] = [' '.join(fields[3*x+2:3*x+5])]
                    else:
                        pass
                except IndexError:
                    pass
                __ql_structure.append(((__id, __parent),fields[3*x],fields[3*x+1],fields[3*x+2]))

        return (__ql_content[len(__ql_content)-1], __ql_structure)

    def escape_curlies(__ql_content):
        lcurly = 0
        rcurly = 0
        replacements = []
        for x in range(0,len(__ql_content)):
            if(__ql_content[x] == "{"):
                lcurly += 1
            elif(__ql_content[x] == "}"):
                if(lcurly > 1):
                    replacements.append(x)
                lcurly -= 1

        for x in range(0, len(replacements)):
            __ql_content = __ql_content[:replacements[x]+x]+"\\"+__ql_content[replacements[x]+x:]

        return __ql_content
