from json import dump
from sys import argv, exit
from os.path import isfile, splitext

from gui.app import App
from misc.messages import *
from ql.grammar import parse_file as parse_ql
from ql.visitors.dependency_checker import DependencyChecker
from ql.visitors.printer import Printer
from ql.visitors.symbol_checker import SymbolChecker
from ql.visitors.type_checker import TypeChecker
from qls.grammar import parse_file as parse_qls
from qls.visitors.type_checker import TypeChecker as QlsTypeChecker
from qls.visitors.symbol_checker import SymbolChecker as QlsSymbolChecker


def export(filename, dictionary):
    with open(filename, "w") as fp:
        dump(dictionary, fp)


def qls_filename(ql_filename):
    root, extension = splitext(ql_filename)
    return root + ".qls"


def print_errors(errors):
    for error in errors:
        print(error)


def exit_on_errors(errors):
    if any(error.critical for error in errors):
        print_errors(errors)
        exit()


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
    exit_on_errors(errors)

    TypeChecker(symboltable, errors).check(form)
    DependencyChecker(errors).check(form)
    exit_on_errors(errors)

    print_errors(errors)

    layout_file = qls_filename(form_file)
    if not isfile(layout_file):
        layout = None
        print(WarningMessage(
            "qls filename \"{}\" does not exist".format(layout_file)))
    else:
        layout = parse_qls(layout_file)

        layout_errors = []

        QlsSymbolChecker(symboltable, layout_errors).check(layout)
        exit_on_errors(layout_errors)

        QlsTypeChecker(symboltable, layout_errors).check(layout)
        exit_on_errors(layout_errors)

        print_errors(layout_errors)

    app = App(form, layout=layout, on_exit=lambda app: export(dump_file, app.environment))
    app.start()

if __name__ == "__main__":
    main()
