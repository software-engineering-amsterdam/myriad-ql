# coding=utf-8
from pyparsing import (Suppress, Literal, Word, alphas, alphanums, nums, opAssoc, infixNotation, OneOrMore,
                       QuotedString, Optional, Forward, Combine)

from pql.ast import ast
from pql.typechecker.types import DataTypes


def parse(input_string):
    def flatten_binary_operators(flattened_tokens):
        while len(flattened_tokens) >= 3:
            lhs, type_call, rhs = flattened_tokens[:3]
            flattened_tokens = [type_call(lhs, rhs)] + flattened_tokens[3:]
        return flattened_tokens[0]

    def flatten_unary_operators(flattened_tokens):
        type_call = flattened_tokens[0]
        return type_call(flattened_tokens[1])

    # Packrat
    # ParserElement.enablePackrat()

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
        lambda source, location, parsed_tokens: ast.Money(DataTypes.money, {"src": source, "loc": location}))
    type_integer = Literal("integer").setParseAction(
        lambda source, location, parsed_tokens: ast.Integer(DataTypes.integer, {"src": source, "loc": location}))
    type_boolean = Literal("boolean").setParseAction(
        lambda source, location, parsed_tokens: ast.Boolean(DataTypes.boolean, {"src": source, "loc": location}))
    data_types = type_money | type_integer | type_boolean

    true = Literal("true").setParseAction(lambda _: ast.Value(True, DataTypes.boolean))
    false = Literal("false").setParseAction(lambda _: ast.Value(False, DataTypes.boolean))
    boolean = (true | false)

    integer = Word(nums).setParseAction(
        lambda parsed_tokens: ast.Value(int(parsed_tokens[0]), DataTypes.integer))
    money = Combine(Word(nums) + Literal(".") + Word(nums)).setParseAction(
        lambda parsed_tokens: ast.Value(float(parsed_tokens[0]), DataTypes.money))
    number = (money | integer)

    reserved_words = (lit_form | lit_if | lit_else | boolean | number | data_types)

    name = ~reserved_words + Word(alphas, alphanums + '_').setResultsName('identifier').setParseAction(
        lambda source, location, parsed_tokens: ast.Identifier(parsed_tokens[0], {"src": source, "loc": location}))

    operand_arith = (number | name)
    operand_bool = (boolean | operand_arith)

    operand_list_arith = [
        (lit_op_positive | lit_op_negative | lit_op_not,
         1, opAssoc.RIGHT,
         lambda flattened_tokens: flatten_unary_operators(*flattened_tokens)),
        (lit_op_multiplication | lit_op_division,
         2, opAssoc.LEFT,
         lambda flattened_tokens: flatten_binary_operators(*flattened_tokens)),
        (lit_op_addition | lit_op_subtract,
         2, opAssoc.LEFT,
         lambda flattened_tokens: flatten_binary_operators(*flattened_tokens)),
    ]

    operand_list_bool = [
        (lit_op_lower_inclusive | lit_op_greater_inclusive | lit_op_greater_exclusive | lit_op_lower_exclusive,
         2, opAssoc.LEFT,
         lambda flattened_tokens: flatten_binary_operators(*flattened_tokens)),
        (lit_op_equality | lit_op_inequality,
         2, opAssoc.LEFT,
         lambda flattened_tokens: flatten_binary_operators(*flattened_tokens)),
        (lit_op_and,
         2, opAssoc.LEFT,
         lambda flattened_tokens: flatten_binary_operators(*flattened_tokens)),
        (lit_op_or,
         2, opAssoc.LEFT,
         lambda flattened_tokens: flatten_binary_operators(*flattened_tokens)),
    ]

    operator_precendence = infixNotation(
        operand_bool,
        (operand_list_arith + operand_list_bool)
    )

    boolean_expression = \
        OneOrMore(operator_precendence | (lit_l_paren + operator_precendence + lit_r_paren))

    field_statement = (
        QuotedString('"', unquoteResults=True).setResultsName("title") +
        name.setResultsName("identifier") + lit_colon + data_types.setResultsName("data_type") +
        Optional(
            lit_assign_op +
            boolean_expression
        )
    )
    field_statement.setParseAction(lambda parsed_tokens: ast.Field(*parsed_tokens))

    conditional_if = Forward()
    conditional_if_else = Forward()
    statement = Forward()
    body = Forward()

    if_statement = lit_if + lit_l_paren + boolean_expression + lit_r_paren + body
    conditional_if <<= if_statement
    conditional_if.setParseAction(ast.If)

    conditional_if_else <<= (
        if_statement +
        Optional(lit_else + body).setResultsName('else_statement')
    )
    conditional_if_else.setParseAction(ast.IfElse)

    conditional = conditional_if_else | conditional_if

    statement <<= (field_statement | conditional)

    body <<= lit_l_curly + OneOrMore(statement) + lit_r_curly
    body.addParseAction(lambda parsed_tokens: [parsed_tokens.asList()])
    body.setResultsName('statement_list')

    form = (lit_form + name + body).addParseAction(lambda parsed_tokens: ast.Form(*parsed_tokens))
    return form.parseString(input_string)
