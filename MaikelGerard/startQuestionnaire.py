import sys

from QL.environment import Environment
from QL.errorHandler import ErrorHandler
from QL.GUI.initWidgets import InitWidgets
from QL.stages.findCycles import FindCycles
from QL.stages.parser import QuestionnaireParser
from QL.stages.typeChecker import TypeChecker
from QL.stages.printAST import PrintAst
from QL.stages.initEnvironment import InitEnvironment

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
    PrintAst(parsedAST)

    # Create an error handler and environment.
    error_handler = ErrorHandler()
    environment = Environment(error_handler)
    InitEnvironment(parsedAST, environment, error_handler).start_traversal()
    error_handler.check_and_print_errors()
    error_handler.clear_errors()

    # Type-check and evaluate the AST.
    TypeChecker(parsedAST, environment, error_handler).start_traversal()
    FindCycles(parsedAST, error_handler).start_traversal()
    error_handler.check_and_print_errors()

    # Finally, draw the GUI.
    built_gui = InitWidgets(parsedAST, environment, error_handler)
    built_gui.start_traversal()
    gui = built_gui.get_initialized_gui()
    gui.start()
