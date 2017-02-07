import pql.combinator.Parser as Parser
import pql.combinator.Result as Result


class Tag(Parser):
    def __init__(self, tag):
        self.tag = tag

    def __call__(self, tokens, pos):
        if pos < len(tokens) and \
                        tokens[pos][1] is self.tag:
            return Result(tokens[pos][0], pos + 1)
        else:
            return None
