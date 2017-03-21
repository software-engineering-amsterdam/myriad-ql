# coding=utf-8
from collections import defaultdict

from pql.messages.alert import Alert
from pql.traversal.FormVisitor import FormVisitor


class LabelChecker(FormVisitor):
    def __init__(self, ast):
        self.ast = ast

    def visit(self):
        def build_alert_list(identifiers):
            alerts = list()
            for key, nodes in identifiers.items():
                if len(nodes) > 1:
                    alerts.append(
                        Alert("Form contained multiple declarations of the same label: {}, "
                                "at the following locations: {}"
                                  .format(key, [v.location for v in nodes]), nodes[0].location))
            return alerts

        result = self.ast.apply(self, defaultdict(list))
        return build_alert_list(result)

    def form(self, node, environment=None):
        for statement in node.statements:
            statement.apply(self, environment)
        return environment

    def conditional_if_else(self, node, environment=None):
        self.conditional_if(node, environment)
        for statement in node.else_statement_list:
            statement.apply(self, environment)

    def conditional_if(self, node, environment=None):
        for statement in node.statements:
            statement.apply(self, environment)

    def field(self, node, environment=None):
        environment[node.title].append(node.name)

    def assignment(self, node, environment=None):
        self.field(node, environment)
