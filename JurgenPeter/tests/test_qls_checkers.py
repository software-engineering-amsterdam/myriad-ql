from unittest import TestCase, main

from ql.datatypes import *
from qls.grammar import parse_string
from qls.visitors.symbol_checker import SymbolChecker
from qls.visitors.type_checker import TypeChecker


class TestSymbolChecker(TestCase):

    stylesheets = [
        ("stylesheet S {"
         "  page P {"
         "      question x"
         "  }"
         "}", 0),
        ("stylesheet S {"
         "  page P {"
         "      question x"
         "      question x"
         "  }"
         "}", 1),
        ("stylesheet S {"
         "  page P {"
         "      question x"
         "      question y"
         "  }"
         "}", 1),
    ]

    def testSymbolErrors(self):
        for stylesheet, expected_errors in self.stylesheets:
            layout = parse_string(stylesheet)
            symboltable = {"x": IntegerDatatype()}

            errors = []
            SymbolChecker(symboltable, errors).check(layout)
            self.assertEqual(len(errors), expected_errors)


class TestTypeChecker(TestCase):

    stylesheets = [
        ("stylesheet S {"
         "  page P {"
         "      question x widget whole-number"
         "  }"
         "}", 0),
        ("stylesheet S {"
         "  page P {"
         "      question x widget checkbox"
         "  }"
         "}", 1),
        ("stylesheet S {"
         "  page P {"
         "      question x"
         "  }"
         "  default integer widget checkbox"
         "}", 1),
    ]

    def testTypeErrors(self):
        for stylesheet, expected_errors, in self.stylesheets:
            layout = parse_string(stylesheet)
            symboltable = {"x": IntegerDatatype()}

            errors = []
            TypeChecker(symboltable, errors).check(layout)
            self.assertEqual(len(errors), expected_errors)


if __name__ == "__main__":
    main()
