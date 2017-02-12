import sys
import io


def open_file(path):
    try:
        return io.open(path, "r")
    except FileNotFoundError:
        print("The given file could not be found. Usage: python pql.py path\\to\\your\\file")
    return None


def main(sys_args):
    ql_file = open_file(sys_args[1])
    if ql_file is None:
        sys.exit(2)
    ql_char = ql_file.read()
    ql_file.close()


if __name__ == '__main__':
    main(sys.argv)
