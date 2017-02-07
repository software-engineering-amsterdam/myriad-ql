import unittest
import pql.lexer.lexer as lexer


class TestLexer(unittest.TestCase):

    def test_lex_single_field(self):
        test_string = '"Did you enter a loan?"'
        result_set = lexer.lex_using_default_tokens(test_string)
        result = result_set[0]

        self.assertTrue(len(result_set) == 1, 'Length of result was {}, should be 1'.format(len(result_set)))
        self.assertEqual('FIELD', result[1])
        self.assertEqual(test_string, result[0])

    def test_lex_single_declaration(self):
        test_string_declaration = 'hasMaintLoan: boolean'
        result_set = lexer.lex_using_default_tokens(test_string_declaration)

        self.assertTrue(len(result_set) == 3, 'Length of result was {}, should be 3'.format(len(result_set)))
        self.assertEqual('hasMaintLoan', result_set[0][0])
        self.assertEqual('IDENTIFIER', result_set[0][1])

        self.assertEqual(':', result_set[1][0])
        self.assertEqual('COLON', result_set[1][1])

        self.assertEqual('boolean', result_set[2][0])
        self.assertEqual('BOOLEAN', result_set[2][1])

    def test_lex_form_declaration(self):
        test_string_declaration = 'form taxOfficeExample { }'
        result_set = lexer.lex_using_default_tokens(test_string_declaration)

        self.assertTrue(len(result_set) == 4, 'Length of result was {}, should be 4'.format(len(result_set)))
        self.assertEqual('form', result_set[0][0])
        self.assertEqual('FORM', result_set[0][1])

        self.assertEqual('taxOfficeExample', result_set[1][0])
        self.assertEqual('IDENTIFIER', result_set[1][1])

        self.assertEqual('{', result_set[2][0])
        self.assertEqual('LCURLY', result_set[2][1])

        self.assertEqual('}', result_set[3][0])
        self.assertEqual('RCURLY', result_set[3][1])

if __name__ == '__main__':
    unittest.main()
