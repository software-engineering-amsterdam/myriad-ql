#!/usr/bin/python

import sys
import os
import io
from tokens import *

if __name__ == '__main__':

    try:
        ql_file = io.open(sys.argv[1], "r")
    except IndexError:
        print(" ".join(["Usage: python pql.py", str(os.path.join("path", "to", "your", "file"))]))
        sys.exit(1)
    except FileNotFoundError:
        print("The given file could not be found.")
        sys.exit(1)

    ql_char = ql_file.read()
    ql_file.close()

    tokens = imp_lex(ql_char)
    for token in tokens:
        print(token)
