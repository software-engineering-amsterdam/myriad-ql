# coding=utf-8
from tests.shared import Shared


class TestDependenciesChecker(Shared):
    def test_simple_circular(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = hasBought
            "Did you buy a house in 2010?" hasBought: boolean = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_simple_non_circular(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = true
            "Did you buy a house in 2010?" hasBought: integer = 1
            "Value residue:" valueResidue: money = 100.00
            "Rent" hasRent: string = 'abc'
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_boolean_circular_and(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = hasBought
            "Did you buy a house in 2010?" hasBought: boolean = hasLoan && hasSoldHouse
            "Loan" hasLoan: boolean = !hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_integer_circular_plus(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = hasBought
            "Did you buy a house in 2010?" hasBought: integer = hasLoan + hasSoldHouse
            "Loan" hasLoan: integer = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_integer_circular_subtraction(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = hasBought
            "Did you buy a house in 2010?" hasBought: integer = hasLoan - hasSoldHouse
            "Loan" hasLoan: integer = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_integer_circular_division(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = hasBought
            "Did you buy a house in 2010?" hasBought: integer = +hasLoan / -hasSoldHouse
            "Loan" hasLoan: integer = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_integer_circular_multiplication(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = hasBought
            "Did you buy a house in 2010?" hasBought: integer = hasLoan * hasSoldHouse
            "Loan" hasLoan: integer = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_integer_circular_equality(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = hasBought
            "Did you buy a house in 2010?" hasBought: boolean = hasLoan == hasSoldHouse
            "Loan" hasLoan: integer = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_integer_circular_inequality(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = hasBought
            "Did you buy a house in 2010?" hasBought: boolean = hasLoan != hasSoldHouse
            "Loan" hasLoan: integer = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_integer_circular_greater_equal(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = hasBought
            "Did you buy a house in 2010?" hasBought: boolean = hasLoan  >= hasSoldHouse
            "Loan" hasLoan: integer = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_integer_circular_greater(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = hasBought
            "Did you buy a house in 2010?" hasBought: boolean = hasLoan  > hasSoldHouse
            "Loan" hasLoan: integer = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_integer_circular_smaller(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = hasBought
            "Did you buy a house in 2010?" hasBought: boolean = hasLoan  < hasSoldHouse
            "Loan" hasLoan: integer = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_integer_circular_smaller_equal(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = hasBought
            "Did you buy a house in 2010?" hasBought: boolean = hasLoan  <= hasSoldHouse
            "Loan" hasLoan: integer = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no messages")

    def test_circular_multiple(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = hasBoughtHouse
            "Did you buy a house in 2010?" hasBought: boolean = hasSoldHouse
            "Has house" hasHouse: boolean = hasCar && hasBike
            "Did you buy a house in 2010?" hasCar: boolean = hasSoldHouse
            "Did you buy a house in 2010?" hasBike: boolean = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 1, "There should be exactly 1 messages")

    def test_circular_inside_if(self):
        input_string = """
        form taxOfficeExample {
            "Did you buy a house in 2010?" hasBought: boolean
            if(hasBought){
                "Did you buy a house in 2010?" hasCar: boolean
                "Did you buy a house in 2010?" hasBike: boolean
            }
            "Has house" hasHouse: boolean = hasCar && hasBike
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 1, "There should be exactly 1 messages")

    def test_circular_inside_if_else(self):
        input_string = """
        form taxOfficeExample {
            "Did you buy a house in 2010?" hasBought: boolean
            if(hasBought){
                "Did you buy a house in 2010?" hasCar: boolean
            }
            else {
                "Did you buy a house in 2010?" hasBike: boolean
            }
            "Has house" hasHouse: boolean = hasCar
            "Has second house" hasHouse2: boolean = hasBike

        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 2, "There should be exactly 2 errors")

    def test_circular_inside_nested_if(self):
        input_string = """
        form taxOfficeExample {
            "Did you buy a house in 2010?" hasBought: boolean
            if(hasBought){
                "Did you buy a house in 2010?" hasCar: boolean
                if(hasCar){
                    "Did you buy a house in 2010?" hasBike: boolean
                }
                "Did you buy a house in 2010?" hasNewBike: boolean = hasBike
            }
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 1, "There should be exactly 1 messages")

    def test_circular_inside_nested_if_2(self):
        input_string = """
        form taxOfficeExample {
            if(hasBought){
                "Did you buy a house in 2010?" hasCar: boolean = hasBike
                if(hasCar){
                    "Did you buy a house in 2010?" hasBike: boolean
                }
                "Did you buy a house in 2010?" hasNewBike: boolean = hasBike
            }
            "Did you buy a house in 2010?" hasBought: boolean
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 2, "There should be exactly 2 errors")

    def test_reference_simple(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?"
                hasSoldHouse: boolean
            "Did you buy a house in 2010?"
                hasBoughtHouse: boolean
            "Did you buy or sell a house in 2010?"
                hasDoneEither: boolean = hasSoldHouse || hasBoughtHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no errors")

    def test_forward_reference_simple(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?"
                hasSoldHouse: boolean = hasBoughtHouse
            "Did you buy a house in 2010?"
                hasBoughtHouse: boolean
            "Did you buy or sell a house in 2010?"
                hasDoneEither: boolean = hasSoldHouse || hasBoughtHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no errors")
