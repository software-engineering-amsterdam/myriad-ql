from .builder import UIBuilder


class UIVisitor(object):
    def __init__(self, ast):
        self.__ast = ast
        self.__ui = UIBuilder()

    def execute(self):
        self.__ui.set_title(self.__ast.title)
        queue = self.__ast.build_order

        queue.reverse()
        while queue:
            node = self.__ast.register[queue.pop()]
            node.build_ui(self.__ui)

        return self.__ui
