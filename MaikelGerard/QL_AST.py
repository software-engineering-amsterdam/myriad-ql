from QL_Parser import QuestionnaireParser

class QuestionnaireAST(object):
    def __init__(self, parser_output):
        self.root = Node("root")

        self.create_ast(parser_output)

    def create_ast(self, parser_output):
        for form in parser_output:
            self.root.add_child(FormNode(form))

    def print_ast(self):
        self.root.__str__(0)


class Node(object):
    def __init__(self, node_type):
        self.node_type = node_type
        self.children = []

    def add_child(self, node):
        self.children.append(node)

    def __str__(self, indent=0):
        output = "{}:\n".format(self.node_type)

        for child in self.children:
            output += child.__str__(indent + 1)
        return output


class FormNode(Node):
    def __init__(self, form_data):
        super(FormNode, self).__init__("form")
        assert form_data[0] == "@form", "Form is of invalid type: " + form_data[0]

        self.name = form_data[1]

        block = BlockNode("block", form_data[2])
        self.add_child(block)

    def __str__(self, indent=0):
        output = indent * "  " + "{}:\n".format(self.node_type)

        for child in self.children:
            output += child.__str__(indent + 1)
        return output


class BlockNode(Node):
    def __init__(self, block_type, block_data):
        super(BlockNode, self).__init__(block_type)

        for statement in block_data:
            if statement[0] == "@if" or statement[0] == "@else":
                self.add_child(ConditionalNode(statement))
            else:
                self.add_child(QuestionNode(statement))

    def __str__(self, indent=0):
        output = indent * "  " + "{}:\n".format(self.node_type)

        for child in self.children:
            output += child.__str__(indent + 1)
        return output


class QuestionNode(Node):
    def __init__(self, input):
        super(QuestionNode, self).__init__("question")

        self.name = input[0]
        self.question = input[1]
        self.type = input[2]

        self.computed = len(input) > 3
        self.expression = None
        if self.computed:
            self.expression = ExpressionNode(input[3])

    def __str__(self, indent=0):
        output = indent * "  " + "{}: {} {}".format(
            self.node_type, self.question, self.type
        )
        if self.computed:
            output += "({})".format(self.expression.__str__(0))
        output += "\n"
        return output


class ConditionalNode(Node):
    def __init__(self, input):
        super(ConditionalNode, self).__init__("conditional")

        self.expression = ExpressionNode(input[1])
        self.add_child(BlockNode("if", input[2]))

        self.else_block = None
        if len(input) == 5:
            self.else_block = BlockNode("else", input[4])

    def __str__(self, indent=0):
        output = indent * "  " + "{}:({})\n".format(self.node_type,
                                                    self.expression)

        for child in self.children:
            output += child.__str__(indent + 1)
        if self.else_block is not None:
            output += self.else_block.__str__(indent + 1)
        return output


class ExpressionNode(Node):
    def __init__(self, expr_data):
        super(ExpressionNode, self).__init__("expression")

        self.right = None
        self.op = None
        self.left = None

        # A single variable or number
        if not isinstance(expr_data, list):
            self.left = expr_data

        elif expr_data[0] in ['!', '-']:  # Postfix operators
            self.op = expr_data[0]
            self.right = self.add_expression(expr_data[1])

        else:  # Infix operators
            self.left = self.add_expression(expr_data[0])
            self.op = expr_data[1]
            self.right = self.add_expression(expr_data[2])

    @staticmethod
    def add_expression(expr_data):
        if isinstance(expr_data, list):
            return ExpressionNode(expr_data)
        return expr_data

    def __str__(self, indent=0):
        output = ""
        if self.left is not None:
            if isinstance(self.left, ExpressionNode):
                output += "({})".format(self.left)
            else:
                output += "{}".format(self.left)
        if self.right is not None:
            if isinstance(self.right, ExpressionNode):
                output += " {} ({})".format(self.op, self.right)
            else:
                output += " {} {}".format(self.op, self.right)
        return output


if __name__ == '__main__':
    form1 = """
    form Box1HouseOwning {
        hasSoldHouse: "Did you sell a house in 2010?" boolean
        hasBoughtHouse: "Did you buy a house in 2010?" boolean
        hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean

        if (hasSoldHouse + newPrice + 4 + 23) {
            sellingPrice: "Price the house was sold for:" money
            privateDebt: "Private debts for the sold house:" money
            valueResidue: "Value residue:" money(300 * 100 - 20 * 10 * (25 - 3))
            if (newPrice) {
                privateDebt: "Private debts for the sold house:" money
            }
            else {
                privateDebt: "Private debts for the sold house:" money
            }
        }

        letTheDogsOut: "Who let the dogs out?" boolean
    }
    """

    parser = QuestionnaireParser()
    print (QuestionnaireAST(parser.parse(form1)).root)

