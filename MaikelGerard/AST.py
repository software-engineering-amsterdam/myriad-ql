class QuestionnaireAST(object):
    def __init__(self, form, line=0, col=0):
        self.root = Node(line, col)
        self.root = form

    def __eq__(self, other):
        return other.root == self.root

    def __str__(self):
        return str(self.root)


class Node(object):
    def __init__(self, line=0, col=0):
        self.children = []

        self.line = line
        self.col = col

    def add_child(self, node):
        self.children.append(node)

    def assert_message(self, message):
        return "Node [{},{}]: {}".format(
            self.line, self.col, message
        )

    def accept(self, visitor):
        for child in self.children:
            child.accept(visitor)

    def __eq__(self, other):
        if type(self) == type(other):
            return False

        for (child_self, child_other) in zip(self.children, other.children):
            if not child_self == child_other:  # Use == as it is the only node operator implemented.
                return False
        return True

    def __str__(self, indent=0):
        output = indent * "  " + "Node:\n"

        for child in self.children:
            output += child.__str__(indent + 1)
        return output


class FormNode(Node):
    def __init__(self, name, body, line=0, col=0):
        super(FormNode, self).__init__(line, col)

        self.name = name
        self.form_block = body

    def accept(self, visitor):
        super(FormNode, self).accept(visitor)
        self.form_block.accept(visitor)

    def __eq__(self, other):
        return super(FormNode, self).__eq__(other) and self.name == other.name and \
               self.form_block == other.form_block

    def __str__(self, indent=0):
        output = indent * "  " + "form:\n"
        output += self.form_block.__str__(indent + 1)
        return output


class BlockNode(Node):
    def __init__(self, block_body, line=0, col=0):
        super(BlockNode, self).__init__(line, col)
        block_data = block_body

        for statement in block_data:
            self.add_child(statement)


class QuestionNode(Node):
    def __init__(self, question, name, var_type, line=0, col=0):
        super(QuestionNode, self).__init__(line, col)

        self.question = question
        self.name = name
        self.type = var_type

    def accept(self, visitor):
        super(QuestionNode, self).accept(visitor)
        visitor.question_node(self)

    def __eq__(self, other):
        return super(QuestionNode, self).__eq__(other) and \
               other.question == self.question and other.name == self.name and \
               other.type == self.type

    def __str__(self, indent=0):
        return indent * "  " + "question: \"{}\" {}: {}\n".format(
            self.question, self.type, self.name
        )


class ComputedQuestionNode(QuestionNode):
    def __init__(self, question, name, var_type, line=0, col=0):
        super(ComputedQuestionNode, self).__init__(question, name, var_type, line, col)
        self.expression = var_type

    def accept(self, visitor):
        super(ComputedQuestionNode, self).accept(visitor)
        visitor.comp_question_node(self)

    def __eq__(self, other):
        return super(ComputedQuestionNode, self).__eq__(other) and \
               other.expression == self.expression

    def __str__(self, indent=0):
        output = indent * "  " + "question: \"{}\" {}: {}".format(
            self.question, self.name, self.type
        )
        return output + " = ({})\n".format(self.expression.__str__(0))


class IfNode(Node):
    def __init__(self, condition, if_block, line=0, col=0):
        super(IfNode, self).__init__(line, col)

        self.expression = condition
        self.if_block = if_block

    def accept(self, visitor):
        super(IfNode, self).accept(visitor)
        visitor.if_node(self)

    def __eq__(self, other):
        return super(IfNode, self).__eq__(other) and \
               other.expression == self.expression and other.if_block == self.if_block

    def __str__(self, indent=0):
        output = indent * "  " + "if ({}): \n".format(self.expression)
        return output + self.if_block.__str__(indent + 1)


class IfElseNode(IfNode):
    def __init__(self, condition, if_block, else_block, line=0, col=0):
        super(IfElseNode, self).__init__(condition, if_block, line, col)
        self.else_block = else_block

    def accept(self, visitor):
        super(IfElseNode, self).accept(visitor)
        visitor.if_else_node(self)

    def __eq__(self, other):
        return super(IfElseNode, self).__eq__(other) and other.else_block == self.else_block

    def __str__(self, indent=0):
        output = super(IfElseNode, self).__str__(indent + 1)
        output += indent * "  " + "else: \n"
        return output + self.else_block.__str__(indent + 1)


class MonOpNode(Node):
    def __init__(self, expression, line, col):
        super(MonOpNode, self).__init__(line, col)
        self.expression = expression

    def accept(self, visitor):
        super(MonOpNode, self).accept(visitor)
        visitor.monop_node(self)

    def __eq__(self, other):
        return super(MonOpNode, self).__eq__(other) and other.expression == self.expression


class NegNode(MonOpNode):
    def __init__(self, expression, line=0, col=0):
        super(NegNode, self).__init__(expression, line, col)

    def accept(self, visitor):
        super(NegNode, self).accept(visitor)
        return visitor.neg_node(self)

    def __str__(self, indent=0):
        return "!{}".format(self.expression)


class MinNode(MonOpNode):
    def __init__(self, expression, line=0, col=0):
        super(MinNode, self).__init__(expression, line, col)

    def accept(self, visitor):
        super(MinNode, self).accept(visitor)
        return visitor.min_node(self)

    def __str__(self, indent=0):
        return "-{}".format(self.expression)


class PlusNode(MonOpNode):
    def __init__(self, expression, line=0, col=0):
        super(PlusNode, self).__init__(expression, line, col)

    def accept(self, visitor):
        super(PlusNode, self).accept(visitor)
        return visitor.plus_node(self)

    def __str__(self, indent=0):
        return "+{}".format(self.expression)


class ArithmeticExprNode(Node):
    def __init__(self, left, right, line=0, col=0):
        super(ArithmeticExprNode, self).__init__(line, col)
        self.left = left
        self.right = right

    def accept(self, visitor):
        super(ArithmeticExprNode, self).accept(visitor)
        return visitor.arithmetic_node(self)


class MulNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(MulNode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(MulNode, self).accept(visitor)
        return visitor.mul_node(self)

    def __str__(self, indent=0):
        return "({} * {})".format(self.left, self.right)


class DivNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(DivNode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(DivNode, self).accept(visitor)
        return visitor.div_node(self)

    def __str__(self, indent=0):
        return "({} / {})".format(self.left, self.right)


class AddNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(AddNode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(AddNode, self).accept(visitor)
        return visitor.add_node(self)

    def __str__(self, indent=0):
        return "({} + {})".format(self.left, self.right)


class SubNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(SubNode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(SubNode, self).accept(visitor)
        return visitor.sub_node(self)

    def __str__(self, indent=0):
        return "({} - {})".format(self.left, self.right)


class ComparisonExprNode(Node):
    def __init__(self, left, right, line=0, col=0):
        super(ComparisonExprNode, self).__init__(line, col)
        self.left = left
        self.right = right

    def accept(self, visitor):
        super(ComparisonExprNode, self).accept(visitor)
        return visitor.comparison_node(self)


class LTNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(LTNode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(LTNode, self).accept(visitor)
        return visitor.lt_node(self)

    def __str__(self, indent=0):
        return "({} < {})".format(self.left, self.right)


class LTENode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(LTENode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(LTENode, self).accept(visitor)
        return visitor.lte_node(self)

    def __str__(self, indent=0):
        return "({} <= {})".format(self.left, self.right)


class GTNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(GTNode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(GTNode, self).accept(visitor)
        return visitor.gt_node(self)

    def __str__(self, indent=0):
        return "({} > {})".format(self.left, self.right)


class GTENode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(GTENode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(GTENode, self).accept(visitor)
        return visitor.gte_node(self)

    def __str__(self, indent=0):
        return "({} >= {})".format(self.left, self.right)


class EqNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(EqNode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(EqNode, self).accept(visitor)
        return visitor.eq_node(self)

    def __str__(self, indent=0):
        return "({} == {})".format(self.left, self.right)


class NeqNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(NeqNode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(NeqNode, self).accept(visitor)
        return visitor.neq_node(self)

    def __str__(self, indent=0):
        return "({} != {})".format(self.left, self.right)


class LogicalExprNode(Node):
    def __init__(self, left, right, line=0, col=0):
        super(LogicalExprNode, self).__init__(line, col)
        self.left = left
        self.right = right

    def accept(self, visitor):
        super(LogicalExprNode, self).accept(visitor)
        return visitor.logical_node(self)


class AndNode(LogicalExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(AndNode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(AndNode, self).accept(visitor)
        return visitor.and_node(self)

    def __str__(self, indent=0):
        return "({} && {})".format(self.left, self.right)


class OrNode(LogicalExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(OrNode, self).__init__(left, right, line, col)

    def accept(self, visitor):
        super(OrNode, self).accept(visitor)
        return visitor.or_node(self)

    def __str__(self, indent=0):
        return "({} || {})".format(self.left, self.right)


class StringNode(Node):
    def __init__(self, string, line=0, col=0):
        super(StringNode, self).__init__(line, col)
        self.val = string

    def accept(self, visitor):
        super(StringNode, self).accept(visitor)
        return visitor.string_node(self)

    def __eq__(self, other):
        return super(StringNode, self).__eq__(other) and other.val == self.val

    def __str__(self, indent=0):
        return self.val


class IntNode(Node):
    def __init__(self, integer, line=0, col=0):
        super(IntNode, self).__init__(line, col)
        self.val = integer

    def accept(self, visitor):
        super(IntNode, self).accept(visitor)
        return visitor.int_node(self)

    def __eq__(self, other):
        return super(IntNode, self).__eq__(other) and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)


class BoolNode(Node):
    def __init__(self, boolean, line=0, col=0):
        super(BoolNode, self).__init__(line, col)
        self.val = boolean

    def accept(self, visitor):
        super(BoolNode, self).accept(visitor)
        return visitor.bool_node(self)

    def __eq__(self, other):
        return super(BoolNode, self).__eq__(other) and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)


class VarNode(Node):
    def __init__(self, var_name, line=0, col=0):
        super(VarNode, self).__init__(line, col)
        self.val = var_name

    def accept(self, visitor):
        super(VarNode, self).accept(visitor)
        return visitor.var_node(self)

    def __eq__(self, other):
        return super(VarNode, self).__eq__(other) and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)


class MoneyNode(Node):
    def __init__(self, money, line=0, col=0):
        super(MoneyNode, self).__init__(line, col)
        self.val = money

    def accept(self, visitor):
        super(MoneyNode, self).accept(visitor)
        return visitor.decimal_node(self)

    def __eq__(self, other):
        return super(MoneyNode, self).__eq__(other) and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)


class DecimalNode(Node):
    def __init__(self, decimal, line=0, col=0):
        super(DecimalNode, self).__init__(line, col)
        self.val = decimal

    def accept(self, visitor):
        super(DecimalNode, self).accept(visitor)
        return visitor.decimal_node(self)

    def __eq__(self, other):
        return super(Node, self).__eq__(other) and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)


class DateNode(Node):
    def __init__(self, date, line=0, col=0):
        super(DateNode, self).__init__(line, col)
        self.val = date

    def accept(self, visitor):
        super(DateNode, self).accept(visitor)
        return visitor.date_node(self)

    def __eq__(self, other):
        return super(DateNode, self).__eq__(other) and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)
