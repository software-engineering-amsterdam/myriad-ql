from QL.error_handler import ErrorHandler as QLErrorHandler
from QL.stages.print_handler import PrintHandler as QLPrintHandler


class ErrorHandler(QLErrorHandler):
    def __init__(self):
        super(ErrorHandler, self).__init__()

    def add_question_not_in_ql_error(self, question_node):
        print question_node
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
