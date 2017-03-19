from parser.ql import QL
import os
import operations.ql
from Tkinter import *
import Tkinter as tk
import user_interface.ui as ui
import sys
import operations.gui
# Read QL file to string
with open('./WickedProblems/src/tests/input/tax_office_example.ql', 'r') as ql_file:
    ql_string = ql_file.read().replace('\n', '')


# init parser
parser = QL()


# build AST
form_ast = parser.parse(ql_string)

test_ast = parser.parse('form apple {   "Have you sold a house in 2010?" hasSoldHouse: string "How many people are there in your household?" numberInHoushold: integer}')
action = operations.ql.PrettyPrint


# isinstance_alg = form_ast.alg(action(0))
# print isinstance_alg.to_string(0)
# state = operations.ql.GetVariables([])
# isinstance_alg = form_ast.alg(state)

# print isinstance_alg.execute()
# print set(state.environment.get_variables())
# print state.environment.undefined_variables()
# print state.environment.questions
# print "---"*10
# print state.environment.duplicate_labels()
# print "-"*10
# print state.environment.check_types()

environment = operations.ql.GetVariables([])
create_environment = form_ast.alg(environment)






app = ui.Application()
create_ui = operations.gui.BuildGui(app.root, environment.environment)
form = form_ast.alg(create_ui).execute()
app.add_element(form)
def export_form():
    print create_ui.environment.export()
export_button = tk.Button(app.window, text="Export", command=export_form)
export_button.pack()
app.render()


# # Make printable AST
# action = operations.ql.QlAlgView
# instance_alg = AlgebraFactory.make(action(), form_ast)
# print instance_alg.view()

# action = operations.gui.QlAlgGUI
# instance_alg = AlgebraFactory.make(action(), form_ast)

# def start():
#     # parser = QL()
#     # fileName = "./WickedProblems/src/tests/input/tax_office_example.ql"
#     # with open(os.path.abspath(fileName), 'r') as qlsource:
#     #     ql = qlsource.read().replace('\n', '')
#     # parseTree = parser.parse(ql)
#     # print(parseTree)

# if __name__ == '__main__':
#     start()
