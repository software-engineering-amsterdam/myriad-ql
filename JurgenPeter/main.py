from sys import argv, exit
from os.path import isfile

from ql.grammar import parse_file
from ql.visitors.printer import Printer
from ql.visitors.symbolchecker import SymbolChecker
from ql.visitors.typechecker import TypeChecker
from ql.visitors.dependencychecker import DependencyChecker


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

    symbol_check_success, symboltable = SymbolChecker().visit(form)
    type_check_success = TypeChecker(symboltable).visit(form)
    dependency_check_success = DependencyChecker().visit(form)

    if not (symbol_check_success and type_check_success and
            dependency_check_success):
        return

if __name__ == "__main__":
    main()
