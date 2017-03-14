# coding=utf-8
from pql.parser.parser import parse
from tests.shared import Shared


class TestDependenciesChecker(Shared):
    def test_simple_circular(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = hasBoughtHouse
            "Did you buy a house in 2010?" hasBought: boolean = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 2, "There should be exactly 2 errors")

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
        self.assertEqual(len(errors), 5, "There should be exactly 2 errors")

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
        self.assertEqual(len(errors), 1, "There should be exactly 1 error")

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
        self.assertEqual(len(errors), 1, "There should be exactly 1 error")

    def test_reference_known_inside_if(self):
        input_string = """
        form taxOfficeExample {
            if (hasSoldHouse) {
                "What was the selling price?" sellingPrice: money
                "Private debts for the sold house:"  privateDebt: money
                if (hasBoughtHouse) {
                    "What was the buying price?" buyingPrice: money
                }
                "Value residue:" valueResidue: money = (sellingPrice - privateDebt)
            }
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.assertEqual(len(errors), 0, "There should be no errors")
