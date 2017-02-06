#!/usr/bin/python

import sys, os, io

if __name__ == '__main__':

    try:
        ql = io.open(sys.argv[1], "r")
    except IndexError:
        print(" ".join(["Usage: python pql.py", str(os.path.join("path", "to", "your", "file"))]))
        sys.exit(0)
    except FileNotFoundError:
        print("The given file could not be found.")
        sys.exit(0)

    print(ql.read())
