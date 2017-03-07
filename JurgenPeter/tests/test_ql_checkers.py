from unittest import TestCase, main

from ql.grammar import parse_string
from ql.datatypes import Datatypes
from ql.visitors.symbol_checker import SymbolChecker
from ql.visitors.type_checker import TypeChecker
from ql.visitors.dependency_checker import DependencyChecker


class TestSymbolChecker(TestCase):

    symboltables = [
        ("form Name {}", {}),
        ("form Name {"
         "  a: \"label\" integer }",
         {"a": Datatypes.integer}),
        ("form Name {"
         "  a: \"label\" integer"
         "  b: \"label\" boolean }",
         {"a": Datatypes.integer, "b": Datatypes.boolean}),
        ("form Name {"
         "  a: \"label\" integer"
         "  if a > 10 {"
         "    b: \"label\" boolean }"
         "  else {"
         "    c: \"label\" decimal } }",
         {"a": Datatypes.integer, "b": Datatypes.boolean, "c": Datatypes.decimal})
    ]

    def testSymbolTables(self):
        for form, table in self.symboltables:
            ast = parse_string(form)
            symboltable = {}
            SymbolChecker(symboltable).check(ast)
            self.assertEqual(symboltable, table)

    forms = [
        ("form Name {}", 0),
        ("form Name {"
         "  a: \"label 1\" integer"
         "  a: \"label 2\" integer }", 1),
        ("form Name {"
         "  a: \"label 1\" integer"
         "  b: \"label 1\" integer }", 1),
    ]

    def testSymbolErrors(self):
        for form, e in self.forms:
            ast = parse_string(form)
            symboltable = {}
            errors = []
            SymbolChecker(symboltable, errors).check(ast)
            self.assertEqual(len(errors), e)


class TestTypeChecker(TestCase):

    forms = [
        ("form Name {}", 0),
        ("form Name {"
         "  a: \"label 1\" integer = 2 * b }", 1),
        ("form Name {"
         "  a: \"label 1\" boolean "
         "  b: \"label 2\" integer = 2 * a }", 1),
        ("form Name {"
         "  a: \"label 1\" integer = + true }", 1),
        ("form Name {"
         "  if 2 + 2 {}"
         "  if \"hello\" {} else {} }", 2),
    ]

    def testTypeErrors(self):
        for form, e, in self.forms:
            ast = parse_string(form)
            symboltable = {}
            SymbolChecker(symboltable).check(ast)
            errors = []
            TypeChecker(symboltable, errors).check(ast)
            self.assertEqual(len(errors), e)


class TestDependencyChecker(TestCase):

    forms = [
        ("form Name {}", 0),
        ("form Name {"
         "  a: \"label 1\" integer = b"
         "  b: \"label 2\" integer = a }", 1),
        ("form Name {"
         "  a: \"label 1\" integer = b"
         "  b: \"label 2\" integer = c"
         "  c: \"label 3\" integer = a }", 1),
        ("form Name {"
         "  a: \"label 1\" integer = b"
         "  if true {"
         "    b: \"label 2\" integer = c"
         "    c: \"label 3\" integer = d"
         "    d: \"label 4\" integer = a } }", 1),
        ("form Name {"
         "  a: \"label 1\" integer = b"
         "  b: \"label 2\" integer = a"
         "  c: \"label 3\" integer = a }", 1),
        ("form Name {"
         "  a: \"label 1\" integer = b + c"
         "  b: \"label 2\" integer = a"
         "  c: \"label 3\" integer = a }", 3),
        ("form Name {"
         "  if a > 0 {"
         "    a: \"label 1\" integer } }", 1),
        ("form Name {"
         "  if a > 0 { }"
         "  else {"
         "    a: \"label 1\" integer } }", 1),
        ("form Name {"
         "  if a > 0 {"
         "    a: \"label 1\" integer }"
         "  else { } }", 1),
    ]

    def testDependencyErrors(self):
        for form, e in self.forms:
            ast = parse_string(form)
            errors = []
            DependencyChecker(errors).check(ast)
            self.assertEqual(len(errors), e)

if __name__ == "__main__":
    main()
