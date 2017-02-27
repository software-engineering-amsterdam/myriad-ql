# coding=utf-8
# At a stable state of lexing and parsing this import needs to be changed to a more concrete version which only imports
# the needed methods from the pyparsing package
from pyparsing import *
from pql.ast import ast
from pql.typechecker.types import DataTypes


def parse(input_string):
    # Reserved keywords
    lit_form = Suppress("form")
    lit_if = Suppress("if")
    lit_else = Suppress("else")

    # Reserved symbols
    lit_l_curly = Suppress("{")
    lit_r_curly = Suppress("}")
    lit_l_paren = Suppress("(")
    lit_r_paren = Suppress(")")

    name = Word(alphas, alphanums + '_').setResultsName('identifier')
    name.setParseAction(lambda parsed_tokens: ast.Identifier(parsed_tokens[0]))

    integer = Word(nums).setParseAction(lambda parsed_tokens: ast.Value(parsed_tokens[0], DataTypes.integer))
    money = Word(nums + ".").setParseAction(lambda parsed_tokens: ast.Value(parsed_tokens[0], DataTypes.money))
    number = (integer | money)

    true = Literal("true").setParseAction(lambda _: ast.Value(True, DataTypes.boolean))
    false = Literal("false").setParseAction(lambda _: ast.Value(False, DataTypes.boolean))
    boolean = (true | false)

    arith_operand = (number | name)
    bool_operand = (boolean | arith_operand)

    op_multiplication = Literal("*").setParseAction(lambda _: ast.Multiplication)
    op_division = Literal("/").setParseAction(lambda _: ast.Division)
    op_subtract = Literal("-").setParseAction(lambda _: ast.Subtraction)
    op_addition = Literal("+").setParseAction(lambda _: ast.Addition)
    op_positive = Literal("+").setParseAction(lambda _: ast.Positive)
    op_negative = Literal("-").setParseAction(lambda _: ast.Negative)

    op_not = Literal("!").setParseAction(lambda _: ast.Negation)
    op_lower_exclusive = Literal("<").setParseAction(lambda _: ast.LowerExclusive)
    op_lower_inclusive = Literal("<=").setParseAction(lambda _: ast.LowerInclusive)
    op_greater_inclusive = Literal(">=").setParseAction(lambda _: ast.GreaterInclusive)
    op_greater_exclusive = Literal(">").setParseAction(lambda _: ast.GreaterExclusive)
    op_equality = Literal("==").setParseAction(lambda _: ast.Equality)
    op_inequality = Literal("!=").setParseAction(lambda _: ast.Inequality)
    op_and = Literal("&&").setParseAction(lambda _: ast.And)
    op_or = Literal("||").setParseAction(lambda _: ast.Or)

    colon = Suppress(":")
    data_types = oneOf([data_type.value for data_type in DataTypes])

    assign_op = Suppress("=")

    def flatten_binary_operators(unflattened_tokens):
        flattened_tokens = unflattened_tokens[0]
        while len(flattened_tokens) >= 3:
            lhs, type_call, rhs = flattened_tokens[:3]
            flattened_tokens = [type_call(lhs, rhs)] + flattened_tokens[3:]
        return flattened_tokens[0]

    def flatten_unary_operators(unflattened_tokens):
        flattened_tokens = unflattened_tokens[0]
        type_call = flattened_tokens[0]
        return type_call(flattened_tokens[1])

    arith_precedent = [
        (op_positive | op_negative | op_not, 1, opAssoc.RIGHT, flatten_unary_operators),
        (op_multiplication | op_division, 2, opAssoc.LEFT, flatten_binary_operators),
        (op_addition | op_subtract, 2, opAssoc.LEFT, flatten_binary_operators),
    ]

    bool_precedent = [
        (op_lower_exclusive | op_lower_inclusive |
         op_greater_inclusive | op_greater_exclusive,
         2, opAssoc.LEFT, flatten_binary_operators),
        (op_equality | op_inequality, 2, opAssoc.LEFT, flatten_binary_operators),
        (op_and, 2, opAssoc.LEFT, flatten_binary_operators),
        (op_or, 2, opAssoc.LEFT, flatten_binary_operators)
    ]

    # Arithmetic precedence
    arithmetic = infixNotation(
        arith_operand.setResultsName('arithmetic_operand*'),
        arith_precedent
    ).setResultsName('arithmetic_expr')

    bool_expr = infixNotation(
        bool_operand.setResultsName('boolean_operand'),
        (arith_precedent + bool_precedent)
    ).setResultsName('boolean_expr')

    arithmetic.setParseAction(lambda parsed_tokens: ast.Arithmetic(*parsed_tokens))

    boolean_expr = bool_expr

    arithmetic_expression = \
        OneOrMore(
            arithmetic | (lit_l_paren + arithmetic + lit_r_paren)
        ).setResultsName("arithmetic_statement")

    arithmetic_expression.addParseAction(lambda parsed_tokens: ast.Expression(*parsed_tokens))
    boolean_statement = \
        OneOrMore(boolean_expr | (lit_l_paren + boolean_expr + lit_r_paren))
    boolean_statement.setParseAction(lambda parsed_tokens: ast.Condition(*parsed_tokens))

    field_expr = \
        QuotedString('"', unquoteResults=True).setResultsName("title") + \
        name.setResultsName("identifier") + \
        colon + \
        data_types.setResultsName("data_type") + \
        Optional(
            assign_op +
            arithmetic_expression
        )
    field_expr.setParseAction(lambda parsed_tokens: ast.Field(*parsed_tokens))

    body = Forward()
    if_block = Forward()
    if_block << lit_if + lit_l_paren + boolean_statement + lit_r_paren + body + Optional(
        lit_else + body).setResultsName('else_statement')
    if_block.setParseAction(ast.Conditional)

    statement = field_expr | if_block
    body <<= lit_l_curly + OneOrMore(statement) + lit_r_curly
    body.addParseAction(lambda parsed_tokens: [parsed_tokens.asList()])
    body.setResultsName('statement_list')

    # Form
    form = lit_form + name + body
    form.addParseAction(lambda parsed_tokens: ast.Form(*parsed_tokens))
    tokens = form.parseString(input_string)
    return tokens
