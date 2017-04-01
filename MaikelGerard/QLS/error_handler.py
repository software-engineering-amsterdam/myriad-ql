from QL.error_handler import ErrorHandler as QLErrorHandler
from QL.print_handler import PrintHandler as QLPrintHandler


class ErrorHandler(QLErrorHandler):
    def __init__(self):
        super(ErrorHandler, self).__init__()

    def add_incompat_stylsheet_name_error(self, stylesheet_node, ql_name):
        message = "Stylesheet name '{}' is not equal to "
        message += "the name of the ql form '{}'"
        message = message.format(stylesheet_node.name, ql_name)
        self.add_error(stylesheet_node, message)

    def add_question_not_in_ql_error(self, question_node):
        message = "Question '{}' defined in QLS and not in QL"
        message = message.format(question_node.name)
        self.add_error(question_node, message)

    def add_question_not_in_qls_error(self, question_name):
        message = "Question '{}' defined in QL and not in QLS"
        message = message.format(question_name)
        self.add_error(None, message)

    def add_incompatible_widget_type_error(self, widget_node, widget_name,
                                           literal_type):
        literal_str = literal_type.accept(QLPrintHandler())
        message = "Widget type '{}' incompatible with '{}'"
        message = message.format(widget_name, literal_str)
        self.add_error(widget_node, message)
