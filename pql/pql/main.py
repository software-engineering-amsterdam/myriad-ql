import sys
import io
import os
from parser.parser import parse

PATH_EXAMPLE = str(os.path.join("path", "to", "your", "file"))


def open_file(path):
    try:
        return io.open(path, "r")
    except FileNotFoundError:
        print("The given file could not be found. Usage: python pql.py %s" % PATH_EXAMPLE)
        sys.exit(1)


def main(sys_args):
    try:
        ql_file = open_file(sys_args[1])
    except IndexError:
        print("Usage: python pql.py %s" % PATH_EXAMPLE)
        sys.exit(2)

    if ql_file is None:
        print("Usage: python pql.py %s" % PATH_EXAMPLE)
        sys.exit(2)

    ql_str = ql_file.read()
    ql_file.close()
    parse(ql_str)

if __name__ == '__main__':
    main(sys.argv)
