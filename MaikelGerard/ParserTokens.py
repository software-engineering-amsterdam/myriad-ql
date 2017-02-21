import pyparsing as pp
import operator


class ParserTokens(object):
    # Define the tokens used for parsing the input.
    LIT = {
        "IS": pp.Literal("=").suppress(),
        "COLON": pp.Literal(":").suppress(),
        "L_CURLY": pp.Literal("{").suppress(),
        "R_CURLY": pp.Literal("}").suppress(),
        "L_BRACE": pp.Literal("(").suppress(),
        "R_BRACE": pp.Literal(")").suppress()
    }
    KW = {
        "FORM": pp.Keyword("form").setParseAction(lambda s, l, t: "@" + t[0]),
        "IF": pp.Keyword("if").setParseAction(lambda s, l, t: "@" + t[0]),
        "ELSE": pp.Keyword("else").setParseAction(lambda s, l, t: "@" + t[0])
    }
    TYPE = {
        "BOOL": pp.Regex("true|false"),
        "INT": pp.Word(pp.nums),
        "VAR": pp.Word(pp.alphas, pp.alphanums + "_"),
        "DECIMAL": pp.Regex("([0-9]+\.[0-9]*)|([0-9]*\.[0-9]+)"),
        "MONEY": pp.Regex("([0-9]+\.[0-9]{0,2})|([0-9]*\.[0-9]{1,2})"),
        "DATE": pp.Regex("^[0-9]{2}-[0-9]{2}-[0-9]{4}$"),
        "STRING": pp.quotedString.addParseAction(pp.removeQuotes)
    }
    TYPE_NAME = pp.oneOf("boolean int string date decimal money")

    # Relate operator strings to built-in functions for evaluation.
    MONOPS = {"-": operator.neg, "+": operator.pos, "!": operator.not_}
    BINOPS = {
        "*": operator.mul, "/": operator.truediv, "+": operator.add,
        "-": operator.sub, "<": operator.lt, "<=": operator.le,
        ">": operator.gt, ">=": operator.ge, "==": operator.eq,
        "!=": operator.ne, "&&": operator.and_, "||": operator.or_
    }
