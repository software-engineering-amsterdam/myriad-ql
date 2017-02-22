from sys import argv, exit
from os.path import isfile

from ql.grammar import parse_file
from ql.visitors.printer import Printer
from ql.visitors.symbolchecker import SymbolChecker
from ql.visitors.typechecker import TypeChecker
from ql.visitors.dependencychecker import DependencyChecker
from ql.gui.formapp import *


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

    form = parse_file(filename_inp)

    Printer().visit(form)

    symbol_errors, symbol_warnings, symboltable = SymbolChecker().visit(form)
    type_errors, type_warnings = TypeChecker(symboltable).visit(form)
    dependency_errors, dependency_warnings = DependencyChecker().visit(form)

    errors = symbol_errors + type_errors + dependency_errors
    warnings = symbol_warnings + type_warnings + dependency_warnings

    for e in errors:
        report_error(e)

    for w in warnings:
        report_warning(w)

    if errors:
        return

    app = FormApp(form, symboltable)

if __name__ == "__main__":
    main()
