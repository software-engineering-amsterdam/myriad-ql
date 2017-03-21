# coding=utf-8
from pql.typechecker.types import DataTypes
from tests.shared import Shared


class TestAst(Shared):
    def test_ast_single_question(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        form_node = self.acquire_ast(input_string)

        self.assertEqual('taxOfficeExample', form_node.name.name)
        field_node_1 = form_node.statements[0]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasSoldHouse', field_node_1.name.name)
        self.assertEqual('Did you sell a house in 2010?', field_node_1.title)

    def test_ast_double_question(self):
        input_string = """
        form taxOfficeExample {
            "Did you buy a house in 2010?" hasBoughtHouse: boolean
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        form_node = self.acquire_ast(input_string)

        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(2, len(form_node.statements))
        field_node_1 = form_node.statements[0]
        field_node_2 = form_node.statements[1]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasBoughtHouse', field_node_1.name.name)
        self.assertEqual('Did you buy a house in 2010?', field_node_1.title)

        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('hasSoldHouse', field_node_2.name.name)
        self.assertEqual('Did you sell a house in 2010?', field_node_2.title)

    def test_ast_single_simple_assignment(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice - privateDebt
        }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        assignment_node_1 = form_node.statements[0]
        self.assertEqual('assignment', assignment_node_1.var_type)
        self.assertEqual('valueResidue', assignment_node_1.name.name)
        self.assertEqual('Value residue:', assignment_node_1.title)

        subtraction_node = assignment_node_1.expression
        self.assertEqual('subtraction', subtraction_node.var_type,
                         'Subtraction node should have type subtraction')

        self.assertEqual('sellingPrice', subtraction_node.lhs.name)
        self.assertEqual('privateDebt', subtraction_node.rhs.name)

    def test_ast_single_simple_assignment_(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice + privateDebt - interest
        }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        assignment_node_1 = form_node.statements[0]
        self.assertEqual('assignment', assignment_node_1.var_type)
        self.assertEqual('valueResidue', assignment_node_1.name.name)
        self.assertEqual('Value residue:', assignment_node_1.title)

        subtraction_node = assignment_node_1.expression
        self.assertEqual('subtraction', subtraction_node.var_type,
                         'Subtraction node should have type subtraction')
        self.assertEqual('interest', subtraction_node.rhs.name)

        addition_node = subtraction_node.lhs
        self.assertEqual('addition', addition_node.var_type,
                         'Addition node should have type addition')

        self.assertEqual('sellingPrice', addition_node.lhs.name)
        self.assertEqual('privateDebt', addition_node.rhs.name)

    def test_ast_single_simple_assignment_reversed(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice - privateDebt + interest
        }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        assignment_node_1 = form_node.statements[0]
        self.assertEqual('assignment', assignment_node_1.var_type)
        self.assertEqual('valueResidue', assignment_node_1.name.name)
        self.assertEqual('Value residue:', assignment_node_1.title)

        addition_node = assignment_node_1.expression
        self.assertEqual('addition', addition_node.var_type,
                         'Addition node should have type addition')
        self.assertEqual('interest', addition_node.rhs.name)

        subtraction_node = addition_node.lhs
        self.assertEqual('subtraction', subtraction_node.var_type,
                         'Subtraction node should have type subtraction')

        self.assertEqual('sellingPrice', subtraction_node.lhs.name)
        self.assertEqual('privateDebt', subtraction_node.rhs.name)

    def test_ast_single_combination_assignment(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  (sellingPrice - privateDebt) * debt
        }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        assignment_node_1 = form_node.statements[0]
        self.assertEqual('assignment', assignment_node_1.var_type)
        self.assertEqual('valueResidue', assignment_node_1.name.name)
        self.assertEqual('Value residue:', assignment_node_1.title)

        multiplication_node = assignment_node_1.expression
        self.assertEqual('multiplication', multiplication_node.var_type,
                         'Multiplication node should have type multiplication')
        self.assertEqual('debt', multiplication_node.rhs.name)

        subtraction_node = multiplication_node.lhs
        self.assertEqual('subtraction', subtraction_node.var_type,
                         'Subtraction node should have type subtraction')

        self.assertEqual('sellingPrice', subtraction_node.lhs.name)
        self.assertEqual('privateDebt', subtraction_node.rhs.name)

    def test_ast_single_combination_assignment_(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice - privateDebt * debt *  salary + interest
        }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        assignment_node_1 = form_node.statements[0]
        self.assertEqual('assignment', assignment_node_1.var_type)
        self.assertEqual('valueResidue', assignment_node_1.name.name)
        self.assertEqual('Value residue:', assignment_node_1.title)

        addition_node = assignment_node_1.expression
        self.assertEqual('addition', addition_node.var_type,
                         'Addition node should have type addition')
        self.assertEqual('interest', addition_node.rhs.name)

        subtraction_node = addition_node.lhs
        self.assertEqual('subtraction', subtraction_node.var_type,
                         'Subtraction node should have type subtraction')
        self.assertEqual('sellingPrice', subtraction_node.lhs.name)

        multiplication_node_1 = subtraction_node.rhs
        self.assertEqual('multiplication', multiplication_node_1.var_type,
                         'Multiplication node should have type multiplication')
        self.assertEqual('salary', multiplication_node_1.rhs.name)

        multiplication_node_2 = multiplication_node_1.lhs
        self.assertEqual('multiplication', multiplication_node_2.var_type,
                         'Multiplication node should have type multiplication')

        self.assertEqual('privateDebt', multiplication_node_2.lhs.name)
        self.assertEqual('debt', multiplication_node_2.rhs.name)

    def test_ast_if_single_question(self):
        input_string = """
            form taxOfficeExample {
                if (hasSoldHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        conditional_node = form_node.statements[0]
        self.assertEqual('if', conditional_node.var_type,
                         'If node should have type if')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_1 = conditional_node.statements[0]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name.name)
        self.assertEqual(DataTypes.money, field_node_1.data_type.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        boolean_operand_node = conditional_node.condition
        self.assertEqual('hasSoldHouse', boolean_operand_node.name)
        self.assertEqual('identifier', boolean_operand_node.var_type)

    def test_ast_if_with_and_expression_single_question(self):
        input_string = """
            form taxOfficeExample {
                if (hasSoldHouse && hasBoughtHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        conditional_node = form_node.statements[0]
        self.assertEqual('if', conditional_node.var_type,
                         'If node should have type if')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_1 = conditional_node.statements[0]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name.name)
        self.assertEqual(DataTypes.money, field_node_1.data_type.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        boolean_and_node = conditional_node.condition

        self.assertEqual('and', boolean_and_node.var_type)

        identifier_node_1 = boolean_and_node.lhs
        self.assertEqual('hasSoldHouse', identifier_node_1.name)
        self.assertEqual('identifier', identifier_node_1.var_type)

        identifier_node_2 = boolean_and_node.rhs
        self.assertEqual('hasBoughtHouse', identifier_node_2.name)
        self.assertEqual('identifier', identifier_node_2.var_type)

    def test_ast_if_expression_or_and_combined_single_question(self):
        input_string = """
            form taxOfficeExample {
                if (hasSoldHouse || hasBoughtHouse && wantsToBuyHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        conditional_node = form_node.statements[0]
        self.assertEqual('if', conditional_node.var_type,
                         'If node should have type if')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_1 = conditional_node.statements[0]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name.name)
        self.assertEqual(DataTypes.money, field_node_1.data_type.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        or_node = conditional_node.condition
        self.assertEqual('or', or_node.var_type)

        identifier_node_1 = or_node.lhs
        self.assertEqual('hasSoldHouse', identifier_node_1.name)
        self.assertEqual('identifier', identifier_node_1.var_type)

        and_node = or_node.rhs
        self.assertEqual('and', and_node.var_type)

        identifier_node_2 = and_node.lhs
        self.assertEqual('hasBoughtHouse', identifier_node_2.name)
        self.assertEqual('identifier', identifier_node_2.var_type)

        identifier_node_3 = and_node.rhs
        self.assertEqual('wantsToBuyHouse', identifier_node_3.name)
        self.assertEqual('identifier', identifier_node_3.var_type)

    def test_ast_if_with_complex_expression_and_3_operands_single_question(self):
        input_string = """
            form taxOfficeExample {
                if (hasSoldHouse && hasBoughtHouse && wantsToBuyHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        conditional_node = form_node.statements[0]
        self.assertEqual('if', conditional_node.var_type,
                         'If node should have type if')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_1 = conditional_node.statements[0]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name.name)
        self.assertEqual(DataTypes.money, field_node_1.data_type.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        boolean_and_node = conditional_node.condition

        self.assertEqual('and', boolean_and_node.var_type)

        identifier_node_1 = boolean_and_node.rhs
        self.assertEqual('wantsToBuyHouse', identifier_node_1.name)
        self.assertEqual('identifier', identifier_node_1.var_type)

        and_node_2 = boolean_and_node.lhs

        identifier_node_2 = and_node_2.lhs
        self.assertEqual('hasSoldHouse', identifier_node_2.name)
        self.assertEqual('identifier', identifier_node_2.var_type)

        identifier_node_3 = and_node_2.rhs
        self.assertEqual('hasBoughtHouse', identifier_node_3.name)
        self.assertEqual('identifier', identifier_node_3.var_type)

    def test_ast_if_with_complex_expression_and_3_operands_with_or_single_question(self):
        input_string = """
            form taxOfficeExample {
                if (hasSoldHouse && hasBoughtHouse && ( wantsToBuyHouse || wantsToRentHouse)) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        conditional_node = form_node.statements[0]
        self.assertEqual('if', conditional_node.var_type,
                         'If node should have type if')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_1 = conditional_node.statements[0]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name.name)
        self.assertEqual(DataTypes.money, field_node_1.data_type.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        and_node_1 = conditional_node.condition

        self.assertEqual('and', and_node_1.var_type)

        and_node_2 = and_node_1.lhs

        self.assertEqual('and', and_node_2.var_type)

        or_node_1 = and_node_1.rhs
        self.assertEqual('or', or_node_1.var_type)

        identifier_node_1 = and_node_2.lhs
        self.assertEqual('hasSoldHouse', identifier_node_1.name)
        self.assertEqual('identifier', identifier_node_1.var_type)

        identifier_node_2 = and_node_2.rhs
        self.assertEqual('hasBoughtHouse', identifier_node_2.name)
        self.assertEqual('identifier', identifier_node_2.var_type)

        identifier_node_4 = or_node_1.lhs
        self.assertEqual('wantsToBuyHouse', identifier_node_4.name)
        self.assertEqual('identifier', identifier_node_4.var_type)

        identifier_node_5 = or_node_1.rhs
        self.assertEqual('wantsToRentHouse', identifier_node_5.name)
        self.assertEqual('identifier', identifier_node_5.var_type)

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
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        if_else_node = form_node.statements[0]
        self.assertIsNotNone(if_else_node.else_statement_list,
                             'This test has an else block, else block should not be none')
        self.assertEqual('if_else', if_else_node.var_type,
                         'IfElse node should have type if_else')
        self.assertEqual(1, len(if_else_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(if_else_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(if_else_node.statements))

        field_node_1 = if_else_node.statements[0]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('sellingPrice', field_node_1.name.name)
        self.assertEqual(DataTypes.money, field_node_1.data_type.data_type)
        self.assertEqual('What was the selling price?', field_node_1.title)

        identifier_node_1 = if_else_node.condition
        self.assertEqual('hasSoldHouse', identifier_node_1.name)
        self.assertEqual('identifier', identifier_node_1.var_type)

        else_statement_list = if_else_node.else_statement_list

        field_node_2 = else_statement_list[0]

        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('buyingPrice', field_node_2.name.name)
        self.assertEqual(DataTypes.money, field_node_2.data_type.data_type)
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
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)

        field_node_1 = form_node.statements[0]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasSoldHouse', field_node_1.name.name)
        self.assertEqual(DataTypes.boolean, field_node_1.data_type.data_type)
        self.assertEqual('Did you sell a house in 2010?', field_node_1.title)

        conditional_node = form_node.statements[1]
        self.assertEqual('if', conditional_node.var_type,
                         'If node should have type if')
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_2 = conditional_node.statements[0]

        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('sellingPrice', field_node_2.name.name)
        self.assertEqual(DataTypes.money, field_node_2.data_type.data_type)
        self.assertEqual('What was the selling price?', field_node_2.title)

        identifier_node_1 = conditional_node.condition
        self.assertEqual('hasSoldHouse', identifier_node_1.name)
        self.assertEqual('identifier', identifier_node_1.var_type)

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
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)

        field_node_1 = form_node.statements[0]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasSoldHouse', field_node_1.name.name)
        self.assertEqual(DataTypes.boolean, field_node_1.data_type.data_type)
        self.assertEqual('Did you sell a house in 2010?', field_node_1.title)

        conditional_node = form_node.statements[1]
        self.assertEqual(1, len(conditional_node.statements),
                         'This else block has one question inside, length should be 1')
        self.assertIsNotNone(conditional_node.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node.statements))
        field_node_2 = conditional_node.statements[0]

        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('sellingPrice', field_node_2.name.name)
        self.assertEqual(DataTypes.money, field_node_2.data_type.data_type)
        self.assertEqual('What was the selling price?', field_node_2.title)

        field_node_3 = form_node.statements[2]
        self.assertEqual('field', field_node_3.var_type)
        self.assertEqual('hasBoughtHouse', field_node_3.name.name)
        self.assertEqual(DataTypes.boolean, field_node_3.data_type.data_type)
        self.assertEqual('Did you buy a house in 2010?', field_node_3.title)

        identifier_node_1 = conditional_node.condition
        self.assertEqual('hasSoldHouse', identifier_node_1.name)
        self.assertEqual('identifier', identifier_node_1.var_type)

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
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)

        field_node_1 = form_node.statements[0]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasSoldHouse', field_node_1.name.name)
        self.assertEqual(DataTypes.boolean, field_node_1.data_type.data_type)
        self.assertEqual('Did you sell a house in 2010?', field_node_1.title)

        field_node_2 = form_node.statements[1]

        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('hasBoughtHouse', field_node_2.name.name)
        self.assertEqual(DataTypes.boolean, field_node_2.data_type.data_type)
        self.assertEqual('Did you buy a house in 2010?', field_node_2.title)

        conditional_node_1 = form_node.statements[2]
        self.assertEqual('if', conditional_node_1.var_type,
                         'If node should have type if')
        self.assertEqual(1, len(conditional_node_1.statements),
                         'This if block has one if block inside, length should be 1')
        self.assertIsNotNone(conditional_node_1.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node_1.statements))

        conditional_node_2 = conditional_node_1.statements[0]
        self.assertEqual('if', conditional_node_2.var_type,
                         'If node should have type if')
        self.assertEqual(1, len(conditional_node_2.statements),
                         'This if block has one field inside, length should be 1')
        self.assertIsNotNone(conditional_node_2.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node_2.statements))

        field_node_3 = conditional_node_2.statements[0]

        self.assertEqual('field', field_node_3.var_type)
        self.assertEqual('sellingPrice', field_node_3.name.name)
        self.assertEqual(DataTypes.money, field_node_3.data_type.data_type)
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
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)

        field_node_1 = form_node.statements[0]

        self.assertEqual('field', field_node_1.var_type)
        self.assertEqual('hasSoldHouse', field_node_1.name.name)
        self.assertEqual(DataTypes.boolean, field_node_1.data_type.data_type)
        self.assertEqual('Did you sell a house in 2010?', field_node_1.title)

        field_node_2 = form_node.statements[1]

        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('hasBoughtHouse', field_node_2.name.name)
        self.assertEqual(DataTypes.boolean, field_node_2.data_type.data_type)
        self.assertEqual('Did you buy a house in 2010?', field_node_2.title)

        conditional_node_1 = form_node.statements[2]
        self.assertEqual('if', conditional_node_1.var_type,
                         'If node should have type conditional')
        self.assertEqual(2, len(conditional_node_1.statements),
                         'This if block has one field and one if block inside, length should be 2')
        self.assertIsNotNone(conditional_node_1.condition, 'If block should have a condition')

        self.assertEqual(2, len(conditional_node_1.statements))

        field_node_2 = conditional_node_1.statements[0]

        self.assertEqual('field', field_node_2.var_type)
        self.assertEqual('wasMarried', field_node_2.name.name)
        self.assertEqual(DataTypes.boolean, field_node_2.data_type.data_type)
        self.assertEqual('Were you married?', field_node_2.title)

        conditional_node_2 = conditional_node_1.statements[1]
        self.assertEqual('if', conditional_node_2.var_type,
                         'If node should have type if')
        self.assertEqual(1, len(conditional_node_2.statements),
                         'This if block has one field inside, length should be 1')
        self.assertIsNotNone(conditional_node_2.condition, 'If block should have a condition')

        self.assertEqual(1, len(conditional_node_2.statements))

        field_node_3 = conditional_node_2.statements[0]

        self.assertEqual('field', field_node_3.var_type)
        self.assertEqual('sellingPrice', field_node_3.name.name)
        self.assertEqual(DataTypes.money, field_node_3.data_type.data_type)
        self.assertEqual('What was the selling price?', field_node_3.title)

    def test_ast_unary_positive(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  +privateDebt
        }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        assignment_node_1 = form_node.statements[0]
        self.assertEqual('assignment', assignment_node_1.var_type)
        self.assertEqual('valueResidue', assignment_node_1.name.name)
        self.assertEqual('Value residue:', assignment_node_1.title)

        positive_node = assignment_node_1.expression
        self.assertEqual('positive', positive_node.var_type,
                         'Positive node should have type positive')

        identifier_node = positive_node.operand
        self.assertEqual('identifier', identifier_node.var_type,
                         'Identifier node should have type identifier')

        self.assertEqual('privateDebt', identifier_node.name)

    def test_ast_unary_negative(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  -privateDebt
        }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        assignment_node_1 = form_node.statements[0]
        self.assertEqual('assignment', assignment_node_1.var_type)
        self.assertEqual('valueResidue', assignment_node_1.name.name)
        self.assertEqual('Value residue:', assignment_node_1.title)

        negative = assignment_node_1.expression
        self.assertEqual('negative', negative.var_type,
                         'Negative node should have type negative')

        identifier_node = negative.operand
        self.assertEqual('identifier', identifier_node.var_type,
                         'Identifier node should have type identifier')

        self.assertEqual('privateDebt', identifier_node.name)

    def test_ast_unary_not(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  !privateDebt
        }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        assignment_node_1 = form_node.statements[0]
        self.assertEqual('assignment', assignment_node_1.var_type)
        self.assertEqual('valueResidue', assignment_node_1.name.name)
        self.assertEqual('Value residue:', assignment_node_1.title)

        negative = assignment_node_1.expression
        self.assertEqual('negation', negative.var_type,
                         'Negation node should have type negation')

        identifier_node = negative.operand
        self.assertEqual('identifier', identifier_node.var_type,
                         'Identifier node should have type identifier')

        self.assertEqual('privateDebt', identifier_node.name)

    def test_ast_binary_unary_not(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice && (!privateDebt || hasLoans)
        }
        """
        form_node = self.acquire_ast(input_string)
        self.assertEqual('taxOfficeExample', form_node.name.name)
        self.assertEqual(1, len(form_node.statements))

        assignment_node_1 = form_node.statements[0]
        self.assertEqual('assignment', assignment_node_1.var_type)
        self.assertEqual('valueResidue', assignment_node_1.name.name)
        self.assertEqual('Value residue:', assignment_node_1.title)

        and_node = assignment_node_1.expression
        self.assertEqual('and', and_node.var_type,
                         'And node should have type and')

        identifier_node = and_node.lhs
        self.assertEqual('identifier', identifier_node.var_type,
                         'Identifier node should have type identifier')

        self.assertEqual('sellingPrice', identifier_node.name)

        or_node = and_node.rhs
        self.assertEqual('or', or_node.var_type,
                         'Or node should have type or')

        negation_node = or_node.lhs
        self.assertEqual('negation', negation_node.var_type,
                         'Negation node should have type negation')

        identifier_node_2 = negation_node.operand
        self.assertEqual('identifier', identifier_node_2.var_type,
                         'Identifier node should have type identifier')

        self.assertEqual('privateDebt', identifier_node_2.name)

        identifier_node_3 = or_node.rhs
        self.assertEqual('identifier', identifier_node_3.var_type,
                         'Identifier node should have type identifier')

        self.assertEqual('hasLoans', identifier_node_3.name)
