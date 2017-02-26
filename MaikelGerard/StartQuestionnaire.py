from Parser import QuestionnaireParser
from TypeChecker import TypeChecker
from Environment import Environment
from ErrorHandler import ErrorHandler
from Evaluate import Evaluate
from FindCycles import FindCycles
from DrawGUI import DrawGUI
from InitGUI import InitGUI
import sys


if __name__ == '__main__':
    if len(sys.argv) != 2:
        print "Invalid amount of parameters, usage:"
        print "python StartQuestionnaire.py [QL FILEPATH]"
        sys.exit()

    # Read the contents of the questionnaire source file.
    with open(sys.argv[1]) as ql_contents:
        file_contents = ql_contents.read()

    # Parse the questionnaire into an AST.
    parsedAST = QuestionnaireParser().parse(file_contents)
    print (parsedAST)

    # Create an error handler and environment.
    error_handler = ErrorHandler()
    environment = Environment(error_handler)

    # Type-check and evaluate the AST.
    FindCycles(parsedAST, error_handler).start_traversal()
    TypeChecker(parsedAST, environment, error_handler).start_traversal()
    evaluator = Evaluate(parsedAST, environment, error_handler)

    # Finally, draw the GUI.09o8
    built_gui = InitGUI(parsedAST, environment, evaluator, error_handler)
    built_gui.start_traversal()
    DrawGUI(built_gui).start()
