from parser.ql import *
from ast.ast import *

if __name__ == '__main__':
    # ql_test = 'form taxOfficeExample { "Did you sell a house in 2010?" hasSoldHouse: boolean "Did you buy a house in 2010?" hasBoughtHouse: boolean "Did you enter a loan?" hasMaintLoan: boolean  if (hasSoldHouse || (!hasSoldHouse && hasBoughtHouse)) { "What was the selling price?" sellingPrice: money "Private debts for the sold house:" privateDebt: money "Value residue:" valueResidue: money = (sellingPrice - privateDebt) } "Is this questions being printed?" isPrinted: boolean }'
    # __parsed = QL.form.parseString(ql_test)
    # print(__parsed[0])
    # eval_test = 'if (hasSoldHouse || (!hasSoldHouse && hasBoughtHouse)) { "What was the selling price?" sellingPrice: money "Private debts for the sold house:" privateDebt: money "Value residue:" valueResidue: money = (sellingPrice - privateDebt) }'
    # __parsed = QL.conditional.parseString(eval_test)
    # print(__parsed)
    eval_test = '(hasSoldHouse || (hasBoughtHouse && hasMaintLoan))'
    __parsed = QL.boolean_expression.parseString(eval_test)[0]
    print(__parsed.right_hand_side)
    print(__parsed.right_hand_side.right_hand_side)
    print(__parsed.right_hand_side.right_hand_side.left_hand_side.eval())
    print(__parsed.right_hand_side.right_hand_side.left_hand_side._identifier)
    print(__parsed.right_hand_side.right_hand_side.right_hand_side)
    print(__parsed.right_hand_side.right_hand_side.right_hand_side.right_hand_side)
    left = __parsed.right_hand_side.right_hand_side.right_hand_side.right_hand_side.left_hand_side
    print(left._identifier)
    right = __parsed.right_hand_side.right_hand_side.right_hand_side.right_hand_side.right_hand_side
    print(right._identifier)
    # Basic Arithmics
    test = Addition(Integer(None, [1]),Integer(None, [2]))
    print("Test Evaluation Addition: {}".format(test.eval()))
    test = Substraction(Integer(None, [1]),Integer(None, [2]))
    print("Test Evaluation Substraction: {}".format(test.eval()))
    test = Division(Integer(None, [1]),Integer(None, [2]))
    print("Test Evaluation Division: {}".format(test.eval()))
    test = LessThan(Integer(None, [1]),Integer(None, [2]))
    print("Test Evaluation LessThan: {}".format(test.eval()))
    test = LessThanEquals(Integer(None, [1]),Integer(None, [2]))
    print("Test Evaluation LessThanEquals: {}".format(test.eval()))
    test = Equality(Integer(None, [1]),Integer(None, [2]))
    print("Test Evaluation Equality: {}".format(test.eval()))
    test = GreaterThan(Integer(None, [1]),Integer(None, [2]))
    print("Test Evaluation GreaterThan: {}".format(test.eval()))
    test = GreaterThanEquals(Integer(None, [1]),Integer(None, [2]))
    print("Test Evaluation GreaterThanEquals: {}".format(test.eval()))
    test = Multiplication(Integer(None, [1]),Integer(None, [2]))
    print("Test Evaluation Multiplication: {}".format(test.eval()))


    test = LogicalOr(Boolean(None,[True]),Boolean(None,[False]))
    print("Test Evaluation LogicalOr: {}".format(test.eval()))
    test = LogicalNot(Boolean(None,[False]))
    print("Test Evaluation LogicalNot: {}".format(test.eval()))
    test = LogicalAnd(Boolean(None,[True]),Boolean(None,[False]))
    print("Test Evaluation LogicalAnd: {}".format(test.eval()))
    test = Inequality(Boolean(None,[True]),Boolean(None,[False]))
    print("Test Evaluation Inequality: {}".format(test.eval()))
    # # Boolean expression
    # left._value = Boolean(None, [True])
    # right._value = Boolean(None, [False])
    # print("left: {}".format(left))
    # print("right: {}".format(right))
    # print("left && right: {}".format(left & right))
    # print("left || right: {}".format(left | right))
    # print("!left: {}".format(~left))
    # print("!right: {}".format(~right))
    # # Comparisons
    # left._value = Integer(None, [5])
    # right._value = Integer(None, [4])
    # print("left: {}".format(left))
    # print("right: {}".format(right))
    # print("left < right: {}".format(left < right))
    # print("left > right: {}".format(left > right))
    # print("left >= right: {}".format(left >= right))
    # print("left <= right: {}".format(left <= right))
    # print("left != right: {}".format(left != right))
    # print("left == right: {}".format(left == right))

    # eval_test = '(sellingPrice - privateDebt)'
    # __parsed = QL.arithmic_expression.parseString(eval_test)[0]
    # print(__parsed.eval())
    # print(__parsed.left_hand_side)
    # print(__parsed.right_hand_side)
