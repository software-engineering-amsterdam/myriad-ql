import os
import sys
req_version = (3, 0)
cur_version = sys.version_info

if cur_version >= req_version:
    from parser.ql import QL
    from operations.gui import BuildGui
    from operations.environment import Environment
    from operations.pretty_print import PrettyPrint
    from operations.type_checker import TypeChecker, DuplicateLabelsChecker, UndefinedVariableChecker, QuestionTypeChecker, InvalidOperandChecker
    from user_interface.application import Application
    from operations.ql import VoidAlg
else:
    exit("Use Python 3")

# Read QL file to string
with open('tests/input/tax_office_example.ql', 'r') as ql_file:
    ql_string = ql_file.read().replace('\n', '')

# init parser
parser = QL()

# build AST
form_ast = parser.parse(ql_string)

type_checker = TypeChecker()
type_checker.add_checker(DuplicateLabelsChecker)
type_checker.add_checker(UndefinedVariableChecker)
type_checker.add_checker(QuestionTypeChecker)
type_checker.add_checker(InvalidOperandChecker)

if type_checker.is_valid(form_ast):
    app = Application(form_ast, form_ast.name)
    app.render()
