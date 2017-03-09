from json import dump
from sys import argv
from os.path import isfile, splitext

from gui.formapp import FormApp
from misc.messages import *
from ql.grammar import parse_file as parse_ql
from ql.visitors.dependency_checker import DependencyChecker
from ql.visitors.printer import Printer
from ql.visitors.symbol_checker import SymbolChecker
from ql.visitors.type_checker import TypeChecker
from qls.grammar import parse_file as parse_qls
from qls.visitors.type_checker import *


def export(filename, dictionary):
    with open(filename, "w") as fp:
        dump(dictionary, fp)


def qls_filename(ql_filename):
    root, extension = splitext(ql_filename)
    return root + ".qls"


def main():
    if len(argv) < 3:
        print(ErrorMessage("insufficient arguments given, requires input and"
                           "output file names"))
        return

    form_file = argv[1]
    dump_file = argv[2]

    if not isfile(form_file):
        print(ErrorMessage("file {} does not exist".format(form_file)))
        return

    # form_file = "wrongForm.ql"

    form = parse_ql(form_file)
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

    layout_file = qls_filename(form_file)
    if not isfile(layout_file):
        layout = None
        print(WarningMessage(
            "qls filename \"{}\" does not exist".format(layout_file)))
    else:
        layout = parse_qls(layout_file)

        # TODO: symbol checking
        layout_errors = []
        QlsTypeChecker(symboltable, layout_errors).check(layout)

    for error in layout_errors:
        print(error)

    if any(error.critical for error in layout_errors):
        return

    app = FormApp(form, layout=layout, on_exit=lambda form_app: export(dump_file, form_app.environment))
    app.start()

if __name__ == "__main__":
    main()
