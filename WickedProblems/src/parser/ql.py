from pyparsing import *
from ast.ast import *
from ast.base_nodes import *
from ast.field_types import *

'''
References:
https://pyparsing.wikispaces.com/file/view/simpleArith.py

'''

class QL:
    # Booleans
    literal_and = Literal('&&').addParseAction(lambda \
        left_hand_side, right_hand_side : LogicalAnd(left_hand_side,
                                                     right_hand_side))
    literal_or = Literal('||').addParseAction(lambda \
        left_hand_side, right_hand_side : LogicalOr(left_hand_side,
                                                    right_hand_side))
    # Unary
    literal_not = Literal('!').addParseAction(lambda child : LogicalNot(child))

    # Comparisons
    literal_greater_than = Literal('>').addParseAction(lambda \
        left_hand_side, right_hand_side : GreaterThan(left_hand_side,
                                                      right_hand_side))
    literal_less_than = Literal('<').addParseAction(lambda \
        left_hand_side, right_hand_side : LessThan(left_hand_side,
                                                   right_hand_side))
    literal_greater_than_equal = Literal('>=').addParseAction(lambda \
        left_hand_side, right_hand_side : GreaterThanEquals(left_hand_side,
                                                            right_hand_side))
    literal_less_than_equal = Literal('<=').addParseAction(lambda \
        left_hand_side, right_hand_side : LessThanEquals(left_hand_side,
                                                         right_hand_side))
    literal_equal = Literal('=').addParseAction(lambda \
        left_hand_side, right_hand_side : Equality(left_hand_side,
                                                   right_hand_side))
    literall_not_equal = Literal('!=').addParseAction(lambda \
        left_hand_side, right_hand_side : Inequality(left_hand_side,
                                                     right_hand_side))
    # Basic Arithmics
    literal_addition = Literal('+').addParseAction(lambda \
        left_hand_side, right_hand_side : Addition(left_hand_side,
                                                   right_hand_side))
    literal_substraction = Literal('-').addParseAction(lambda \
        left_hand_side, right_hand_side : Substraction(left_hand_side,
                                                       right_hand_side))
    literal_multiplication = Literal('*').addParseAction(lambda \
        left_hand_side, right_hand_side : Multiplication(left_hand_side,
                                                         right_hand_side))
    literal_division = Literal('/').addParseAction(lambda \
        left_hand_side, right_hand_side : Division(left_hand_side,
                                                   right_hand_side))

    # Missing Unary Operators (plus, minus)
    literal_postitive = Literal('+').addParseAction(lambda : UnaryPlus)
    literal_negative = Literal('-').addParseAction(lambda : UnaryNegation)

    # Defines
    literal_if = Literal('if')
    colon = ':'
    left_curly = '{'
    right_curly = '}'
    left_parenthesis = '('
    right_parenthesis = ')'
    form_type = oneOf('form')
    field_boolean = Literal('boolean').addParseAction(lambda : Boolean)
    field_string = Literal('string').addParseAction(lambda: String)
    field_integer = Literal('integer').addParseAction(lambda: Integer)
    field_data = Literal('data').addParseAction(lambda: Data)
    field_decimal = Literal('decimal').addParseAction(lambda: Decimal)
    field_money = Literal('money').addParseAction(lambda: Money)
    field_type = field_boolean | field_string | field_integer | field_data | \
                field_decimal | field_money
    word = Word(alphas)
    identifier = word.setResultsName("identifier")
    identifier.addParseAction(lambda identifier : Variable(*identifier))

    def parse_binary(self, content):
        left_hand_side, condition, right_hand_side = \
                content[0][0], content[0][1], content[0][2]
        return condition(left_hand_side, right_hand_side)

    def parse_unary(self, content):
        # TODO:
        # print(content)
        return content

    boolean_precedence = [(literal_and, 2, opAssoc.LEFT, parse_binary),
                          (literal_or, 2, opAssoc.LEFT, parse_binary),
                          (literal_not, 1, opAssoc.RIGHT, parse_unary)] # Unary

    comparison_precedence = [(literal_greater_than, 2, opAssoc.LEFT,
                              parse_binary),
                             (literal_less_than, 2, opAssoc.LEFT,
                              parse_binary),
                             (literal_greater_than_equal, 2, opAssoc.LEFT,
                              parse_binary),
                             (literal_less_than_equal, 2, opAssoc.LEFT,
                              parse_binary),
                             (literal_equal, 2, opAssoc.LEFT,
                              parse_binary),
                             (literall_not_equal, 2, opAssoc.LEFT,
                              parse_binary)]

    arithmic_precedence = [(literal_addition, 2, opAssoc.LEFT,
                            parse_binary),
                           (literal_substraction, 2, opAssoc.LEFT,
                            parse_binary),
                           (literal_multiplication, 2, opAssoc.LEFT,
                            parse_binary),
                           (literal_division, 2, opAssoc.LEFT,
                            parse_binary)]

    boolean_expression = operatorPrecedence(identifier,boolean_precedence)
    boolean_expression.addParseAction(lambda child : \
            Expression(child))

    comparison_expression = operatorPrecedence(identifier,comparison_precedence)
    arithmic_expression = operatorPrecedence(identifier,arithmic_precedence)

    string = QuotedString('"')
    string.setResultsName("text")
    string.addParseAction(lambda text : String("",text))

    # form content
    question = string + identifier + Suppress(colon) + field_type
    question.addParseAction(lambda content : Question(*content))

    statement = string + identifier + Suppress(colon) + field_type + \
                Suppress("=") + arithmic_expression
    statement.addParseAction(lambda content : Statement(*content))

    evaluated = Suppress(left_curly) + \
    Group(OneOrMore(Group(statement | question))) + \
    Suppress(right_curly)

    conditional = Suppress(literal_if) + boolean_expression + evaluated
    conditional.addParseAction(lambda content : Conditional(*content))

    # form items
    form_content = conditional | statement | question
    form_item = Group(OneOrMore(Group(form_content))
                      ).setResultsName('form_content')

    # outer form
    form = Suppress(form_type) + identifier + Suppress(left_curly) + \
        form_item + Suppress(right_curly)
    form.addParseAction(lambda form_content : Root(*form_content))
