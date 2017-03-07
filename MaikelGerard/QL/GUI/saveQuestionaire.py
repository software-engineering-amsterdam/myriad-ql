import json

from collections import OrderedDict
from QL.undefined import Undefined
from QL.stages.updateComputedVariables import UpdateComputedVariables


class SaveQuestionaire(object):
    def __init__(self, ast, widgets):
        self.ast = ast
        self.widgets = widgets
        self.form_output = OrderedDict()

    def start_traversal(self):
        self.ast.accept(self)
        self.write_fields("./form_output.txt")

    def write_fields(self, path):
        with open(path, "w+") as json_file:
            json.dump(self.form_output, json_file, indent=4)

    def if_node(self, if_node):
        if_node.if_block.accept(self)

    def if_else_node(self, if_else_node):
        if_else_node.if_block.accept(self)
        if_else_node.else_block.accept(self)

    def store_question_val(self, question_node):
        identifier = question_node.name
        widget = self.widgets[identifier]

        if not widget.hidden:
            value = widget.get_entry()
            if value == "":
                return
            self.form_output[identifier] = \
                question_node.type.accept(self, value)

    def question_node(self, question_node):
        self.store_question_val(question_node)

    def comp_question_node(self, comp_question_node):
        self.store_question_val(comp_question_node)

    def string_type_node(self, _, value):
        return value

    def date_type_node(self, _, date_value):
        return date_value.strftime("%d-%m-%Y")

    def int_type_node(self, _, value):
        return int(value)

    def decimal_type_node(self, _, value):
        return float(value)

    def money_type_node(self, _, value):
        return float(value)

    def bool_type_node(self, _, value):
        return value
