import pql.combinator.Parser as Parser
import pql.combinator.Result as Result


class Reserved(Parser):
    def __init__(self, value, tag):
        self.value = value
        self.tag = tag

    def __call__(self, tokens, pos):
        if pos < len(tokens) and \
                        tokens[pos][0] == self.value and \
                        tokens[pos][1] == self.tag:
            return Result(tokens[pos][0], pos + 1)
        else:
            return None
