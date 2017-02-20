from sys import argv, exit
from os.path import isfile
from json import dump

import ql.grammar


def main():

    if len(argv) < 3:
        print("Error: insufficient arguments given, requires input and output filenames")
        return

    filename_inp = argv[1]
    filename_out = argv[2]

    if not isfile(filename_inp):
        print("Error: file {} does not exist".format(filename_inp))
        return

    form = ql.grammar.parse_file(filename_inp)
    #symboltable = form.symbols()

    #if not form.typecheck():
    #    print("Error: form is not correct")
    #    return

    # GUI:
    # create appjar window from form and symboltable
    # when a value is entered/changed, also update internal dictionary and
    # update conditional entry fields and update expression fields
    # when finished, dump results to JSON file

if __name__ == "__main__":
    main()
