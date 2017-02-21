from sys import exit
from sys import argv
from io import open
from os.path import join
from pql.parser.parser import parse

PATH_EXAMPLE = str(join("path", "to", "your", "file"))


def open_file(path):
    try:
        return open(path, "r")
    except FileNotFoundError:
        print("The given file could not be found. Usage: python pql.py %s" % PATH_EXAMPLE)
        exit(1)


def main(sys_args):
    ql_file = None

    try:
        ql_file = open_file(sys_args[1])
    except IndexError:
        print("Usage: python pql.py %s" % PATH_EXAMPLE)
        exit(2)

    if ql_file is None:
        print("Usage: python pql.py %s" % PATH_EXAMPLE)
        exit(2)

    ql_str = ql_file.read()
    ql_file.close()
    ql_parsed = parse(ql_str)
    ql_parsed.pprint()


if __name__ == '__main__':
    main(argv)
