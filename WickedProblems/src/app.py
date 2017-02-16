from grammar.ql import QL
from ast.form import Form
from ast.node import *
import os


def start():
    parser = QL()
    fileName = "./WickedProblems/src/tests/input/tax_office_example.ql"
    with open(os.path.abspath(fileName), 'r') as qlsource:
        ql = qlsource.read().replace('\n', '')
    #parse_tree = parser.parse(ql)
    parse_tree = parser.parse('"are you an apple?" isApple: boolean  = (var1 + var2)')
    # print(parse_tree.type)
    # print(parse_tree.identifier)
    # print(parse_tree.asDict())
    # print parse_tree.asDict()
    print parse_tree.asXML()
    root = Node()
    root.parseNodes(parse_tree.asDict())
    # print key, element




if __name__ == '__main__':
    start()
