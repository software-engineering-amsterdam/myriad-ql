import argparse

from QL.environment import Environment
from QL.error_handler import ErrorHandler
from QL.GUI.drawGUI import DrawGUI
from QL.stages.find_cycles import FindCycles
from QL.stages.parser import Parser
from QL.stages.type_checker import TypeChecker
from QL.stages.print_handler import PrintHandler
from QL.stages.init_environment import InitEnvironment

from QLS.environment import Environment as QLSEnvironment
from QLS.error_handler import ErrorHandler as QLSErrorHandler
from QLS.stages.parser import Parser as QLSParser
from QLS.stages.type_checker import TypeChecker as QLSTypeChecker
from QLS.stages.determine_widget_type import DetermineWidgetType
from QLS.stages.print_handler import PrintHandler as QLSPrintHandler
from QLS.GUI.drawGUI import DrawGUI as QLSDrawGUI


class Questionnaire(object):
    def __init__(self, ql_file):
        with open(ql_file) as ql_contents:
            file_contents = ql_contents.read()

        # Parse the questionnaire into an AST.
        self.ql_ast = Parser().parse(file_contents)
        self.handler = ErrorHandler()
        self.ql_env = Environment(self.handler)
        self.validate()

    def validate(self):
        InitEnvironment(self.ql_ast, self.ql_env, self.handler)\
            .start_traversal()
        self.handler.check_and_print_errors()
        self.handler.clear_errors()

        TypeChecker(self.ql_ast, self.ql_env, self.handler).start_traversal()
        FindCycles(self.ql_ast, self.handler).start_traversal()
        self.handler.check_and_print_errors()
        print "QL validated"

    def draw(self):
        DrawGUI(self.ql_ast, self.ql_env).start_traversal()

    def print_form(self):
        PrintHandler().print_ast(self.ql_ast)


class QLSQuestionnaire(Questionnaire):
    def __init__(self, ql_file, stylesheet_file):
        super(QLSQuestionnaire, self).__init__(ql_file)
        with open(stylesheet_file) as ql_contents:
            file_contents = ql_contents.read()

        self.qls_ast = QLSParser().parse(file_contents)
        self.qls_env = QLSEnvironment(self.handler)
        self.handler = QLSErrorHandler()
        self.validate_stylesheet()

        DetermineWidgetType(self.qls_ast, self.qls_env, self.ql_env)\
            .start_traversal()

    def validate_stylesheet(self):
        QLSTypeChecker(self.qls_ast, self.qls_env, self.ql_env, self.handler)\
            .start_traversal()
        self.handler.check_and_print_errors()

    def draw(self):
        QLSDrawGUI(self.qls_ast, self.qls_env, self.ql_ast, self.ql_env)\
            .start_traversal()

    def print_stylesheet(self):
        QLSPrintHandler().print_ast(self.qls_ast)


if __name__ == '__main__':
    arg_parser = argparse.ArgumentParser(
        description='Validate a ql file and render the gui, '
                    'an optional stylesheet can be added'
    )
    arg_parser.add_argument(
        'ql_file', help='a questionaire language file'
    )
    arg_parser.add_argument(
        '--stylesheet', help='optional questionaire stylesheet file'
    )

    cmd_args = arg_parser.parse_args()
    ql_file = cmd_args.ql_file
    stylesheet_file = cmd_args.stylesheet

    if stylesheet_file is not None:
        main = QLSQuestionnaire(ql_file, stylesheet_file)
    else:
        main = Questionnaire(ql_file)

    main.print_form()
    if stylesheet_file:
        main.print_stylesheet()

    main.draw()
