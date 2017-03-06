import json

from QL.Undefined import Undefined
from collections import OrderedDict


class SaveQuestionaire(object):
    def __init__(self, ast, env, evaluator, error_handler):
        self.ast = ast
        self.env = env
        self.evaluator = evaluator
        self.handler = error_handler
        self.show_stack = []
        self.form_output = OrderedDict()

    def start_traversal(self):
        self.handler.clear_errors()
        self.show_stack = []

        # Set context for outputting errors; start traversal.
        prev_context = self.env.context
        self.env.context = "SaveQuestionaire"
        self.ast.root.accept(self)
        self.write_fields("./form_output.txt")

        # Output errors afterwards.
        self.handler.print_errors()
        self.env.context = prev_context

    def write_fields(self, path):
        with open(path, "w+") as json_file:
            json.dump(self.form_output, json_file, indent=4)

    def traverse_branch(self, node_branch, condition):
        self.show_stack.append(condition)
        node_branch.accept(self)
        self.show_stack.pop()

    def if_node(self, if_node):
        condition = if_node.expression.accept(self.evaluator)
        condition = condition if condition != Undefined else False

        self.traverse_branch(if_node.if_block, condition)

    def if_else_node(self, if_else_node):
        condition = if_else_node.expression.accept(self.evaluator)
        condition = condition if condition != Undefined else False

        self.traverse_branch(if_else_node.if_block, condition)
        self.traverse_branch(if_else_node.else_block, not condition)

    def is_shown(self):
        return all(self.show_stack)

    def set_question_val(self, question_node):
        identifier = question_node.get_identifier()
        if self.is_shown():
            node = self.env.get_node(identifier)
            value = self.env.get_var_value(identifier)

            if value == Undefined:
                return

            # Retrieve the correct conversion function by accepting the
            # type node. You do not have
            conversion_func = node.type.accept(self)
            self.form_output[identifier] = conversion_func(value)

    def question_node(self, question_node):
        self.set_question_val(question_node)

    def comp_question_node(self, comp_question_node):
        self.set_question_val(comp_question_node)

    def string_type_node(self, _):
        return lambda x: x

    def date_type_node(self, _):
        return lambda x: x.strftime("%d-%m-%Y")

    def int_type_node(self, _):
        return lambda x: int(x)

    def decimal_type_node(self, _):
        return lambda x: float(x)

    def money_type_node(self, _):
        return lambda x: float(x)

    def bool_type_node(self, _):
        return lambda x: x
