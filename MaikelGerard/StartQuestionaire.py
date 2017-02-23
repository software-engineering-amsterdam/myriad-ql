from Parser import QuestionnaireParser
from TypeChecker import TypeChecker
from Environment import Environment
from ErrorHandler import ErrorHandler
from Evaluate import Evaluate
from FindCycles import FindCycles
import sys


if __name__ == '__main__':
    if len(sys.argv) != 2:
        print "Give a file as second argument to start a questionaire"

    file_contents = ""
    with open(sys.argv[1]) as ql_contents:
        file_contents = ql_contents.read()

    parser = QuestionnaireParser()
    parsedAST = parser.parse(file_contents)
    print parsedAST

    error_handler = ErrorHandler()

    FindCycles(parsedAST, error_handler).start_traversal()
    environment = Environment(error_handler)
    TypeChecker(parsedAST, environment, error_handler).start_traversal()
    Evaluate(parsedAST, environment).start_traversal()