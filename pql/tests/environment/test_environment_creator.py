# coding=utf-8
from tests.shared import Shared


class TestEnvironmentCreator(Shared):

    def test_parse_field_money_default(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money
        }
        """
        form_node = self.acquire_ast(input_string)

        environment = self.acquire_environment(form_node)
        self.assertEqual(environment['valueResidue'], float(0.0), 'valueResidue should be in environment')

    def test_parse_field_integer_default(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer
        }
        """
        form_node = self.acquire_ast(input_string)

        environemnt = self.acquire_environment(form_node)
        self.assertEqual(environemnt['valueResidue'], int(0), 'valueResidue should be in environment')

    def test_parse_field_boolean_default(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean
        }
        """
        form_node = self.acquire_ast(input_string)

        environemnt = self.acquire_environment(form_node)
        self.assertEqual(environemnt['valueResidue'], False, 'valueResidue should be in environment')

    def test_parse_field_string_default(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string
        }
        """
        form_node = self.acquire_ast(input_string)

        environemnt = self.acquire_environment(form_node)
        self.assertEqual(environemnt['valueResidue'], "", 'valueResidue should be in environment')

    def test_parse_field_boolean_assign(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true
        }
        """
        form_node = self.acquire_ast(input_string)

        environemnt = self.acquire_environment(form_node)
        self.assertEqual(environemnt['valueResidue'], False, 'valueResidue should be in environment')

    def test_parse_field_in_if_boolean_default(self):
        input_string = """
        form taxOfficeExample {
            if(test){
            "Value residue:" valueResidue: boolean
            }
        }
        """
        form_node = self.acquire_ast(input_string)

        environemnt = self.acquire_environment(form_node)
        self.assertEqual(environemnt['valueResidue'], False, 'valueResidue should be in environment')

    def test_parse_field_in_if_else_boolean_default(self):
        input_string = """
        form taxOfficeExample {
            if(test){
             "Value:" value: boolean
            } else {
            "Value residue:" valueResidue: boolean
            }
        }
        """
        form_node = self.acquire_ast(input_string)

        environemnt = self.acquire_environment(form_node)
        self.assertEqual(environemnt['valueResidue'], False, 'valueResidue should be in environment')
