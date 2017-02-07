parsedEq = [['!', ['30', '+', '239.0', '-', '239', '+', '239']]]
eq2 = ['30']
eq3 = [['newPrice', '*', '1000']]
eq4 = [['40', '*', '30']]
eq5 = [['40', '+', '30']]
eq6 = ['hoi']
eq7 = [['388.923', '+', ['39.9', '*', 'hoi']]]
eq8 = ['!', '100']


def temp(eq):
    t = []

    #for bla in eq:
    #    t.append(createExpressions(bla))
    return createExpressions(eq)


def removeLists(eq):
    if isinstance(eq, list) and len(eq) == 1:
        return removeLists(eq)
    else:
        return eq


def createExpressions(eq):

    if isinstance(eq, str):
        return eq

    if len(eq) > 2:
        res = createExpressions(eq[2:])
        if isinstance(res, list):
            return [eq[:2] + res]
        return eq[:2] + [res]

    elif len(eq) == 2:
        return [eq[0], createExpressions(eq[1])]
    else:
        return eq

print temp(parsedEq[0])
print temp(eq2)
print temp(eq3[0])
print temp(eq4[0])
print temp(eq5[0])
print temp(eq6)
print temp(eq7[0])
print temp(eq8)
