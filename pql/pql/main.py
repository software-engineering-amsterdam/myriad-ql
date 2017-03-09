# coding=utf-8
from io import open
from os.path import join
from sys import argv
from sys import exit

from PyQt5.QtWidgets import QApplication

from gui.FileWindow import FileWindow
from gui.Questionnaire import Questionnaire
from pql.evaluator.evaluator import Evaluator
from pql.identifierchecker.identifierchecker import IdentifierChecker
from pql.parser.parser import parse
from pql.typechecker.typechecker import TypeChecker

PATH_EXAMPLE = str(join("path", "to", "your", "file"))


def open_file(path):
    try:
        return open(path)
    except FileNotFoundError:
        print("The given file could not be found. Usage: python pql.py %s" % PATH_EXAMPLE)
        exit(1)


def print_result(main_message, error_list, exit_code):
    print(main_message)
    print('\n'.join(map(str, error_list)))
    exit(exit_code)


def acquire_text(sys_args):
    ql_file = None

    try:
        ql_file = open_file(sys_args[1])
    except IndexError:
        print("Usage: python pql.py %s" % PATH_EXAMPLE)
        exit(2)

    if ql_file is None:
        print("No file was found:  usage: python pql.py %s" % PATH_EXAMPLE)
        exit(3)

    ql_str = ql_file.read()
    ql_file.close()
    del ql_file
    return ql_str


def acquire_identifiers(ql_ast):
    identifier_checker = IdentifierChecker()
    result = identifier_checker.visit(ql_ast)
    del identifier_checker
    return result


def check_type(ql_ast, ql_identifier_check_result):
    type_checker = TypeChecker(ql_identifier_check_result)
    result = type_checker.visit(ql_ast)
    del type_checker
    return result


def evaluate(ql_ast, ql_identifier_check_result):
    evaluator = Evaluator(strip_keys_from_dict(ql_identifier_check_result))
    return evaluator.visit(ql_ast)


def strip_keys_from_dict(ql_identifier_check_result):
    dict_ = dict()
    for key in ql_identifier_check_result.keys():
        dict_[key] = None
    return dict_


def ql(ql_str):
    ql_ast = parse(ql_str)
    if ql_ast is None:
        exit(4)
    ql_identifier_check_result, identifier_result_errors = acquire_identifiers(ql_ast)

    if identifier_result_errors:
        print_result('Identifier checker had errors', identifier_result_errors, 4)

    ql_type_check_result = check_type(ql_ast, ql_identifier_check_result)
    if ql_type_check_result:
        print_result('Type checker had errors', ql_type_check_result, 5)

    return evaluate(ql_ast, ql_identifier_check_result)

if __name__ == '__main__':
    app = QApplication(argv)
    file_window = FileWindow()

    ql_str = acquire_text(argv)
    ql_ast = parse(ql_str)
    if ql_ast is None:
        exit(4)

    ql_identifier_check_result, identifier_result_errors = acquire_identifiers(ql_ast)

    gui = Questionnaire()
    gui.visit(ql_ast).show()

    exit(app.exec_())


#TODO: UI zo bouwen dat er een scherm komt waar een file ingeladen kan worden, dit zodat, we de errors kunnen tonen van het parsen/checken
#TODO: Dit eerste scherm is vrij leeg buiten een knop met een eventueel overzicht van errors, als het goed gaat maak een QuestionairreWizard aan
#TODO: Line numbers in het Node object stoppen en deze in error messages gebruiken
