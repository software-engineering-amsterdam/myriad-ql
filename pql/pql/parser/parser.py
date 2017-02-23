# At a stable state of lexing and parsing this import needs to be changed to a more concrete version which only imports
# the needed methods from the pyparsing package
from pyparsing import *
from pql.ast import ast


def parse(input_string):
    identifier = Word(alphas, alphanums + '_').setResultsName('identifier')
    number = Word(nums + ".")

    arith_operand = number | identifier
    bool_operand = Literal("true") | Literal("false") | number | identifier
    bool_operand.setParseAction(ast.BoolOperand).setResultsName('bool_operand')

    # Reserved keywords
    form_lit = Suppress("form")
    if_lit = Suppress("if")
    else_lit = Suppress("else")

    # Reserved symbols
    l_curly = Suppress("{")
    r_curly = Suppress("}")
    l_paren = Suppress("(")
    r_paren = Suppress(")")

    op_multiplication = Literal("*").setParseAction(lambda _: ast.Multiplication)
    op_division = Literal("/").setParseAction(lambda _: ast.Division)
    op_subtract = Literal("-").setParseAction(lambda _: ast.Subtraction)
    op_addition = Literal("+").setParseAction(lambda _: ast.Addition)
    op_positive = Literal("+").setParseAction(lambda _: ast.Positive)
    op_negative = Literal("-").setParseAction(lambda _: ast.Negative)

    op_not = Literal("!").setParseAction(lambda _: ast.Negation)
    op_lower_exclusive = Literal("<").setParseAction(lambda _: ast.LowerExclusive)
    op_lower_inclusive = Literal("<=").setParseAction(lambda _: ast.LowerInlusive)
    op_greater_inclusive = Literal(">=").setParseAction(lambda _: ast.GreaterInclusive)
    op_greater_exclusive = Literal(">").setParseAction(lambda _: ast.GreaterExclusive)
    op_equality = Literal("==").setParseAction(lambda _: ast.Equality)
    op_inequality = Literal("!=").setParseAction(lambda _: ast.Inequality)
    op_and = Literal("&&").setParseAction(lambda _: ast.And)
    op_or = Literal("||").setParseAction(lambda _: ast.Or)

    colon = Suppress(":")
    data_types = oneOf(["boolean", "money", "string", "integer"])

    assign_op = Suppress("=")

    def flatten_binary_operators(unflatted_tokens):
        flattened_tokens = unflatted_tokens[0]
        while len(flattened_tokens) >= 3:
            lhs, type_call, rhs = flattened_tokens[:3]
            flattened_tokens = [type_call(lhs, rhs)] + flattened_tokens[3:]
        return flattened_tokens[0]

    def flatten_unary_operators(unflattened_tokens):
        flattened_tokens = unflattened_tokens[0]
        type_call = flattened_tokens[0]
        return type_call(flattened_tokens[1])

    arith_prec = [
        (op_positive | op_negative | op_not, 1, opAssoc.RIGHT, flatten_unary_operators),
        (op_multiplication | op_division, 2, opAssoc.LEFT, flatten_binary_operators),
        (op_addition | op_subtract, 2, opAssoc.LEFT, flatten_binary_operators),
    ]

    bool_prec = [
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
        arith_prec
    ).setResultsName('arithmetic_expr')

    bool_expr = infixNotation(
        bool_operand.setResultsName('boolean_operand'),
        (arith_prec + bool_prec)
    ).setResultsName('boolean_expr')

    arithmetic.setParseAction(lambda parsed_tokens: ast.Arithmetic(*parsed_tokens))

    boolean_expr = bool_expr

    arithmetic_expression = \
        OneOrMore(
            arithmetic | (l_paren + arithmetic + r_paren)
        ).setResultsName("arithmetic_statement")

    arithmetic_expression.addParseAction(lambda parsed_tokens: ast.Expression(*parsed_tokens))
    boolean_statement = \
        OneOrMore(boolean_expr | (l_paren + boolean_expr + r_paren))
    boolean_statement.setParseAction(lambda parsed_tokens: ast.Condition(*parsed_tokens))

    field_expr = \
        QuotedString('"', unquoteResults=True).setResultsName("title") + \
        identifier.setResultsName("identifier") + \
        colon + \
        data_types.setResultsName("data_type") + \
        Optional(
            assign_op +
            arithmetic_expression
        )
    field_expr.setParseAction(lambda parsed_tokens: ast.Field(*parsed_tokens))

    statement_list = Forward()
    if_stmt = Forward()
    if_stmt << if_lit + l_paren + boolean_statement + r_paren + \
               statement_list + \
               Optional(else_lit + statement_list).setResultsName('else_statement')
    if_stmt.setParseAction(ast.Conditional)

    statement = field_expr | if_stmt
    statement_list <<= l_curly + ZeroOrMore(statement) + r_curly
    statement_list.addParseAction(lambda parsed_tokens: [parsed_tokens.asList()])
    statement_list.setResultsName('statement_list')

    # Form
    form = form_lit + identifier + statement_list
    form.addParseAction(lambda parsed_tokens: ast.Form(*parsed_tokens))
    tokens = form.parseString(input_string)
    return tokens
