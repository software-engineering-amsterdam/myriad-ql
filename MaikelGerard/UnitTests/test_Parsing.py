import Parser
import AST
import pyparsing as pp
import unittest


class TestParser(unittest.TestCase):
    parser = Parser.QuestionnaireParser()
    q_parser = parser.question
    exp_parser = parser.expression

    @staticmethod
    def parse(parse_method, input_str):
        return parse_method.parseString(input_str, parseAll=True)

    def create_questionNode(self, question, name, var_type):
        return AST.QuestionNode("", 0, [[question, self.create_varNode(name), var_type]])

    @staticmethod
    def create_varNode(var_name):
        return AST.VarNode("", 0, [var_name])

    def test_question_parser(self):
        def validate_question_node(question, name, var_type):
            q_valid = "\"{}\" {}: {}".format(question, name, var_type)
            parse_res = self.parse(self.q_parser, q_valid)[0]
            test_node = self.create_questionNode(question, name, var_type)
            self.assertEqual(parse_res, test_node)

        validate_question_node("Did you sell a house in 2010?", "hasSoldHouse", "boolean")
        validate_question_node("Did you sell a house in 2010?", "hasSoldHouse", "money")
        validate_question_node("question?", "moneyVar", "decimal")

        def check_invalid_parse_question(parse_str):
            with self.assertRaises(pp.ParseException) as excep:
                self.parse(self.q_parser, parse_str)
            self.assertEqual(excep.expected, pp.ParseException)

        check_invalid_parse_question("name: \"question?\" boolean ")
        check_invalid_parse_question("\"question\" name boolean ")
        check_invalid_parse_question("\"question?\" boolean: name")

    def test_expression_parser(self):
        pass
        #parse_res1 = self.parse(exp_parser, "newPrice * 1000")
        #parse_res1 = self.parse(exp_parser, "100.0 + true")
        #parse_res1 = self.parse(exp_parser, " + false")

        #self.setUp()


def test_parser(parser):
    # Define full grammar and expression grammar.

    print "=== Testing form parsing ==="
    #parse_form(parser)
    print "=== Testing expression parsing ==="
    parse_expr(parser.define_expression())
    assert  1== 2 - 1, "Hoi"


def parse_form(parser):
    # Forms to test
    form1 = """
    form Box1HouseOwning {
        hasSoldHouse: "Did you sell a house in 2010?" boolean
        hasBoughtHouse: "Did you buy a house in 2010?" boolean
        hasMaintLoan:
            "Did you enter a loan for maintenance/reconstruction?" boolean

        if (hasSoldHouse + newPrice + 4 + 23) {
            sellingPrice: "Price the house was sold for:" money
            privateDebt: "Private debts for the sold house:" money
            valueResidue: "Value residue:" money(300 * 100 - 20 * 10 * (25 - 3))
            if (newPrice > 20) {
                privateDebt: "Private debts for the sold house:" money
            }
            else {
                privateDebt: "Private debts for the sold house:" money
            }
        }
    }
    """
    hoi = parser.parse(form1)
    print hoi


def parse_expr(expr):
    # Testing expression parsing
    ex1 = '30 + 239.0 - 239 * 239'
    ex2 = '30'
    ex3 = 'newPrice * 1000'
    ex4 = '(40 * 30)'
    ex5 = '((40 + 30))'
    ex6 = '5 + 10 / 234 / 1 - 20 && (19 * 12) || 2'
    ex7 = '10 && 20 || 2'
    ex8 = '!5 + !10'
    ex9 = '!(5 + 10 / 234 / 1 - !20 && (19 * 12) || 2)'
    ex10 = "!!8"
    ex11 = "300 * 100 - 20 * 10 * (25 - 3)"

    # Test examples. NOTE: Example 1 cannot be done for now.
    print expr.parseString(ex1, parseAll=True)
    print expr.parseString(ex2, parseAll=True)
    print expr.parseString(ex3, parseAll=True)[0]
    print expr.parseString(ex4, parseAll=True)[0]
    print expr.parseString(ex5, parseAll=True)[0]
    print expr.parseString(ex6, parseAll=True)[0]
    print expr.parseString(ex7, parseAll=True)[0]
    print expr.parseString(ex8, parseAll=True)[0]
    print expr.parseString(ex9, parseAll=True)[0]
    print expr.parseString(ex10, parseAll=True)[0]
    print expr.parseString(ex11, parseAll=True)[0]

if __name__ == '__main__':
    unittest.main()
