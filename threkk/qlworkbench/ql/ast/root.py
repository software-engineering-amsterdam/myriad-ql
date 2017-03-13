# -*- coding: utf-8 -*-
# from logging import basicConfig
# from logging import getLogger


class QLAST(object):
    def __init__(self, title):
        super().__init__()
        self.title = title
        self.register = {}
        self.build_order = []

    def register_node(self, node):
        variable_name = node.variable.name
        self.register[variable_name] = node
        self.build_order.append(variable_name)
