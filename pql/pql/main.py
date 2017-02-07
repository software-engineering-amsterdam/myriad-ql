import sys
import io
from pql.lexer.lexer import lex_using_default_tokens


def open_file(path):
    try:
        return io.open(path, "r")
    except FileNotFoundError:
        print("The given file could not be found. Usage: python pql.py path\\to\\your\\file")
    return None


def main(sys_args):
    def read_file_for_text(file):
        ql_char = file.read()
        ql_file.close()
        return ql_char

    def print_result(token_stream):
        for token in token_stream:
            print(token)

    ql_file = open_file(sys_args[1])
    if ql_file is None:
        sys.exit(2)

    characters = read_file_for_text(ql_file)
    tokens = lex_using_default_tokens(characters)
    print_result(tokens)

if __name__ == '__main__':
    main(sys.argv)
