from parser.ql import QL
import os
import operations.ql
from Tkinter import *
import sys

# Read QL file to string
with open('./WickedProblems/src/tests/input/tax_office_example.ql', 'r') as ql_file:
    ql_string = ql_file.read().replace('\n', '')


# init parser
parser = QL()


# build AST
form_ast = parser.parse(ql_string)

test_ast = parser.parse('form apple {   "How many people are there in your household?" numberInHoushold: integer}')
action = operations.ql.ToString


isinstance_alg = form_ast.alg(action())
print isinstance_alg.to_string()

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
