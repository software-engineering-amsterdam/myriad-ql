from gui.formapp import *
from ql.grammar import parse_file
from ql.visitors.dependencychecker import DependencyChecker
from ql.visitors.printer import Printer
from ql.visitors.symbolchecker import SymbolChecker
from ql.visitors.typechecker import TypeChecker


def report_error(message):
    print("\33[31mError: {}\33[39m".format(message))


def report_warning(message):
    print("\33[33mWarning: {}\33[38m".format(message))


def main():

    # if len(argv) < 3:
    #     print("Error: insufficient arguments given, requires input and"
    #           "output file names")
    #     return
    #
    # filename_inp = argv[1]
    # filename_out = argv[2]
    #
    # if not isfile(filename_inp):
    #     print("Error: file {} does not exist".format(filename_inp))
    #     return

    filename_inp = "exampleForm.txt"

    ast = parse_file(filename_inp)

    Printer().visit(ast)

    symbol_errors, symbol_warnings, symboltable = SymbolChecker().visit(ast)

    type_errors, type_warnings = TypeChecker(symboltable).visit(ast)

    dependency_errors, dependency_warnings = DependencyChecker().visit(ast)

    errors = symbol_errors + type_errors + dependency_errors
    warnings = symbol_warnings + type_warnings + dependency_warnings

    for e in errors:
        report_error(e)

    for w in warnings:
        report_warning(w)

    if errors:
        return

    app = FormApp(ast)
    app.start()

if __name__ == "__main__":
    main()
