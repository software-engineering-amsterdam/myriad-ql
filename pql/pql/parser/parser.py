# coding=utf-8
from pyparsing import (Suppress, Literal, Word, alphas, alphanums, nums, opAssoc, infixNotation, OneOrMore,
                       QuotedString, Optional, Forward, Combine, ParserElement)

from pql.ast import ast


def parse(input_string):
    def flatten_binary_operators(position, source, flattened_tokens):
        while len(flattened_tokens) >= 3:
            lhs, type_call, rhs = flattened_tokens[:3]
            flattened_tokens = [type_call(position, source, lhs, rhs)] + flattened_tokens[3:]
        return flattened_tokens[0]

    def flatten_unary_operators(position, source, flattened_tokens):
        type_call = flattened_tokens[0]
        return type_call(position, source, flattened_tokens[1])

    # Packrat
    ParserElement.enablePackrat()

    lit_form = Suppress("form")
    lit_if = Suppress("if")
    lit_else = Suppress("else")

    lit_l_curly = Suppress("{")
    lit_r_curly = Suppress("}")
    lit_l_paren = Suppress("(")
    lit_r_paren = Suppress(")")

    lit_colon = Suppress(":")
    lit_assign_op = Suppress("=")

    lit_op_multiplication = Literal("*").setParseAction(lambda _: ast.Multiplication)
    lit_op_division = Literal("/").setParseAction(lambda _: ast.Division)
    lit_op_subtract = Literal("-").setParseAction(lambda _: ast.Subtraction)
    lit_op_addition = Literal("+").setParseAction(lambda _: ast.Addition)
    lit_op_positive = Literal("+").setParseAction(lambda _: ast.Positive)
    lit_op_negative = Literal("-").setParseAction(lambda _: ast.Negative)

    lit_op_not = Literal("!").setParseAction(lambda _: ast.Negation)
    lit_op_lower_exclusive = Literal("<").setParseAction(lambda _: ast.LowerExclusive)
    lit_op_lower_inclusive = Literal("<=").setParseAction(lambda _: ast.LowerInclusive)
    lit_op_greater_inclusive = Literal(">=").setParseAction(lambda _: ast.GreaterInclusive)
    lit_op_greater_exclusive = Literal(">").setParseAction(lambda _: ast.GreaterExclusive)
    lit_op_equality = Literal("==").setParseAction(lambda _: ast.Equality)
    lit_op_inequality = Literal("!=").setParseAction(lambda _: ast.Inequality)
    lit_op_and = Literal("&&").setParseAction(lambda _: ast.And)
    lit_op_or = Literal("||").setParseAction(lambda _: ast.Or)

    type_money = Literal("money").setParseAction(
        lambda source, position, _: ast.Money(position, source))
    type_integer = Literal("integer").setParseAction(
        lambda source, position, _: ast.Integer(position, source))
    type_boolean = Literal("boolean").setParseAction(
        lambda source, position, _: ast.Boolean(position, source))

    type_string = Literal("string").setParseAction(
        lambda source, position, _: ast.String(position, source))

    data_types = (type_money | type_integer | type_boolean | type_string)

    true = Literal("true").setParseAction(
        lambda source, position, _: ast.Boolean(position, source, True))
    false = Literal("false").setParseAction(
        lambda source, position, _: ast.Boolean(position, source, False))
    boolean = (true | false)

    integer = Word(nums).setParseAction(
        lambda source, position, parsed_tokens: ast.Integer(position, source, int(parsed_tokens[0])))
    money = Combine(Word(nums) + Literal(".") + Word(nums)).setParseAction(
        lambda source, position, parsed_tokens: ast.Money(position, source, float(parsed_tokens[0])))
    number = (money | integer)

    string = QuotedString("'", unquoteResults=True)\
        .setParseAction(
            lambda source, position, parsed_tokens:
                ast.String(position, source, str(parsed_tokens[0])))

    reserved_words = (lit_form | lit_if | lit_else | boolean | number | data_types)

    name = ~reserved_words + Word(alphas, alphanums + '_')\
        .setResultsName('identifier')\
        .setParseAction(lambda source, position, parsed_tokens: ast.Identifier(position, source, parsed_tokens[0]))

    operand_arith = (number | boolean | name | string)

    operand_list_arith = [
        (lit_op_positive | lit_op_negative | lit_op_not,
         1, opAssoc.RIGHT,
         lambda source, position, flattened_tokens: flatten_unary_operators(position, source, *flattened_tokens)),
        (lit_op_multiplication | lit_op_division,
         2, opAssoc.LEFT,
         lambda source, position, flattened_tokens: flatten_binary_operators(position, source, *flattened_tokens)),
        (lit_op_addition | lit_op_subtract,
         2, opAssoc.LEFT,
         lambda source, position, flattened_tokens: flatten_binary_operators(position, source, *flattened_tokens)),
    ]

    operand_list_bool = [
        (lit_op_lower_inclusive | lit_op_greater_inclusive | lit_op_greater_exclusive | lit_op_lower_exclusive,
         2, opAssoc.LEFT,
         lambda source, position, flattened_tokens: flatten_binary_operators(position, source, *flattened_tokens)),
        (lit_op_equality | lit_op_inequality,
         2, opAssoc.LEFT,
         lambda source, position, flattened_tokens: flatten_binary_operators(position, source, *flattened_tokens)),
        (lit_op_and,
         2, opAssoc.LEFT,
         lambda source, position, flattened_tokens: flatten_binary_operators(position, source, *flattened_tokens)),
        (lit_op_or,
         2, opAssoc.LEFT,
         lambda source, position, flattened_tokens: flatten_binary_operators(position, source, *flattened_tokens)),
    ]

    literal_precedence = infixNotation(
        operand_arith,
        (operand_list_arith + operand_list_bool)
    )

    expression = \
        OneOrMore(
            literal_precedence |
            (lit_l_paren + literal_precedence + lit_r_paren)
        )

    field = Forward()
    field_assignment = Forward()

    field_statement = (
        QuotedString('"', unquoteResults=True).setResultsName("title") +
        name.setResultsName("identifier") + lit_colon + data_types.setResultsName("data_type")
    )

    field <<= field_statement
    field.setParseAction(lambda source, position, parsed_tokens: ast.Field(position, source, *parsed_tokens))

    field_assignment <<= field_statement + lit_assign_op + expression
    field_assignment.setParseAction(
        lambda source, position, parsed_tokens: ast.Assignment(position, source, *parsed_tokens))

    field_order = field_assignment | field

    conditional_if = Forward()
    conditional_if_else = Forward()
    statement = Forward()
    body = Forward()

    if_statement = lit_if + lit_l_paren + expression + lit_r_paren + body
    conditional_if <<= if_statement
    conditional_if.setParseAction(ast.If)

    conditional_if_else <<= (
        if_statement +
        Optional(lit_else + body).setResultsName('else_statement')
    )
    conditional_if_else.setParseAction(ast.IfElse)

    conditional = (conditional_if_else | conditional_if)

    statement <<= (field_order | conditional)

    body <<= lit_l_curly + OneOrMore(statement) + lit_r_curly
    body.addParseAction(lambda parsed_tokens: [parsed_tokens.asList()])
    body.setResultsName('statement_list')

    form = (lit_form + name + body)\
        .addParseAction(lambda parsed_tokens: ast.Form(*parsed_tokens))\
        .setResultsName('form')\
        .parseWithTabs()
    return form.parseString(input_string).form
