from QL_Parser import QuestionnaireParser

parsedEq = [['!', ['30', '+', '239.0', '-', '239', '+', '239']]]
eq1 = ['20', '*', '50', '/', '2']
eq2 = ['30']
eq3 = [['newPrice', '*', '1000']]
eq4 = [['40', '*', '30']]
eq5 = [['40', '+', '30']]
eq6 = ['hoi']
eq7 = [['388.923', '+', ['39.9', '*', 'hoi']]]
eq8 = ['!', '100']

def divide_expressions(eq):
    splitted_expr = split_expression(eq)
    return remove_redudant_lists(splitted_expr)


def remove_redudant_lists(expression):
    if isinstance(expression, list):
        if len(expression) == 1 and isinstance(expression[0], list):
            return expression[0]
        else:
            temp = []
            for expr in expression:
                if isinstance(expr, list):
                    temp.append(remove_redudant_lists(expr))
                else:
                    temp.append(expr)
            return temp
    else:
        return expression


def split_expression(expression):
    if isinstance(expression, str):
        return expression

    if len(expression) > 2:
        return [expression[:2] + split_expression(expression[2:])]
    elif len(expression) == 2:
        return [expression[0], split_expression(expression[1])]
    else:
        return expression


def binary_expr(expr):
    # When handed a string instead of a list, just return the string.
    if isinstance(expr, str):
        return expr

    # Case: expr op expr
    if len(expr) > 2:
        return [expr[:2] + binary_expr(expr[2:])]
    # Case: op expr
    elif len(expr) == 2:
        return [expr.asList()]
    else:
        return expr

parser = QuestionnaireParser()
expr = parser.define_expression()
eq9 = expr.parseString("5 + 10 / 234 / 1 - 20 && (19 * 12) || 2", parseAll=True)
print eq9.asList()
eq10 = expr.parseString("10 && 20 || 2", parseAll=True)
print eq10.asList()
print expr.parseString('30 + 239.0 - 239 * 239', parseAll=True).asList()
