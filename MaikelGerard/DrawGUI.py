from Undefined import Undefined


class DrawGUI(object):
    def __init__(self, built_gui):
        self.env = built_gui.env
        self.ast = built_gui.ast
        self.handler = built_gui.error_handler
        self.evaluator = built_gui.evaluator
        self.hide_branch = False

        # Obtain the GUI instances.
        self.form_gui = built_gui.gui
        self.built_gui = built_gui
        self.form_gui.redraw_function = self.redraw

    def adjust_env(self, question_values):
        for question in question_values:
            question_node = self.env.variables[question]["node"]

            new_value = question_values[question]
            if new_value == "@undefined":
                continue

            question_type = question_node.type
            new_value = question_type.convert_to_type(new_value)

            # Value is non default, update the environment.
            if question_node.is_defined or new_value != question_type.default:
                self.env.set_var_value(question, new_value)
                question_node.is_defined = True

    def start(self):
        self.start_traversal()
        self.form_gui.start()

    def redraw(self, question_values):
        self.adjust_env(question_values)
        self.evaluator.start_traversal()
        self.start_traversal()

    def start_traversal(self):
        self.ast.root.accept(self)

    def if_node(self, if_node):
        condition = if_node.expression.accept(self.evaluator)
        condition = condition if condition != Undefined else False
        if condition or self.hide_branch:
            if_node.if_block.accept(self)
        else:
            self.hide_branch = True
            if_node.if_block.accept(self)
            self.hide_branch = False

    def if_else_node(self, if_else_node):
        condition = if_else_node.expression.accept(self.evaluator)
        condition = condition if condition != Undefined else False

        current_val = self.hide_branch
        if condition or self.hide_branch:
            if_else_node.if_block.accept(self)
            self.hide_branch = True
            if_else_node.else_block.accept(self)
            self.hide_branch = current_val
        else:
            self.hide_branch = True
            if_else_node.if_block.accept(self)
            self.hide_branch = current_val
            if_else_node.else_block.accept(self)

    def question_node(self, question_node):
        identifier = question_node.name.val
        if self.hide_branch:
            (get_data_func, set_data_func, show, hide) = \
                self.form_gui.get_question_functions(identifier)
            self.form_gui.main.hideLabel(identifier)
            hide(identifier)
        else:
            (get_data_func, set_data_func, show, hide) = \
                self.form_gui.get_question_functions(identifier)
            self.form_gui.main.showLabel(identifier)
            show(identifier)
            set_data_func(identifier, self.env.get_var_value(identifier))

    def comp_question_node(self, comp_question):
        identifier = comp_question.name.val
        if self.hide_branch:
            self.form_gui.main.hideLabel("@computed_" + identifier)
            self.form_gui.main.hideLabel(identifier)
        else:
            value = self.env.get_var_value(identifier)
            if value == comp_question.type.default:
                return
            self.form_gui.main.showLabel("@computed_" + identifier)
            self.form_gui.main.showLabel(identifier)
            self.form_gui.main.setLabel(identifier, value)
