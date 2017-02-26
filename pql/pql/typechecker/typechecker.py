class Typechecker(object):

    def visit(self, input):
        return [form.apply(self) for form in input]

    def form(self, node):
        return [statement.apply(self) for statement in node.children]

    def field(self, node):
        return [arithmetic_statement.apply(self) for arithmetic_statement in node.children]

    def expression(self, node):
        return [expression.apply(self) for expression in node.children]

    def arithmetic(self, node):
        return [arithmetic.apply(self) for arithmetic in node.children]

    def subtraction(self, node):
        return self.binary(node)

    def binary(self, node):
        type_left = node.lhs.apply(self)
        type_right = node.rhs.apply(self)
