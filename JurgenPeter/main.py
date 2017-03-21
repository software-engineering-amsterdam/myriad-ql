from json import dump
from sys import argv, exit
from os.path import isfile, splitext

from ql.grammar import parse_file as parse_ql
from ql.visitors.symbol_checker import SymbolChecker
from ql.visitors.type_checker import TypeChecker
from ql.visitors.dependency_checker import DependencyChecker
from qls.grammar import parse_file as parse_qls
from qls.visitors.symbol_checker import SymbolChecker as QlsSymbolChecker
from qls.visitors.type_checker import TypeChecker as QlsTypeChecker
from gui.app import QlApp, QlsApp
from misc.messages import *


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


def check_form(form, symboltable):
    form_errors = []

    SymbolChecker(symboltable, form_errors).check(form)
    exit_on_errors(form_errors)

    TypeChecker(symboltable, form_errors).check(form)
    DependencyChecker(form_errors).check(form)
    exit_on_errors(form_errors)

    print_errors(form_errors)


def check_layout(layout, symboltable):
    layout_errors = []

    QlsSymbolChecker(symboltable, layout_errors).check(layout)
    exit_on_errors(layout_errors)

    QlsTypeChecker(symboltable, layout_errors).check(layout)
    exit_on_errors(layout_errors)

    print_errors(layout_errors)


def main():
    if len(argv) < 3:
        ErrorMessage.print("insufficient arguments given, requires input and"
                           "output file names")
        return

    form_file = argv[1]
    dump_file = argv[2]

    if not isfile(form_file):
        ErrorMessage.print("file {} does not exist".format(form_file))
        return

    form = parse_ql(form_file)

    symboltable = {}
    check_form(form, symboltable)

    layout_file = qls_filename(form_file)
    if isfile(layout_file):
        layout = parse_qls(layout_file)
        check_layout(layout, symboltable)

        QlsApp(form, layout, on_exit=lambda app:
               export(dump_file, app.environment)).start()

    else:
        WarningMessage.print("qls filename \"{}\" does not "
                             "exist".format(layout_file))
        QlApp(form, on_exit=lambda app:
              export(dump_file, app.environment)).start()

if __name__ == "__main__":
    main()
