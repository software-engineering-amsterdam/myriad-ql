import ast.node as node

class Form(node.Node):

    def __init__(self, form_elements=None):
        self.form_elements = form_elements