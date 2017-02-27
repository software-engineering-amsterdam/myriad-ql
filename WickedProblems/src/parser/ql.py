from pyparsing import *
from ast.ast import *

'''
References:
https://pyparsing.wikispaces.com/file/view/simpleArith.py

'''

class QL:
    # Booleans
    AND = Literal('&&').addParseAction(lambda \
        left_child, right_child : LogicalAnd(left_child, right_child))
    OR = Literal('||').addParseAction(lambda \
        left_child, right_child : LogicalOr(left_child, right_child))
    NOT = Literal('!').addParseAction(lambda child : LogicalNot(child)) # Unary

    # Comparisons
    GT = Literal('>').addParseAction(lambda \
        left_child, right_child : GreaterThan(left_child, right_child))
    LT = Literal('<').addParseAction(lambda \
        left_child, right_child : LessThan(left_child, right_child))
    GTE = Literal('>=').addParseAction(lambda \
        left_child, right_child : GreaterThanEquals(left_child, right_child))
    LTE = Literal('<=').addParseAction(lambda \
        left_child, right_child : LessThanEquals(left_child, right_child))
    EQ = Literal('=').addParseAction(lambda \
        left_child, right_child : Equality(left_child, right_child))
    NEQ = Literal('!=').addParseAction(lambda \
        left_child, right_child : Inequality(left_child, right_child))

    # Basic Arithmics
    ADD = Literal('+').addParseAction(lambda \
        left_child, right_child : Addition(left_child, right_child))
    SUB = Literal('-').addParseAction(lambda \
        left_child, right_child : Substraction(left_child, right_child))
    MUL = Literal('*').addParseAction(lambda \
        left_child, right_child : Multiplication(left_child, right_child))
    DIV = Literal('/').addParseAction(lambda \
        left_child, right_child : Division(left_child, right_child))

    # Missing Unary Operators (plus, minus)
    POS = Literal('+').addParseAction(lambda : UnaryPlus) # Unary
    NEG = Literal('-').addParseAction(lambda : UnaryNegation) # Unary

    # Defines
    IF = 'if'
    colon = ':'
    lcurly = '{'
    rcurly = '}'
    lparens = '('
    rparens = ')'
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
        left_child, condition, right_child = \
                content[0][0], content[0][1], content[0][2]
        return condition(left_child, right_child)

    def parse_unary(self, content):
        # print(content)
        return content

    boolean_precedence = [(AND, 2, opAssoc.LEFT, parse_binary),
                          (OR, 2, opAssoc.LEFT, parse_binary),
                          (NOT, 1, opAssoc.RIGHT, parse_unary)] # Unary

    comparison_precedence = [(GT, 2, opAssoc.LEFT, parse_binary),
                             (LT, 2, opAssoc.LEFT, parse_binary),
                             (GTE, 2, opAssoc.LEFT, parse_binary),
                             (LTE, 2, opAssoc.LEFT, parse_binary),
                             (EQ, 2, opAssoc.LEFT, parse_binary),
                             (NEQ, 2, opAssoc.LEFT, parse_binary)]

    arithmic_precedence = [(ADD, 2, opAssoc.LEFT, parse_binary),
                           (SUB, 2, opAssoc.LEFT, parse_binary),
                           (MUL, 2, opAssoc.LEFT, parse_binary),
                           (DIV, 2, opAssoc.LEFT, parse_binary)]


    boolean_expression = operatorPrecedence(identifier,
                                            boolean_precedence)


    boolean_expression.addParseAction(lambda child : \
            Evaluation(child))

    comparison_expression = operatorPrecedence(identifier,
                                               comparison_precedence)

    arithmic_expression = operatorPrecedence(identifier,
                                             arithmic_precedence)

    string = QuotedString('"')

    # form content
    question = string + identifier + Suppress(colon) + field_type
    question.addParseAction(lambda content : Question(*content))

    statement = string + identifier + Suppress(colon) + field_type + \
                Suppress("=") + arithmic_expression
    statement.addParseAction(lambda content : Statement(*content))

    evaluated = Suppress(lcurly) + \
    Group(OneOrMore(Group(statement | question))) + \
    Suppress(rcurly)

    conditional = Suppress(IF) + boolean_expression + evaluated
    conditional.addParseAction(lambda content : Conditional(*content))

    # form items
    form_content = conditional | statement | question
    form_item = Group(OneOrMore(Group(form_content))).setResultsName('form_content')

    # outer form
    form = Suppress(form_type) + identifier + Suppress(lcurly) + form_item + Suppress(rcurly)
    form.addParseAction(lambda form_content : Root(*form_content))
