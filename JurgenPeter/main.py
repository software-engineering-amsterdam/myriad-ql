from json import dump
from os.path import isfile, splitext

from gui.formapp import FormApp
from misc.messages import *
from ql.grammar import parse_file as parse_ql
from ql.visitors.dependency_checker import DependencyChecker
from ql.visitors.printer import Printer
from ql.visitors.symbol_checker import SymbolChecker
from ql.visitors.type_checker import TypeChecker
from qls.grammar import parse_file as parse_qls


def export(filename, dictionary):
    with open(filename, "w") as fp:
        dump(dictionary, fp)


def qls_filename(ql_filename):
    root, extension = splitext(ql_filename)
    return root + ".qls"


def main():
    # if len(argv) < 3:
    #     print(ErrorMessage("insufficient arguments given, requires input and"
    #                        "output file names"))
    #     return
    #
    # filename_inp = argv[1]
    # filename_out = argv[2]
    #
    # if not isfile(filename_inp):
    #     print(ErrorMessage("file {} does not exist".format(filename_inp)))
    #     return

    filename = "exampleForm.ql"
    # filename = "wrongForm.ql"

    form = parse_ql(filename)
    Printer().print(form)

    errors = []
    symboltable = {}

    SymbolChecker(symboltable, errors).check(form)
    TypeChecker(symboltable, errors).check(form)
    DependencyChecker(errors).check(form)

    for error in errors:
        print(error)

    if any(error.critical for error in errors):
        return

    filename_layout = qls_filename(filename)
    if not isfile(filename_layout):
        layout = None
        print(WarningMessage(
            "qls filename \"{}\" does not exist".format(filename_layout)))
    else:
        layout = parse_qls(filename_layout)
    # TODO: typechecking

    app = FormApp(form, layout=layout, on_exit=lambda form_app: export("output.json", form_app.environment))
    app.start()

if __name__ == "__main__":
    main()
