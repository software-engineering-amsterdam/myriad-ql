from sys import exit
from sys import argv
from io import open
from os.path import join
from pql.parser.parser import parse
from pql.typechecker.typechecker import Typechecker

PATH_EXAMPLE = str(join("path", "to", "your", "file"))


def open_file(path):
    try:
        return open(path, "r")
    except FileNotFoundError:
        print("The given file could not be found. Usage: python pql.py %s" % PATH_EXAMPLE)
        exit(1)


def main(sys_args):
    ql_str = acquire_text(sys_args)
    ql_ast = parse(ql_str)
    if ql_ast is not None:
        ql_ast.pprint()
    ql_type_check_result = check_type(ql_ast)
    if ql_type_check_result:
        print('Type checker had errors')
        print('\n'.join(map(str, ql_type_check_result)))
        exit(3)


def acquire_text(sys_args):
    ql_file = None

    try:
        ql_file = open_file(sys_args[1])
    except IndexError:
        print("Usage: python pql.py %s" % PATH_EXAMPLE)
        exit(2)

    if ql_file is None:
        print("No file was found:  usage: python pql.py %s" % PATH_EXAMPLE)
        exit(2)

    ql_str = ql_file.read()
    ql_file.close()
    del ql_file
    return ql_str


def check_type(ql_ast):
    typechecker = Typechecker()
    result = typechecker.visit(ql_ast)
    del typechecker
    return result

if __name__ == '__main__':
    main(argv)
