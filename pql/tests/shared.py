# coding=utf-8
from io import open
from os.path import join
from sys import exit

from pql.environment.environmentcreator import EnvironmentCreator
from pql.evaluator.evaluator import Evaluator
from pql.identifierchecker.identifierchecker import IdentifierChecker
from pql.parser.parser import parse
from pql.typechecker.type_environment import TypeEnvironment
from pql.typechecker.typechecker import TypeChecker

PATH_EXAMPLE = str(join("path", "to", "your", "file"))


def acquire_identifiers(ast):
    return IdentifierChecker().visit(ast)


# ast = self.create_ast(contents)
#         if ast is not None:
#             identifier_errors = self.check_ids(ast)
#             if identifier_errors:
#                 self.list_errors.addItems(identifier_errors)
#             else:
#                 type_errors = self.check_type(TypeEnvironment().visit(ast), ast)
#                 if type_errors:
#                     self.list_errors.addItems(type_errors)
#                 else:
#                     self.form = Questionnaire().visit(ast)
#                     self.form.show()


def check_type(ql_ast):
    type_checker = TypeChecker(TypeEnvironment().visit(ql_ast))
    result = type_checker.visit(ql_ast)
    del type_checker
    return result


def evaluate(ql_ast, ql_identifier_check_result):
    evaluator = Evaluator(strip_keys_from_dict(ql_identifier_check_result))
    return evaluator.visit(ql_ast)


def ql(ql_str):
    ql_ast = parse(ql_str)
    if ql_ast is None:
        exit(4)
    identifier_result_errors = acquire_identifiers(ql_ast)

    if identifier_result_errors:
        print_result('Identifier checker had errors', identifier_result_errors, 4)

    ql_type_check_result = check_type(ql_ast)
    if ql_type_check_result:
        print_result('Type checker had errors', ql_type_check_result, 5)

    return evaluate(ql_ast, EnvironmentCreator().visit(ql_ast))



def print_result(main_message, error_list, exit_code):
    print(main_message)
    print('\n'.join(map(str, error_list)))
    exit(exit_code)



