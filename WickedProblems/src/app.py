from grammar.ql import QL
import os


def start():
    parser = QL()
    fileName = "./WickedProblems/src/tests/input/tax_office_example.ql"
    with open(os.path.abspath(fileName), 'r') as qlsource:
        ql = qlsource.read().replace('\n', '')
    parseTree = parser.parse(ql)
    print(parseTree.asXML())

if __name__ == '__main__':
    start()
