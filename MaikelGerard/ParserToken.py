import json
import decimal as d


class ParserToken(object):
    def __init__(self, token, line, col, var_type="str"):
        self.val = token
        self.type = var_type
        self.line = line
        self.col = col

    def to_json(self):
        return json.dumps(self.__dict__)

    def convert_to_type(self):
        if self.type == "int":
            self.val = int(self.val)
        elif self.type == "dec":
            self.val = d.Decimal(self.val)
        elif self.type == "bool":
            self.val = bool(self.val)
        elif self.type in ["str", "var"]:
            self.val = self.val
        else:
            assert False, \
                "ParserToken: Unknown type conversion {}".format(self.type)
        return self

    def __str__(self):
        return str(self.val)
