from sys import argv, exit
from os.path import isfile
from json import dump

import ql.grammar
from ql.visitor.printer import Printer
from ql.visitor.symbolchecker import SymbolChecker
from ql.visitor.typechecker import TypeChecker

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

    filename_inp = "exampleForm.txt" # temp override for testing

    form = ql.grammar.parse_file(filename_inp)

    Printer().execute(form)

    symboltable, symbol_check_success = SymbolChecker().execute(form)
    type_check_success = TypeChecker(symboltable).execute(form)

    if not (symbol_check_success and type_check_success):
        return

    # GUI:
    # create appjar window from form and symboltable
    # when a value is entered/changed, also update internal dictionary and
    # update conditional entry fields and update expression fields
    # when finished, dump results to JSON file

if __name__ == "__main__":
    main()
