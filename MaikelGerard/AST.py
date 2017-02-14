import pyparsing as pp
import decimal


class QuestionnaireAST(object):
    def __init__(self, src, loc, tokens):
        self.root = Node("root")
        self.root.add_loc_info(loc, src)

        for form in tokens:
            self.root.add_child(form)

    def print_ast(self):
        return self.root.__str__(0)


class Node(object):
    def __init__(self, node_type):
        self.node_type = node_type
        self.children = []
        self.col = None
        self.line = None

    def add_child(self, node):
        self.children.append(node)

    def add_loc_info(self, loc, src):
        self.col = pp.col(loc, src)
        self.line = pp.line(loc, src)

    def __str__(self, indent=0):
        output = "{}:\n".format(self.node_type)

        for child in self.children:
            output += child.__str__(indent + 1)
        return output


class FormNode(Node):
    def __init__(self, src, loc, tokens):
        super(FormNode, self).__init__("form")

        form_data = tokens[0]
        self.add_loc_info(loc, src)
        assert form_data[0] == "@form", \
            "Form is of invalid type: " + form_data[0]

        self.name = form_data[1]
        self.add_child(form_data[2])

    def __str__(self, indent=0):
        output = indent * "  " + "{}:\n".format(self.node_type)

        for child in self.children:
            output += child.__str__(indent + 1)
        return output


class BlockNode(Node):
    def __init__(self, src, loc, block_data):
        super(BlockNode, self).__init__("Block")
        self.add_loc_info(loc, src)

        block_data = block_data[0]
        for statement in block_data:
            self.add_child(statement)

    def __str__(self, indent=0):
        output = indent * "  " + "{}:\n".format(self.node_type)

        for child in self.children:
            output += child.__str__(indent + 1)
        return output


class QuestionNode(Node):
    def __init__(self, src, loc, token):
        super(QuestionNode, self).__init__("question")

        self.add_loc_info(loc, src)
        question = token[0]

        self.name = question[0]
        self.question = question[1]
        self.type = question[2]

        self.computed = len(question) > 3
        self.expression = None
        if self.computed:
            self.expression = question[3]

    def eval_type(self):
        return self.type

    def __str__(self, indent=0):
        output = indent * "  " + "{}: {} {}".format(
            self.node_type, self.question, self.type
        )
        if self.computed:
            output += " = ({})".format(self.expression.__str__(0))
        output += "\n"
        return output


class ConditionalNode(Node):
    def __init__(self, src, loc, conditional):
        super(ConditionalNode, self).__init__("conditional")
        self.add_loc_info(loc, src)

        conditional = conditional[0]

        assert conditional[0] == "@if", \
            "creating conditional node while having {} data".format(conditional[0])
        self.expression = conditional[1]
        self.add_child(conditional[2])

        self.else_block = None
        if len(conditional) == 5:
            assert conditional[3] == "@else", \
                "Invalid else condition {}".format(conditional[3])
            self.else_block = conditional[4]

    def __str__(self, indent=0):
        output = indent * "  " + "if ({}): \n".format(self.expression)

        for child in self.children:
            output += child.__str__(indent + 1)
        if self.else_block is not None:
            output += indent * "  " + "else: \n"
            output += self.else_block.__str__(indent + 1)
        return output


class BinOpNode(Node):
    def __init__(self, src, loc, binop):
        super(BinOpNode, self).__init__("BinOp")

        self.add_loc_info(loc, src)
        self.left = binop[0]
        self.op = binop[1]
        self.right = binop[2]

    def __str__(self, ident=0):
         return "({} {} {})".format(str(self.left), str(self.op), str(self.right))


class MonOpNode(Node):
    def __init__(self, src, loc, token):
        super(MonOpNode, self).__init__("MonOp")
        monop = token[0]
        self.add_loc_info(loc, src)
        self.op = monop[0]
        self.right = monop[1]

    def __str__(self, ident=0):
        return "{}{}".format(self.op, str(self.right))


class IntNode(Node):
    def __init__(self, src, loc, token):
        super(IntNode, self).__init__("Int")
        self.val = decimal.Decimal(token[0])
        self.add_loc_info(loc, src)

    def __str__(self, indent=0):
        return str(self.val)


class BoolNode(Node):
    def __init__(self, src, loc, token):
        super(BoolNode, self).__init__("Boolean")
        self.val = True if token[0] == "true" else False
        self.add_loc_info(loc, src)

    def __str__(self, indent=0):
        return str(self.val)


class VarNode(Node):
    def __init__(self, src, loc, token):
        super(VarNode, self).__init__("Var")
        self.val = token[0]
        self.add_loc_info(loc, src)

    def __str__(self, indent=0):
        return str(self.val)


class DecimalNode(Node):
    def __init__(self, src, loc, token):
        super(DecimalNode, self).__init__("Dec")
        self.val = decimal.Decimal(token[0])
        self.add_loc_info(loc, src)

    def __str__(self, indent=0):
        return str(self.val)