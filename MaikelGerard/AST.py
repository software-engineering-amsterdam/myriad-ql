from ParserTokens import ParserTokens as Tokens
import pyparsing as pp
import decimal
import datetime


class QuestionnaireAST(object):
    def __init__(self, src, loc, tokens):
        self.root = Node("root", src, loc)
        self.root = tokens[0]

    def __eq__(self, other):
        return other.root == self.root

    def __str__(self):
        return self.root.__str__(0)


class Node(object):
    def __init__(self, node_type, src=None, loc=None):
        self.node_type = node_type
        self.children = []

        # Add location info if it is available.
        if src is None or loc is None:
            self.col = self.line = None
        else:
            self.col = pp.col(loc, src)
            self.line = pp.lineno(loc, src)

    def add_child(self, node):
        self.children.append(node)

    def assert_message(self, message):
        return "{}[{},{}]: {}".format(
            self.node_type, self.line, self.col, message
        )

    def accept(self, visitor):
        print self.node_type
        for child in self.children:
            child.accept(visitor)

    def __eq__(self, other):
        if other.node_type != self.node_type:
            return False

        for (child_self, child_other) in zip(self.children, other.children):
            if not child_self == child_other:  # Use == as it is the only node operator implemented.
                return False
        return True

    def __str__(self, indent=0):
        output = indent * "  " + "{}:\n".format(self.node_type)

        for child in self.children:
            output += child.__str__(indent + 1)
        return output


class FormNode(Node):
    def __init__(self, src, loc, tokens):
        super(FormNode, self).__init__("form", src, loc)
        form_data = tokens[0]

        assert form_data[0] == "@form", self.assert_message(
            "Form is of invalid type: " + form_data[0]
        )
        self.name = form_data[1].val
        self.form_block = form_data[2]

    def accept(self, visitor):
        super(FormNode, self).accept(visitor)
        self.form_block.accept(visitor)

    def __eq__(self, other):
        return self.node_type == other.node_type and \
               self.form_block == other.form_block

    def __str__(self, indent=0):
        output = indent * "  " + "{}:\n".format(self.node_type)
        output += self.form_block.__str__(indent + 1)
        return output


class BlockNode(Node):
    def __init__(self, src, loc, block_data):
        super(BlockNode, self).__init__("block", src, loc)
        block_data = block_data[0]

        for statement in block_data:
            self.add_child(statement)


class QuestionNode(Node):
    def __init__(self, src, loc, token):
        super(QuestionNode, self).__init__("question", src, loc)
        question = token[0]

        self.question = question[0].val
        self.name = question[1].val
        self.type = question[2]

    def accept(self, visitor):
        super(QuestionNode, self).accept(visitor)
        visitor.question_node(self)

    def __eq__(self, other):
        return other.node_type == self.node_type and \
               other.question == self.question and other.name == self.name and \
               other.type == self.type

    def __str__(self, indent=0):
        return indent * "  " + "{}: {} {}\n".format(
            self.node_type, self.question, self.type
        )


class ComputedQuestionNode(Node):
    def __init__(self, src, loc, token):
        super(ComputedQuestionNode, self).__init__("computedQuestion", src, loc)
        question = token[0]

        self.question = question[0].val
        self.name = question[1].val
        self.type = question[2]

        self.expression = question[3]

    def accept(self, visitor):
        super(ComputedQuestionNode, self).accept(visitor)
        visitor.computed_question_node(self)

    def __eq__(self, other):
        return other.node_type == self.node_type and \
               other.question == self.question and other.name == self.name and \
               other.type == self.type and other.expression == self.expression

    def __str__(self, indent=0):
        output = indent * "  " + "{}: \"{}\" {}: {}".format(
            self.node_type, self.question, self.name, self.type
        )
        return output + " = ({})\n".format(self.expression.__str__(0))


class IfNode(Node):
    def __init__(self, src, loc, conditional):
        super(IfNode, self).__init__("if", src, loc)
        conditional = conditional[0]

        assert conditional[0] == "@if", self.assert_message(
            "invalid keyword '{}'".format(conditional[0])
        )
        self.expression = conditional[1]
        self.if_block = conditional[2]

    def accept(self, visitor):
        super(IfNode, self).accept(visitor)
        visitor.if_node(self)

    def __eq__(self, other):
        return other.expression == self.expression and other.if_block == self.if_block

    def __str__(self, indent=0):
        output = indent * "  " + "if ({}): \n".format(self.expression)
        return output + self.if_block.__str__(indent + 1)


class IfElseNode(Node):
    def __init__(self, src, loc, conditional):
        super(IfElseNode, self).__init__("ifElse", src, loc)
        conditional = conditional[0]

        assert conditional[0] == "@if", self.assert_message(
            "invalid keyword '{}'".format(conditional[0]))
        self.expression = conditional[1]
        self.if_block = conditional[2]

        assert conditional[3] == "@else", self.assert_message(
            "invalid keyword '{}'".format(conditional[3]))
        self.else_block = conditional[4]

    def accept(self, visitor):
        super(IfElseNode, self).accept(visitor)
        visitor.if_else_node(self)

    def __eq__(self, other):
        return other.expression == self.expression and other.if_block == self.if_block and \
               other.else_block == self.else_block

    def __str__(self, indent=0):
        output = indent * "  " + "if ({}): \n".format(self.expression)
        output += self.if_block.__str__(indent + 1)
        output += indent * "  " + "else: \n"
        return output + self.else_block.__str__(indent + 1)


class BinOpNode(Node):
    def __init__(self, src, loc, binop):
        super(BinOpNode, self).__init__("binOp", src, loc)

        self.left = binop[0]
        self.op = binop[1]
        self.op_function = Tokens.BINOPS[binop[1]]
        self.right = binop[2]

    def accept(self, visitor):
        super(BinOpNode, self).accept(visitor)
        visitor.binop_node(self)

    def __eq__(self, other):
        return other.node_type == self.node_type and \
               other.left == self.left and other.op == self.op and \
               other.right == self.right

    def __str__(self, indent=0):
        return "({} {} {})".format(
            str(self.left), str(self.op), str(self.right)
        )


class MonOpNode(Node):
    def __init__(self, src, loc, token):
        super(MonOpNode, self).__init__("monOp", src, loc)
        monop = token[0]

        self.op = monop[0]
        self.op_function = Tokens.MONOPS[monop[0]]
        self.right = monop[1]

    def accept(self, visitor):
        super(MonOpNode, self).accept(visitor)
        visitor.monop_node(self)

    def __eq__(self, other):
        return other.node_type == self.node_type and \
               other.op == self.op and other.right == self.right

    def __str__(self, indent=0):
        return "{}{}".format(self.op, str(self.right))


class StringNode(Node):
    def __init__(self, src, loc, token):
        super(StringNode, self).__init__("string", src, loc)
        self.val = token[0]

    def accept(self, visitor):
        super(StringNode, self).accept(visitor)
        return visitor.string_node(self)

    def __eq__(self, other):
        return other.node_type == self.node_type and other.val == self.val

    def __str__(self, indent=0):
        return self.val


class IntNode(Node):
    def __init__(self, src, loc, token):
        super(IntNode, self).__init__("int", src, loc)
        self.val = decimal.Decimal(token[0])

    def accept(self, visitor):
        super(IntNode, self).accept(visitor)
        return visitor.int_node(self)

    def __eq__(self, other):
        return other.node_type == self.node_type and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)


class BoolNode(Node):
    def __init__(self, src, loc, token):
        super(BoolNode, self).__init__("boolean", src, loc)
        self.val = True if token[0] == "true" else False

    def accept(self, visitor):
        super(BoolNode, self).accept(visitor)
        return visitor.bool_node(self)

    def __eq__(self, other):
        return other.node_type == self.node_type and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)


class VarNode(Node):
    def __init__(self, src, loc, token):
        super(VarNode, self).__init__("var", src, loc)
        self.val = token[0]

    def accept(self, visitor):
        super(VarNode, self).accept(visitor)
        return visitor.var_node(self)

    def __eq__(self, other):
        return other.node_type == self.node_type and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)


class DecimalNode(Node):
    def __init__(self, src, loc, token):
        super(DecimalNode, self).__init__("dec", src, loc)
        self.val = decimal.Decimal(token[0])

    def accept(self, visitor):
        super(DecimalNode, self).accept(visitor)
        return visitor.decimal_node(self)

    def __eq__(self, other):
        return other.node_type == self.node_type and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)


class DateNode(Node):
    def __init__(self, src, loc, token):
        super(DateNode, self).__init__("date", src, loc)
        date = token[0].split("-")
        assert len(date) == 3, "Date is of invalid length {}".format(date)

        self.val = datetime.date(day=date[0], month=date[1], year=date[2])

    def accept(self, visitor):
        super(DateNode, self).accept(visitor)
        return visitor.date_node(self)

    def __eq__(self, other):
        return other.node_type == self.node_type and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)
