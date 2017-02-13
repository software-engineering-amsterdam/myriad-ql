from grammar.ql import QL
import os


def start():
    parser = QL()
    fileName = "./WickedProblems/src/tests/input/tax_office_example.ql"
    with open(os.path.abspath(fileName), 'r') as qlsource:
        ql = qlsource.read().replace('\n', '')
    parse_tree = parser.parse(ql)
    # print(parse_tree.type)
    # print(parse_tree.identifier)
    # print(parse_tree.asDict())
    for element in parse_tree.form_elements: 
        print(element.getName())
    print(parse_tree.pprint())
    print(parse_tree.asXML())
    
if __name__ == '__main__':
    start()
