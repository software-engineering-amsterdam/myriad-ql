# coding=utf-8
import unittest
import os.path

import pql.main as main


class TestMain(unittest.TestCase):
    def test_main_no_file(self):
        with self.assertRaises(SystemExit) as cm:
            main.main(("", ""))
        the_exception = cm.exception
        self.assertEqual(the_exception.code, 1)

    def test_open_file_invalid_path(self):
        with self.assertRaises(SystemExit):
            main.open_file("pql/examples/taxOfficeExample.ql")

    def test_open_file(self):
        my_path = os.path.abspath(os.path.dirname(os.path.dirname(__file__)))
        path = os.path.join(my_path, "examples/taxOfficeExample.ql")
        result = main.open_file(path)
        self.assertIsNotNone(result)
