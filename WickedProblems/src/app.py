import os
import sys
req_version = (3,0)
cur_version = sys.version_info

if cur_version >= req_version:
    from parser.ql import QL
    from operations.ql import Eval, RegisterComputedQuestions, RegisterConditions
    from operations.gui import BuildGui,PrettyPrint,GetVariables
    from tkinter import Button
    from user_interface.ui import Application
else:
   exit("FOEI JORDAN! Python3 gebruiken!")

# Read QL file to string
with open('tests/input/tax_office_example.ql', 'r') as ql_file:
    ql_string = ql_file.read().replace('\n', '')

# init parser
parser = QL()

# build AST
form_ast = parser.parse(ql_string)
environment = GetVariables([])
create_environment = form_ast.alg(environment)
create_environment.execute()
register_computed_questions = RegisterComputedQuestions(environment)
form_ast.alg(register_computed_questions).execute()
form_ast.alg(RegisterConditions(environment)).execute()
app = Application()
create_ui = BuildGui(app.root, environment.environment)
app.environment = environment.environment
form = form_ast.alg(create_ui).execute()
app.add_element(form)
app.render()
