import os
import sys
req_version = (3, 0)
cur_version = sys.version_info

if cur_version >= req_version:
    from parser.ql import QL
    # from operations.gui import *
    from operations.variables import GetVariables
    from operations.register_questions import RegisterComputedQuestions
    from operations.register_conditions import RegisterConditions
    from operations.gui import BuildGui
    from operations.environment import Environment
    from operations.type_checker import TypeChecker, DuplicateLabelsChecker, UndefinedVariableChecker, QuestionTypeChecker, InvalidOperandChecker
    # from operations.qui import *
    # from operations.gui import BuildGui,PrettyPrint,GetVariables
    from tkinter import Button
    from user_interface.ui import Application
    from operations.ql import VoidAlg
else:
    exit("FOEI JORDAN! Python3 gebruiken!")

# Read QL file to string
with open('tests/input/tax_office_example.ql', 'r') as ql_file:
    ql_string = ql_file.read().replace('\n', '')

# init parser
parser = QL()

# build AST
form_ast = parser.parse(ql_string)
environment = Environment()

type_checker = TypeChecker()
type_checker.add_checker(DuplicateLabelsChecker)
type_checker.add_checker(UndefinedVariableChecker)
type_checker.add_checker(QuestionTypeChecker)
type_checker.add_checker(InvalidOperandChecker)




create_environment = form_ast.alg(GetVariables(environment))
create_environment.execute()
register_computed_questions = RegisterComputedQuestions(environment)
form_ast.alg(register_computed_questions).execute()
form_ast.alg(RegisterConditions(environment)).execute()
app = Application(form_ast.name)
create_ui = BuildGui(app.root, environment)
app.environment = environment
form = form_ast.alg(create_ui).execute()
app.add_element(form)
app.render()
