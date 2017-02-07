parsedEq = [['!', ['30', '+', '239.0', '-', '239', '+', '239']]]
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

print divide_expressions(parsedEq[0])
print divide_expressions(eq2)
print divide_expressions(eq3[0])
print divide_expressions(eq4[0])
print divide_expressions(eq5[0])
print divide_expressions(eq6)
print divide_expressions(eq7[0])
print divide_expressions(eq8)
