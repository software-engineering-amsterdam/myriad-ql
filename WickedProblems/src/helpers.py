class Helpers:
    def load_ql_file(ql_file):
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

    # def escape_curlies(__ql_content):
    #         lcurly = 0
    #         rcurly = 0
    #         replacements = []
    #         for x in range(0,len(__ql_content)):
    #             if(__ql_content[x] == "{"):
    #                 lcurly += 1
    #             elif(__ql_content[x] == "}"):
    #                 if(lcurly > 1):
    #                     replacements.append(x)
    #                 lcurly -= 1
    #
    #         for x in range(0, len(replacements)):
    #             __ql_content = __ql_content[:replacements[x]+x]+"\\"+__ql_content[replacements[x]+x:]
    #
    #         return __ql_content
