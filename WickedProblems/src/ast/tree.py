from .node import *
from parser.ql import QL
from pyparsing import Word, ParseException


class Tree(object):

    def __init__(self):
        pass

    def construct(self, parse_result):
        # Check if valid form type
        try:
            QL.form_type.parseString(parse_result[0])
        except (KeyError, IndexError, ParseException):
            raise ValueError("Malformed Form Data")
        # Construct the root node
        #   0 type, 1 identifier, 2 content
        root = BaseNode(parse_result[1])
        # Recursively parse the children
        dict_nodes = parse_result.asDict()
        for declaration in dict_nodes['declarations']:
            for x in declaration:
                if (x == 'question'):
                    content = declaration[x]
                    print(content[1], root, content[2], content[0])
                    QuestionNode(content[1], root, content[2], content[0])
             
        # for x in range(2,len(parse_result)):
        #    self.create_node(root, parse_result[x])
        return root

    def create_node1(self, parent, content):
        if(len(content) == 2):  # Conditional
            node = ConditionalNode(parent, content[0], content[1][0])
            for sub in content[1][1]:
                self.create_node(node, sub)
        elif(len(content) == 3):  # Question
            QuestionNode(content[1], parent, content[2], content[0])
        elif(len(content) == 5):  # Statement
            # We skip index 3, since it contains the '='
            StatementNode(content[1], parent, content[
                          2], content[0], content[4])
        else:
            raise ParseException("Invalid Field Type")
