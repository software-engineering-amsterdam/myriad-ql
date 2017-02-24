from pyparsing import *
from ast.ast import *

'''
References:
https://pyparsing.wikispaces.com/file/view/simpleArith.py

'''

class QL:
    # Booleans
    AND = Literal('&&').addParseAction(lambda : LogicalAnd)
    OR = Literal('||').addParseAction(lambda : LogicalOr)
    NOT = Literal('!').addParseAction(lambda : LogicalNot) # Unary

    # Comparisons
    GT = Literal('>').addParseAction(lambda : GreaterThan)
    LT = Literal('<').addParseAction(lambda : LessThan)
    GTE = Literal('>=').addParseAction(lambda : GreaterThanEquals)
    LTE = Literal('<=').addParseAction(lambda : LessThanEquals)
    EQ = Literal('=').addParseAction(lambda : Equality)
    NEQ = Literal('!=').addParseAction(lambda : Inequality)

    # Basic Arithmics
    ADD = Literal('+').addParseAction(lambda : Addition)
    SUB = Literal('-').addParseAction(lambda : Substraction)
    MUL = Literal('*').addParseAction(lambda : Multiplication)
    DIV = Literal('/').addParseAction(lambda : Division)

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

    boolean_precedence = [(AND, 2, opAssoc.LEFT),
                          (OR, 2, opAssoc.LEFT),
                          (NOT, 1, opAssoc.RIGHT)]

    comparison_precedence = [(GT, 2, opAssoc.LEFT),
                             (LT, 2, opAssoc.LEFT),
                             (GTE, 2, opAssoc.LEFT),
                             (LTE, 2, opAssoc.LEFT),
                             (EQ, 2, opAssoc.LEFT),
                             (NEQ, 2, opAssoc.LEFT)]

    arithmic_precedence = [(ADD, 2, opAssoc.LEFT),
                           (SUB, 2, opAssoc.LEFT),
                           (MUL, 2, opAssoc.LEFT),
                           (DIV, 2, opAssoc.LEFT)]


    boolean_expression = operatorPrecedence(identifier,
                                            boolean_precedence)

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

    conditional = Suppress(IF) + \
    boolean_expression.addParseAction(
        lambda evaluation : Evaluation(*evaluation)
    ) + evaluated
    conditional.addParseAction(lambda content : Conditional(*content))

    # form items
    form_content = conditional | statement | question
    form_item = Group(OneOrMore(Group(form_content))).setResultsName('form_content')

    # outer form
    form = Suppress(form_type) + identifier + Suppress(lcurly) + form_item + Suppress(rcurly)
    form.addParseAction(lambda form_content : Root(*form_content))
