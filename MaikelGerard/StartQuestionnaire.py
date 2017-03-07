import sys

from QL.Environment import Environment
from QL.ErrorHandler import ErrorHandler
from QL.GUI.InitWidgets import InitWidgets
from QL.Stages.Evaluator import Evaluate
from QL.Stages.FindCycles import FindCycles
from QL.Stages.Parser import QuestionnaireParser
from QL.Stages.TypeChecker import TypeChecker
from QL.Stages.PrintAST import PrintAst
from QL.Stages.InitEnvironment import InitEnvironment

if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("Invalid amount of parameters, usage:")
        print("python StartQuestionnaire.py [QL FILEPATH]")
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

    # Type-check and evaluate the AST.
    FindCycles(parsedAST, error_handler).start_traversal()
    TypeChecker(parsedAST, environment, error_handler).start_traversal()
    evaluator = Evaluate(parsedAST, environment)

    # Finally, draw the GUI.
    built_gui = InitWidgets(parsedAST, environment, evaluator, error_handler)
    built_gui.start_traversal()
    gui = built_gui.get_initialized_gui()
    gui.start()
