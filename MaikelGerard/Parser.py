from TypeChecker import TypeChecker
# from Evaluate import Evaluate
from TypeEnums import TypeEnums
import pyparsing as pp
import AST
import decimal


class QuestionnaireParser(object):
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
        "FORM": pp.Keyword("form").suppress(),
        "IF": pp.Keyword("if").suppress(),
        "ELSE": pp.Keyword("else").suppress()
    }

    TYPE_NAME = pp.MatchFirst(TypeEnums.ALL_TYPES)

    def __init__(self):
        # Enable caching of parsing logic.
        pp.ParserElement.enablePackrat()
        self.types = self.get_types()

        # Create the grammar incrementally to simplify unit test creation.
        self.expression = self.define_expression()
        self.question = self.define_question()
        self.block = pp.Forward()
        self.conditional = self.define_conditional()

        self.grammar = self.define_grammar()

    @staticmethod
    def get_types():
        def create_decimal(src, loc, tokens):
            del src, loc
            return decimal.Decimal(tokens[0])

        types = {
            "BOOL": (pp.Keyword("true").addParseAction(lambda _: True) |
                     pp.Keyword("false").addParseAction(lambda _: False)),
            "INT": pp.Word(pp.nums),
            "VAR": pp.Word(pp.alphas, pp.alphanums + "_"),
            "DECIMAL": pp.Regex("([0-9]+\.[0-9]*)|([0-9]*\.[0-9]+)"),
            "MONEY": pp.Regex("([0-9]+\.[0-9]{0,2})|([0-9]*\.[0-9]{1,2})"),
            "DATE": pp.Regex("^[0-9]{2}-[0-9]{2}-[0-9]{4}$"),
            "STRING": pp.quotedString.addParseAction(pp.removeQuotes)
        }
        types["INT"].addParseAction(create_decimal)
        types["DECIMAL"].addParseAction(create_decimal)
        types["MONEY"].addParseAction(create_decimal)

        return types

    @staticmethod
    def get_line_loc_info(src, loc):
        col = pp.col(loc, src)
        line = pp.lineno(loc, src)
        return line, col

    def round_embrace(self, arg):
        return self.LIT["L_BRACE"] + arg + self.LIT["R_BRACE"]

    def curly_embrace(self, arg):
        return self.LIT["L_CURLY"] + arg + self.LIT["R_CURLY"]

    def define_question(self):
        def create_question_grammar():
            """
            Use this function to prevent double parseActions, as the question
            grammar is re-used in the comp_question grammar definition.
            """
            return self.types["STRING"] + self.types["VAR"] + \
                self.LIT["COLON"] + self.TYPE_NAME

        question = create_question_grammar()
        question.addParseAction(self.create_node(AST.QuestionNode))

        comp_question = create_question_grammar() + self.LIT["IS"] + \
            self.round_embrace(self.expression)
        comp_question.addParseAction(self.create_node(AST.ComputedQuestionNode))

        return comp_question | question

    def define_conditional(self):
        def create_if_grammar():
            """ Again used to prevent double parseActions. """
            return self.KW["IF"] + self.round_embrace(self.expression) + \
                self.curly_embrace(self.block)

        if_cond = create_if_grammar()
        if_cond.addParseAction(self.create_node(AST.IfNode))

        if_else_cond = create_if_grammar() + self.KW["ELSE"] + \
            self.curly_embrace(self.block)
        if_else_cond.addParseAction(self.create_node(AST.IfElseNode))

        return if_else_cond | if_cond

    def define_grammar(self):
        self.block << pp.Group(
            pp.OneOrMore(self.question | self.conditional)
        ).addParseAction(self.create_node(AST.BlockNode))

        form = self.KW["FORM"] + self.types["VAR"] + \
            self.curly_embrace(self.block)
        form.addParseAction(self.create_node(AST.FormNode))

        return form.addParseAction(self.create_node(AST.QuestionnaireAST))

    def create_node(self, ast_class):
        def create_args(src, loc, token):
            line, col = self.get_line_loc_info(src, loc)
            args = token.asList() + [line, col]
            return ast_class(*args)
        return create_args

    def define_expression(self):
        # Define expressions including operator precedence. Based on:
        # http://pythonhosted.org/pyparsing/pyparsing-module.html#infixNotation
        var_types = (
            self.types["BOOL"].addParseAction(self.create_node(AST.BoolNode)) |
            self.types["VAR"].addParseAction(self.create_node(AST.VarNode)) |
            self.types["DECIMAL"].addParseAction(self.create_node(AST.DecimalNode)) |
            self.types["INT"].addParseAction(self.create_node(AST.IntNode)) |
            self.types["DATE"].addParseAction(self.create_node(AST.DateNode)) |
            self.types["STRING"].addParseAction(self.create_node(AST.StringNode))
        )

        def create_operator(opp, ast_class):
            return pp.Literal(opp).addParseAction(lambda _: ast_class)

        def create_operators(opp_list):
            opp, ast_class = opp_list[0]
            operator = create_operator(opp, ast_class)
            for (opp, ast_class) in opp_list[1:]:
                operator |= create_operator(opp, ast_class)
            return operator

        unary_ops = create_operators([('!', AST.NegNode), ('-', AST.MinNode), ('+', AST.PlusNode)])
        arithmetic_level1 = create_operators([('*', AST.MulNode), ('/', AST.DivNode)])
        arithmetic_level2 = create_operators([('+', AST.AddNode), ('-', AST.SubNode)])
        logical_level1 = create_operators([('<=', AST.LTENode), ('<', AST.LTNode),
                                           ('>=', AST.GTENode), ('>', AST.GTNode)])
        logical_level2 = create_operators([('==', AST.EqNode), ('!=', AST.NeqNode)])
        infix_and = create_operator('&&', AST.AndNode)
        infix_or = create_operator('||', AST.OrNode)

        return pp.infixNotation(var_types, [
            (unary_ops, 1, pp.opAssoc.RIGHT, self.create_monop_node),
            (arithmetic_level1, 2, pp.opAssoc.LEFT, self.create_binops),
            (arithmetic_level2, 2, pp.opAssoc.LEFT, self.create_binops),
            (logical_level1, 2, pp.opAssoc.LEFT, self.create_binops),
            (logical_level2, 2, pp.opAssoc.LEFT, self.create_binops),
            (infix_and, 2, pp.opAssoc.LEFT, self.create_binops),
            (infix_or, 2, pp.opAssoc.LEFT, self.create_binops),
        ])

    def create_monop_node(self, src, loc, token):
        monop = token[0]
        ast_class = monop[0]
        monop_expr = monop[1]
        line, col = self.get_line_loc_info(src, loc)
        return ast_class(monop_expr, line, col)

    def create_binops(self, src, loc, token):
        token = token[0]
        line, col = self.get_line_loc_info(src, loc)

        # As pyparsing returns all terms on the same precedence level at once,
        # it is required to split the resulting tokens into binary operations.
        while len(token) > 1:
            [left, binop_class, right] = token[:3]
            token = [binop_class(left, right, line, col)] + token[3:]
        return token

    def parse(self, input_str):
        return self.grammar.parseString(input_str, parseAll=True)[0]

if __name__ == '__main__':
    form1 = """
    form taxOfficeExample {
        "Did you sell a house in 2010?" hasSoldHouse: boolean
        "Did you buy a house in 2010?" hasBoughtHouse: boolean
        "Did you enter a loan?" hasMaintLoan: integer


        if (-true >= !false * !100 * +5 * !hasMaintLoan) {
            "What was the selling price?" sellingPrice: money
            "Private debts for the sold house:" privateDebt: money
            "Value residue:" valueResidue: money = (sellingPrice -
            privateDebt)
        }
        else {
            "question?" test:           boolean
            "Did you sell a house in 2010?" hasSoldHouse: boolean = (-var == 600)
        }
    }
    """
    parser = QuestionnaireParser()
    parsedAST = parser.parse(form1)
    print parsedAST

    TypeChecker(parsedAST).start_traversal()
    # Evaluate(parsedAST).start_traversal()
