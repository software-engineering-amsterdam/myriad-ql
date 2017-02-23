import unittest

from pql.parser.parser import *


class TestAst(unittest.TestCase):
    def test_ast_single_question(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]

        self.assertEqual('taxOfficeExample', form_node.name)
        field_node_1 = form_node.children[0]

        self.assertEqual(0, len(field_node_1.children))
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasSoldHouse', field_node_1.name)
        self.assertEqual('Did you sell a house in 2010?', field_node_1.title)

    def test_ast_double_question(self):
        input_string = """
        form taxOfficeExample {
            "Did you buy a house in 2010?" hasBoughtHouse: boolean
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]

        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(2, len(form_node.children))
        field_node_1 = form_node.children[0]
        field_node_2 = form_node.children[1]

        self.assertEqual(0, len(field_node_1.children))
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasBoughtHouse', field_node_1.name)
        self.assertEqual('Did you buy a house in 2010?', field_node_1.title)

        self.assertEqual(0, len(field_node_2.children))
        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('hasSoldHouse', field_node_2.name)
        self.assertEqual('Did you sell a house in 2010?', field_node_2.title)

    def test_ast_single_simple_assignment(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice - privateDebt
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(1, len(form_node.children))

        field_node_1 = form_node.children[0]
        self.assertEqual(1, len(field_node_1.children), 'Field node should have 1 node of arithmetic statement')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('valueResidue', field_node_1.name)
        self.assertEqual('Value residue:', field_node_1.title)

        arithmetic_expression_node = field_node_1.children[0]
        self.assertEqual('arithmetic_expression', arithmetic_expression_node.var_type,
                         'First child should have type: arithmetic_expression')
        self.assertEqual(1, len(arithmetic_expression_node.children),
                         'Arithmetic expression should have 1 node as child')

        arithmetic_statement_node = arithmetic_expression_node.children[0]
        self.assertEqual('arithmetic_statement', arithmetic_statement_node.var_type,
                         'First child should have type: arithmetic_statement')

        self.assertEqual(1, len(arithmetic_expression_node.children),
                         'Arithmetic statement should have 1 node as child')

        substraction_node = arithmetic_statement_node.children[0]
        self.assertEqual(0, len(substraction_node.children),
                         'Subtraction node should have no nodes as children')
        self.assertEqual('substraction', substraction_node.var_type,
                         'Subtraction node should have type substraction')

        self.assertEqual('sellingPrice', substraction_node.lhs)
        self.assertEqual('privateDebt', substraction_node.rhs)

    def test_ast_single_simple_assignment_(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice + privateDebt - interest
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(1, len(form_node.children))

        field_node_1 = form_node.children[0]
        self.assertEqual(1, len(field_node_1.children), 'Field node should have 1 node of arithmetic statement')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('valueResidue', field_node_1.name)
        self.assertEqual('Value residue:', field_node_1.title)

        arithmetic_expression_node = field_node_1.children[0]
        self.assertEqual('arithmetic_expression', arithmetic_expression_node.var_type,
                         'First child should have type: arithmetic_expression')
        self.assertEqual(1, len(arithmetic_expression_node.children),
                         'Arithmetic expression should have 1 node as child')

        arithmetic_statement_node = arithmetic_expression_node.children[0]
        self.assertEqual('arithmetic_statement', arithmetic_statement_node.var_type,
                         'First child should have type: arithmetic_statement')

        self.assertEqual(1, len(arithmetic_expression_node.children),
                         'Arithmetic statement should have 1 node as child')

        substraction_node = arithmetic_statement_node.children[0]
        self.assertEqual(0, len(substraction_node.children),
                         'Subtraction node should have no nodes as children')
        self.assertEqual('substraction', substraction_node.var_type,
                         'Subtraction node should have type substraction')
        self.assertEqual('interest', substraction_node.rhs)

        addition_node = substraction_node.lhs
        self.assertEqual(0, len(addition_node.children),
                         'Addition node should have no nodes as children')
        self.assertEqual('addition', addition_node.var_type,
                         'Addition node should have type addition')

        self.assertEqual('sellingPrice', addition_node.lhs)
        self.assertEqual('privateDebt', addition_node.rhs)

    def test_ast_single_simple_assignment_reversed(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice - privateDebt + interest
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(1, len(form_node.children))

        field_node_1 = form_node.children[0]
        self.assertEqual(1, len(field_node_1.children), 'Field node should have 1 node of arithmetic statement')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('valueResidue', field_node_1.name)
        self.assertEqual('Value residue:', field_node_1.title)

        arithmetic_expression_node = field_node_1.children[0]
        self.assertEqual('arithmetic_expression', arithmetic_expression_node.var_type,
                         'First child should have type: arithmetic_expression')
        self.assertEqual(1, len(arithmetic_expression_node.children),
                         'Arithmetic expression should have 1 node as child')

        arithmetic_statement_node = arithmetic_expression_node.children[0]
        self.assertEqual('arithmetic_statement', arithmetic_statement_node.var_type,
                         'First child should have type: arithmetic_statement')

        self.assertEqual(1, len(arithmetic_expression_node.children),
                         'Arithmetic statement should have 1 node as child')

        addition_node = arithmetic_statement_node.children[0]
        self.assertEqual(0, len(addition_node.children),
                         'Addition node should have no nodes as children')
        self.assertEqual('addition', addition_node.var_type,
                         'Addition node should have type addition')
        self.assertEqual('interest', addition_node.rhs)

        substraction_node = addition_node.lhs
        self.assertEqual(0, len(substraction_node.children),
                         'Subtraction node should have no nodes as children')
        self.assertEqual('substraction', substraction_node.var_type,
                         'Subtraction node should have type substraction')

        self.assertEqual('sellingPrice', substraction_node.lhs)
        self.assertEqual('privateDebt', substraction_node.rhs)

    def test_ast_single_combi_assignment(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  (sellingPrice - privateDebt) * debt
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(1, len(form_node.children))

        field_node_1 = form_node.children[0]
        self.assertEqual(1, len(field_node_1.children), 'Field node should have 1 node of arithmetic statement')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('valueResidue', field_node_1.name)
        self.assertEqual('Value residue:', field_node_1.title)

        arithmetic_expression_node = field_node_1.children[0]
        self.assertEqual('arithmetic_expression', arithmetic_expression_node.var_type,
                         'First child should have type: arithmetic_expression')
        self.assertEqual(1, len(arithmetic_expression_node.children),
                         'Arithmetic expression should have 1 node as child')

        arithmetic_statement_node = arithmetic_expression_node.children[0]
        self.assertEqual('arithmetic_statement', arithmetic_statement_node.var_type,
                         'First child should have type: arithmetic_statement')

        self.assertEqual(1, len(arithmetic_expression_node.children),
                         'Arithmetic statement should have 1 node as child')

        multiplication_node = arithmetic_statement_node.children[0]
        self.assertEqual(0, len(multiplication_node.children),
                         'Multiplication node should have no nodes as children')
        self.assertEqual('multiplication', multiplication_node.var_type,
                         'Multiplication node should have type multiplication')
        self.assertEqual('debt', multiplication_node.rhs)

        substraction_node = multiplication_node.lhs
        self.assertEqual(0, len(substraction_node.children),
                         'Subtraction node should have no nodes as children')
        self.assertEqual('substraction', substraction_node.var_type,
                         'Subtraction node should have type substraction')

        self.assertEqual('sellingPrice', substraction_node.lhs)
        self.assertEqual('privateDebt', substraction_node.rhs)

    def test_ast_single_combi_assignment_(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice - privateDebt * debt *  salary + interest
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(1, len(form_node.children))

        field_node_1 = form_node.children[0]
        self.assertEqual(1, len(field_node_1.children), 'Field node should have 1 node of arithmetic statement')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('valueResidue', field_node_1.name)
        self.assertEqual('Value residue:', field_node_1.title)

        arithmetic_expression_node = field_node_1.children[0]
        self.assertEqual('arithmetic_expression', arithmetic_expression_node.var_type,
                         'First child should have type: arithmetic_expression')
        self.assertEqual(1, len(arithmetic_expression_node.children),
                         'Arithmetic expression should have 1 node as child')

        arithmetic_statement_node = arithmetic_expression_node.children[0]
        self.assertEqual('arithmetic_statement', arithmetic_statement_node.var_type,
                         'First child should have type: arithmetic_statement')

        self.assertEqual(1, len(arithmetic_expression_node.children),
                         'Arithmetic statement should have 1 node as child')
        addition_node = arithmetic_statement_node.children[0]
        self.assertEqual(0, len(addition_node.children),
                         'Addition node should have no nodes as children')
        self.assertEqual('addition', addition_node.var_type,
                         'Addition node should have type addition')
        self.assertEqual('interest', addition_node.rhs)

        subtraction_node = addition_node.lhs
        self.assertEqual(0, len(subtraction_node.children),
                         'Subtraction node should have no nodes as children')
        self.assertEqual('substraction', subtraction_node.var_type,
                         'Subtraction node should have type substraction')
        self.assertEqual('sellingPrice', subtraction_node.lhs)

        multiplication_node_1 = subtraction_node.rhs
        self.assertEqual(0, len(multiplication_node_1.children),
                         'Multiplication node should have no nodes as children')
        self.assertEqual('multiplication', multiplication_node_1.var_type,
                         'Multiplication node should have type multiplication')
        self.assertEqual('salary', multiplication_node_1.rhs)

        multiplication_node_2 = multiplication_node_1.lhs
        self.assertEqual(0, len(multiplication_node_2.children),
                         'Multiplication node should have no nodes as children')
        self.assertEqual('multiplication', multiplication_node_2.var_type,
                         'Multiplication node should have type multiplication')

        self.assertEqual('privateDebt', multiplication_node_2.lhs)
        self.assertEqual('debt', multiplication_node_2.rhs)

    def test_ast_if_single_question(self):
        input_string = """
            form taxOfficeExample {
                if (hasSoldHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(1, len(form_node.children))

        conditional_node = form_node.children[0]
        self.assertIsNone(conditional_node.else_statement_list,
                          'This test has no else block, else block should be none')
        self.assertEqual('conditional', conditional_node.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_1 = conditional_node.statements[0]

        self.assertEqual(0, len(field_node_1.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name)
        self.assertEqual('money', field_node_1.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        condition_node = conditional_node.condition

        self.assertEqual(1, len(condition_node.children), 'Condition node should have 1 child')
        self.assertEqual('condition', condition_node.var_type, 'Condition node should have type condition')

        boolean_operand_node = condition_node.children[0]
        self.assertEqual(0, len(boolean_operand_node.children))
        self.assertEqual('hasSoldHouse', boolean_operand_node.label)
        self.assertEqual('bool_operand', boolean_operand_node.var_type)

    def test_ast_if_with_and_expression_single_question(self):
        input_string = """
            form taxOfficeExample {
                if (hasSoldHouse && hasBoughtHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(1, len(form_node.children))

        conditional_node = form_node.children[0]
        self.assertIsNone(conditional_node.else_statement_list,
                          'This test has no else block, else block should be none')
        self.assertEqual('conditional', conditional_node.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_1 = conditional_node.statements[0]

        self.assertEqual(0, len(field_node_1.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name)
        self.assertEqual('money', field_node_1.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        condition_node = conditional_node.condition
        self.assertEqual(1, len(condition_node.children), 'Condition node should have 1 child')
        self.assertEqual('condition', condition_node.var_type, 'Condition node should have type condition')

        boolean_and_node = condition_node.children[0]

        self.assertEqual('and', boolean_and_node.var_type)
        self.assertEqual(0, len(boolean_and_node.children), 'Boolean AND node should have no children')

        boolean_operand_node_1 = boolean_and_node.lhs
        self.assertEqual(0, len(boolean_operand_node_1.children))
        self.assertEqual('hasSoldHouse', boolean_operand_node_1.label)
        self.assertEqual('bool_operand', boolean_operand_node_1.var_type)

        boolean_operand_node_2 = boolean_and_node.rhs
        self.assertEqual(0, len(boolean_operand_node_2.children))
        self.assertEqual('hasBoughtHouse', boolean_operand_node_2.label)
        self.assertEqual('bool_operand', boolean_operand_node_2.var_type)

    def test_ast_if_expression_or_and_combined_single_question(self):
        input_string = """
            form taxOfficeExample {
                if (hasSoldHouse || hasBoughtHouse && wantsToBuyHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(1, len(form_node.children))

        conditional_node = form_node.children[0]
        self.assertIsNone(conditional_node.else_statement_list,
                          'This test has no else block, else block should be none')
        self.assertEqual('conditional', conditional_node.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_1 = conditional_node.statements[0]

        self.assertEqual(0, len(field_node_1.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name)
        self.assertEqual('money', field_node_1.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        condition_node = conditional_node.condition
        self.assertEqual(1, len(condition_node.children), 'Condition node should have 1 child')
        self.assertEqual('condition', condition_node.var_type, 'Condition node should have type condition')

        boolean_or_node = condition_node.children[0]
        self.assertEqual('or', boolean_or_node.var_type)
        self.assertEqual(0, len(boolean_or_node.children), 'Boolean OR node should have no children')

        boolean_operand_node_1 = boolean_or_node.lhs
        self.assertEqual(0, len(boolean_operand_node_1.children))
        self.assertEqual('hasSoldHouse', boolean_operand_node_1.label)
        self.assertEqual('bool_operand', boolean_operand_node_1.var_type)

        boolean_and_node = boolean_or_node.rhs
        self.assertEqual('and', boolean_and_node.var_type)
        self.assertEqual(0, len(boolean_and_node.children), 'Boolean AND node should have no children')

        boolean_operand_node_2 = boolean_and_node.lhs
        self.assertEqual(0, len(boolean_operand_node_2.children))
        self.assertEqual('hasBoughtHouse', boolean_operand_node_2.label)
        self.assertEqual('bool_operand', boolean_operand_node_2.var_type)

        boolean_operand_node_3 = boolean_and_node.rhs
        self.assertEqual(0, len(boolean_operand_node_3.children))
        self.assertEqual('wantsToBuyHouse', boolean_operand_node_3.label)
        self.assertEqual('bool_operand', boolean_operand_node_3.var_type)

    def test_ast_if_with_complex_expression_and_3_operands_single_question(self):
        input_string = """
            form taxOfficeExample {
                if (hasSoldHouse && hasBoughtHouse && wantsToBuyHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(1, len(form_node.children))

        conditional_node = form_node.children[0]
        self.assertIsNone(conditional_node.else_statement_list,
                          'This test has no else block, else block should be none')
        self.assertEqual('conditional', conditional_node.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_1 = conditional_node.statements[0]

        self.assertEqual(0, len(field_node_1.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name)
        self.assertEqual('money', field_node_1.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        condition_node = conditional_node.condition
        self.assertEqual(1, len(condition_node.children), 'Condition node should have 1 child')
        self.assertEqual('condition', condition_node.var_type, 'Condition node should have type condition')

        boolean_and_node = condition_node.children[0]

        self.assertEqual('and', boolean_and_node.var_type)
        self.assertEqual(0, len(boolean_and_node.children), 'Boolean AND node should no children')

        boolean_operand_node_1 = boolean_and_node.rhs
        self.assertEqual(0, len(boolean_operand_node_1.children))
        self.assertEqual('wantsToBuyHouse', boolean_operand_node_1.label)
        self.assertEqual('bool_operand', boolean_operand_node_1.var_type)

        boolean_and_node_2 = boolean_and_node.lhs
        self.assertEqual(0, len(boolean_and_node_2.children))

        boolean_operand_node_2 = boolean_and_node_2.lhs
        self.assertEqual(0, len(boolean_operand_node_2.children))
        self.assertEqual('hasSoldHouse', boolean_operand_node_2.label)
        self.assertEqual('bool_operand', boolean_operand_node_2.var_type)

        boolean_operand_node_3 = boolean_and_node_2.rhs
        self.assertEqual(0, len(boolean_operand_node_3.children))
        self.assertEqual('hasBoughtHouse', boolean_operand_node_3.label)
        self.assertEqual('bool_operand', boolean_operand_node_3.var_type)

    def test_ast_if_with_complex_expression_and_3_operands_with_or_single_question(self):
        input_string = """
            form taxOfficeExample {
                if (hasSoldHouse && hasBoughtHouse && ( wantsToBuyHouse || wantsToRentHouse)) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(1, len(form_node.children))

        conditional_node = form_node.children[0]
        self.assertIsNone(conditional_node.else_statement_list,
                          'This test has no else block, else block should be none')
        self.assertEqual('conditional', conditional_node.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_1 = conditional_node.statements[0]

        self.assertEqual(0, len(field_node_1.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name)
        self.assertEqual('money', field_node_1.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        condition_node = conditional_node.condition
        self.assertEqual(1, len(condition_node.children), 'Condition node should have 1 child')
        self.assertEqual('condition', condition_node.var_type, 'Condition node should have type condition')

        boolean_and_node = condition_node.children[0]

        self.assertEqual('and', boolean_and_node.var_type)
        self.assertEqual(0, len(boolean_and_node.children), 'Boolean AND node should no children')

        boolean_and_node_2 = boolean_and_node.lhs

        self.assertEqual('and', boolean_and_node_2.var_type)
        self.assertEqual(0, len(boolean_and_node_2.children), 'Boolean AND node should no children')

        boolean_or_node = boolean_and_node.rhs
        self.assertEqual(0, len(boolean_or_node.children))
        self.assertEqual('or', boolean_or_node.var_type)

        boolean_operand_node_2 = boolean_and_node_2.lhs
        self.assertEqual(0, len(boolean_operand_node_2.children))
        self.assertEqual('hasSoldHouse', boolean_operand_node_2.label)
        self.assertEqual('bool_operand', boolean_operand_node_2.var_type)

        boolean_operand_node_3 = boolean_and_node_2.rhs
        self.assertEqual(0, len(boolean_operand_node_3.children))
        self.assertEqual('hasBoughtHouse', boolean_operand_node_3.label)
        self.assertEqual('bool_operand', boolean_operand_node_3.var_type)

        boolean_operand_node_4 = boolean_or_node.lhs
        self.assertEqual(0, len(boolean_operand_node_4.children))
        self.assertEqual('wantsToBuyHouse', boolean_operand_node_4.label)
        self.assertEqual('bool_operand', boolean_operand_node_4.var_type)

        boolean_operand_node_5 = boolean_or_node.rhs
        self.assertEqual(0, len(boolean_operand_node_5.children))
        self.assertEqual('wantsToRentHouse', boolean_operand_node_5.label)
        self.assertEqual('bool_operand', boolean_operand_node_5.var_type)

    def test_ast_if_else_single_question(self):
        input_string = """
            form taxOfficeExample {
                if (hasSoldHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
                else {
                    "What was the buying price?"        buyingPrice: money
                }
            }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(1, len(form_node.children))

        conditional_node = form_node.children[0]
        self.assertIsNotNone(conditional_node.else_statement_list,
                             'This test has an else block, else block should not be none')
        self.assertEqual('conditional', conditional_node.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))

        field_node_1 = conditional_node.statements[0]

        self.assertEqual(0, len(field_node_1.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name)
        self.assertEqual('money', field_node_1.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        condition_node = conditional_node.condition

        self.assertEqual(1, len(condition_node.children), 'Condition node should have 1 child')
        self.assertEqual('condition', condition_node.var_type, 'Condition node should have type condition')

        boolean_operand_node = condition_node.children[0]
        self.assertEqual(0, len(boolean_operand_node.children))
        self.assertEqual('hasSoldHouse', boolean_operand_node.label)
        self.assertEqual('bool_operand', boolean_operand_node.var_type)

        else_statement_list = conditional_node.else_statement_list

        field_node_2 = else_statement_list[0]

        self.assertEqual(0, len(field_node_2.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('buyingPrice', field_node_2.name)
        self.assertEqual('money', field_node_2.data_type)
        self.assertEqual('What was the buying price?', field_node_2.title)

    def test_ast_question_with_if_single_question(self):
        input_string = """
            form taxOfficeExample {
                "Did you sell a house in 2010?" hasSoldHouse: boolean
                if (hasSoldHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(2, len(form_node.children), 'Should have one field and one conditional as children')

        field_node_1 = form_node.children[0]

        self.assertEqual(0, len(field_node_1.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasSoldHouse', field_node_1.name)
        self.assertEqual('boolean', field_node_1.data_type)
        self.assertEqual('Did you sell a house in 2010?', field_node_1.title)

        conditional_node = form_node.children[1]
        self.assertIsNone(conditional_node.else_statement_list,
                          'This test has no else block, else block should be none')
        self.assertEqual('conditional', conditional_node.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_2 = conditional_node.statements[0]

        self.assertEqual(0, len(field_node_2.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('sellingPrice', field_node_2.name)
        self.assertEqual('money', field_node_2.data_type)
        self.assertEqual('What was the selling price?', field_node_2.title)

        condition_node = conditional_node.condition

        self.assertEqual(1, len(condition_node.children), 'Condition node should have 1 child')
        self.assertEqual('condition', condition_node.var_type, 'Condition node should have type condition')

        boolean_operand_node = condition_node.children[0]
        self.assertEqual(0, len(boolean_operand_node.children))
        self.assertEqual('hasSoldHouse', boolean_operand_node.label)
        self.assertEqual('bool_operand', boolean_operand_node.var_type)

    def test_ast_question_with_if_questions_below_and_above(self):
        input_string = """
            form taxOfficeExample {
                "Did you sell a house in 2010?" hasSoldHouse: boolean
                if (hasSoldHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
                "Did you buy a house in 2010?" hasBoughtHouse: boolean
            }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(3, len(form_node.children), 'Should have two fields and one conditional as children')

        field_node_1 = form_node.children[0]

        self.assertEqual(0, len(field_node_1.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasSoldHouse', field_node_1.name)
        self.assertEqual('boolean', field_node_1.data_type)
        self.assertEqual('Did you sell a house in 2010?', field_node_1.title)

        conditional_node = form_node.children[1]
        self.assertIsNone(conditional_node.else_statement_list,
                          'This test has no else block, else block should be none')
        self.assertEqual('conditional', conditional_node.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_2 = conditional_node.statements[0]

        self.assertEqual(0, len(field_node_2.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('sellingPrice', field_node_2.name)
        self.assertEqual('money', field_node_2.data_type)
        self.assertEqual('What was the selling price?', field_node_2.title)

        field_node_3 = form_node.children[2]
        self.assertEqual(0, len(field_node_3.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_3.var_type)
        self.assertEqual('hasBoughtHouse', field_node_3.name)
        self.assertEqual('boolean', field_node_3.data_type)
        self.assertEqual('Did you buy a house in 2010?', field_node_3.title)

        condition_node = conditional_node.condition

        self.assertEqual(1, len(condition_node.children), 'Condition node should have 1 child')
        self.assertEqual('condition', condition_node.var_type, 'Condition node should have type condition')

        boolean_operand_node = condition_node.children[0]
        self.assertEqual(0, len(boolean_operand_node.children))
        self.assertEqual('hasSoldHouse', boolean_operand_node.label)
        self.assertEqual('bool_operand', boolean_operand_node.var_type)

    def test_ast_recursive_if(self):
        input_string = """
            form taxOfficeExample {
                "Did you sell a house in 2010?" hasSoldHouse: boolean
                "Did you buy a house in 2010?" hasBoughtHouse: boolean
                if (hasSoldHouse) {
                    if (hasBoughtHouse) {
                        "What was the selling price?"        sellingPrice: money
                    }
                }
            }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(3, len(form_node.children), 'Should have two fields and one conditional as children')

        field_node_1 = form_node.children[0]

        self.assertEqual(0, len(field_node_1.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasSoldHouse', field_node_1.name)
        self.assertEqual('boolean', field_node_1.data_type)
        self.assertEqual('Did you sell a house in 2010?', field_node_1.title)

        field_node_2 = form_node.children[1]

        self.assertEqual(0, len(field_node_2.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('hasBoughtHouse', field_node_2.name)
        self.assertEqual('boolean', field_node_2.data_type)
        self.assertEqual('Did you buy a house in 2010?', field_node_2.title)

        conditional_node_1 = form_node.children[2]
        self.assertIsNone(conditional_node_1.else_statement_list,
                          'This test has no else block, else block should be none')
        self.assertEqual('conditional', conditional_node_1.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(1, len(conditional_node_1.statements),
                         'This if block has one if block inside, length should be 1')
        self.assertIsNotNone(conditional_node_1.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node_1.statements))

        conditional_node_2 = conditional_node_1.statements[0]
        self.assertIsNone(conditional_node_2.else_statement_list,
                          'This test has no else block, else block should be none')
        self.assertEqual('conditional', conditional_node_2.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(1, len(conditional_node_2.statements),
                         'This if block has one field inside, length should be 1')
        self.assertIsNotNone(conditional_node_2.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node_2.statements))

        field_node_3 = conditional_node_2.statements[0]

        self.assertEqual(0, len(field_node_3.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_3.var_type)
        self.assertEqual('sellingPrice', field_node_3.name)
        self.assertEqual('money', field_node_3.data_type)
        self.assertEqual('What was the selling price?', field_node_3.title)

    def test_ast_recursive_if_and_extra_field(self):
        input_string = """
            form taxOfficeExample {
                "Did you sell a house in 2010?" hasSoldHouse: boolean
                "Did you buy a house in 2010?" hasBoughtHouse: boolean
                if (hasSoldHouse) {
                    "Were you married?"        wasMarried: boolean
                    if (hasBoughtHouse) {
                        "What was the selling price?"        sellingPrice: money
                    }
                }
            }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result[0]
        self.assertEqual('taxOfficeExample', form_node.name)
        self.assertEqual(3, len(form_node.children), 'Should have two fields and one conditional as children')

        field_node_1 = form_node.children[0]

        self.assertEqual(0, len(field_node_1.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasSoldHouse', field_node_1.name)
        self.assertEqual('boolean', field_node_1.data_type)
        self.assertEqual('Did you sell a house in 2010?', field_node_1.title)

        field_node_2 = form_node.children[1]

        self.assertEqual(0, len(field_node_2.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('hasBoughtHouse', field_node_2.name)
        self.assertEqual('boolean', field_node_2.data_type)
        self.assertEqual('Did you buy a house in 2010?', field_node_2.title)

        conditional_node_1 = form_node.children[2]
        self.assertIsNone(conditional_node_1.else_statement_list,
                          'This test has no else block, else block should be none')
        self.assertEqual('conditional', conditional_node_1.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(2, len(conditional_node_1.statements),
                         'This if block has one field and one if block inside, length should be 2')
        self.assertIsNotNone(conditional_node_1.condition, 'If block should have a condition')

        self.assertEqual(2, len(conditional_node_1.statements))

        field_node_2 = conditional_node_1.statements[0]

        self.assertEqual(0, len(field_node_2.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('wasMarried', field_node_2.name)
        self.assertEqual('boolean', field_node_2.data_type)
        self.assertEqual('Were you married?', field_node_2.title)

        conditional_node_2 = conditional_node_1.statements[1]
        self.assertIsNone(conditional_node_2.else_statement_list,
                          'This test has no else block, else block should be none')
        self.assertEqual('conditional', conditional_node_2.var_type,
                         'Conditional node should have type conditional')
        self.assertEqual(1, len(conditional_node_2.statements),
                         'This if block has one field inside, length should be 1')
        self.assertIsNotNone(conditional_node_2.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node_2.statements))

        field_node_3 = conditional_node_2.statements[0]

        self.assertEqual(0, len(field_node_3.children), 'Field node should have no child nodes')
        self.assertEqual('field', field_node_3.var_type)
        self.assertEqual('sellingPrice', field_node_3.name)
        self.assertEqual('money', field_node_3.data_type)
        self.assertEqual('What was the selling price?', field_node_3.title)