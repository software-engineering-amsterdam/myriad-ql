from json import dump
from sys import argv, exit
from os.path import isfile, splitext

from ql.grammar import parse_file as parse_ql
from ql.check import check as check_ql
from qls.grammar import parse_file as parse_qls
from qls.check import check as check_qls

from gui.app import QlApp, QlsApp
from misc.messages import *


def export(filename, dictionary):
    with open(filename, "w") as pointer:
        dump(dictionary, pointer)


def qls_filename(ql_filename):
    root, extension = splitext(ql_filename)
    return root + ".qls"


def print_errors(errors):
    for error in errors:
        print(error)


def exit_on_critcal_error(errors):
    if any(error.critical for error in errors):
        exit()


def validate_arguments():
    if len(argv) < 3:
        ErrorMessage.print("insufficient arguments given, requires input and"
                           "output file names")
        exit()

    form_file = argv[1]
    json_file = argv[2]

    if not isfile(form_file):
        ErrorMessage.print("file {} does not exist".format(form_file))
        exit()

    return form_file, json_file


def main():
    form_file, json_file = validate_arguments()

    form = parse_ql(form_file)

    symboltable = {}
    errors = []

    check_ql(form, symboltable, errors)

    print_errors(errors)
    exit_on_critcal_error(errors)

    exit_function = lambda app: export(json_file, app.environment)

    layout_file = qls_filename(form_file)

    if isfile(layout_file):
        layout = parse_qls(layout_file)

        errors.clear()
        check_qls(layout, symboltable, errors)

        print_errors(errors)
        exit_on_critcal_error(errors)

        QlsApp(form, layout, on_exit=exit_function).start()

    else:
        WarningMessage.print("qls filename \"{}\" does not "
                             "exist".format(layout_file))
        QlApp(form, on_exit=exit_function).start()

if __name__ == "__main__":
    main()
