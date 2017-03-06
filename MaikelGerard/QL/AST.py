from decimal import Decimal
from datetime import date

# TODO: Improve hierarchy VarNodes should be separate, add expressionNode,
# create LiteralNodes and VarNode separate --> both are ExpressionNode.
# TODO: Remove print and eq, add to seperate traversal.
# TODO: Remove QuestionnaireAST, just make form the root.
# TODO: Replace 'is_boolean' etc. into double dispatch.
# TODO: Do we want to pass state in the visitor?
# TODO: question.name instead of question.name.val


class QuestionnaireAST(object):
    def __init__(self, form, line=0, col=0):
        self.root = form

    def __eq__(self, other):
        return other.root == self.root

    def __str__(self):
        return str(self.root.__str__(0))


class Node(object):
    def __init__(self, line=0, col=0):
        self.line = line
        self.col = col

    def assert_message(self, message):
        return "Node [{},{}]: {}".format(self.line, self.col, message)

    def __eq__(self, other):
        if type(self) != type(other):
            return False
        return True


class FormNode(Node):
    def __init__(self, name, form_body, line=0, col=0):
        super(FormNode, self).__init__(line, col)
        self.name = name
        self.form_block = form_body

    def accept(self, visitor):
        self.form_block.accept(visitor)

    def __eq__(self, other):
        return super(FormNode, self).__eq__(other) and \
               self.name == other.name and \
               self.form_block == other.form_block

    def __str__(self, indent=0):
        output = indent * "  " + "form:\n"
        output += self.form_block.__str__(indent + 1)
        return output


class BlockNode(Node):
    def __init__(self, block_body, line=0, col=0):
        super(BlockNode, self).__init__(line, col)
        self.children = []

        for statement in block_body:
            self.children.append(statement)

    def accept(self, visitor):
        for child in self.children:
            child.accept(visitor)

    def __eq__(self, other):
        if not super(BlockNode, self).__eq__(other):
            return False

        for (child_self, child_other) in zip(self.children, other.children):
            # Use == as it is the only operator implemented for 'Node'.
            if not child_self == child_other:
                return False
        return True

    def __str__(self, indent=0):
        output = indent * "  " + "block:\n"

        for child in self.children:
            output += child.__str__(indent + 1)
        return output


class QuestionNode(Node):
    def __init__(self, question, name, var_type, line=0, col=0):
        super(QuestionNode, self).__init__(line, col)
        self.question = question
        self.name = name
        self.type = var_type

    def get_identifier(self):
        return self.name.val

    def get_question(self):
        return self.question.val

    def accept(self, visitor):
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
    def __init__(self, question, name, var_type, expression, line=0, col=0):
        super(ComputedQuestionNode, self).__init__(
            question, name, var_type, line, col
        )
        self.expression = expression

    def accept(self, visitor):
        visitor.comp_question_node(self)

    def __eq__(self, other):
        return super(ComputedQuestionNode, self).__eq__(other) and \
               other.expression == self.expression

    def __str__(self, indent=0):
        output = indent * "  " + "question: \"{}\" {}: {}".format(
            self.question, self.name, self.type
        )
        return output + " = ({})\n".format(str(self.expression))


class IfNode(Node):
    def __init__(self, condition, if_block, line=0, col=0):
        super(IfNode, self).__init__(line, col)
        self.expression = condition
        self.if_block = if_block

    def accept(self, visitor):
        visitor.if_node(self)

    def __eq__(self, other):
        return super(IfNode, self).__eq__(other) and \
               other.expression == self.expression and \
               other.if_block == self.if_block

    def __str__(self, indent=0):
        output = indent * "  " + "if ({}): \n".format(self.expression)
        return output + self.if_block.__str__(indent + 1)


class IfElseNode(IfNode):
    def __init__(self, condition, if_block, else_block, line=0, col=0):
        super(IfElseNode, self).__init__(condition, if_block, line, col)
        self.else_block = else_block

    def accept(self, visitor):
        visitor.if_else_node(self)

    def __eq__(self, other):
        return super(IfElseNode, self).__eq__(other) and \
               other.else_block == self.else_block

    def __str__(self, indent=0):
        output = super(IfElseNode, self).__str__(indent)
        output += indent * "  " + "else: \n"
        return output + self.else_block.__str__(indent + 1)


class MonOpNode(Node):
    def __init__(self, operator, expression, line, col):
        super(MonOpNode, self).__init__(line, col)
        self.operator = operator
        self.expression = expression

    def __eq__(self, other):
        return super(MonOpNode, self).__eq__(other) and \
               other.expression == self.expression

    def __str__(self, indent=0):
        return "({}{})".format(self.operator, self.expression)


class NegNode(MonOpNode):
    def __init__(self, expression, line=0, col=0):
        super(NegNode, self).__init__("!", expression, line, col)

    def accept(self, visitor):
        return visitor.neg_node(self)


class MinNode(MonOpNode):
    def __init__(self, expression, line=0, col=0):
        super(MinNode, self).__init__("-", expression, line, col)

    def accept(self, visitor):
        return visitor.min_node(self)


class PlusNode(MonOpNode):
    def __init__(self, expression, line=0, col=0):
        super(PlusNode, self).__init__("+", expression, line, col)

    def accept(self, visitor):
        return visitor.plus_node(self)


class ArithmeticExprNode(Node):
    def __init__(self, operator, left, right, line=0, col=0):
        super(ArithmeticExprNode, self).__init__(line, col)
        self.left = left
        self.operator = operator
        self.right = right

    def __eq__(self, other):
        return super(ArithmeticExprNode, self).__eq__(other) and \
               other.left == self.left and other.right == self.right

    def __str__(self, indent=0):
        return "({} {} {})".format(self.left, self.operator, self.right)


class AddNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(AddNode, self).__init__("+", left, right, line, col)

    def accept(self, visitor):
        return visitor.add_node(self)


class SubNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(SubNode, self).__init__("-", left, right, line, col)

    def accept(self, visitor):
        return visitor.sub_node(self)


class MulNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(MulNode, self).__init__("*", left, right, line, col)

    def accept(self, visitor):
        return visitor.mul_node(self)


class DivNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(DivNode, self).__init__("/", left, right, line, col)

    def accept(self, visitor):
        return visitor.div_node(self)


class ComparisonExprNode(Node):
    def __init__(self, operator, left, right, line=0, col=0):
        super(ComparisonExprNode, self).__init__(line, col)
        self.left = left
        self.operator = operator
        self.right = right

    def __eq__(self, other):
        return super(ComparisonExprNode, self).__eq__(other) and \
               other.left == self.left and other.right == self.right

    def __str__(self, indent=0):
        return "({} {} {})".format(self.left, self.operator, self.right)


class LTNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(LTNode, self).__init__("<", left, right, line, col)

    def accept(self, visitor):
        return visitor.lt_node(self)


class LTENode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(LTENode, self).__init__("<=", left, right, line, col)

    def accept(self, visitor):
        return visitor.lte_node(self)


class GTNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(GTNode, self).__init__(">", left, right, line, col)

    def accept(self, visitor):
        return visitor.gt_node(self)


class GTENode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(GTENode, self).__init__(">=", left, right, line, col)

    def accept(self, visitor):
        return visitor.gte_node(self)


class EqNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(EqNode, self).__init__("==", left, right, line, col)

    def accept(self, visitor):
        return visitor.eq_node(self)


class NeqNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(NeqNode, self).__init__("!=", left, right, line, col)

    def accept(self, visitor):
        return visitor.neq_node(self)


class LogicalExprNode(Node):
    def __init__(self, operator, left, right, line=0, col=0):
        super(LogicalExprNode, self).__init__(line, col)
        self.left = left
        self.operator = operator
        self.right = right

    def __eq__(self, other):
        return super(LogicalExprNode, self).__eq__(other) and \
               other.left == self.left and other.right == self.right

    def __str__(self, indent=0):
        return "({} {} {})".format(self.left, self.operator, self.right)


class AndNode(LogicalExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(AndNode, self).__init__("&&", left, right, line, col)

    def accept(self, visitor):
        return visitor.and_node(self)


class OrNode(LogicalExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(OrNode, self).__init__("||", left, right, line, col)

    def accept(self, visitor):
        return visitor.or_node(self)


class TypeNode(Node):
    def __init__(self, type_name, line=0, col=0):
        super(TypeNode, self).__init__(line, col)
        self.name = type_name

        self.numeric = ["integer", "money", "decimal"]
        self.alphanumeric = self.numeric + ["string"]

    def is_numeric(self):
        return self.name in self.numeric

    def is_alphanumeric(self):
        return self.name in self.alphanumeric

    def is_boolean(self):
        return self.name == "boolean"

    def is_integer(self):
        return self.name == "integer"

    def is_money(self):
        return self.name == "money"

    def is_decimal(self):
        return self.name == "decimal"

    def is_string(self):
        return self.name == "string"

    def is_date(self):
        return self.name == "date"

    def __eq__(self, other):
        return super(TypeNode, self).__eq__(other) \
               and other.name == self.name

    def __str__(self, indent=0):
        return str(self.name)


class BoolTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(BoolTypeNode, self).__init__("boolean", line, col)
        self.default = False

    def parse_value(self, value):
        return value

    def accept(self, visitor):
        return visitor.bool_type_node(self)


class IntTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(IntTypeNode, self).__init__("integer", line, col)
        self.default = Decimal("0")

    def parse_value(self, value):
        return Decimal(value)

    def accept(self, visitor):
        return visitor.int_type_node(self)


class MoneyTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(MoneyTypeNode, self).__init__("money", line, col)
        self.default = Decimal("0.00")

    def parse_value(self, value):
        return Decimal(value)

    def accept(self, visitor):
        return visitor.money_type_node(self)


class DecimalTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(DecimalTypeNode, self).__init__("decimal", line, col)
        self.default = Decimal("0.00")

    def parse_value(self, value):
        return Decimal(value)

    def accept(self, visitor):
        return visitor.decimal_type_node(self)


class StringTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(StringTypeNode, self).__init__("string", line, col)
        self.default = ""

    def parse_value(self, value):
        return value

    def accept(self, visitor):
        return visitor.string_type_node(self)


class DateTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(DateTypeNode, self).__init__("date", line, col)
        self.default = date(day=1, month=1, year=2000)

    def parse_value(self, value):
        return value

    def accept(self, visitor):
        return visitor.date_type_node(self)


class VarNode(Node):
    def __init__(self, value, line=0, col=0):
        super(VarNode, self).__init__(line, col)
        self.val = value

    def accept(self, visitor):
        return visitor.var_node(self)

    def __eq__(self, other):
        return super(VarNode, self).__eq__(other) and other.val == self.val

    def __str__(self, indent=0):
        return str(self.val)


class StringNode(VarNode):
    def __init__(self, string, line=0, col=0):
        super(StringNode, self).__init__(string, line, col)

    def accept(self, visitor):
        return visitor.string_node(self)

    def __str__(self, indent=0):
        return "'{}'".format(self.val)


class IntNode(VarNode):
    def __init__(self, integer, line=0, col=0):
        super(IntNode, self).__init__(integer, line, col)

    def accept(self, visitor):
        return visitor.int_node(self)


class BoolNode(VarNode):
    def __init__(self, boolean, line=0, col=0):
        super(BoolNode, self).__init__(boolean, line, col)

    def accept(self, visitor):
        return visitor.bool_node(self)


class MoneyNode(VarNode):
    def __init__(self, money, line=0, col=0):
        super(MoneyNode, self).__init__(money, line, col)

    def accept(self, visitor):
        return visitor.decimal_node(self)


class DecimalNode(VarNode):
    def __init__(self, decimal, line=0, col=0):
        super(DecimalNode, self).__init__(decimal, line, col)

    def accept(self, visitor):
        return visitor.decimal_node(self)


class DateNode(VarNode):
    def __init__(self, date_val, line=0, col=0):
        super(DateNode, self).__init__(date_val, line, col)

    def accept(self, visitor):
        return visitor.date_node(self)

    def __str__(self, indent=0):
        return self.val.strftime("%d-%m-%Y")
