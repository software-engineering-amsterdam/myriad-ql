from pyparsing import *
from ast.ast import *
from ast.base_nodes import *
from ast.field_types import *


def binary_node(node):
    def makeNode(_, __, tokens):
        return node(*tokens[0])
    return makeNode

def unary_node(node):
    def makeNode(_, __, tokens):
        return node(*tokens[0])
    return makeNode

def if_action(string, location, tokens):
    return ifThen(**tokens[0].asDict())

def computed_question_action(string, location, tokens):
    return ComputedQuestion(**tokens[0].asDict())

def statements_action(string, location, tokens):
    return Block(tokens[0].asList())

def form_action(string, location, tokens):
    return Form(**tokens[0].asDict())

class QL:
    literal_and = Literal('&&').suppress()
    literal_or = Literal('||').suppress()
    literal_not = Literal('!').suppress()
    literal_greater_than = Literal('>').suppress()
    literal_less_than = Literal('<').suppress()
    literal_greater_than_equal = Literal('>=').suppress()
    literal_less_than_equal = Literal('<=').suppress()
    literal_equal = Literal('==').suppress()
    literall_not_equal = Literal('!=').suppress()
    literal_addition = Literal('+').suppress()
    literal_substraction = Literal('-').suppress()
    literal_multiplication = Literal('*').suppress()
    literal_division = Literal('/').suppress()
    literal_if = Literal('if').suppress()
    colon = Literal(':').suppress()
    left_curly = Literal('{').suppress()
    right_curly = Literal('}').suppress()
    left_parenthesis = Literal('(').suppress()
    right_parenthesis = Literal(')').suppress()
    form_type = oneOf('form')
    field_boolean = Literal('boolean').addParseAction(lambda: Boolean())
    field_string = Literal('string').addParseAction(lambda: String())
    field_integer = Literal('integer').addParseAction(lambda: Integer())
    field_decimal = Literal('decimal').addParseAction(lambda: Decimal())
    field_money = Literal('money').addParseAction(lambda: Money())
    field_type = field_boolean | field_string | field_integer | \
        field_decimal | field_money
    word = Word(alphas)

    numbers = Word(nums).addParseAction(lambda string, location,
                                        tokens: Integer(*tokens))
    reference_variable = Forward()
    operand = numbers | reference_variable

    boolean_precedence = [(literal_and, 2, opAssoc.LEFT,
                           binary_node(LogicalAnd)),
                          (literal_or, 2, opAssoc.LEFT,
                           binary_node(LogicalOr)),
                          (literal_not, 1, opAssoc.RIGHT,
                           unary_node(LogicalNot))]

    comparison_precedence = [(literal_greater_than, 2, opAssoc.LEFT,
                              binary_node(GreaterThan)),
                             (literal_less_than, 2, opAssoc.LEFT,
                              binary_node(LessThan)),
                             (literal_greater_than_equal, 2, opAssoc.LEFT,
                              binary_node(GreaterThanEquals)),
                             (literal_less_than_equal, 2, opAssoc.LEFT,
                              binary_node(LessThanEquals)),
                             (literal_equal, 2, opAssoc.LEFT,
                              binary_node(Equality)),
                             (literall_not_equal, 2, opAssoc.LEFT,
                              binary_node(Inequality))]

    arithmic_precedence = [(literal_multiplication, 2, opAssoc.LEFT,
                            binary_node(Multiplication)),
                           (literal_division, 2, opAssoc.LEFT,
                            binary_node(Division)),
                           (literal_addition, 2, opAssoc.LEFT,
                            binary_node(Addition)),
                           (literal_substraction, 2, opAssoc.LEFT,
                            binary_node(Substraction)),
                           ]

    boolean_expression = operatorPrecedence(operand, boolean_precedence)
    comparison_expression = operatorPrecedence(operand, comparison_precedence)
    arithmic_expression = operatorPrecedence(operand, arithmic_precedence)

    expression = Or(boolean_expression | arithmic_expression |
                    comparison_expression)

    string = QuotedString('"')
    string.addParseAction(lambda _, __, tokens: StringLiteral(*tokens))

    label = QuotedString('"')("value").addParseAction(
        lambda _, __, tokens: String(**tokens.asDict()))

    # form content
    variable = word
    init_variable = Group(variable('name') + colon +
                          field_type('datatype'))('variable')

    reference_variable << Group(variable('name'))

    reference_variable.addParseAction(
        lambda _, __, tokens: RefVariable(**tokens[0].asDict()))

    init_variable.addParseAction(
        lambda _, __, tokens: Variable(**tokens[0].asDict()))
    question = Group(string('label') + init_variable('variable'))

    question.addParseAction(
        lambda _, __, tokens: Question(**tokens[0].asDict()))
    computed_question = Group(string('label') + \
        init_variable('variable') + Literal('=').suppress() + \
        expression('expression'))('computed_question')

    computed_question.addParseAction(computed_question_action)

    block = Suppress(left_curly) + Group(OneOrMore(Group(question))) + \
        Suppress(right_curly)

    form_item = Forward()

    if_statement = Group(literal_if + left_parenthesis + expression(
        'condition') + right_parenthesis + left_curly + form_item + right_curly)

    if_statement.addParseAction(if_action)

    statements = Group(
        OneOrMore(Or(computed_question | question | if_statement)))('block')

    conditional = literal_if + boolean_expression + block
    statements.addParseAction(statements_action)

    form_item << statements

    # outer form
    form = Group(Suppress(form_type) + Word(alphanums)('name') +
                 Suppress(left_curly) + form_item + \
                 Suppress(right_curly))('form')

    form.addParseAction(form_action)

    def parse(self, _string):
        return self.form.parseString(_string)['form']

    def parse_expression(self, _string):
        return self.expression.parseString(_string)[0]
