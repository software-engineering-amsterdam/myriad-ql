from QL.stages.parser import Parser as QLParser
from QL.stages.init_environment import InitEnvironment
from QL.environment import Environment as QLEnvironment
from QL.error_handler import ErrorHandler as QLErrorHandler
from QLS.stages.parser import Parser as QLSParser
from QLS.environment import Environment as QLSEnvironment
from QLS.stages.type_checker import TypeChecker
from QLS.error_handler import ErrorHandler as QLSErrorHandler

import unittest


class TestTypeChecker(unittest.TestCase):
    ql_parser = QLParser()
    ql_handler = QLErrorHandler()
    ql_env = QLEnvironment(ql_handler)
    qls_parser = QLSParser()
    qls_handler = QLSErrorHandler()
    qls_env = QLSEnvironment(qls_handler)

    def clear_variables(self):
        self.ql_env.clear_env()
        self.ql_handler.clear_errors()
        self.qls_env.clear_env()
        self.qls_handler.clear_errors()

    def check_form(self, ql_form, qls_stylesheet, num_warnings, num_errors):
        ql_ast = self.ql_parser.parse(ql_form)
        InitEnvironment(ql_ast, self.ql_env, self.ql_handler).start_traversal()

        qls_ast = self.qls_parser.parse(qls_stylesheet)
        TypeChecker(
            qls_ast, self.qls_env, self.ql_env, self.qls_handler
        ).start_traversal()

        # Determine whether the typechecker result is as expected.
        correct_warnings = self.qls_handler.warn_count == num_warnings
        correct_errors = self.qls_handler.error_count == num_errors
        self.assertTrue(correct_warnings and correct_errors)

        # Clear the error handler and environment.
        self.clear_variables()

    def test_widget_type(self):
        ql_form_good = """
            form taxOfficeExample {
                "Did you sell a house in 2010?" hasSoldHouse: boolean
                "How many houses did you buy in 2010?" hasBoughtHouse: integer
                "Write down PI" whatIsPI: decimal
                "What is your favorite color" favColor: string
                "When were you born?" birthDate: date
            }
        """

        qls_stylesheet_good = """
            stylesheet taxOfficeExample {
                page Housing {
                    section "Buying" {
                        question hasSoldHouse widget checkbox
                        question hasBoughtHouse widget slider
                        question whatIsPI widget numeric
                        question favColor widget text
                        question birthDate widget date
                    }
                }
            }
        """
        qls_stylesheet_bad = """
            stylesheet taxOfficeExample {
                page Housing {
                    section "Buying" {
                        question hasSoldHouse widget text
                        question hasBoughtHouse widget radio
                        question whatIsPI widget slider
                        question favColor widget spinbox
                        question birthDate widget numeric
                    }
                }
            }
        """
        self.check_form(ql_form_good, qls_stylesheet_good, 0, 0)
        self.check_form(ql_form_good, qls_stylesheet_bad, 0, 5)

    def test_questions_defined(self):
        ql_form_good = """
            form taxOfficeExample {
                "Did you sell a house in 2010?" hasSoldHouse: boolean
                "Did you buy a house in 2010?" hasBoughtHouse: boolean
            }
        """
        qls_stylesheet_good = """
            stylesheet taxOfficeExample {
                page Housing {
                    section "Buying" {
                        question hasSoldHouse widget checkbox
                        question hasBoughtHouse widget checkbox
                    }
                }
            }
        """
        qls_stylesheet_bad1 = """
            stylesheet taxOfficeExample {
                page Housing {
                    section "Buying" {
                        question hasBoughtHouse widget checkbox
                    }
                }
            }
        """
        qls_stylesheet_bad2 = """
            stylesheet taxOfficeExample {
                page Housing {
                    section "Buying" {
                        question hasSoldHouse widget checkbox
                        question hasBoughtHouse widget checkbox
                        question hasDestroyedHouse widget checkbox
                    }
                }
            }
        """
        self.check_form(ql_form_good, qls_stylesheet_good, 0, 0)
        self.check_form(ql_form_good, qls_stylesheet_bad1, 0, 1)
        self.check_form(ql_form_good, qls_stylesheet_bad2, 0, 1)

if __name__ == '__main__':
    unittest.main()
