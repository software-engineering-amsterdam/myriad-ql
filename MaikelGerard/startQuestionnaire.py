import sys

from QL.environment import Environment
from QL.errorHandler import ErrorHandler
from QL.GUI.initWidgets import InitWidgets
from QL.Stages.evaluator import Evaluate
from QL.Stages.findCycles import FindCycles
from QL.Stages.parser import QuestionnaireParser
from QL.Stages.typeChecker import TypeChecker

if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("Invalid amount of parameters, usage:")
        print("python startQuestionnaire.py [QL FILEPATH]")
        sys.exit()

    # Read the contents of the questionnaire source file.
    with open(sys.argv[1]) as ql_contents:
        file_contents = ql_contents.read()

    # Parse the questionnaire into an AST.
    parsedAST = QuestionnaireParser().parse(file_contents)
    print(parsedAST)

    # Create an error handler and environment.
    error_handler = ErrorHandler()
    environment = Environment(error_handler)

    # Type-check and evaluate the AST.
    FindCycles(parsedAST, error_handler).start_traversal()
    TypeChecker(parsedAST, environment, error_handler).start_traversal()
    evaluator = Evaluate(parsedAST, environment, error_handler)

    # Finally, draw the GUI.
    built_gui = InitWidgets(parsedAST, environment, evaluator, error_handler)
    built_gui.start_traversal()
    gui = built_gui.get_initialized_gui()
    gui.start()
