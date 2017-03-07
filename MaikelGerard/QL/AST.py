from decimal import Decimal
from datetime import date


class Node(object):
    def __init__(self, line=0, col=0):
        self.line = line
        self.col = col

    def __eq__(self, other):
        if type(self) != type(other):
            return False

        for key in self.__dict__:
            if key in ['line', 'col']:
                continue

            if not (getattr(self, key) == getattr(other, key)):
                return False
        return True


class FormNode(Node):
    def __init__(self, name, form_body, line=0, col=0):
        super(FormNode, self).__init__(line, col)
        self.name = name
        self.form_block = form_body

    def accept(self, visitor, *args):
        self.form_block.accept(visitor, *args)


class BlockNode(Node):
    def __init__(self, block_body, line=0, col=0):
        super(BlockNode, self).__init__(line, col)
        self.children = []

        for statement in block_body:
            self.children.append(statement)

    def accept(self, visitor, *args):
        for child in self.children:
            child.accept(visitor, *args)


class QuestionNode(Node):
    def __init__(self, question, name, var_type, line=0, col=0):
        super(QuestionNode, self).__init__(line, col)
        self.question = question
        self.name = name
        self.type = var_type

    def accept(self, visitor, *args):
        visitor.question_node(self, *args)


class ComputedQuestionNode(QuestionNode):
    def __init__(self, question, name, var_type, expression, line=0, col=0):
        super(ComputedQuestionNode, self).__init__(
            question, name, var_type, line, col
        )
        self.expression = expression

    def accept(self, visitor, *args):
        visitor.comp_question_node(self, *args)


class IfNode(Node):
    def __init__(self, condition, if_block, line=0, col=0):
        super(IfNode, self).__init__(line, col)
        self.condition = condition
        self.if_block = if_block

    def accept(self, visitor, *args):
        visitor.if_node(self, *args)


class IfElseNode(IfNode):
    def __init__(self, condition, if_block, else_block, line=0, col=0):
        super(IfElseNode, self).__init__(condition, if_block, line, col)
        self.else_block = else_block

    def accept(self, visitor, *args):
        visitor.if_else_node(self, *args)


class ExpressionNode(Node):
    def __init__(self, line=0, col=0):
        super(ExpressionNode, self).__init__(line, col)

    def accept(self, visitor, *args):
        return visitor.expr_node(self, *args)


class MonOpNode(ExpressionNode):
    def __init__(self, operator, expression, line, col):
        super(MonOpNode, self).__init__(line, col)
        self.operator = operator
        self.expression = expression


class NegNode(MonOpNode):
    def __init__(self, expression, line=0, col=0):
        super(NegNode, self).__init__("!", expression, line, col)

    def accept(self, visitor, *args):
        return visitor.neg_node(self, *args)


class MinNode(MonOpNode):
    def __init__(self, expression, line=0, col=0):
        super(MinNode, self).__init__("-", expression, line, col)

    def accept(self, visitor, *args):
        return visitor.min_node(self, *args)


class PlusNode(MonOpNode):
    def __init__(self, expression, line=0, col=0):
        super(PlusNode, self).__init__("+", expression, line, col)

    def accept(self, visitor, *args):
        return visitor.plus_node(self, *args)


class ArithmeticExprNode(ExpressionNode):
    def __init__(self, operator, left, right, line=0, col=0):
        super(ArithmeticExprNode, self).__init__(line, col)
        self.left = left
        self.operator = operator
        self.right = right


class AddNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(AddNode, self).__init__("+", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.add_node(self, *args)


class SubNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(SubNode, self).__init__("-", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.sub_node(self, *args)


class MulNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(MulNode, self).__init__("*", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.mul_node(self, *args)


class DivNode(ArithmeticExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(DivNode, self).__init__("/", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.div_node(self, *args)


class ComparisonExprNode(Node):
    def __init__(self, operator, left, right, line=0, col=0):
        super(ComparisonExprNode, self).__init__(line, col)
        self.left = left
        self.operator = operator
        self.right = right


class LTNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(LTNode, self).__init__("<", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.lt_node(self, *args)


class LTENode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(LTENode, self).__init__("<=", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.lte_node(self, *args)


class GTNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(GTNode, self).__init__(">", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.gt_node(self, *args)


class GTENode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(GTENode, self).__init__(">=", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.gte_node(self, *args)


class EqNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(EqNode, self).__init__("==", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.eq_node(self, *args)


class NeqNode(ComparisonExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(NeqNode, self).__init__("!=", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.neq_node(self, *args)


class LogicalExprNode(ExpressionNode):
    def __init__(self, operator, left, right, line=0, col=0):
        super(LogicalExprNode, self).__init__(line, col)
        self.left = left
        self.operator = operator
        self.right = right


class AndNode(LogicalExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(AndNode, self).__init__("&&", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.and_node(self, *args)


class OrNode(LogicalExprNode):
    def __init__(self, left, right, line=0, col=0):
        super(OrNode, self).__init__("||", left, right, line, col)

    def accept(self, visitor, *args):
        return visitor.or_node(self, *args)


class TypeNode(Node):
    def __init__(self, line=0, col=0):
        super(TypeNode, self).__init__(line, col)

    def is_numeric(self):
        return self in [IntTypeNode(), MoneyTypeNode(), DecimalTypeNode()]

    def is_alphanumeric(self):
        return self in [IntTypeNode(), MoneyTypeNode(),
                        DecimalTypeNode(), StringTypeNode()]

class BoolTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(BoolTypeNode, self).__init__(line, col)
        self.default = False

    @staticmethod
    def parse_value(value):
        return value

    def accept(self, visitor, *args):
        return visitor.bool_type_node(self, *args)


class IntTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(IntTypeNode, self).__init__(line, col)
        self.default = Decimal("0")

    @staticmethod
    def parse_value(value):
        return Decimal(value)

    def accept(self, visitor, *args):
        return visitor.int_type_node(self, *args)


class MoneyTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(MoneyTypeNode, self).__init__(line, col)
        self.default = Decimal("0.00")

    @staticmethod
    def parse_value(value):
        return Decimal(value)

    def accept(self, visitor, *args):
        return visitor.money_type_node(self, *args)


class DecimalTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(DecimalTypeNode, self).__init__(line, col)
        self.default = Decimal("0.00")

    @staticmethod
    def parse_value(value):
        return Decimal(value)

    def accept(self, visitor, *args):
        return visitor.decimal_type_node(self, *args)


class StringTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(StringTypeNode, self).__init__(line, col)
        self.default = ""

    @staticmethod
    def parse_value(value):
        return value

    def accept(self, visitor, *args):
        return visitor.string_type_node(self, *args)


class DateTypeNode(TypeNode):
    def __init__(self, line=0, col=0):
        super(DateTypeNode, self).__init__(line, col)
        self.default = date(day=1, month=1, year=2000)

    @staticmethod
    def parse_value(value):
        return value

    def accept(self, visitor, *args):
        return visitor.date_type_node(self, *args)


class VarNode(ExpressionNode):
    def __init__(self, name, line=0, col=0):
        super(ExpressionNode, self).__init__(line, col)
        self.name = name

    def accept(self, visitor, *args):
        return visitor.var_node(self, *args)


class LiteralNode(ExpressionNode):
    def __init__(self, value, line=0, col=0):
        super(LiteralNode, self).__init__(line, col)
        self.val = value

    def accept(self, visitor, *args):
        return visitor.literal_node(self, *args)


class StringNode(LiteralNode):
    def __init__(self, value, line=0, col=0):
        super(StringNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        return visitor.string_node(self, *args)


class IntNode(LiteralNode):
    def __init__(self, value, line=0, col=0):
        super(IntNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        return visitor.int_node(self, *args)


class BoolNode(LiteralNode):
    def __init__(self, value, line=0, col=0):
        super(BoolNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        return visitor.bool_node(self, *args)


class MoneyNode(LiteralNode):
    def __init__(self, value, line=0, col=0):
        super(MoneyNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        return visitor.decimal_node(self, *args)


class DecimalNode(LiteralNode):
    def __init__(self, value, line=0, col=0):
        super(DecimalNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        return visitor.decimal_node(self, *args)


class DateNode(LiteralNode):
    def __init__(self, value, line=0, col=0):
        super(DateNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        return visitor.date_node(self, *args)
