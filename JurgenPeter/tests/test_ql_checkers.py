from unittest import TestCase, main

from ql.grammar import parse_string
from ql.ast import Datatype
from ql.visitors.symbolchecker import SymbolChecker
from ql.visitors.typechecker import TypeChecker
from ql.visitors.dependencychecker import DependencyChecker


class TestSymbolChecker(TestCase):

    symboltables = [
        ("form Name {}", {}),
        ("form Name {"
         "  a: \"label\" integer }",
         {"a": Datatype.integer}),
        ("form Name {"
         "  a: \"label\" integer"
         "  b: \"label\" boolean }",
         {"a": Datatype.integer, "b": Datatype.boolean}),
        ("form Name {"
         "  a: \"label\" integer"
         "  if a > 10 {"
         "    b: \"label\" boolean }"
         "  else {"
         "    c: \"label\" decimal } }",
         {"a": Datatype.integer, "b": Datatype.boolean, "c": Datatype.decimal})
    ]

    def testSymbolTables(self):
        for form, table in self.symboltables:
            ast = parse_string(form)
            _, _, symboltable = SymbolChecker().visit(ast)
            self.assertEqual(symboltable, table)

    forms = [
        ("form Name {}", 0, 0),
        ("form Name {"
         "  a: \"label 1\" integer"
         "  a: \"label 2\" integer }", 1, 0),
        ("form Name {"
         "  a: \"label 1\" integer"
         "  b: \"label 1\" integer }", 0, 1),
    ]

    def testSymbolErrors(self):
        for form, e, w in self.forms:
            ast = parse_string(form)
            errors, warnings, _ = SymbolChecker().visit(ast)
            self.assertEqual(len(errors), e)
            self.assertEqual(len(warnings), w)


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
            _, _, symboltable = SymbolChecker().visit(ast)
            errors, _ = TypeChecker(symboltable).visit(ast)
            self.assertEqual(len(errors), e)


class TestDependencyChecker(TestCase):

    forms = [
        ("form Name {}", 0, 0),
        ("form Name {"
         "  a: \"label 1\" integer = b"
         "  b: \"label 2\" integer = a }", 1, 0),
        ("form Name {"
         "  a: \"label 1\" integer = b"
         "  b: \"label 2\" integer = c"
         "  c: \"label 3\" integer = a }", 1, 0),
        ("form Name {"
         "  a: \"label 1\" integer = b"
         "  if true {"
         "    b: \"label 2\" integer = c"
         "    c: \"label 3\" integer = d"
         "    d: \"label 4\" integer = a } }", 1, 0),
        ("form Name {"
         "  a: \"label 1\" integer = b"
         "  b: \"label 2\" integer = a"
         "  c: \"label 3\" integer = a }", 1, 0),
        ("form Name {"
         "  a: \"label 1\" integer = b + c"
         "  b: \"label 2\" integer = a"
         "  c: \"label 3\" integer = a }", 3, 0),
        ("form Name {"
         "  if a > 0 {"
         "    a: \"label 1\" integer } }", 1, 0),
        ("form Name {"
         "  if a > 0 { }"
         " else {"
         "    a: \"label 1\" integer } }", 1, 0),
        ("form Name {"
         "  if a > 0 {"
         "    a: \"label 1\" integer }"
         " else { } }", 1, 0),
        ("form Name {"
         "a: \"label 1\" integer = 0"
         "  if a > 0 { } }", 0, 1),
        ("form Name {"
         "a: \"label 1\" integer"
         "b: \"label 2\" integer = 2 * a"
         "  if b > 0 { } }", 0, 0),
    ]

    def testDependencyErrors(self):
        for form, e, w, in self.forms:
            ast = parse_string(form)
            errors, warnings = DependencyChecker().visit(ast)
            self.assertEqual(len(errors), e)
            self.assertEqual(len(warnings), w)

if __name__ == "__main__":
    main()
